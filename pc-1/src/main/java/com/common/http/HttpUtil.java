package com.common.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.config.Config;

@Component
public class HttpUtil {

	@Autowired /// 按类型
	private Config config;// 注入配置信息

	private Log logger = LogFactory.getLog(getClass());// 日志工具

	public String get(String url) {
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(config.getApi_url() + url);
		HttpResponse response;
		String json = "";
		try {
			response = client.execute(get);
			json = EntityUtils.toString(response.getEntity());

		} catch (ClientProtocolException e) {
			logger.error("请求api时发生ClientProtocolException异常");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("请求api时发生IOException异常");
			e.printStackTrace();
		} finally {
			get.releaseConnection();// 释放连接
		}
		return json;
	}

	public String post(String url, Map<String, String> map) {
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(config.getApi_url() + url);
		HttpResponse response;
		String json = "";
		try {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			HttpEntity entityPost = new UrlEncodedFormEntity(list, "UTF-8");
			post.setEntity(entityPost);
			response = client.execute(post);
			json = EntityUtils.toString(response.getEntity());

		} catch (ClientProtocolException e) {
			logger.error("请求api时发生ClientProtocolException异常");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("请求api时发生IOException异常");
			e.printStackTrace();
		} finally {
			post.releaseConnection();// 释放连接
		}
		return json;
	}

}
