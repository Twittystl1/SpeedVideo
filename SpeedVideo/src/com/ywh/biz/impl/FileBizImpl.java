package com.ywh.biz.impl;

import java.io.IOException;

import com.ywh.biz.FileBiz;
import com.ywh.dao.FileDao;
import com.ywh.entity.Video;

public class FileBizImpl implements FileBiz {
	private FileDao fileDao;

	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	public void saveVideo(Video video) {
		fileDao.save(video);
	}

	/**
	 * �����ļ�����ͼ
	 */
	public void saveVideoImage(String fileName) {
		// ��Ƶ�ļ�
		String str = fileName.substring(0, fileName.lastIndexOf('.'));
		String videoRealPath = "D://���/apache-tomcat-6.0.36/webapps/SpeedVideo/videos/"
				+ str + ".mp4";
		// ��ͼ��·�������·����
		String imageRealPath = "D://���/apache-tomcat-6.0.36/webapps/SpeedVideo/images/"
				+ str + ".jpg";
		String cmd = "cmd /c start D:/���/apache-tomcat-6.0.36/ffmpeg.bat";
		try {
			// �����������ļ�
			Runtime.getRuntime().exec(
					cmd + " " + videoRealPath + " " + imageRealPath);

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
	}

}
