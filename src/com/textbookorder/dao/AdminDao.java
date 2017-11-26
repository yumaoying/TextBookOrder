package com.textbookorder.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.textbookorder.domain.Admin;
import com.textbookorder.tools.jdbc.TxQueryRunner;

/**
 * 管理员持久层
 * 
 * @author Administrator
 * 
 */
public class AdminDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 按管理员账户查找
	 * 
	 * @param adminid
	 * @return
	 */
	public Admin findByAdminId(String adminid) {
		String sql = "select * from admin where adminid=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class), adminid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 按管理员姓名查找
	 * 
	 * @param adminid
	 * @return
	 */
	public List<Admin> findByAdminName(String adminName) {
		String sql = "select * from admin where  name=?";
		try {
			return qr.query(sql, new BeanListHandler<Admin>(Admin.class),
					adminName);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按管理员联系方式
	 * 
	 * @param adminid
	 * @return
	 */
	public Admin findByAdminPhone(String adminPhone) {
		String sql = "select * from admin where  telphone=?  or   email=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class),
					adminPhone, adminPhone);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 模糊查询
	 * 
	 * @param info
	 * @return
	 */
	public List<Admin> findGroup(String info) {
		String sql = "select * from admin where  1=1";
		if (info != null && info.trim().isEmpty()) {
			sql = sql
					+ "  and  (  adminid like  ?  or    name  like  ?  or   telphone like ?   or  email  like  ?  ) ";
		}
		Object params[] = { "%" + info + "%", "%" + info + "%",
				"%" + info + "%", "%" + info + "%" };
		try {
			return qr.query(sql, new BeanListHandler<Admin>(Admin.class),
					params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 加载某管理员信息
	 * 
	 * @param aid
	 * @return
	 */
	public Admin load(int aid) {
		String sql = "select * from admin where aid=?";
		try {
			return qr.query(sql, new BeanHandler<Admin>(Admin.class), aid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 添加用户
	 * 
	 * @param admin
	 */
	public void addAdmin(Admin admin) {
		String sql = "insert into admin (adminid,pwd,name,rights,telphone,email) values(?,?,?,?,?,?)";
		Object[] params = { admin.getAdminid(), admin.getPwd(),
				admin.getName(), admin.getRights(), admin.getTelphone(),
				admin.getEmail() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查看所有用户
	 * 
	 * @return
	 */
	public List<Admin> findAll() {
		String sql = "select * from admin";
		try {
			return qr.query(sql, new BeanListHandler<Admin>(Admin.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	/**
	 * 删除管理员
	 * 
	 * @param aid
	 */
	public void delete(int aid) {
		String sql = "delete from admin where aid=?";
		try {
			qr.update(sql, aid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 修改用户信息
	 * 
	 * @param admin
	 */
	public void modify(Admin admin) {
		String sql = "update admin set adminid=?,pwd=?,name=?,rights=?,telphone=?,email=? where aid=?";
		Object[] params = { admin.getAdminid(), admin.getPwd(),
				admin.getName(), admin.getRights(), admin.getTelphone(),
				admin.getEmail(), admin.getAid() };
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
