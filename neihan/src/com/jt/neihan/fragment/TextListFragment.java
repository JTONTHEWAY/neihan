package com.jt.neihan.fragment;

import java.util.LinkedList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jt.neihan.R;

import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class TextListFragment extends Fragment implements OnClickListener,
		OnScrollListener, OnRefreshListener2<ListView> {

	private View quickToolsView;
	private TextView textNotifiy;
	private View quickPublish;
	private View quickReView;

	public TextListFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

		List<String> strings = new LinkedList<String>();
		for (int i = 0; i < 20; i++) {
			strings.add("java");
		}
		header = inflater.inflate(R.layout.textlist_header_tools, listView,
				false);
		listView.addHeaderView(header);
		listView.setOnScrollListener(this);
		quickPublish = header.findViewById(R.id.quick_tools_publish);
		quickPublish.setOnClickListener(this);

		quickReView = header.findViewById(R.id.quick_tools_review);
		quickReView.setOnClickListener(this);
		listView.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, strings));
		quickToolsView = view.findViewById(R.id.textlist_quick_tools);
		quickToolsView.setVisibility(View.GONE);
		// 新消息提醒
		textNotifiy = (TextView) view.findViewById(R.id.textlist_new_notify);
		textNotifiy.setVisibility(View.GONE);
		return view;

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
	 * 从上向下拉动，那么就要加载新数据操作
	 */
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub

	}

	/**
	 * 从下往上拉，那么就要考虑加载旧的数据
	 */
	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub

	}

}
