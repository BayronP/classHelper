package com.vote.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.content.SharedPreferences;
import android.util.Log;

public class HttpUtils {
	
	static String token = "test";
	 static String sessionid = "";
	 static CookieStore cookieStore = new BasicCookieStore();
	 static Cookie csrfCookie = null;
	 static String ip = "";
	 
	 public static String[] find(String username, String password, String myUrl) throws ClientProtocolException, IOException {
			// TODO Auto-generated method stub
			ip = myUrl;

			return LoginWithToken(username,password);
		}

	private static String[] LoginWithToken(String username, String password) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		token = getCsrfFromUrl("http://"+ip+"/api-auth/login/?next=/");
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://"+ip+"/api-auth/login/?next=/");
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		//nameValuePairs.add(new BasicNameValuePair("username", username));
	    //nameValuePairs.add(new BasicNameValuePair("password", password));
	    nameValuePairs.add(new BasicNameValuePair("username", "bayron3"));
	    nameValuePairs.add(new BasicNameValuePair("password", "bay12321"));
	    nameValuePairs.add(new BasicNameValuePair("csrfmiddlewaretoken", token));
	    try {
	    	httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    	httpPost.setHeader("X-CSRFToken", token);
	    	httpPost.setHeader("Cookie","csrftoken="+token);  
	    } catch (UnsupportedEncodingException e) {
	    	e.printStackTrace();
	    }
			 
	    final BasicCookieStore cookieStore =  new BasicCookieStore();
	    BasicClientCookie csrf_cookie = new BasicClientCookie("X-CSRFToken", token);
	    //csrf_cookie.setDomain("172.18.35.222444");
	    cookieStore.addCookie(csrf_cookie); 
	    int code = 0;
	    HttpContext localContext = new BasicHttpContext();
	    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	    // Execute HTTP Post Request
	    HttpResponse response = null;
	
	    
	    try {
	    	Log.e("TOKEN_COOKIE","Token: "+token);
	    	response = httpClient.execute(httpPost, localContext);
	    	Log.i("StatueCode", ""+response.getStatusLine().getStatusCode());
	    	code = response.getStatusLine().getStatusCode();
	    	  for (Cookie cookie : cookieStore.getCookies()) {
	    	  		 if (cookie.getName().equals("sessionid")){
	    	  	    	sessionid = cookie.getValue();
	    	  	    	Log.i("sessionid", sessionid);
	    	  	      }
	    	  	  }
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    String str[] = new String[2];
	    str[0]=token;
        response.getEntity().getContent().close(); //!!!IMPORTANT  
	    httpClient.getConnectionManager().shutdown();
	    httpPost.abort();
	    if(code == 200){
	    	str[1]=sessionid;
	    	return str;
	    }
	    return null;
	}
	
public static String getCsrfFromUrl(String url) throws ClientProtocolException, IOException {
	  HttpClient httpClient = new DefaultHttpClient();
	  HttpGet httpGet = new HttpGet(url);
	  HttpContext localContext = new BasicHttpContext();
	  localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	  httpClient.execute(httpGet, localContext);
	  for (Cookie cookie : cookieStore.getCookies()) {
		Log.i("cooki5e", cookie.getName());
	      if (cookie.getName().equals("csrftoken") ) {
	          csrfCookie = cookie;
	          break;
	      } 
	  }

	  if (csrfCookie == null) {
	      throw new NullPointerException("CSRF cookie not found! ");
	  }
	  httpClient.getConnectionManager().shutdown();
	  httpGet.abort();
	  return csrfCookie.getValue().toString();
	}
	/*
	private static int TestWithToken() throws ClientProtocolException, IOException{
		HttpClient httpClient = new DefaultHttpClient();
	  	  HttpGet httpGet = new HttpGet("http://172.18.35.222/problemset/.json");
	  	  httpGet.setHeader("X-CSRFToken", token);
		  httpGet.setHeader("Cookie","sessionid="+sessionid+"; csrftoken="+token);
		  
	  	  HttpContext localContext = new BasicHttpContext();
	  	  localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	  	  HttpResponse httpResponse = httpClient.execute(httpGet, localContext);
		  Log.i("Coed",""+httpResponse.getStatusLine().getStatusCode());
		  httpGet.abort();
		  httpClient.getConnectionManager().shutdown();
		return httpResponse.getStatusLine().getStatusCode();  
	}
	 */

}
