package com.jt.neihan.bean;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageUrlList {
	private String uri;
	private int height;
	private int width;
	private List<String> largeImageList;

	public void parseJson(JSONObject object) throws JSONException {
		largeImageList = parseImageUrlList(object);
		uri = object.getString("uri");
		height = object.getInt("height");
		width = object.getInt("width");
	}

	public List<String> getLargeImageList() {
		return largeImageList;
	}



	private List<String> parseImageUrlList(JSONObject largeImage)
			throws JSONException {
		JSONArray urls = largeImage.getJSONArray("url_list");
		List<String> largeImageList = new LinkedList<String>();
		int ulen = urls.length();
		for (int j = 0; j < ulen; j++) {
			JSONObject uobj = urls.getJSONObject(j);
			String url = uobj.getString("url");
			largeImageList.add(url);
		}
		return largeImageList;
	}

	public String getUri() {
		return uri;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public ImageUrlList() {
		// TODO Auto-generated constructor stub
	}

}
