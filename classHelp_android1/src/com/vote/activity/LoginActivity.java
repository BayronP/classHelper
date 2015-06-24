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
	private EditText accountEdit;// �û���
	private EditText passwordEdit;// ����
	private Button login;// ��½��ť
	private CheckBox rememberPass;// ��ס����
	private CheckBox denglu;// �Զ���½
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
		// ��SharedPreferences���ȡ�� ��ס�����״̬
		if (shared.getBoolean("ISCHECK", false)) {
			// ����ס��������Ϊ�����״̬
			rememberPass.setChecked(true);
			// Ȼ��ֵ��ֵ��EditText
			accountEdit.setText(shared.getString("oa_name", ""));
			passwordEdit.setText(shared.getString("oa_pass", ""));
			// ��ȡ�Զ���¼��ť��״̬
			if (shared.getBoolean("AUTO_ISCHECK", false)) {
				// �����Զ���¼����� Ȼ��ʵ����ת
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
		// �������checkBOx���뵽users��
		rememberPass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Boolean isChecked1 = rememberPass.isChecked();
				editor.putBoolean("ISCHECK", isChecked1);
				editor.commit();
			}
		});
		// �����Զ���¼Ĭ��Ϊ�����
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
				// �����ס������Զ���¼����ѡ�о�ѡ���¼��ת
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
		// ����Ϣ���뵽users����
		editor.putString("oa_name", accountEdit.getText().toString());
		editor.putString("oa_pass", passwordEdit.getText().toString());
		editor.commit();
		if (TextUtils.isEmpty(accountEdit.getText().toString())) {
			Toast.makeText(this, "�������û���", Toast.LENGTH_LONG).show();
			return; 
		}
		if (TextUtils.isEmpty(passwordEdit.getText().toString())) {
			Toast.makeText(this, "����������", Toast.LENGTH_LONG).show();
			return;
		}
		
		//HttpUtils util = new HttpUtils();
		result = HttpUtils.find(accountEdit.getText().toString(),passwordEdit.getText().toString(),myUrl);
		if (result[0]!=null&&result[1]!=null) {
			// ����Intent���󣬴���ԴActivity��Ŀ��Activity�������
			Intent intent = new Intent(LoginActivity.this, GetProActivity.class);
			intent.putExtra("sessionid", result[1]);
			intent.putExtra("csrftoken", result[0]);
			// ����Activity
			startActivity(intent);
			LoginActivity.this.finish();
		} else {
			// ��¼��Ϣ����ͨ��Toast��ʾ��ʾ��Ϣ
			Toast.makeText(LoginActivity.this, "�û������������", Toast.LENGTH_SHORT)
					.show();
		}

	}
	
}
