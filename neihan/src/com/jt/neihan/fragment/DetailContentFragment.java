package com.jt.neihan.fragment;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import pl.droidsonroids.gif.GifImageView;

import cn.sharesdk.framework.l;

import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.Volley;
import com.jt.neihan.R;
import com.jt.neihan.adapter.CommetnAdapter;
import com.jt.neihan.bean.Comment;
import com.jt.neihan.bean.CommentList;
import com.jt.neihan.bean.ImageEntity;
import com.jt.neihan.bean.TextEntity;
import com.jt.neihan.bean.UserEntity;
import com.jt.neihan.client.ClientAPI;

import android.R.integer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailContentFragment extends Fragment implements OnTouchListener,
		Listener<String> {
	private TextEntity entity;
	private ScrollView scrollView;
	private TextView txyHotCommentCount;
	private ListView hotListView;
	private TextView txyRecentCommentCount;
	private ListView recentListView;
	private CommetnAdapter hotAdapter;
	private List<Comment> hotcomments;
	private List<Comment> recentComments;
	private CommetnAdapter recentAdapter;
	private RequestQueue queue;
	private int offset;
	private boolean hasmore;
	private long groupid;
	private LinearLayout layout;

	public DetailContentFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (entity == null) {
			Serializable serializable = getArguments()
					.getSerializable("entity");
			if (serializable instanceof TextEntity) {
				entity = (TextEntity) serializable;
			}
		}
		queue = Volley.newRequestQueue(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_detail_content,
				container, false);
		scrollView = (ScrollView) view.findViewById(R.id.detail_scroll);
		scrollView.setOnTouchListener(this);
		layout = (LinearLayout) view.findViewById(R.id.scroll_content);

		// if (entity != null) {
		// UserEntity user = entity.getUser();
		// if (user != null) {
		// String avatarUrl = user.getAvatarUrl();
		// String nick = user.getName();
		// }
		// }
		setEssayContent(view);
		txyHotCommentCount = (TextView) view
				.findViewById(R.id.txt_hot_comments_count);
		hotListView = (ListView) view.findViewById(R.id.hot_comment_list);
		hotcomments = new LinkedList<Comment>();
		hotAdapter = new CommetnAdapter(getActivity(), hotcomments);
		hotListView.setAdapter(hotAdapter);

		txyRecentCommentCount = (TextView) view
				.findViewById(R.id.txt_reccent_comments_count);
		recentComments = new LinkedList<Comment>();
		recentAdapter = new CommetnAdapter(getActivity(), recentComments);

		recentListView = (ListView) view.findViewById(R.id.recent_comment_list);
		recentListView.setAdapter(recentAdapter);
		groupid = entity.getGroupId();
		ClientAPI.getComment(queue, groupid, 0, this);
		return view;
	}

	// 设置段子主体内容
	public void setEssayContent(View view) {
		// 1.先设置文本内容的数据
		TextView btnGifPlay = (TextView) view.findViewById(R.id.btnGifPlay);
		ImageButton btnShareButton = (ImageButton) view
				.findViewById(R.id.item_share);
		CheckBox chbBuryCount = (CheckBox) view
				.findViewById(R.id.item_bury_count);
		CheckBox chbDiggCount = (CheckBox) view
				.findViewById(R.id.item_digg_count);
		GifImageView gifImageView = (GifImageView) view
				.findViewById(R.id.gifimageview);
		ImageView profileImage = (ImageView) view
				.findViewById(R.id.item_profile_image);
		ProgressBar pbDownLoadProgress = (ProgressBar) view
				.findViewById(R.id.item_image_downLoad_progress);
		TextView txtCommentCount = (TextView) view
				.findViewById(R.id.item_comment_count);
		TextView txtContent = (TextView) view.findViewById(R.id.item_content);
		TextView txtProfileNick = (TextView) view
				.findViewById(R.id.item_profile_nick);
		UserEntity user = entity.getUser();
		String nick = "";
		if (user != null) {
			nick = user.getName();
		}
		txtProfileNick.setText(nick);
		String content = entity.getContent();
		txtContent.setText(content);

		chbDiggCount.setText("" + entity.getDiggCount());
		chbBuryCount.setText(Integer.toString(entity.getBuryCount()));
		int userDigg = entity.getUserDigg();// 用户是否赞过

		chbDiggCount.setEnabled(userDigg == 1 ? false : true);
		// 如果为1的话，代表用户已经赞过，那么chbDiggcount必须禁用
		int userBury = entity.getUserBury();

		chbBuryCount.setEnabled(userBury == 1 ? false : true);

		int commentCount = entity.getCommentCount();
		txtCommentCount.setText("" + commentCount);// 因为是int类型，所以需要转化为string
		// 设置图片的数据
	}

	private boolean hasMove = false;

	/**
	 * 处理scrollview触摸事件，用于在scrollview在滚动时，自动加载数据
	 * 
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		boolean bret = false;
		int action = arg1.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			bret = true;
			hasMove = false;
		} else if (action == MotionEvent.ACTION_UP) {
			if (hasMove) {
				int sy = scrollView.getScrollY();
				int mh = scrollView.getMeasuredHeight();
				int ch = layout.getHeight();
				if (sy + mh >= ch) {
					ClientAPI.getComment(queue, groupid, offset, this);
				}
			}

		} else if (action == MotionEvent.ACTION_MOVE) {
			hasMove = true;
		}
		return bret;
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
			// Log.d("TestActivity", "comment list json" + json.toString(4));
			// 解析获取到的评论列表
			CommentList commentList = new CommentList();
			commentList.parseJson(json);// 包含两组数据，一组热门评论，一组新鲜评论，且都有可能为空

			hasmore = commentList.isHasMore();

			// Log.d("TextActivity", " groupid " + groupid);

			// Log.d("TextActivity", "hasmore " + hasmore);
			// 热门评论列表，可能为空，第一次offset为0 时可能有数据
			List<Comment> topComments = commentList.getTopcomments();
			// 新鲜评论 ，可能有数据
			List<Comment> rtComments = commentList.getRecentComments();

			if (topComments != null) {
				hotcomments.addAll(topComments);
				hotAdapter.notifyDataSetChanged();
			}

			if (rtComments != null) {
				recentComments.addAll(rtComments);
				recentAdapter.notifyDataSetChanged();
			}
			// 直接把commentList 提交给listview 的adapter，利用adapter更新数据
			// 分页标识，要求服务器每次返回20条，通过hasmore 来进行判断
			offset += 20;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
