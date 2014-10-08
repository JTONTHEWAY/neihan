package com.jt.neihan.adapter;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jt.neihan.R;
import com.jt.neihan.bean.ImageEntity;
import com.jt.neihan.bean.TextEntity;
import com.jt.neihan.bean.UserEntity;

public class EssayAdapter extends BaseAdapter {
	private Context context;
	private List<TextEntity> entities;
	private LayoutInflater inflater;
	private OnClickListener listener;

	public EssayAdapter(Context context, List<TextEntity> entities) {
		this.context = context;
		this.entities = entities;
		inflater = LayoutInflater.from(context);
	}

	public void setListener(OnClickListener listener) {
		this.listener = listener;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entities.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return entities.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View ret = convertView;
		if (convertView == null) {

			ret = inflater.inflate(R.layout.item_essay, parent, false);
		}
		if (ret != null) {
			ViewHolder holder = (ViewHolder) ret.getTag();
			if (holder == null) {
				holder = new ViewHolder();
				holder.btnGifPlay = (TextView) ret
						.findViewById(R.id.btnGifPlay);
				holder.btnShareButton = (ImageButton) ret
						.findViewById(R.id.item_share);
				holder.chbBuryCount = (CheckBox) ret
						.findViewById(R.id.item_bury_count);
				holder.chbDiggCount = (CheckBox) ret
						.findViewById(R.id.item_digg_count);
				holder.gifImageView = (GifImageView) ret
						.findViewById(R.id.gifimageview);
				holder.profileImage = (ImageView) ret
						.findViewById(R.id.item_profile_image);
				holder.pbDownLoadProgress = (ProgressBar) ret
						.findViewById(R.id.item_image_downLoad_progress);
				holder.txtCommentCount = (TextView) ret
						.findViewById(R.id.item_comment_count);
				holder.txtContent = (TextView) ret
						.findViewById(R.id.item_content);
				holder.txtProfileNick = (TextView) ret
						.findViewById(R.id.item_profile_nick);
				ret.setTag(holder);
			}
			TextEntity entity = entities.get(position);
			// 1.先设置文本内容的数据
			UserEntity user = entity.getUser();
			String nick = "";
			if (user != null) {
				nick = user.getName();
			}
			holder.txtProfileNick.setText(nick);
			String content = entity.getContent();
			holder.txtContent.setText(content);
			holder.txtContent.setOnClickListener(listener);
			holder.txtContent.setTag(Integer.toString(position));
			holder.chbDiggCount.setText("" + entity.getDiggCount());
			holder.chbBuryCount
					.setText(Integer.toString(entity.getBuryCount()));
			int userDigg = entity.getUserDigg();// 用户是否赞过

			holder.chbDiggCount.setEnabled(userDigg == 1 ? false : true);
			// 如果为1的话，代表用户已经赞过，那么chbDiggcount必须禁用
			int userBury = entity.getUserBury();

			holder.chbBuryCount.setEnabled(userBury == 1 ? false : true);

			int commentCount = entity.getCommentCount();
			holder.txtCommentCount.setText("" + commentCount);// 因为是int类型，所以需要转化为string
			// 设置图片的数据
			if (entity instanceof ImageEntity) {

			} else {
				holder.pbDownLoadProgress.setVisibility(View.GONE);
				holder.gifImageView.setVisibility(View.GONE);
				holder.btnGifPlay.setVisibility(View.GONE);
			}
		}
		return ret;
	}

	public static class ViewHolder {
		public ImageView profileImage;// 头像

		public TextView txtProfileNick;// 昵称

		public TextView txtContent;// 段子内容

		public ProgressBar pbDownLoadProgress;// gif下载进度

		public GifImageView gifImageView;// 图片显示部分按钮

		public TextView btnGifPlay;// 点击 开启播放

		public CheckBox chbDiggCount;

		public CheckBox chbBuryCount;

		public TextView txtCommentCount;

		public ImageButton btnShareButton;

	}
}
