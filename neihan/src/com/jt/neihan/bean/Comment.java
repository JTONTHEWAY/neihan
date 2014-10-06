package com.jt.neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class Comment {
	private long uid;
	private String platform;
	private String text;
	private int diggCount;
	private int userDigg;
	private boolean userVerfied;
	private int buryCount;
	private String userPrifileUrl;
	private long id;
	private String userName;
	private int userBury;
	private String userProfileImageUrl;
	private String description;
	private long createTime;
	private long userId;

	public void parseJson(JSONObject object) throws JSONException {
		if (object != null) {
			uid = object.getLong("uid");
			platform = object.getString("platform");
			text = object.getString("text");
			diggCount = object.getInt("digg_count");
			userDigg = object.getInt("user_digg");
			userVerfied = object.getBoolean("user_verified");
			buryCount = object.getInt("bury_count");
			userPrifileUrl = object.getString("user_profile_url");
			id = object.getLong("id");
			userName = object.getString("user_name");
			userBury = object.getInt("user_bury");
			userProfileImageUrl = object.getString("user_profile_image_url");
			description = object.optString("description");
			createTime = object.getLong("create_time");
			userId = object.getLong("user_id");
		}
	}

	public long getUid() {
		return uid;
	}

	public String getPlatform() {
		return platform;
	}

	public String getText() {
		return text;
	}

	public int getDiggCount() {
		return diggCount;
	}

	public int getUserDigg() {
		return userDigg;
	}

	public boolean isUserVerfied() {
		return userVerfied;
	}

	public int getBuryCount() {
		return buryCount;
	}

	public String getUserPrifileUrl() {
		return userPrifileUrl;
	}

	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public int getUserBury() {
		return userBury;
	}

	public String getUserProfileImageUrl() {
		return userProfileImageUrl;
	}

	public String getDescription() {
		return description;
	}

	public long getCreateTime() {
		return createTime;
	}

	public long getUserId() {
		return userId;
	}

}
