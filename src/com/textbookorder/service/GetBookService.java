package com.textbookorder.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.textbookorder.dao.GetBookDao;
import com.textbookorder.dao.OrderDao;
import com.textbookorder.domain.Getbook;
import com.textbookorder.tools.jdbc.JdbcUtils;

/**
 * 领书管理业务层
 * 
 * @author Administrator
 * 
 */
public class GetBookService {
	private GetBookDao getDao = new GetBookDao();
	private OrderDao orderdao = new OrderDao();

	/**
	 * 登记购书时，生成领书单
	 * 
	 * @param getBook
	 * @throws UserException
	 */
	public void add(Getbook getBook) throws UserException {
		try {
			JdbcUtils.beginTransaction();
			orderdao.updateisregisbuy(1, getBook.getDate(),
					getBook.getOitemid());
			getDao.add(getBook);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.commitTransaction();
			} catch (SQLException e1) {
				throw new UserException("登记购书操作失败!");
			}
			throw new UserException("登记购书操作失败!");
		}

	}

	/**
	 * 取消登记某订单的条目
	 * 
	 * @param getBook
	 * @throws UserException
	 */
	public void quitregister(int oitemid) throws UserException {
		try {
			JdbcUtils.beginTransaction();
			orderdao.updateisregisbuy(0, null, oitemid);
			getDao.deleteByOitemid(oitemid);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.commitTransaction();
			} catch (SQLException e1) {
				throw new UserException("操作失败!");
			}
			throw new UserException("操作失败!");
		}
	}

	/**
	 * 取消登记某用户的购书记录， 删除某用户的领书单
	 * 
	 * @param userid
	 * @throws UserException
	 */
	public void noRegisterByuid(int userid) throws UserException {
		try {
			JdbcUtils.beginTransaction();
			orderdao.updateisregisbuybyuid(0, null, userid);
			getDao.delete(userid);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.commitTransaction();
			} catch (SQLException e1) {
				throw new UserException("操作失败!");
			}
			throw new UserException("操作失败!");
		}

	}

	/**
	 * 用户确认领书
	 * 
	 * @param uid
	 * @throws UserException
	 */
	public void sureGetBook(int uid) throws UserException {
		try {
			JdbcUtils.beginTransaction();
			// 更新订单中用户的领书时间
			orderdao.updateEnddate(uid, new Date());
			// 删除领书单
			getDao.delete(uid);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.commitTransaction();
			} catch (SQLException e1) {
				throw new UserException("操作失败!");
			}
			throw new UserException("操作失败!");
		}
	}

	/**
	 * 某条订单记录确认领书
	 * 
	 * @param uid
	 * @throws UserException
	 */
	public void sureGetBookByoid(int oid, int gid) throws UserException {
		try {
			JdbcUtils.beginTransaction();
			// 更新订单中用户的领书时间
			orderdao.updateEnddateByid(oid, new Date());
			// 删除领书单
			getDao.delete(gid);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				JdbcUtils.commitTransaction();
			} catch (SQLException e1) {
				throw new UserException("操作失败!");
			}
			throw new UserException("操作失败!");
		}
	}

	/**
	 * 查找用户的领书单
	 * 
	 * @param uid
	 * @return
	 */
	public List<Getbook> findByUid(int uid) {
		return getDao.findByUid(uid);
	}

	/**
	 * 按用户账号查找领书单
	 * 
	 * @param userid
	 * @return
	 */
	public List<Getbook> findByUid(String userid) {
		return getDao.findByUid(userid);
	}

	/**
	 * 查找所有的领书单
	 * 
	 * @return
	 */
	public List<Getbook> findAll() {
		return getDao.findAll();

	}

	/**
	 * 删除某条领书单记录
	 * 
	 * @param userid
	 */
	public void deleteBygid(int gid) {
		getDao.deleteBygid(gid);
	}

	/**
	 * 修改领书时间和地点
	 * 
	 * @param getBook
	 */
	public void update(Getbook getBook) {
		getDao.update(getBook);
	}

	public void noRegisterByGid(int gid) {
		// TODO Auto-generated method stub

	}

	/**
	 * 更新某条领书单信息
	 * 
	 * @param getbook
	 */
	public void updateBygid(Getbook getbook) {
		getDao.updateBygid(getbook);

	}

	public Getbook findByGid(int gid) {
		return getDao.findByGid(gid);
	}

	/**
	 * 查找某订单项的领书单信息
	 * 
	 * @param oitemid
	 * @return
	 */
	public Getbook findByoitemid(int oitemid) {
		return getDao.findByGid(oitemid);
	}

	public List<Getbook> findGroup(String lookup) {
		return getDao.findGroup(lookup);
	}

}
