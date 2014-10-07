package com.jt.neihan.bean;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageEntity extends TextEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8808873004534593482L;
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
