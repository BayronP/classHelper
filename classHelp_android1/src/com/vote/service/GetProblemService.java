package com.vote.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

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
import com.vote.utils.StreamTool; 
 
public class GetProblemService {

	public static ProDomain find(String myurl, String sessionid,
			String csrftoken) throws IllegalStateException, IOException, Exception {
		CookieStore cookieStore = new BasicCookieStore();
		HttpClient httpClient = new DefaultHttpClient();
		myurl = myurl.replace("\\", "/");
		myurl = myurl.replace("//", "/");
		Log.i("myurl ", myurl);
		URI url = new URI(myurl);

		//Log.i("str ", str);
	  	  HttpGet httpGet = new HttpGet(url);
	  	  httpGet.setHeader("X-CSRFToken", csrftoken);
	  	  Log.i("sessionid", sessionid);
	  	  httpGet.setHeader("Cookie","sessionid="+sessionid+"; csrftoken="+csrftoken);
	  	  HttpContext localContext = new BasicHttpContext();
	  	  localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	  	  HttpResponse httpResponse = httpClient.execute(httpGet, localContext);
		  Log.i("Coed",""+httpResponse.getStatusLine().getStatusCode());
		return parseJSON(httpResponse.getEntity().getContent());  
	}
	private static ProDomain parseJSON(InputStream inStream) throws Exception {
		// TODO Auto-generated method stub
		byte[] data = StreamTool.read(inStream);
		String json = new String(data);
		Log.i("json", json);
		ProDomain proDomain=JSON.parseObject(json,ProDomain.class); 
		return proDomain;
	}

}
