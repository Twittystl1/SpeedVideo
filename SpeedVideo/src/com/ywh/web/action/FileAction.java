package com.ywh.web.action;

import java.io.File;
import java.sql.Date;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.ywh.biz.FileBiz;
import com.ywh.entity.Video;

public class FileAction {
	private File upvideo;
	private String upvideoFileName;// ��ȡ�ļ�������ʽ:�ռ��name����ֵ+������FIleNameΪ��׺
	private String upvideoContentType;// ��ȡ�ļ����ͣ���ʽ:�ؼ���name����ֵ+ContentTypeΪ��׺
	private String filemessage;
	private Video video;
	private FileBiz fileBiz;

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
			video.setTitle(upvideoFileName);
			video.setUrl(upvideoFileName);
			video.setUpload_date(new Date(System.currentTimeMillis()));
			video.setViews(0);
			fileBiz.saveVideo(video);
			filemessage = "�ϴ��ɹ�";// �����ϴ���Ϣ
			return "upload";
		} catch (Exception e) {
			e.printStackTrace();
			filemessage = "�ϴ�����,�ļ����ͱ���mp4����С��Ҫ����200m";// �ϴ�����
			return "upload";
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

}
