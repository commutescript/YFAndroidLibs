package com.github.yf_library.utils;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import android.util.Log;


public class HttpClient{
	private HttpResponse httpResponse = null;
	private String urlappend = "";
	private List<NameValuePair> params = null;
	private HttpPost httpPost = null;
	private String result = "";
	
	public HttpClient(String urlappend,List<NameValuePair> params) {
		this.urlappend = urlappend;
		this.params = params;
		httpPost = new HttpPost(""+this.urlappend);
//		HttpConnectionParams.setConnectionTimeout(params, 2000);
		if(params!=null){
		Log.i("mylog", params.toString());}

	}
	
	//允许debug
	private void enableDebug() {
		java.util.logging.Logger.getLogger("org.apache.http").setLevel(
				java.util.logging.Level.FINEST);
		java.util.logging.Logger.getLogger("org.apache.http.wire").setLevel(
				java.util.logging.Level.FINER);
		java.util.logging.Logger.getLogger("org.apache.http.headers").setLevel(
				java.util.logging.Level.OFF);
		
	}
	
	public String getHttpJson() throws ClientProtocolException, IOException, JSONException{
		if(params==null){
		httpResponse = new DefaultHttpClient().execute(httpPost); 
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			result = EntityUtils.toString(httpResponse.getEntity());
			Log.i("mylog","��������Ľ��01-->" + result);
		}
		return result;
		}
		else{
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				httpResponse = new DefaultHttpClient().execute(httpPost); 
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					result = EntityUtils.toString(httpResponse.getEntity());
					Log.i("mylog","��������Ľ��02-->" + result);
				}
			} catch (Exception e) {
				// TODO: handle exception
				result = null;

				
			}

			return result;

		}
		

	}

	
}
