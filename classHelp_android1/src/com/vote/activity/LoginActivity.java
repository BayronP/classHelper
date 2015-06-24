package com.vote.activity;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.vote.utils.GetIp;
import com.vote.utils.HttpUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private EditText accountEdit;// 用户名
	private EditText passwordEdit;// 密码
	private Button login;// 登陆按钮
	private CheckBox rememberPass;// 记住密码
	private CheckBox denglu;// 自动登陆
	private SharedPreferences shared;
	private Editor editor;
	private String[] result = new String[2];
	private ProgressDialog dialog;
	String myUrl = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myUrl = GetIp.getPropertiesURL(getApplicationContext(),"ip");
		Log.i("myUrl", myUrl);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.login);
		accountEdit = (EditText) findViewById(R.id.edit_user);
		passwordEdit = (EditText) findViewById(R.id.edit_psd);
		rememberPass = (CheckBox) findViewById(R.id.checkBox1);
		denglu = (CheckBox) findViewById(R.id.checkBox2);
		login = (Button) findViewById(R.id.btn_login);
		shared = getSharedPreferences("users", MODE_WORLD_READABLE);
		editor = shared.edit();
		// 从SharedPreferences里边取出 记住密码的状态
		if (shared.getBoolean("ISCHECK", false)) {
			// 将记住密码设置为被点击状态
			rememberPass.setChecked(true);
			// 然后将值赋值给EditText
			accountEdit.setText(shared.getString("oa_name", ""));
			passwordEdit.setText(shared.getString("oa_pass", ""));
			// 获取自动登录按钮的状态
			if (shared.getBoolean("AUTO_ISCHECK", false)) {
				// 设置自动登录被点击 然后实现跳转
				denglu.setChecked(true);
			}

		}
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					LoginMain();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// 将点击的checkBOx存入到users中
		rememberPass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Boolean isChecked1 = rememberPass.isChecked();
				editor.putBoolean("ISCHECK", isChecked1);
				editor.commit();
			}
		});
		// 设置自动登录默认为不点击
				Boolean value1 = shared.getBoolean("AUTO_ISCHECK", false);
				denglu.setChecked(value1);
				denglu.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Boolean isChecked2 = denglu.isChecked();
						editor.putBoolean("AUTO_ISCHECK", isChecked2);
						editor.commit();
					}
				});
				// 如果记住密码跟自动登录都被选中就选择登录跳转
				if (rememberPass.isChecked() && denglu.isChecked()) {
					try {
						LoginMain();
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

	}

	public void LoginMain() throws ClientProtocolException, IOException {
		// 将信息存入到users里面
		editor.putString("oa_name", accountEdit.getText().toString());
		editor.putString("oa_pass", passwordEdit.getText().toString());
		editor.commit();
		if (TextUtils.isEmpty(accountEdit.getText().toString())) {
			Toast.makeText(this, "请输入用户名", Toast.LENGTH_LONG).show();
			return; 
		}
		if (TextUtils.isEmpty(passwordEdit.getText().toString())) {
			Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show();
			return;
		}
		
		//HttpUtils util = new HttpUtils();
		result = HttpUtils.find(accountEdit.getText().toString(),passwordEdit.getText().toString(),myUrl);
		if (result[0]!=null&&result[1]!=null) {
			// 创建Intent对象，传入源Activity和目的Activity的类对象
			Intent intent = new Intent(LoginActivity.this, GetProActivity.class);
			intent.putExtra("sessionid", result[1]);
			intent.putExtra("csrftoken", result[0]);
			// 启动Activity
			startActivity(intent);
			LoginActivity.this.finish();
		} else {
			// 登录信息错误，通过Toast显示提示信息
			Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT)
					.show();
		}

	}
	
}
