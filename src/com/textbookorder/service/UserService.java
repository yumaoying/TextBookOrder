package com.textbookorder.service;

import java.util.List;

import com.textbookorder.dao.UserDao;
import com.textbookorder.domain.User;

/**
 * User业务层
 * 
 * @author Administrator
 * 
 */
public class UserService {
	private UserDao userdao = new UserDao();

	/**
	 * 注册功能
	 * 
	 * @param form
	 * @throws UserException
	 */
	public void register(User form) throws UserException {
		User user = userdao.findByUserId(form.getUserid());
		// 校验用户名
		if (user != null) {
			throw new UserException("用户账号已被注册！");
		}
		user = userdao.findByUserEmail(form.getEmail());
		if (user != null) {
			throw new UserException("该邮箱已被注册！");
		}
		// 插入用户到数据库中
		userdao.addUser(form);

	}

	/**
	 * 用户登录
	 * 
	 * @param form
	 * @return
	 * @throws UserException
	 */
	public User login(User form) throws UserException {
		User user = userdao.findByUserId(form.getUserid());
		if (user == null) {
			throw new UserException("该用户不存在！");
		}
		if (!user.getUserpwd().equals(form.getUserpwd())) {
			throw new UserException("用户密码错误！");
		}
		return user;
	}

	/**
	 * 修改用户
	 * 
	 * @param form
	 * @return
	 * @throws UserException
	 */
	public void modify(User form) throws UserException {
		User user = userdao.findByUserId(form.getUserid());
		if (user == null) {
			throw new UserException("该用户不存在！");
		}
		User other = userdao.findByUserEmail(form.getEmail());
		if (other != null && !other.getUserid().equals(form.getUserid())) {
			throw new UserException("该邮箱已被注册！");
		}
		userdao.modifyUser(form);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param form
	 * @return
	 * @throws UserException
	 */
	public void modifyPwd(User form) throws UserException {
		User user = userdao.findByUserId(form.getUserid());
		if (user == null) {
			throw new UserException("该用户不存在！");
		}
		userdao.modifyPwd(form);
	}

	/**
	 * 删除某用户
	 * 
	 * @param uid
	 */
	public void delete(int uid) {
		userdao.delete(uid);
	}

	/**
	 * 加载用户
	 * 
	 * @param userid
	 * @return
	 */
	public User load(String userid) {
		return userdao.findByUserId(userid);

	}

	/**
	 * 按用户编号加载用户
	 * 
	 * @param userid
	 * @return
	 */
	public User loadByuid(int uid) {
		return userdao.findByUserId(uid);

	}

	/**
	 * 激活用户
	 * 
	 * @param code
	 * @throws UserException
	 */
	public void active(String code) throws UserException {
		User user = userdao.findbyCode(code);
		if (user == null) {
			throw new UserException("激活码无效！");
		}
		if (user.isIsstate()) {
			throw new UserException("您已经激活过了！");
		}
		// 设置用户状态为已激活
		userdao.updateState(user.getUserid(), true);
	}

	/**
	 * 查找所有用户
	 * 
	 * @return
	 */
	public List<User> findAllUser() {
		return userdao.findAllUser();
	}

	/**
	 * 查找所有学生用户
	 * 
	 * @return
	 */
	public List<User> findAllStudent() {
		return userdao.findAllStudent();
	}

	/**
	 * 查找所有老师
	 * 
	 * @return
	 */
	public List<User> findAllTeacher() {
		return userdao.findAllTeacher();
	}

	/**
	 * 按用户名查询
	 * 
	 * @param username
	 * @return
	 */
	public List<User> findByName(String username) {
		return userdao.findByName(username);

	}

	/**
	 * 按联系方式
	 * 
	 * @param username
	 * @return
	 */
	public List<User> findByPhone(String phone) {
		return userdao.findByPhone(phone);
	}

	/**
	 * 按联系方式
	 * 
	 * @param username
	 * @return
	 */
	public List<User> findGroup(String info) {
		return userdao.findGroup(info);
	}
}
