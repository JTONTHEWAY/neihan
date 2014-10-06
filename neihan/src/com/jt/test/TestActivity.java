package com.jt.test;

import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.bool;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import cn.sharesdk.dropbox.c;
import cn.sharesdk.framework.l;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.jt.neihan.R;
import com.jt.neihan.bean.Comment;
import com.jt.neihan.bean.CommentList;
import com.jt.neihan.bean.EntityList;
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
	private long lastTime;
	// private Button button;
	private int itemCount;
	private int offset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		itemCount = 30;
		queue = Volley.newRequestQueue(this);
		// button = (Button) this.findViewById(R.id.button1);
		// button.setOnClickListener(this);
		long groupid = 1410602421L;

		ClientAPI.getComment(queue, groupid, offset, this);
		// ClientAPI.getList(queue, CATEGORY_TEXT, itemCount, 0, this);
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

			// Iterator<String> keys = json.keys();
			// while (keys.hasNext()) {
			// String key = keys.next();
			// Log.d("", "" + key);
			// }// 打印看看有多少个类似于data的key，因为log可能没有全部打印出来，所以要判断一下
			Log.d("TestActivity", "comment list json" + json.toString(4));
			// 解析获取到的评论列表
			CommentList commentList = new CommentList();
			commentList.parseJson(json);// 包含两组数据，一组热门评论，一组新鲜评论，且都有可能为空

			long groupid = commentList.getGroupid();
			boolean hasmore = commentList.isHasMore();// 表示评论列表是否还可以继续加载，是否还有数据

			Log.d("TextActivity", " groupid " + groupid);

			Log.d("TextActivity", "hasmore " + hasmore);
			//热门评论列表，可能为空，第一次offset为0 时可能有数据
			List<Comment> topComments = commentList.getTopcomments();
			//新鲜评论 ，可能有数据
			List<Comment> recentcComments = commentList.getRecentComments();
			
			if (topComments!=null) {
				for (Comment comment:topComments) {
					//打印出来 comment.getText();
				}
			}
			if (recentcComments!=null) {
				for (Comment comment:recentcComments) {
					//打印出来 comment.getText();
				}
			}
			//直接把commentList 提交给listview 的adapter，利用adapter更新数据
			//分页标识，要求服务器每次返回20条，通过hasmore 来进行判断
			offset +=20;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 列表网络获取回调部分
	 * 
	 * @param arg0
	 *            列表json数据字符串
	 */
	public void ListOnResponse(String arg0) {
		// TODO Auto-generated method stub

		try {
			JSONObject json = new JSONObject(arg0);

			// 获取根节点下面的data对象
			JSONObject obj = json.getJSONObject("data");
			EntityList entityList = new EntityList();// 解析段子列表的完整数据
			entityList.parseJson(obj);// 这个方法是解析json方法，相当于获取数据内容
			// 如果ishasmore 返回 true ，代表还可以更新一次数据
			if (entityList.isHasMore()) {
				lastTime = entityList.getMinTime();// 获取更新时间标识
				Log.d("TestActivity ", " lastTime" + lastTime);
			} else {// 没有数据更新，休息一会
				String tip = entityList.getTip();
			}
			// 获取段子内容列表,将这个段子的集合体，传递给listview之类的adapter 即可显示
			// entityList.getEntitys();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// ClientAPI.getList(queue, CATEGORY_TEXT, itemCount, lastTime, this);
	// }

}
