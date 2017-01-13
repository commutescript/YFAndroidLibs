package com.github.yf_library.banner;

import android.content.Intent;

/**
 * 广告实体类
 * 
 * @author wwj_748
 * 
 */
public class BannerEntity {
	private String id; // id
	private String date; // 日期
	private String title; // 标题
	private String topicFrom; //
	private String topic; // 标题
	private String imgUrl; // 图片的url
	private boolean isAd; // 是否是广告
	private String targetUrl; // 目标url
	private int width; // 宽度
	private int height; // 高度
	private boolean available; //是否可用
	private Intent mIntent; //activity跳转
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public boolean isAd() {
		return isAd;
	}

	public void setAd(boolean isAd) {
		this.isAd = isAd;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTopicFrom() {
		return topicFrom;
	}

	public void setTopicFrom(String topicFrom) {
		this.topicFrom = topicFrom;
	}


	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * @return the mIntent
	 */
	public Intent getmIntent() {
		return mIntent;
	}

	/**
	 * @param mIntent the mIntent to set
	 */
	public void setmIntent(Intent mIntent) {
		this.mIntent = mIntent;
	}
	

	
}
