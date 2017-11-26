package com.textbookorder.service;

import java.util.List;

import com.textbookorder.dao.AdminDao;
import com.textbookorder.domain.Admin;

/**
 * Admin（管理员）业务层
 * 
 * @author Administrator
 * 
 */
public class AdminService {
	private AdminDao adminDao = new AdminDao();

	/**
	 * 管理员登录
	 * 
	 * @param form
	 * @return
	 * @throws UserException
	 */
	public Admin login(Admin form) throws UserException {
		Admin admin = adminDao.findByAdminId(form.getAdminid());
		if (admin == null) {
			throw new UserException("管理员账户不存在！");
		}
		if (!admin.getPwd().equals(form.getPwd())) {
			throw new UserException("密码错误！");
		}
		return admin;

	}

	/**
	 * 加载某管理员信息
	 * 
	 * @param aid
	 * @return
	 */
	public Admin load(int aid) {
		return adminDao.load(aid);
	}

	/**
	 * 查看所有用户
	 * 
	 * @return
	 */
	public List<Admin> findAll() {
		return adminDao.findAll();
	}

	/**
	 * 添加管理员
	 * 
	 * @param admin
	 * @throws UserException
	 */
	public void add(Admin admin) throws UserException {
		Admin am = adminDao.findByAdminId(admin.getAdminid());
		if (am != null) {
			throw new UserException("该账户已存在！无法添加");
		}
		adminDao.addAdmin(admin);
	}

	/**
	 * 删除用户
	 * 
	 * @param aid
	 */
	public void delete(int aid) {
		adminDao.delete(aid);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param admin
	 * @throws UserException
	 */
	public void mpdify(Admin admin) throws UserException {
		Admin am = adminDao.findByAdminId(admin.getAdminid());
		if (am != null && am.getAid() != admin.getAid()) {
			throw new UserException("该用户账号已存在!");
		}
		adminDao.modify(admin);
	}

	/**
	 * 按管理员账户查找
	 * 
	 * @param adminid
	 * @return
	 */
	public Admin findByAdminId(String adminid) {
		return adminDao.findByAdminId(adminid);
	}

	/**
	 * 按管理员姓名查找
	 * 
	 * @param adminid
	 * @return
	 */
	public List<Admin> findByAdminName(String adminName) {
		return adminDao.findByAdminName(adminName);
	}

	/**
	 * 按管理员联系方式
	 * 
	 * @param adminid
	 * @return
	 */
	public Admin findByAdminPhone(String adminPhone) {
		return adminDao.findByAdminPhone(adminPhone);
	}

	/**
	 * 模糊查询
	 * 
	 * @param adminid
	 * @return
	 */
	public List<Admin> findGroup(String info) {
		return adminDao.findGroup(info);
	}
}
