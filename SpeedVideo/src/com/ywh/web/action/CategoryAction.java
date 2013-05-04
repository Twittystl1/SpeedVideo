package com.ywh.web.action;

import java.util.List;

import com.ywh.biz.CategoryBiz;
import com.ywh.entity.Category;

/**
 * Ŀ¼Action,��Ŀ¼���¼�Ŀ¼����ʾ����
 * @author YWH
 */
public class CategoryAction {
	private List<Category> categories;
	CategoryBiz categoryBiz;

	/**
	 * ��ʾĿ¼
	 */
	public String getCategory() {
		categories = categoryBiz.getAllCategories();
		// Hibernate����ӳ��󲻱�ʹ�ô˷���
		for (Category sub : categories) {
			sub.setSubcategory(categoryBiz.getSubCategories(sub.getId()));
		}
		return "getCategory";
	}

	/**
	 * ���ϴ�ҳ����б�����ʾĿ¼��Ϣ
	 */
	public String selector() {
		categories = categoryBiz.getAllCategories();
		for (Category sub : categories) {
			sub.setSubcategory(categoryBiz.getSubCategories(sub.getId()));
		}
		return "selector";
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setCategoryBiz(CategoryBiz categoryBiz) {
		this.categoryBiz = categoryBiz;
	}

}
