package com.vote.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginServer {
	public static void saveUserInfo(Context context, String username, String password) {
		SharedPreferences sp = context.getSharedPreferences("user",
				Context.MODE_WORLD_READABLE);
		Editor editor = sp.edit();
		editor.putString("username", username);
		editor.putString("password", password);  
		editor.commit();
	}
}
