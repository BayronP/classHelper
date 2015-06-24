package com.vote.activity;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.vote.service.UploadSolution;
import com.vote.utils.GetIp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThankActivity extends Activity{
	
	String sessionid  = new String();
	String csrftoken  = new String();
	String strBuffer  = new String();
	String code  = new String();
	String ip = new String();
	private Button goBack;// µÇÂ½°´Å¥

	@Override
	public View onCreateView(View parent, String name, Context context,
			AttributeSet attrs) {
		// TODO Auto-generated method stub
		return super.onCreateView(parent, name, context, attrs);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thank);
		Intent intent = getIntent();
		strBuffer = intent.getStringExtra("strBuffer");
		csrftoken = intent.getStringExtra("sessionid");
		csrftoken = intent.getStringExtra("csrftoken");
		code = intent.getStringExtra("code");
		Log.i("strBuffer", strBuffer);
		Log.i("sessionid", sessionid);
		Log.i("csrftoken", csrftoken);
		Log.i("code", code);
		//TextView testView = (TextView)findViewById(R.id.button1);
		int result=0;
		ip = GetIp.getPropertiesURL(getApplicationContext(), "ip");
		try {
			result = UploadSolution.excute(strBuffer,sessionid,csrftoken,code,ip);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		goBack = (Button) findViewById(R.id.goBack);
		goBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ThankActivity.this, LoginActivity.class);
				// Æô¶¯Activity
				startActivity(intent);
				ThankActivity.this.finish();
			}
		});
	}
	
}
