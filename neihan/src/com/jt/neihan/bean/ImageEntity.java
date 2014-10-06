package com.jt.neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageEntity extends TextEntity {

	private ImageUrlList largeImageUrlList;
	private ImageUrlList middleimImageUrlList;

	public void parseJson(JSONObject item) throws JSONException {
		super.parseJson(item);
		JSONObject group = item.getJSONObject("group");

		JSONObject largeImage = group.optJSONObject("large_image");
		JSONObject middleImage = group.optJSONObject("middle_image");

		largeImageUrlList = new ImageUrlList();
		if (largeImage != null) {
			largeImageUrlList.parseJson(largeImage);
		}

		middleimImageUrlList = new ImageUrlList();
		if (middleImage != null) {
			middleimImageUrlList.parseJson(middleImage);
		}

	}

	public ImageUrlList getLargeImageUrlList() {
		return largeImageUrlList;
	}

	public ImageUrlList getMiddleimImageUrlList() {
		return middleimImageUrlList;
	}

}
