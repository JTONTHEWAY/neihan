package com.jt.neihan.adapter;

import java.util.List;

import cn.sharesdk.framework.authorize.f;

import com.jt.neihan.bean.DataStore;
import com.jt.neihan.bean.TextEntity;
import com.jt.neihan.fragment.DetailContentFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DetailPageAdapter extends FragmentPagerAdapter {

	private List<TextEntity> entities;

	public DetailPageAdapter(FragmentManager fm, List<TextEntity> entities) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.entities = entities;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		DetailContentFragment fragment = new DetailContentFragment();
		Bundle argment = new Bundle();
		TextEntity entity = entities.get(arg0);
		argment.putSerializable("entity", entity);
		fragment.setArguments(argment);
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entities.size();
	}

}
