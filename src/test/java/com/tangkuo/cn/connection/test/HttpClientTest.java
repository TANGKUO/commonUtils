package com.tangkuo.cn.connection.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

	public static void main(String[] args) throws Exception {
		String url = "http://localhost:9080/tk-mapi/gw/router";
		// POST的URL
		HttpPost httppost = new HttpPost(url);
		// 建立HttpPost对象
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		// 建立一个NameValuePair数组，用于存储欲传送的参数
		params.add(new BasicNameValuePair("method", "tkpay.member.login"));
		params.add(new BasicNameValuePair("loginId", "88888"));
		params.add(new BasicNameValuePair("password", "88888"));
		params.add(new BasicNameValuePair("code", "88888"));
		// 添加参数
		httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		// 设置编码
		HttpResponse response = new DefaultHttpClient().execute(httppost);
		// 发送Post,并返回一个HttpResponse对象
		if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
			String result = EntityUtils.toString(response.getEntity());
			// 得到返回的字符串
			System.err.println(result);
			// 打印输出
		}
	}
}
