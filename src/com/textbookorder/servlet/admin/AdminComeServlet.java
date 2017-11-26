package com.textbookorder.servlet.admin;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.textbookorder.domain.Admin;
import com.textbookorder.domain.BookClass;
import com.textbookorder.domain.Come;
import com.textbookorder.domain.TextBook;
import com.textbookorder.service.AdminService;
import com.textbookorder.service.BookClassService;
import com.textbookorder.service.StoreService;
import com.textbookorder.service.UserException;
import com.textbookorder.tools.commons.CommonUtils;

/**
 * 新书入库表述层（文件操作，上传图片）
 * 
 * @author Administrator
 * 
 */
public class AdminComeServlet extends HttpServlet {
	private BookClassService classService = new BookClassService();
	private AdminService adminService = new AdminService();
	private StoreService storeService = new StoreService();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/hhtml;charset=utf-8");
		Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
		if (loginAdmin == null) {
			response.sendRedirect("/login.jsp");
			return;
		}
		if (loginAdmin.getRight()[3].equals("0")) {
			request.setAttribute("msg", "你没有操作权限，无法操作，请和系统管理员联系!");
			request.getRequestDispatcher("/Admin/AIndex.jsp").forward(request,
					response);
		}
		// 创建工厂
		String basePath = this.getServletContext().getRealPath("/book_img");
		File repository = new File(basePath); // 缓存区目录
		// 创建缓存区目录
		if (!repository.isDirectory()) {
			repository.mkdir();
		}
		// 允许的扩展名
		final String allowExtNames = "jpg,gif,bmp,png";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(repository);
		factory.setSizeThreshold(1024 * 15); // 设置缓存区大小

		// 得到解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 设置单个文件大小为2m
		sfu.setFileSizeMax(1024 * 1024 * 2);
		// 使用解析器去解析request对象，得到List<FileItem>
		List<FileItem> fileItemList = null;
		// 存放错误信息
		Map<String, String> errors = new HashMap<String, String>();
		String fileName = null;
		try {
			fileItemList = sfu.parseRequest(request);
			Map<String, String> map = new HashMap<String, String>();
			for (FileItem fileItem : fileItemList) {
				if (fileItem.isFormField()) {
					// 普通表单项
					map.put(fileItem.getFieldName(),
							fileItem.getString("UTF-8"));
				} else {
					// 文件表单项
					String filePath = fileItem.getName();
					System.out.println("从表单中获取的文件路径：" + filePath);
					if (filePath != null && filePath.trim().length() > 0) {
						fileName = filePath.substring(filePath
								.lastIndexOf(File.separator) + 1);
						String extName = filePath.substring(filePath
								.lastIndexOf(".") + 1);
						fileName = CommonUtils.uuid() + "_" + fileName;
						if (allowExtNames.indexOf(extName) != -1) {
							// 使用目录和文件名称创建目标文件
							File destFile = new File(basePath, fileName);
							Image image = new ImageIcon(
									destFile.getAbsolutePath()).getImage();
							// 校验图片大小
							if (image.getWidth(null) > 300
									|| image.getHeight(null) > 300) {
								errors.put("bookpicture",
										"您上传的图片尺寸超出了300 * 300！");
							} else {
								fileItem.write(destFile);
							}
							System.out.println("上传图片名filename：" + fileName);
						} else {
							errors.put("bookpicture", "您上传的图片扩展名不正确！");
						}
					}
				}
			}
			/*
			 * * 把fileItemList中的数据封装到TextBook对象中 > 把所有的普通表单字段数据先封装到Map中 >
			 * 再把map中的数据封装到Book对象中
			 */
			TextBook textBook = CommonUtils.toBean(map, TextBook.class);

			if (textBook.getIsbn() == null
					|| textBook.getIsbn().trim().isEmpty()) {
				errors.put("isbn", "图书isbn不能为空！");
			}
			if (textBook.getBookname() == null
					|| textBook.getBookname().trim().isEmpty()) {
				errors.put("bookname", "图书名不能为空！");
			}
			// 将TextBook中的cid封装到BookClass对象中，再将bookClass赋给TextBook
			BookClass bookclass = CommonUtils.toBean(map, BookClass.class);
			textBook.setBookclass(bookclass);
			Admin admin = CommonUtils.toBean(map, Admin.class);
			Come come = CommonUtils.toBean(map, Come.class);
			come.setTextbook(textBook);
			come.setAdmin(admin);
			if (come.getComenumber() < 0) {
				errors.put("comenumber", "进货数量不能小于0！");
			}
			if (errors.size() > 0) {
				request.setAttribute("errors", errors);
				request.setAttribute("come", come);
				// request.setAttribute("textBook", textBook);
				request.getRequestDispatcher(
						"/admin/AdminStoreSevrlet?method=comePre").forward(
						request, response);
				return;
			}

			// 设置TextBook对象的图片
			// 如果没有图片，使用默认图片
			if (fileName == null || fileName.trim().isEmpty()) {
				fileName = "moren_01.jpg";
			}
			textBook.setBookpicture("/book_img/" + fileName);
			// 保存
			try {
				storeService.newBookCome(come);
			} catch (UserException e) {
				request.setAttribute("msg", e.getMessage());
				request.setAttribute("errors", errors);
				request.setAttribute("come", come);
				// request.setAttribute("textBook", textBook);
				request.getRequestDispatcher(
						"/admin/AdminStoreSevrlet?method=comePre").forward(
						request, response);
				return;
			}
			request.setAttribute("msg", "入库成功");
			// 返回图书列表
			request.getRequestDispatcher(
					"/admin/AdminStoreSevrlet?method=findAllCome").forward(
					request, response);
			return;
		} catch (Exception e) {
			if (e instanceof FileUploadBase.FileSizeLimitExceededException) {
				request.setAttribute("msg", "您上传的文件超出了2MB");
			} else {
				request.setAttribute("msg", "表单填写错误");
			}
			request.getRequestDispatcher(
					"/admin/AdminStoreSevrlet?method=comePre").forward(request,
					response);
			e.printStackTrace();
			return;
		}
	}
}
