package com.jt.neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageEntity {
	private int type;
	private int commentCount;
	private long group_id;
	private String content;
	private ImageUrlList largeImageUrlList;
	private ImageUrlList middleimImageUrlList;

	public void parseJson(JSONObject item) throws JSONException {
		type = item.getInt("type");
		JSONObject group = item.getJSONObject("group");
		commentCount = group.getInt("comment_count");
		JSONObject largeImage = group.getJSONObject("large_image");
		JSONObject middleImage = group.getJSONObject("middle_image");
		group_id = group.getLong("group_id");
		content = group.getString("content");
		largeImageUrlList = new ImageUrlList();
		largeImageUrlList.parseJson(largeImage);
		middleimImageUrlList = new ImageUrlList();
		middleimImageUrlList.parseJson(middleImage);
	}

	public int getType() {
		return type;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public long getGroup_id() {
		return group_id;
	}

	public String getContent() {
		return content;
	}

	public ImageUrlList getLargeImageUrlList() {
		return largeImageUrlList;
	}

	public ImageUrlList getMiddleimImageUrlList() {
		return middleimImageUrlList;
	}
	
}
