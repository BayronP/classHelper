package com.vote.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.UUID;

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
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import com.alibaba.fastjson.JSON;
import com.vote.domain.SolutionDomain;

import android.util.Log;

public class UploadSolution {
	static CookieStore cookieStore = new BasicCookieStore();
	static Cookie csrfCookie = null;

	public static int excute(String strBuffer, String sessionid,
			String csrftoken, String code, String ip) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		//getCsrfFromUrl("http://"+ip+"/problemset/");
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://"+ip+"/solution/");
		SolutionDomain solutionDomain = new SolutionDomain(); 
		solutionDomain.setAns(strBuffer);
		solutionDomain.setCode(code);
		//String BOUNDARY = UUID.randomUUID().toString();
		//String CONTENT_TYPE = "multipart/form-data";
		String jsonString = JSON.toJSONString(solutionDomain);
		Log.i("jsonA", jsonString);
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair("csrfmiddlewaretoken", csrftoken));
	    nameValuePairs.add(new BasicNameValuePair("code", code));
	    nameValuePairs.add(new BasicNameValuePair("ans", strBuffer));
	    try {
	    	httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    	httpPost.setHeader("X-CSRFToken", csrftoken);
	    	httpPost.setHeader("Host", ip);
	    	httpPost.setHeader("Connection", "keep-alive");
	    	httpPost.setHeader("X-CSRFToken", csrftoken);
	    	httpPost.setHeader("Cookie","tabstyle=raw-tab; sessionid="+sessionid+"; csrftoken="+csrftoken);
	    	//httpPost.setHeader("Referer","http://"+ip+"/solution/");  
	    	//httpPost.setHeader("Content-Type",CONTENT_TYPE + ";boundary=" + BOUNDARY); 
	    	httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
	    } catch (UnsupportedEncodingException e) {
	    	e.printStackTrace();
	    }
			 
	    final BasicCookieStore cookieStore =  new BasicCookieStore();
	    BasicClientCookie csrf_cookie = new BasicClientCookie("X-CSRFToken", csrftoken);
	    cookieStore.addCookie(csrf_cookie); 
	    HttpContext localContext = new BasicHttpContext();
	    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	    // Execute HTTP Post Request
	    HttpResponse response = null;
	    response = httpClient.execute(httpPost, localContext);
	    Log.i("response", ""+response.getStatusLine().getStatusCode());
	    for (Cookie cookie : cookieStore.getCookies()) {
			Log.i("cooki5e", cookie.getName());
		      if (cookie.getName().equals("csrftoken") ) {
		          csrfCookie = cookie;
		          break;
		      } 
		  }
	    csrftoken = csrfCookie.getValue().toString();
	    
	    return excute1(strBuffer, sessionid,
			csrftoken,code,  ip);
	    
	}
	/*
	public static String getCsrfFromUrl(String url) throws ClientProtocolException, IOException {
		  HttpClient httpClient = new DefaultHttpClient();
		  HttpGet httpGet = new HttpGet(url);
		  HttpContext localContext = new BasicHttpContext();
		  localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		  HttpResponse res = httpClient.execute(httpGet, localContext);
		  Log.i("RES", "" + res.getStatusLine().getStatusCode());
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
        */

	private static int excute1(String strBuffer, String sessionid,
			String csrftoken, String code, String ip) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://"+ip+"/solution/");
		SolutionDomain solutionDomain = new SolutionDomain(); 
		solutionDomain.setAns(strBuffer);
		solutionDomain.setCode(code);
		//String BOUNDARY = UUID.randomUUID().toString();
		//String CONTENT_TYPE = "multipart/form-data";
		String jsonString = JSON.toJSONString(solutionDomain);
		Log.i("jsonA", jsonString);
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair("csrfmiddlewaretoken", csrftoken));
	    nameValuePairs.add(new BasicNameValuePair("code", code));
	    nameValuePairs.add(new BasicNameValuePair("ans", strBuffer));
	    try {
	    	httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	    	httpPost.setHeader("X-CSRFToken", csrftoken);
	    	httpPost.setHeader("Host", ip);
	    	httpPost.setHeader("Connection", "keep-alive");
	    	httpPost.setHeader("X-CSRFToken", csrftoken);
	    	httpPost.setHeader("Cookie","tabstyle=raw-tab; sessionid="+sessionid+"; csrftoken="+csrftoken);
	    	//httpPost.setHeader("Referer","http://"+ip+"/solution/");  
	    	//httpPost.setHeader("Content-Type",CONTENT_TYPE + ";boundary=" + BOUNDARY); 
	    	httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
	    } catch (UnsupportedEncodingException e) {
	    	e.printStackTrace();
	    }
			 
	    final BasicCookieStore cookieStore =  new BasicCookieStore();
	    BasicClientCookie csrf_cookie = new BasicClientCookie("X-CSRFToken", csrftoken);
	    cookieStore.addCookie(csrf_cookie); 
	    HttpContext localContext = new BasicHttpContext();
	    localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	    // Execute HTTP Post Request
	    HttpResponse response = null;
	    response = httpClient.execute(httpPost, localContext);
	    Log.i("responseTWO", ""+response.getStatusLine().getStatusCode());
		return response.getStatusLine().getStatusCode();
	}
}
