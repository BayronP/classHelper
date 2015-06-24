package com.vote.service;

import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.vote.domain.ProDomain;
import com.vote.domain.ProSetDomain;
import com.vote.utils.StreamTool;

public class GetProService {
	static CookieStore cookieStore = new BasicCookieStore();
	static String sessionid1;
	static String csrftoken1;
	static int num1;
	public static ProSetDomain find(String myUrl, String sessionid, String csrftoken, String code) throws Exception {
		// TODO Auto-generated method stub
		sessionid1 = sessionid;
		csrftoken1 = csrftoken;
		HttpClient httpClient = new DefaultHttpClient();
	  	  HttpGet httpGet = new HttpGet("http://"+myUrl+"/problemset/"+code+"/.json");
	  	  httpGet.setHeader("X-CSRFToken", csrftoken);
	  	  Log.i("sessionid", sessionid);
	  	  httpGet.setHeader("Cookie","sessionid="+sessionid+"; csrftoken="+csrftoken);
	  	  HttpContext localContext = new BasicHttpContext();
	  	  localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	  	  HttpResponse httpResponse = httpClient.execute(httpGet, localContext);
		  Log.i("Coed",""+httpResponse.getStatusLine().getStatusCode());
		return parseJSON(httpResponse.getEntity().getContent());  
	}

	private static ProSetDomain parseJSON(InputStream inStream) throws Exception {
		// TODO Auto-generated method stub
		byte[] data = StreamTool.read(inStream);
		String json = new String(data);
		Log.i("json", json);
		ProSetDomain proSetDomain=JSON.parseObject(json,ProSetDomain.class); 
		/*
		String[] pro = proSetDomain.getProblems().split("\"");
		for(int i = 1;i<pro.length;i = i+2){
			//GetProblemenService(pro[i],sessionid1,csrftoken1);
		}
		Log.i("proDomain.toString()", pro[0]);
		Log.i("proSetDomain.toString()", proSetDomain.toString());
		//ProDomain proDomain = JSON.parseObject(pro,ProDomain.class); 
		//Log.i("proDomain.toString()", proDomain.toString());
		 * 
		 */
		return proSetDomain;
	}
/*
	private static void GetProblemenService(String muUrl, String sessionid12,
			String csrftoken12) {
		// TODO Auto-generated method stub
		HttpClient httpClient = new DefaultHttpClient();
	  	  HttpGet httpGet = new HttpGet("http://"+myUrl+"/problemset/"+num+"/.json");
	  	  httpGet.setHeader("X-CSRFToken", csrftoken);
	  	  Log.i("sessionid", sessionid);
	  	  httpGet.setHeader("Cookie","sessionid="+sessionid+"; csrftoken="+csrftoken);
	  	  HttpContext localContext = new BasicHttpContext();
	  	  localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	  	  HttpResponse httpResponse = httpClient.execute(httpGet, localContext);
		  Log.i("Coed",""+httpResponse.getStatusLine().getStatusCode());
		return parseJSON(httpResponse.getEntity().getContent());  
	}
*/
}
