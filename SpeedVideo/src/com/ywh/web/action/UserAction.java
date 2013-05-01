package com.ywh.web.action;

import java.util.Date;
import java.util.List;

import com.ywh.biz.UserBiz;
import com.ywh.biz.VideoBiz;
import com.ywh.entity.User;
import com.ywh.entity.Video;
import com.ywh.util.CookieUtil;

public class UserAction extends BaseAction {
	UserBiz userBiz;
	VideoBiz videoBiz;
	List<Video> videolist;
	User user;

	public String quickRegist() {
		user.setName("didNotSetName");
		user.setEmail("didNotSetEmail");
		user.setRegist_date(new Date(System.currentTimeMillis()));
		user.setRights(1);
		userBiz.regist(user);
		return "regist";
	}

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

	public String exitlogin() {
		session.remove("user");
		return "login";
	}

	/*
	 * չʾ�û���Ϣ
	 */
	public String userinfo() {
		user = userBiz.findUserById(user.getId());
		List<Integer> ids = videoBiz.findIdByVideolist(user);
		videolist = videoBiz.showBylist(ids);
		return "userinfo";
	}

	// TODO:����ע���¼
	public String regist() {

		return "regist";
	}

	public String login() {

		return "login";
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

}
