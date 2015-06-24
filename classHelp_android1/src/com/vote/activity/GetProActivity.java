package com.vote.activity;

import java.io.IOException;

import com.vote.domain.ProDomain;
import com.vote.domain.ProSetDomain;
import com.vote.questionnaire.VoteSubmitActivity;
import com.vote.service.GetProService;
import com.vote.service.GetProblemService;
import com.vote.utils.GetIp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class GetProActivity extends Activity {
	private EditText codeEdit;// 用户名
	Button button;
	long exitTime;
	String myUrl = "";
	String[] voteQuestion;
	String[] voteAnswers;
	String sessionid = new String();
	String csrftoken = new String();
	String code = new String();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myUrl = GetIp.getPropertiesURL(getApplicationContext(),"ip");
		setContentView(R.layout.log);
		button = (Button) findViewById(R.id.button1);
		codeEdit = (EditText) findViewById(R.id.edit_code);
		Intent intent = getIntent();
		sessionid = intent.getStringExtra("sessionid");
		csrftoken = intent.getStringExtra("csrftoken");
		Log.i("sessionid", sessionid);
		
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ProSetDomain result = new ProSetDomain();
				ProDomain proresult = new ProDomain();
				try {
					code = codeEdit.getText().toString();
					Log.i("code", code);
					result = GetProService.find(myUrl,sessionid,csrftoken,code);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(result==null){
					Intent intent = new Intent(GetProActivity.this,
							LoginActivity.class);
					startActivity(intent);
					GetProActivity.this.finish();
				}
				Log.i("problem", result.toString());
				String str = result.getProblems();
				String str1[] = str.split("\"");
				int count = 0;
				voteQuestion = new String[str1.length/2];
				voteAnswers = new String [str1.length/2];
				for(int i = 1 ; i< str1.length; i = i + 2){
					try {
						Log.i("count", ""+count);
						Log.i("str1", str1[i]);
						proresult = GetProblemService.find(str1[i],sessionid,csrftoken);
						voteQuestion[count] = proresult.getProblemDesc();
						voteAnswers[count]=proresult.getProblemSelect();	
						
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Log.i("proresult"+i, proresult.toString());
					Log.i("voteQuestion"+i, voteQuestion[count]);
					count++;
				}
				// TODO Auto-generated method stub
				Intent intent = new Intent(GetProActivity.this,
						VoteSubmitActivity.class);
				intent.putExtra("voteQuestion", voteQuestion);
				intent.putExtra("voteAnswers", voteAnswers);
				intent.putExtra("sessionid", sessionid);
				intent.putExtra("csrftoken", csrftoken);
				intent.putExtra("code", code);
				startActivity(intent);
				GetProActivity.this.finish();
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitDialog();
		}
		return true;
	}
	private void exitDialog() {
		AlertDialog.Builder aa = new AlertDialog.Builder(this);
		aa.setTitle("消息提示");
		aa.setMessage("确认退出？");
		aa.setIcon(R.drawable.ic_launcher);
		aa.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				GetProActivity.this.finish();
			}
		});
		aa.setNegativeButton("取消", null);
		aa.create();
		aa.show();
	}
}
