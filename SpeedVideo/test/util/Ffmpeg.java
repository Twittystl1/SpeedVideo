package util;

import java.io.IOException;

public class Ffmpeg {

	public static void main(String[] args) {
		// ��Ƶ�ļ�
		String videoRealPath = "D://���/apache-tomcat-6.0.36/webapps/SpeedVideo/videos/aaaa.mp4";
		// ��ͼ��·�������·����
		String imageRealPath = "D://���/apache-tomcat-6.0.36/webapps/SpeedVideo/images/asdss.jpg";
		String cmd = "cmd /c start D:/���/apache-tomcat-6.0.36/ffmpeg.bat";
		// videoRealPath.replaceAll("\\\\", "/");
		// imageRealPath.replaceAll("\\\\", "/");
		// cmd.replaceAll("\\\\", "/");
		try {
			// �����������ļ�
			Runtime.getRuntime().exec(
					cmd + " " + videoRealPath + " " + imageRealPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}