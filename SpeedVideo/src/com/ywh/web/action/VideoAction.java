package com.ywh.web.action;

import java.text.DecimalFormat;
import java.util.List;

import com.ywh.biz.CategoryBiz;
import com.ywh.biz.VideoBiz;
import com.ywh.entity.Category;
import com.ywh.entity.Comment;
import com.ywh.entity.User;
import com.ywh.entity.Video;

/**
 * ��ƵAction��������ʾ��Ƶ�б���ʾ����ҳ��
 * 
 * @author YWH
 */
public class VideoAction extends BaseAction {
	private VideoBiz videoBiz;
	private CategoryBiz categoryBiz;
	private List<Video> videolist;
	private List<Video> videolist2;
	private List<Video> videolist3;
	private List<Comment> comments;
	private Video video;
	private Category subcategory;// ����
	private Category maincategory;// ��һ�����
	private String serach_text;// �����ؼ���
	private String score;// ����
	private String totalscore;// ������

	/**
	 * ��ʾ��ҳ��Ƶ�б�
	 */
	public String showVideo() {
		videolist = videoBiz.getIndexNew();
		videolist2 = videoBiz.getIndexViews();
		videolist3 = videoBiz.getIndexMostComment();
		return "showVideo";
	}

	/**
	 * ����ҳ��
	 */
	public String play() {
		User user = (User) session.get("user");
		DecimalFormat dcm = new DecimalFormat("0.00");
		if (user != null
				&& videoBiz.getScore(video.getId(), user.getId()) != null) {
			score = dcm.format((Double) videoBiz.getScore(video.getId(), user
					.getId()));
		} else {
			score = "0";
		}
		if (videoBiz.getTotalScore(video.getId()) != null) {
			totalscore = dcm.format((Double) videoBiz.getTotalScore(video
					.getId()));
		} else {
			totalscore = "0.00";
		}
		video = videoBiz.getVideoPlay(video.getId());
		videoBiz.updateViews(video);
		comments = videoBiz.getComments(video);
		return "play";
	}

	/**
	 * �����������Ŀ¼��ʾ��Ƶ�б� TODO:�ȴ��Ű�����ǰ��listview��ʾ
	 */
	public String showByCategory() {
		// ��ʾĿ¼��Ϣ
		subcategory = categoryBiz.getCategoryById(subcategory.getId());
		maincategory = categoryBiz.getCategoryById(maincategory.getId());
		// ��Ƶ�б�
		videolist = videoBiz.showByCategory(subcategory.getId());
		return "showByCategory";
	}

	/**
	 * ��ʾ�ҵ���б�
	 */
	public String favlist() {
		User user = (User) session.get("user");
		List<Integer> idlist = videoBiz.findIdByFavorite(user);
		List<Integer> idlist2 = videoBiz.findIdByVideolist(user);
		List<Integer> idlist3 = videoBiz.findIdByFavorite(user);
		videolist = videoBiz.showBylist(idlist);
		videolist2 = videoBiz.showBylist(idlist2);
		videolist3 = videoBiz.showBylist(idlist2);
		return "favlist";
	}

	/**
	 * �����ť��ӵ��ҵ���б�
	 */
	public String addtoFav() {
		User user = (User) session.get("user");
		Object valid = videoBiz.validFav(user.getId(), video.getId());
		if (valid == null) {
			videoBiz.addtoFav(video.getId(), user);
		}
		return "addtoFav";
	}

	/**
	 * ��������
	 */
	public String serach() {
		videolist = videoBiz.serach(serach_text);
		return "showByCategory";
	}

	public void setVideoBiz(VideoBiz videoBiz) {
		this.videoBiz = videoBiz;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public List<Video> getVideolist() {
		return videolist;
	}

	public void setVideolist(List<Video> videolist) {
		this.videolist = videolist;
	}

	public Category getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Category subcategory) {
		this.subcategory = subcategory;
	}

	public void setCategoryBiz(CategoryBiz categoryBiz) {
		this.categoryBiz = categoryBiz;
	}

	public Category getMaincategory() {
		return maincategory;
	}

	public void setMaincategory(Category maincategory) {
		this.maincategory = maincategory;
	}

	public String getSerach_text() {
		return serach_text;
	}

	public void setSerach_text(String serachText) {
		serach_text = serachText;
	}

	public List<Video> getVideolist2() {
		return videolist2;
	}

	public void setVideolist2(List<Video> videolist2) {
		this.videolist2 = videolist2;
	}

	public List<Video> getVideolist3() {
		return videolist3;
	}

	public void setVideolist3(List<Video> videolist3) {
		this.videolist3 = videolist3;
	}

	public VideoBiz getVideoBiz() {
		return videoBiz;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTotalscore() {
		return totalscore;
	}

	public void setTotalscore(String totalscore) {
		this.totalscore = totalscore;
	}

}
