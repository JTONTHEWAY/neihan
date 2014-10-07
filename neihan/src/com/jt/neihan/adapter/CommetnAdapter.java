package com.jt.neihan.adapter;

import java.util.Date;
import java.util.List;

import cn.sharesdk.dropbox.c;

import com.jt.neihan.R;
import com.jt.neihan.bean.Comment;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class CommetnAdapter extends BaseAdapter {
	private Context context;
	private List<Comment> comments;
	private LayoutInflater inflater;

	public CommetnAdapter(Context context, List<Comment> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.comments = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return comments.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return comments.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return comments.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = inflater
					.inflate(R.layout.item_comment, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.chbDigg = (CheckBox) convertView
					.findViewById(R.id.comment_digg_count);
			viewHolder.profileImage = (ImageView) convertView
					.findViewById(R.id.comment_profile_image);
			viewHolder.txtContent = (TextView) convertView
					.findViewById(R.id.comment_content);
			viewHolder.txtCreateTiem = (TextView) convertView
					.findViewById(R.id.comment_create_time);
			viewHolder.txtnick = (TextView) convertView
					.findViewById(R.id.comment_profile_nick);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		Comment comment = comments.get(position);
		viewHolder.txtnick.setText(comment.getUserName());
		Date dt = new Date(comment.getCreateTime());
		viewHolder.txtCreateTiem.setText(dt.toString());
		viewHolder.txtContent.setText(comment.getText());
		int diggCount = comment.getDiggCount();
		viewHolder.chbDigg.setText(Integer.toString(diggCount));
		int useDigg = comment.getUserDigg();
		viewHolder.chbDigg.setEnabled(useDigg == 0);
		return convertView;
	}

	public static class ViewHolder {
		ImageView profileImage;
		TextView txtnick;
		TextView txtCreateTiem;
		TextView txtContent;
		CheckBox chbDigg;
	}
}
