package com.ywh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ywh.dao.UserDao;
import com.ywh.entity.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	/**
	 * �����û���Ϣ
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * �����û��������û������ڵ�¼ע����
	 */
	public User findUserByName(String username) {
		String hql = "from User where username=?";
		List<User> userlist = this.getHibernateTemplate().find(hql,
				new Object[] { username });
		if (userlist.size() < 1) {
			return null;
		}
		return userlist.get(0);
	}

	/**
	 * ͨ���û�id�����û�
	 */
	public User findUserById(int id) {
		return (User) this.getHibernateTemplate().get(User.class, id);
	}

	/**
	 * �����û�
	 */
	public void updateUser(User user) {
		this.getHibernateTemplate().update(user);
	}

}
