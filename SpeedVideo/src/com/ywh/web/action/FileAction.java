package com.ywh.web.action;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.ywh.biz.FileBiz;
import com.ywh.biz.UserBiz;
import com.ywh.entity.User;
import com.ywh.entity.Video;

/**
 * �ļ�Action����ִ���ļ�(��Ƶ��ͼƬ)�ϴ�����
 * @author YWH
 */
public class FileAction {
	private File upvideo;
	private String upvideoFileName;// ��ȡ�ļ�������ʽ:�ռ��name����ֵ+������FIleNameΪ��׺
	private String upvideoContentType;// ��ȡ�ļ����ͣ���ʽ:�ؼ���name����ֵ+ContentTypeΪ��׺
	private String filemessage;
	private Video video;
	private FileBiz fileBiz;
	private UserBiz userBiz;
	private User user;

	public String upload() throws Exception {
		try {
			ServletContext sc = ServletActionContext.getServletContext();
			String path = sc.getRealPath("/videos");
			// commons.io���ṩ�Ĺ����࣬�����ļ�
			if (!video.getTitle().trim().isEmpty()) {
				upvideoFileName = video.getTitle() + ".mp4";
			}
			FileUtils.copyFile(upvideo, new File(path, upvideoFileName));// ����Ϊ:ԭ�ļ���Ŀ���ļ�
			video.setImage(upvideoFileName.substring(0, upvideoFileName
					.lastIndexOf('.'))
					+ ".jpg");
			fileBiz.saveVideoImage(upvideoFileName);
			video.setTitle(upvideoFileName.substring(0, upvideoFileName
					.lastIndexOf('.')));
			video.setUrl(upvideoFileName);
			video.setUpload_date(new Date(System.currentTimeMillis()));
			video.setViews(0);
			fileBiz.saveVideo(video);
			filemessage = "�ϴ��ɹ�";// �����ϴ���Ϣ
			// ����videolist��
			fileBiz.addtoVideolist(user, video);
			return "upload";
		} catch (Exception e) {
			e.printStackTrace();
			filemessage = "�ϴ�����,�ļ����ͱ���mp4����С��Ҫ����200m";// �ϴ�����
			return "upload";
		}
	}

	public String uploadImage() throws Exception {
		try {
			ServletContext sc = ServletActionContext.getServletContext();
			String path = sc.getRealPath("/images");
			FileUtils.copyFile(upvideo, new File(path, upvideoFileName));
			user = userBiz.findUserById(user.getId());
			user.setPhoto(upvideoFileName);
			userBiz.updatePhoto(user);
			filemessage = "�ϴ��ɹ�!";
			return "uploadimage";
		} catch (Exception e) {
			e.printStackTrace();
			filemessage = "�ϴ�����!";
			return "uploadimage";
		}
	}

	public File getUpvideo() {
		return upvideo;
	}

	public void setUpvideo(File upvideo) {
		this.upvideo = upvideo;
	}

	public String getUpvideoFileName() {
		return upvideoFileName;
	}

	public void setUpvideoFileName(String upvideoFileName) {
		this.upvideoFileName = upvideoFileName;
	}

	public String getUpvideoContentType() {
		return upvideoContentType;
	}

	public void setUpvideoContentType(String upvideoContentType) {
		this.upvideoContentType = upvideoContentType;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public void setFileBiz(FileBiz fileBiz) {
		this.fileBiz = fileBiz;
	}

	public String getFilemessage() {
		return filemessage;
	}

	public void setFilemessage(String filemessage) {
		this.filemessage = filemessage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserBiz getUserBiz() {
		return userBiz;
	}

	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

}
