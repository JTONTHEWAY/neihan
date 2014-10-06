package com.jt.neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class UserEntity {
private String avatarUrl;//头像地址

private long userId;//用户id

private String name;//用户昵称

private boolean userVerified;//是否加v

public void parseJson(JSONObject object) throws JSONException{
	if (object != null) {
		this.avatarUrl = object.getString("avatar_url");
		this.userId = object.getLong("user_id");
		this.name = object.getString("name");
		this.userVerified = object.getBoolean("user_verified");
	}
}

public String getAvatarUrl() {
	return avatarUrl;
}

public long getUserId() {
	return userId;
}

public String getName() {
	return name;
}

public boolean isUserVerified() {
	return userVerified;
}
}
