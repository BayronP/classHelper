package com.vote.questionnaire;

import java.util.ArrayList;
import java.util.List;

import com.vote.activity.GetProActivity;
import com.vote.activity.LoginActivity;
import com.vote.activity.R;
import com.vote.activity.ThankActivity;
import com.vote.service.PostSolution;
import com.vote.utils.GetIp;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VoteSubmitAdapter extends PagerAdapter {

	VoteSubmitActivity mContext;
	// 传递过来的页面view的集合
	List<View> viewItems;
	// 每个item的页面view
	View convertView;
	// 传递过来的所有数据
	ArrayList<VoteSubmitItem> dataItems;
	// 题目选项的adapter
	VoteSubmitListAdapter listAdapter;
	
	StringBuffer strBuffer = new StringBuffer();

	ViewHolder holder = null;
	 int[] positionIds;

	public VoteSubmitAdapter(VoteSubmitActivity context, List<View> viewItems, ArrayList<VoteSubmitItem> dataItems) {
		mContext = context;
		this.viewItems = viewItems;
		this.dataItems = dataItems;
		positionIds = new int[viewItems.size()];
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(viewItems.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		holder = new ViewHolder();
		convertView = viewItems.get(position);
		holder.title = (TextView) convertView.findViewById(R.id.vote_submit_title);
		holder.question = (TextView) convertView.findViewById(R.id.vote_submit_question);
		holder.listView = (ListView) convertView.findViewById(R.id.vote_submit_listview);
		holder.previousBtn = (LinearLayout) convertView.findViewById(R.id.vote_submit_linear_previous);
		holder.nextBtn = (LinearLayout) convertView.findViewById(R.id.vote_submit_linear_next);
		holder.nextText = (TextView) convertView.findViewById(R.id.vote_submit_next_text);
		holder.nextImage = (ImageView) convertView.findViewById(R.id.vote_submit_next_image);

		holder.title.setText("<教师课堂检测系统>");
		listAdapter = new VoteSubmitListAdapter(mContext, dataItems.get(position).voteAnswers);
		holder.question.setText(dataItems.get(position).voteQuestion);
		holder.listView.setAdapter(listAdapter);
		holder.listView.setOnItemClickListener(new ListViewOnClickListener(position, listAdapter));

		// 第一页隐藏"上一步"按钮
		if (position == 0) {
			holder.previousBtn.setVisibility(View.GONE);
		} else {
			
			holder.previousBtn.setVisibility(View.VISIBLE);
			holder.previousBtn.setOnClickListener(new LinearOnClickListener(position - 1));
		}
		// 最后一页修改"下一步"按钮文字
		if (position == viewItems.size() - 1) {
			
			holder.nextText.setText("提交");
			holder.nextImage.setImageResource(R.drawable.vote_submit_finish);
		}
		holder.nextBtn.setOnClickListener(new LinearOnClickListener(position + 1));
		container.addView(viewItems.get(position));
		return viewItems.get(position);
	}

	/**
	 * @author wisdomhu 自定义listview的item点击事件
	 */
	class ListViewOnClickListener implements OnItemClickListener {

		private VoteSubmitListAdapter mListAdapter;
		private int mPosition;

		public ListViewOnClickListener(int position,VoteSubmitListAdapter VoteSubmlistAdapteritListAdapter) {
			mListAdapter = listAdapter;
			mPosition = position;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
			// 设置更新选中项图片和文本变化
			mListAdapter.updateIndex(position);
			Log.i("position",""+position);
			 positionIds[mPosition] = position;
		}

	}

	/**
	 * @author wisdomhu 设置上一步和下一步按钮监听
	 * 
	 */
	class LinearOnClickListener implements OnClickListener {

		private int mPosition;

		public LinearOnClickListener(int position) {
			mPosition = position;
			
			
		}

		@Override
		public void onClick(View v) {
			if (mPosition == viewItems.size()) {
				for(int i = 0;i<viewItems.size();i++){
					Log.i("positionIds", ""+positionIds[i]);
					if(positionIds[i]==0){
						strBuffer.append('A');
					}else if(positionIds[i]==1){
						strBuffer.append('B');
					}
					else if(positionIds[i]==2){
						strBuffer.append('C');
					}
					else if(positionIds[i]==3){
						strBuffer.append('D');
					}
					
				}
				Intent intent = new Intent(mContext,ThankActivity.class);
				intent.putExtra("strBuffer", strBuffer.toString());
				intent.putExtra("sessionid", mContext.sessionid);
				intent.putExtra("csrftoken", mContext.csrftoken);
				intent.putExtra("code", mContext.code);
				mContext.startActivity(intent);
				mContext.finish();
				Log.i("strBuffer", strBuffer.toString());
				Log.i("listAdapter.selected",""+listAdapter.selected);
				Toast.makeText(mContext, "感谢您完成课堂教学检测!", Toast.LENGTH_SHORT).show();
			} else {
				Log.i("listAdapter.selected",""+listAdapter.selected);
				mContext.setCurrentView(mPosition);
			}
		}

	}

	@Override
	public int getCount() {
		if (viewItems == null)  
			return 0;
		return viewItems.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	/**
	 * @author wisdomhu 自定义类
	 */
	class ViewHolder {
		ListView listView;
		TextView title;
		TextView question;
		TextView answer;
		LinearLayout previousBtn, nextBtn;
		TextView nextText;
		ImageView nextImage;
	}

}
