package com.ywh.web.action;

import java.util.Date;
import java.util.List;

import com.ywh.biz.UserBiz;
import com.ywh.biz.VideoBiz;
import com.ywh.entity.User;
import com.ywh.entity.Video;
import com.ywh.util.CookieUtil;

/**
 * �û�Action������ע���¼����ʾ�û���Ϣ
 * 
 * @author YWH
 */
public class UserAction extends BaseAction {
	UserBiz userBiz;
	VideoBiz videoBiz;
	List<Video> videolist;
	User user;
	String intro;

	/**
	 * ����ע�ᣬ����name,email���Զ��趨
	 */
	public String quickRegist() {
		user.setName("didNotSetName");
		user.setEmail("didNotSetEmail");
		user.setRegist_date(new Date(System.currentTimeMillis()));
		user.setRights(1);
		user.setPhoto("title.png");
		user.setGender("����");
		userBiz.regist(user);
		return "regist";
	}

	/**
	 * ���ٵ�¼
	 */
	public String quickLogin() {
		User validUser = userBiz.findUserByName(user.getUsername());
		if (validUser != null) {
			if (validUser.getPassword().equals(user.getPassword())) {
				userBiz.updateLastLogin(validUser);
				session.put("user", validUser);
				CookieUtil.addCookie("username", validUser.getUsername(),
						response);
				CookieUtil.addCookie("password", validUser.getPassword(),
						response);
				return "login";
			}
		}
		return "error";
	}

	/**
	 * �˳���¼
	 */
	public String exitlogin() {
		session.remove("user");
		return "login";
	}

	/**
	 * չʾ�û���Ϣ
	 */
	public String userinfo() {
		user = userBiz.findUserById(user.getId());
		List<Integer> ids = videoBiz.findIdByVideolist(user);
		videolist = videoBiz.showBylist(ids);
		return "userinfo";
	}

	/**
	 * �޸��û���Ϣ
	 */
	public String editintro() {
		user = userBiz.findUserById(user.getId());
		user.setIntro(intro);
		userBiz.update(user);
		return "userinfo";
	}

	/**
	 * ����ע��
	 */
	public String regist() {
		user.setRegist_date(new Date(System.currentTimeMillis()));
		user.setRights(1);
		user.setPhoto("title.png");
		userBiz.regist(user);
		return "regist";
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Video> getVideolist() {
		return videolist;
	}

	public void setVideolist(List<Video> videolist) {
		this.videolist = videolist;
	}

	public void setVideoBiz(VideoBiz videoBiz) {
		this.videoBiz = videoBiz;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

}
