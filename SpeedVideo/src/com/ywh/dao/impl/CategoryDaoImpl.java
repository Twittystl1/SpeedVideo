package com.ywh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ywh.dao.CategoryDao;
import com.ywh.entity.Category;

public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao{
	/**
	 * �ҵ�������Ŀ¼
	 */
	public List<Category> findAllCategories() {
		String hql="from Category where pid=null";
		return this.getHibernateTemplate().find(hql);
	}
	/**
	 * �ҵ������¼�Ŀ¼
	 */
	public List<Category> findAllSubCategories(int id) {
		String hql="from Category where pid=?";
		return this.getHibernateTemplate().find(hql,id);
	}
	/**
	 * ����Ŀ¼idѰ��Ŀ¼
	 */
	public Category findCategoryById(int id) {
		return (Category) this.getHibernateTemplate().get(Category.class, id);
	}


}
