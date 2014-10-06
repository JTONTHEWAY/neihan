package com.jt.neihan.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class ADEntity {
	private int type;
	private long displayTime;
	private long onlineTime;
	private int displayImageHegiht;
	private long adID;
	private int displayImageWidth;
	private String source;
	private String package1;
	private String title;
	private String openUrl;
	private String downloadUrl;
	private int isAd;
	private String displayInfo;
	private String webUrl;
	private int displayType;
	private String buttonText;
	private String appleid;
	private String trackUrl;
	private String label;
	private String type2;
	private long id;
	private String ipaUrl;
	private String displayImage;

	public void parseJson(JSONObject object) throws JSONException {
		if (object != null) {
			type = object.getInt("type");
			displayTime = object.getLong("display_time");
			onlineTime = object.getLong("online_time");
			JSONObject ad = object.getJSONObject("ad");
			displayImageHegiht = ad.getInt("display_image_height");
			adID = ad.getLong("ad_id");
			displayImageWidth = ad.getInt("display_image_width");
			source = ad.getString("source");
			package1 = ad.getString("package");
			title = ad.getString("title");
			openUrl = ad.getString("open_url");
			downloadUrl = ad.getString("download_url");
			isAd = ad.getInt("is_ad");
			displayInfo = ad.getString("display_info");
			webUrl = ad.getString("web_url");
			displayType = ad.getInt("display_type");
			buttonText = ad.getString("button_text");
			appleid = ad.getString("appleid");
			trackUrl = ad.getString("track_url");
			label = ad.getString("label");
			type2 = ad.getString("type");
			id = ad.getLong("id");
			ipaUrl = ad.getString("ipa_url");
			displayImage = ad.getString("display_image");
		}
	}

	public int getType() {
		return type;
	}

	public long getDisplayTime() {
		return displayTime;
	}

	public long getOnlineTime() {
		return onlineTime;
	}

	public int getDisplayImageHegiht() {
		return displayImageHegiht;
	}

	public long getAdID() {
		return adID;
	}

	public int getDisplayImageWidth() {
		return displayImageWidth;
	}

	public String getSource() {
		return source;
	}

	public String getPackage1() {
		return package1;
	}

	public String getTitle() {
		return title;
	}

	public String getOpenUrl() {
		return openUrl;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public int getIsAd() {
		return isAd;
	}

	public String getDisplayInfo() {
		return displayInfo;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public int getDisplayType() {
		return displayType;
	}

	public String getButtonText() {
		return buttonText;
	}

	public String getAppleid() {
		return appleid;
	}

	public String getTrackUrl() {
		return trackUrl;
	}

	public String getLabel() {
		return label;
	}

	public String getType2() {
		return type2;
	}

	public long getId() {
		return id;
	}

	public String getIpaUrl() {
		return ipaUrl;
	}

	public String getDisplayImage() {
		return displayImage;
	}
}
