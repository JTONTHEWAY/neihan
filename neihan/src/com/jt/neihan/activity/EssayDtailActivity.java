package com.jt.neihan.activity;

import java.util.List;

import cn.sharesdk.framework.p;

import com.jt.neihan.R;
import com.jt.neihan.R.id;
import com.jt.neihan.R.layout;
import com.jt.neihan.R.menu;
import com.jt.neihan.adapter.DetailPageAdapter;
import com.jt.neihan.bean.DataStore;
import com.jt.neihan.bean.TextEntity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class EssayDtailActivity extends FragmentActivity {
	private ViewPager pager;
	private DetailPageAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_essay_dtail);
		pager = (ViewPager) this.findViewById(R.id.detail_pager_content);
		Intent intent = getIntent();
		Bundle extra = intent.getExtras();
		int category = 1;
		int currentEssayPosition = 0;
		if (extra != null) {
			category = extra.getInt("category", 1);
			currentEssayPosition = extra.getInt("currentEssayPosition", 0);
		}
		DataStore dataStore = DataStore.getInstance();
		List<TextEntity> entities = null;
		if (category == 1) {
			entities = dataStore.getTextEntities();
		} else if (category == 2) {
			entities = dataStore.getImageEntities();
		}
		// 设置fragmentPagerAdapter
		adapter = new DetailPageAdapter(getSupportFragmentManager(), entities);
		pager.setAdapter(adapter);
		if (currentEssayPosition > 0) {
			pager.setCurrentItem(currentEssayPosition);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.essay_dtail, menu);
		return true;
	}

}
