package com.vote.questionnaire;

import java.util.ArrayList;
import java.util.List;

import com.vote.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class VoteSubmitActivity extends Activity {

	VoteSubmitViewPager viewPager;
	VoteSubmitAdapter pagerAdapter;
	List<View> viewItems = new ArrayList<View>();
	ArrayList<VoteSubmitItem> dataItems = new ArrayList<VoteSubmitItem>();
	private int count = 0;  
	String[] voteQuestion;
	String[] voteAnswers;
	String sessionid = new String();
	String csrftoken = new String();
	String code = new String();
	String[][] myVoteAnswers;
	VoteSubmitItem testItem;
	ArrayList<VoteSubmitItem> testItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vote_submit);
		Intent intent = getIntent();
		voteQuestion = intent.getStringArrayExtra("voteQuestion");
		voteAnswers = intent.getStringArrayExtra("voteAnswers");
		sessionid = intent.getStringExtra("sessionid");
		csrftoken = intent.getStringExtra("csrftoken");
		code = intent.getStringExtra("code");
		count = voteQuestion.length;
		String[] tem = new String[count];
		String temp = "";
		myVoteAnswers = new String[count][4];
		for(int i = 0;i<count;i++){
			temp=voteAnswers[i];
			Log.i("temp", temp);
			tem = temp.split("\\r\\n");
			Log.i("tem", tem[0]);
			myVoteAnswers[i]=tem;
		}
		init();
	}

	/**
	 * 页面初始化
	 */
	private void init() {
		for (int i = 0; i < count; i++) {
			viewItems.add(getLayoutInflater().inflate(R.layout.vote_submit_viewpager_item, null));
		}
		dataItems = getTestData();
		viewPager = (VoteSubmitViewPager) findViewById(R.id.vote_submit_viewpager);
		pagerAdapter = new VoteSubmitAdapter(this, viewItems, dataItems);
		viewPager.setAdapter(pagerAdapter);
		viewPager.getParent().requestDisallowInterceptTouchEvent(false);
	}


		public ArrayList<VoteSubmitItem> getTestData() {

			testItems = new ArrayList<VoteSubmitItem>();
			for (int i = 0; i < count; i++) {
				testItem = new VoteSubmitItem();
				testItem.itemId = i;
				testItem.voteQuestion = voteQuestion[i];
				for (int j = 0; j < myVoteAnswers[i].length; j++) {
					testItem.voteAnswers.add(myVoteAnswers[i][j]);
				}
				testItems.add(testItem);
			}    
			return testItems;
		}

	/** 
	 * @param index
	 *            根据索引值切换页面
	 */
	public void setCurrentView(int index) {
		viewPager.setCurrentItem(index);
	}
}
