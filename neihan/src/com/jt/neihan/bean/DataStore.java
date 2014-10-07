package com.jt.neihan.bean;

import java.security.PublicKey;
import java.util.LinkedList;
import java.util.List;

import android.provider.ContactsContract.CommonDataKinds.Im;

/**
 * 段子列表数据存储区
 * 
 * @author Administrator
 * 
 */
public class DataStore {
	private static DataStore dataStore;

	private DataStore() {
		// TODO Auto-generated constructor stub
		textEntities = new LinkedList<TextEntity>();
		imageEntities = new LinkedList<TextEntity>();

	}

	public static DataStore getInstance() {
		if (dataStore == null) {
			dataStore = new DataStore();
		}
		return dataStore;
	}

	private List<TextEntity> textEntities;
	private List<TextEntity> imageEntities;

	/**
	 * 将获取到的文本段子列表放在最前面，针对下拉刷新的操作
	 * 
	 * @param entities
	 */
	public void addTextEntities(List<TextEntity> entities) {
		if (entities != null) {
			textEntities.addAll(0, entities);
		}
	}

	/**
	 * 将获取到的文本段子列表放在最后面，针对的是上拉查看旧数据操作
	 * 
	 * @param entities
	 */
	public void appendTextEntities(List<TextEntity> entities) {
		if (entities != null) {
			textEntities.addAll(0, entities);
		}
	}

	/**
	 * 将获取到的image段子列表放在最前面，针对下拉刷新的操作
	 * 
	 * @param entities
	 */
	public void addImageEntities(List<ImageEntity> entities) {
		if (entities != null) {
			imageEntities.addAll(0, entities);
		}
	}

	/**
	 * 将获取到的image段子列表放在最后面，针对的是上拉查看旧数据操作
	 * 
	 * @param entities
	 */
	public void appendImageEntities(List<ImageEntity> entities) {
		if (entities != null) {
			imageEntities.addAll(0, entities);
		}
	}

	/**
	 * 获取文本段子列表
	 * 
	 * @return
	 */
	public List<TextEntity> getTextEntities() {
		return textEntities;
	}

	/**
	 * 获取图片段子列表
	 * 
	 * @return
	 */
	public List<TextEntity> getImageEntities() {
		return imageEntities;
	}
}
