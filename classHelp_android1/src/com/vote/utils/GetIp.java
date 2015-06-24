package com.vote.utils;

import java.util.Properties;

import android.content.Context;

public class GetIp {

	 public static String getPropertiesURL(Context c, String s) {
	  String url = null;
	  Properties properties = new Properties();
	  try {
	   properties.load(c.getAssets().open("project.properties"));
	   url = properties.getProperty("ip");
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  return url;
	 }

}
