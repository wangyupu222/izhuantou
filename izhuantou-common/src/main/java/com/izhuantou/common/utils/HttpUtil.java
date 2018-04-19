package com.izhuantou.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送HTTP的工具类
 *
 * @author fucheng
 * @date 2018-01-17
 */
public class HttpUtil {

    private final static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 执行HTTP GET方法
     *
     * @param connectTimeOut
     *            链接超时时间（单位:ms）
     * @param readDataTimeOut
     *            读取超时时间（单位:ms）
     * @param url
     *            http的url前缀
     * @param params
     *            http的参数
     */
    public static String executeGet(int connectTimeOut, int readDataTimeOut, String url, Map<String, String> params)
	    throws IOException {
	StringBuffer urlBuffer = new StringBuffer(url);
	boolean flag = url.contains("?");

	if (params != null) {
	    for (String key : params.keySet()) {
		String value = params.get(key);
		if (StringUtil.isEmpty(value))
		    continue;
		if (flag) {
		    urlBuffer.append("&");
		} else {
		    urlBuffer.append("?");
		    flag = true;
		}
		urlBuffer.append(key).append("=").append(URLEncoder.encode(params.get(key), "UTF-8"));
	    }
	}
	log.info("executeGet,urlBuffer={}", urlBuffer);
	HttpClient httpClient = HttpClientBuilder.create().build();
	RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeOut)
		.setSocketTimeout(readDataTimeOut).build();
	HttpGet httpGet = new HttpGet(urlBuffer.toString());

	httpGet.setConfig(config);
	httpGet.getRequestLine();

	long start = System.currentTimeMillis();
	HttpResponse response = null;
	String result = "";
	response = httpClient.execute(httpGet);
	int statusCode = response.getStatusLine().getStatusCode();

	HttpEntity respEntity = response.getEntity();
	if (respEntity != null) {
	    respEntity = new BufferedHttpEntity(respEntity);
	    InputStream in = respEntity.getContent();
	    result = inputStream2String(in);
	    in.close();
	}
	if (statusCode != 200) {
	    long cost = System.currentTimeMillis() - start;
	    log.info("executeGet,cost:{},urlBuffer={},result={}", cost, urlBuffer, result);
	    throw new IOException("调用URL返回错误状态.statusCode=" + statusCode + ",url=" + url + ",result=" + result);
	}
	long cost = System.currentTimeMillis() - start;
	log.info("executeGet,cost:{},urlBuffer={}", cost, urlBuffer);
	return result;
    }

    /**
     * 执行HTTP POST方法
     *
     * @param connectTimeOut
     *            链接超时时间（单位:ms）
     * @param readDataTimeOut
     *            读取超时时间（单位:ms）
     * @param url
     *            http的url前缀
     * @param params
     *            http的参数
     */
    public static String executePost(int connectTimeOut, int readDataTimeOut, String url, Map<String, String> params)
	    throws IOException {
	String paramString = JsonUtil.toStr(params);
	log.info("executePost,url={},paramString={}", url, paramString);
	HttpClient httpClient = HttpClientBuilder.create().build();
	RequestConfig config = RequestConfig.custom().setConnectTimeout(connectTimeOut)
		.setSocketTimeout(readDataTimeOut).build();
	MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
	if (params != null) {
	    for (String key : params.keySet()) {
		multipartEntityBuilder.addTextBody(key, params.get(key));
	    }
	}

	HttpPost httppost = new HttpPost(url);
	httppost.setEntity(multipartEntityBuilder.build());
	httppost.setConfig(config);

	HttpResponse response = null;
	String result = "";
	long start = System.currentTimeMillis();
	response = httpClient.execute(httppost);
	int statusCode = response.getStatusLine().getStatusCode();

	HttpEntity respEntity = response.getEntity();
	if (respEntity != null) {
	    respEntity = new BufferedHttpEntity(respEntity);
	    InputStream in = respEntity.getContent();
	    result = inputStream2String(in);
	    in.close();
	}
	if (statusCode != 200) {
	    long cost = System.currentTimeMillis() - start;
	    log.info("executePost,cost:{},url={},paramString={},result={}", cost, url, paramString, result);
	    throw new IOException("调用URL返回错误状态.statusCode=" + statusCode + ",url=" + url + ",result=" + result);
	}
	long cost = System.currentTimeMillis() - start;
	log.info("executePost,cost:{},url={},paramString={}", cost, url, paramString);

	return result;
    }

    /**
     * 打印http返回结果
     */
    public static void printResponse(HttpResponse response) throws IOException {
	System.out.println("HttpResponse.getStatusCode:" + response.getStatusLine().getStatusCode());
	System.out.println("HttpResponse.getResponseBody:" + getResponseBody(response));
	System.out.println("End");
    }

    /**
     * 获取ResponseBody的内容
     */
    private static String getResponseBody(HttpResponse response) throws IOException {
	StringBuilder builder = new StringBuilder();
	BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
	String line;
	while ((line = reader.readLine()) != null) {
	    builder.append(line);
	}
	return builder.toString();
    }

    /**
     * InputStream转换为String类型
     */
    private static String inputStream2String(InputStream in) throws IOException {
	StringBuffer out = new StringBuffer();
	byte[] b = new byte[4096];
	for (int n; (n = in.read(b)) != -1;) {
	    out.append(new String(b, 0, n));
	}
	return out.toString();
    }

}
