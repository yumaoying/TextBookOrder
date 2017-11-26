package com.textbookorder.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.textbookorder.domain.User;
import com.textbookorder.tools.jdbc.TxQueryRunner;

/**
 * User持久层
 * 
 * @author Administrator
 * 
 */
public class UserDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 按userid的查询
	 * 
	 * @param userid
	 * @return
	 */
	public User findByUserId(String userid) {
		String sql = "select * from user where userid=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), userid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按用户编号加载用户
	 * 
	 * @param userid
	 * @return
	 */
	public User findByUserId(int uid) {
		String sql = "select * from user where uid=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按邮件查询
	 * 
	 * @param email
	 * @return
	 */
	public User findByUserEmail(String email) {
		String sql = "select * from user where email=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按激活码查询
	 * 
	 * @param code
	 * @return
	 */
	public User findbyCode(String code) {
		String sql = "select * from user where code=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), code);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改某用户的激活状态
	 * 
	 * @param uid
	 * @param state
	 */
	public void updateState(String userid, boolean state) {
		try {
			String sql = "update user set state=? where userid=?";
			qr.update(sql, state, userid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		String sql = "insert into user(userid,username,userpwd,idennty,telphone,school,academy,major,grade,email) values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { user.getUserid(), user.getUsername(),
				user.getUserpwd(), user.getIdennty(), user.getTelphone(),
				user.getSchool(), user.getAcademy(), user.getMajor(),
				user.getGrade(), user.getEmail() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 */
	public void modifyUser(User user) {
		String sql = "update user set username=?,userpwd=?,idennty=?,telphone=?,school=?,academy=?,major=?,grade=?,email=? where userid=?";
		Object[] params = { user.getUsername(), user.getUserpwd(),
				user.getIdennty(), user.getTelphone(), user.getSchool(),
				user.getAcademy(), user.getMajor(), user.getGrade(),
				user.getEmail(), user.getUserid() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 */
	public void modifyPwd(User user) {
		String sql = "update user set userpwd=? where userid=?";
		Object[] params = { user.getUserpwd(), user.getUserid() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除某用户
	 * 
	 * @param uid
	 */
	public void delete(int uid) {
		String sql = "delete from user where uid=?";
		try {
			qr.update(sql, uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找所有用户
	 * 
	 * @return
	 */
	public List<User> findAllUser() {
		String sql = "select * from user";
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找所有学生用户
	 * 
	 * @return
	 */
	public List<User> findAllStudent() {
		String sql = "select * from user where idennty=0";
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查找所有老师
	 * 
	 * @return
	 */
	public List<User> findAllTeacher() {
		String sql = "select * from user where idennty=1";
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按用户名查询
	 * 
	 * @param username
	 * @return
	 */
	public List<User> findByName(String username) {
		String sql = "select * from user where username=? ";
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class),
					username);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按联系方式
	 * 
	 * @param username
	 * @return
	 */
	public List<User> findByPhone(String phone) {
		String sql = "select * from user where ( telphone=?   or   email=? )";
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class), phone,
					phone);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 模糊查询
	 * 
	 * @param username
	 * @return
	 */
	public List<User> findGroup(String info) {
		String sql = "select * from user where  1=1 ";
		List<Object> paramlist = new ArrayList<Object>();
		if (info != null && !info.trim().isEmpty()) {
			sql = sql
					+ "  and  ( userid  like  ?  or  username  like   ?  or   telphone like  ? or  email  like  ?  )  ";
			paramlist.add("%" + info + "%");
			paramlist.add("%" + info + "%");
			paramlist.add("%" + info + "%");
			paramlist.add("%" + info + "%");

		}
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class),
					paramlist.toArray());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
