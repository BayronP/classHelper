package com.vote.questionnaire;

import java.util.ArrayList;

import com.vote.activity.R;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VoteSubmitListAdapter extends BaseAdapter {

	ArrayList<String> dataItems;
	VoteSubmitActivity mContext;
	int selected = 0;// 默认选中第一项

	public VoteSubmitListAdapter(VoteSubmitActivity context, ArrayList<String> dataItems) {
		mContext = context;
		this.dataItems = dataItems;
	}

	@Override
	public int getCount() {
		return dataItems.size();
	}

	/**
	 * @param position
	 *            根据选中项位置刷新adapter
	 */
	public void updateIndex(int position) {
		selected = position;
		
		notifyDataSetChanged();
	}

	@Override
	public Object getItem(int position) {
		return dataItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		Log.i("positionselected", ""+position);
		return position;
	}

	ViewHolder holder = null;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mContext.getLayoutInflater().inflate(R.layout.vote_submit_listview_item, null);
			holder.select_image = (ImageView) convertView.findViewById(R.id.vote_submit_select_image);
			holder.select_text = (TextView) convertView.findViewById(R.id.vote_submit_select_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (position == selected) {
			holder.select_image.setImageResource(R.drawable.vote_submit_selected);
			holder.select_text.setTextColor(mContext.getResources().getColor(R.color.vote_submit_orange));
		} else {
			holder.select_image.setImageResource(R.drawable.vote_submit_normal);
			holder.select_text.setTextColor(mContext.getResources().getColor(R.color.black));
		}
		holder.select_text.setText(dataItems.get(position));
		return convertView;
	}

	/**
	 * @author wisdomhu 自定义类
	 */
	class ViewHolder {
		ImageView select_image;
		TextView select_text;
	}
}
