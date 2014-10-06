package com.jt.neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;

/**
 * 文本段子实体
 * 
 * @author Administrator
 * 
 */
public class TextEntity  {

	private int label; // TODO 分析这个字段的含义

	private UserEntity user;
	private int type;// 1代表段子，5代表广告

	private long createTime;

	private long onlineTime;// 上线时间

	private long displayTime;//

	private int commentCount;// 评论个数

	private int diggCount;// digg 的个数

	private int status;// 状态，其中的可选值3，需要分析是什么类型

	private int userDigg;// TODO 需要了解digg的含义

	private long groupId;// 段子的id，访问详情和评论时，用这个作为接口的参数

	private int categoryId;// 内容分类类型 1.文本 2 图片
	// TODO 需要去分析 comments 这个 json数组中的内容是什么

	private int buryCount;// 代表踩的个数

	private String content;// 文本段子的内容部分

	private int userBury;// 代表当前用户是否踩了0代表没有，1代表踩了

	private int usetRepin;// 用户是否repin，0代表没有

	private int level;// TODO 这个需要分析一下是什么含义，现有两个地方出现，1.获取列表的接口里面有一个level= 6
						// 文本段子的实体中，level=4

	private int repinCount;// TODO 待分析

	private int hasHotComments;// 是否含有热门评论

	private int favoriateCount;// 代表赞的个数

	private int goDetailCount;// TODO 需要分享这个字段的含义
	private int hasComments;// 当前用户是否评论
	private String shareUrl;// 用于第三方分享，提交的网址参数
	private int userFavorite;// 0代表用户没攒，1代表赞了

	private String statusDesc;// 状态描述信息 可选值 “已发表到热门列表”

	public void parseJson(JSONObject object) throws JSONException {
		this.onlineTime = object.getLong("online_time");
		this.displayTime = object.getLong("display_time");
		JSONObject group = object.getJSONObject("group");
		this.createTime = group.getLong("create_time");
		this.favoriateCount = group.getInt("favorite_count");
		this.userBury = group.getInt("user_bury");
		this.userFavorite = group.getInt("user_favorite");
		this.buryCount = group.getInt("bury_count");
		this.shareUrl = group.getString("share_url");
		this.label = group.optInt("label",0);
		this.content = group.getString("content");
		this.commentCount = group.getInt("comment_count");
		this.status = group.getInt("status");
		this.statusDesc = group.getString("status_desc");
		this.hasComments = group.getInt("has_comments");
		this.goDetailCount = group.getInt("go_detail_count");
		user = new UserEntity();
		JSONObject uObject = group.getJSONObject("user");
		user.parseJson(uObject);
		this.userDigg = group.getInt("user_digg");
		this.diggCount = group.getInt("digg_count");
		this.groupId = group.getLong("group_id");
		this.level = group.getInt("level");
		this.repinCount = group.getInt("repin_count");
		this.usetRepin = group.getInt("user_repin");
		this.hasHotComments = group.optInt("has_hot_comments",0);
		this.categoryId = group.getInt("category_id");

	}

	public int getType() {
		return type;
	}

	public long getCreateTime() {
		return createTime;
	}

	public int getUserBury() {
		return userBury;
	}

	public int getUserFavorite() {
		return userFavorite;
	}

	public int getFavoriateCount() {
		return favoriateCount;
	}

	public int getBuryCount() {
		return buryCount;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public int getLabel() {
		return label;
	}

	public String getContent() {
		return content;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public int getStatus() {
		return status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public int getHasComments() {
		return hasComments;
	}

	public int getGoDetailCount() {
		return goDetailCount;
	}

	public int getUserDigg() {
		return userDigg;
	}

	public int getDiggCount() {
		return diggCount;
	}

	public long getGroupId() {
		return groupId;
	}

	public int getLevel() {
		return level;
	}

	public int getRepinCount() {
		return repinCount;
	}

	public int getUsetRepin() {
		return usetRepin;
	}

	public int getHasHotComments() {
		return hasHotComments;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public long getOnlineTime() {
		return onlineTime;
	}

	public long getDisplayTime() {
		return displayTime;
	}

	public UserEntity getUser() {
		return user;
	}
}
