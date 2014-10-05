package com.jt.test;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import cn.sharesdk.framework.l;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.jt.neihan.R;
import com.jt.neihan.bean.ImageEntity;
import com.jt.neihan.bean.ImageUrlList;
import com.jt.neihan.client.ClientAPI;

/**
 * 这个文件就是一个测试入口，所有测试的内容，都可以放在这里
 * 
 * @author Administrator
 * 
 */
public class TestActivity extends Activity implements Listener<String> {
	public static final int CATEGORY_TEXT = 1;// 代表文本段子
	public static final int CATEGORY_IMGE = 2;// 代表图片
	private RequestQueue queue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		int itemCount = 30;
		queue = Volley.newRequestQueue(this);
		ClientAPI.getList(queue, CATEGORY_IMGE, itemCount, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

	@Override
	public void onResponse(String arg0) {
		// TODO Auto-generated method stub

		try {
			JSONObject json = new JSONObject(arg0);
			arg0 = json.toString(4);
			Log.d("TestActivity", "List: " + arg0);
			// 获取根节点下面的data对象
			JSONObject obj = json.getJSONObject("data");
			// 从data对象中获取名称为data的数组，它代表的是段子列表的数据
			JSONArray array = obj.getJSONArray("data");

			int len = array.length();

			if (len > 0) {
				// 遍历数组中的每一个图片段子信息
				for (int i = 0; i < len; i++) {
					JSONObject item = array.getJSONObject(i);
					ImageEntity imageEntity = new ImageEntity();
					imageEntity.parseJson(item);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
