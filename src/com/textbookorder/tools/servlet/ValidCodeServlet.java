package com.textbookorder.tools.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.textbookorder.tools.vcode.ValiCode;

public class ValidCodeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("vercode");
		ValiCode vc = new ValiCode();
		BufferedImage image = vc.getImage();// 获取一次性验证码图片
		// System.out.println(vc.getText());//获取图片上的文本
		ValiCode.output(image, response.getOutputStream());// 把图片写到指定流中

		// 把文本保存到session中
		request.getSession().setAttribute("vercode", vc.getText());
	}

}
