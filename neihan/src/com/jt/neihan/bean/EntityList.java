package com.jt.neihan.bean;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class EntityList {
	private boolean hasMore;
	private long minTime;
	private String tip;
	private long maxTime;
	private List<TextEntity> entitys;

	public void parseJson(JSONObject object) throws JSONException {
		if (object != null) {
			hasMore = object.getBoolean("has_more");
			tip = object.getString("tip");
			if (hasMore == true) {
				minTime = object.getLong("min_time");
			}

			maxTime = object.optLong("max_time");
			JSONArray array = object.getJSONArray("data");
			int len = array.length();
			if (len > 0) {
				entitys = new LinkedList<TextEntity>();
				// 遍历数组中的每一个段子信息
				for (int i = 0; i < len; i++) {
					JSONObject item = array.getJSONObject(i);
					int type = item.getInt("type");// 获取类型 1段子 5 广告
					if (type == 5) {
						ADEntity entity = new ADEntity();
						entity.parseJson(item);
						String downloadString = entity.getDownloadUrl();
						Log.d("textactivity", " received ad url "
								+ downloadString);
					} else if (type == 1) {
						JSONObject group = item.getJSONObject("group");
						int cid = group.getInt("category_id");
						TextEntity textEntity = null;
						if (cid == 1) {
							// TODO 解析文本段子
							textEntity = new TextEntity();

						} else if (cid == 2) {
							textEntity = new ImageEntity();

						}
						textEntity.parseJson(item);
						entitys.add(textEntity);
						long groupid = textEntity.getGroupId();
						Log.d("textactivity", "groupid " + groupid);
					}
				}
			}
		}
	}

	public boolean isHasMore() {
		return hasMore;
	}

	public long getMinTime() {
		return minTime;
	}

	public String getTip() {
		return tip;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public List<TextEntity> getEntitys() {
		return entitys;
	}
}
