package com.jt.neihan.fragment;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.content.Intent;
import android.media.MediaMuxer.OutputFormat;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jt.neihan.R;
import com.jt.neihan.activity.EssayDtailActivity;
import com.jt.neihan.adapter.EssayAdapter;
import com.jt.neihan.bean.DataStore;
import com.jt.neihan.bean.EntityList;
import com.jt.neihan.bean.TextEntity;
import com.jt.neihan.client.ClientAPI;

/**
 * 1.列表界面，第一次启动，并且数据为空的时候，自动加载数据 2.只要列表没有数据，进入这个界面的时候，就尝试加载数据 3.只要有数据，就不进行数据的加载
 * 4.进入该界面，并且有数据的情况下，尝试检查新信息的个数
 * 
 * @author Administrator
 * 
 */
public class TextListFragment extends Fragment implements OnClickListener,
		OnScrollListener, OnRefreshListener2<ListView>, OnItemClickListener {

	private View quickToolsView;
	private TextView textNotifiy;
	private View quickPublish;
	private View quickReView;
	private List<TextEntity> entities;
	private EssayAdapter adapter;

	public static final int CATEGORY_TEXT = 1;// 代表文本段子
	public static final int CATEGORY_IMGE = 2;// 代表图片
	private RequestQueue queue;
	private long lastTime;

	private int requestCategory = CATEGORY_TEXT;// 请求的分类类型ID

	public TextListFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (queue == null) {
			queue = Volley.newRequestQueue(getActivity());
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			lastTime = savedInstanceState.getLong("lastTime");
		}
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_textlist, container,
				false);
		// 获取标题控件，增加点击，进行新消息悬浮框显示的功能
		View titleView = view.findViewById(R.id.textlist_title);
		titleView.setOnClickListener(this);
		// TODO 获取listview并且设置数据（以后需要用pulltorefresh进行完善）
		PullToRefreshListView refreshListView = (PullToRefreshListView) view
				.findViewById(R.id.textlist_listview);
		// ListView listView = (ListView) view
		// .findViewById(R.id.textlist_listview);
		refreshListView.setOnRefreshListener(this);
		refreshListView.setMode(Mode.BOTH);
		ListView listView = refreshListView.getRefreshableView();

		header = inflater.inflate(R.layout.textlist_header_tools, listView,
				false);
		listView.addHeaderView(header);
		listView.setOnScrollListener(this);
		listView.setOnItemClickListener(this);
		quickPublish = header.findViewById(R.id.quick_tools_publish);
		quickPublish.setOnClickListener(this);

		quickReView = header.findViewById(R.id.quick_tools_review);
		quickReView.setOnClickListener(this);
		List<TextEntity> entities = DataStore.getInstance().getTextEntities();
		// if (entities == null) {
		// entities = new LinkedList<TextEntity>();
		// }

		adapter = new EssayAdapter(getActivity(), entities);
		adapter.setListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v instanceof TextView) {
					String string = (String) v.getTag();
					if (string != null) {
						int position = Integer.parseInt(string);
						Intent intent = new Intent(getActivity(),
								EssayDtailActivity.class);

						intent.putExtra("category", requestCategory);
						intent.putExtra("currentEssayPosition", position);
						Log.d("TextListFragment", "this is startactivity");
						startActivity(intent);
					}
				}
			}
		});
		listView.setAdapter(adapter);
		quickToolsView = view.findViewById(R.id.textlist_quick_tools);
		quickToolsView.setVisibility(View.GONE);
		// 新消息提醒
		textNotifiy = (TextView) view.findViewById(R.id.textlist_new_notify);
		textNotifiy.setVisibility(View.GONE);
		return view;

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putLong("lastTime", lastTime);
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		this.adapter = null;
		this.header = null;
		this.quickToolsView = null;
		this.textNotifiy = null;
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int what = msg.what;
			if (what == 1) {
				// TODO 代表有新消息提醒
				textNotifiy.setVisibility(View.GONE);
			}
		}

	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.textlist_title:
			textNotifiy.setVisibility(View.VISIBLE);
			handler.sendEmptyMessageDelayed(1, 3000);
			break;

		default:
			break;
		}
	}

	// /// ///////////////////////////////
	private int lastIndex = 0;
	private View header;

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		if (lastIndex - arg1 < 0 || arg1 == 0) {
			if (quickToolsView != null) {
				quickToolsView.setVisibility(View.GONE);
			}

		} else if (lastIndex - arg1 > 0) {
			if (quickToolsView != null) {
				quickToolsView.setVisibility(View.VISIBLE);
				// if (header.getVisibility() == View.VISIBLE) {
				// header.setVisibility(View.INVISIBLE);
				// }
			}
		}
		lastIndex = arg1;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	// ///////////////////////////////
	/**
	 * 列表网络获取回调部分，这个方法，是在volley联网响应返回的时候调用，他是在主线程执行的，因此可以直接更新UI
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
			} else {// 没有数据更新，休息一会
				String tip = entityList.getTip();
			}
			// 获取段子内容列表,将这个段子的集合体，传递给listview之类的adapter 即可显示
			List<TextEntity> ets = entityList.getEntitys();
			if (ets != null) {
				if (!ets.isEmpty()) {
					// 把object添加到指定的location及以后的内容往后移动
					DataStore.getInstance().addTextEntities(ets);
					// entities.addAll(0, ets);
					adapter.notifyDataSetChanged();
				} else {
					// TODO 没有更多的数据，需要提示一下

				}

			} else {
				// TODO 没有获取到网络数据，可能是数据解析错误，网络错误，需要提示一下
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 从上向下拉动，那么就要加载新数据操作
	 */
	@Override
	public void onPullDownToRefresh(
			final PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		ClientAPI.getList(queue, requestCategory, 30, lastTime,
				new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
						// TODO Auto-generated method stub加载新数据
						refreshView.onRefreshComplete();
						ListOnResponse(arg0);
					}
				});
	}

	/**
	 * 从下往上拉，那么就要考虑加载旧的数据
	 */
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

		// DataStore store = DataStore.getInstance();
		// List<TextEntity> entities = store.getTextEntities();
		// TextEntity entity = entities.get(arg2);

	}

}
