package com.jt.neihan.bean;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;

/**
 * 评论接口返回的data 数据部分的实体定义 包含了 top_comment 和recent_comments两个数组
 * 
 * @author Administrator
 * 
 */
public class CommentList {
	private List<Comment> topcomments;
	private List<Comment> recentComments;
	private long groupid;
	private int totalNumber;// 评论总数
	private boolean hasMore;

	public void parseJson(JSONObject object) throws JSONException {
		if (object != null) {
			groupid = object.getLong("group_id");
			totalNumber = object.getInt("total_number");
			hasMore = object.getBoolean("has_more");
			JSONObject data = object.getJSONObject("data");
			JSONArray tArray = data.optJSONArray("top_comments");
			JSONArray rArray = data.optJSONArray("recent_comments");
			if (tArray != null) {
				topcomments = new LinkedList<Comment>();
				int len = tArray.length();
				if (len > 0) {
					for (int i = 0; i < len; i++) {
						JSONObject object2 = tArray.getJSONObject(i);
						Comment comment = new Comment();
						comment.parseJson(object2);
						topcomments.add(comment);
					}
				}
			}
			if (rArray != null) {
				recentComments = new LinkedList<Comment>();
				int len = rArray.length();
				if (len > 0) {
					for (int i = 0; i < len; i++) {
						JSONObject object3 = rArray.getJSONObject(i);
						Comment comment = new Comment();
						comment.parseJson(object3);
						recentComments.add(comment);
					}
				}
			}
		}
	}

	public long getGroupid() {
		return groupid;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public List<Comment> getTopcomments() {
		return topcomments;
	}

	public List<Comment> getRecentComments() {
		return recentComments;
	}
}
