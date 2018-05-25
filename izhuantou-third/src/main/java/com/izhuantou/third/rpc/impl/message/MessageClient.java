package com.izhuantou.third.rpc.impl.message;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.izhuantou.common.tool.SSLClient;

public class MessageClient {

    private static HttpClient httpClient;

    public static Map<String, String> sendSMSContent(Map<String, String> msm) throws Exception {
	try {
	    httpClient = new SSLClient();

	    String extNumber = "";
	    String planSendTime = "";
	    String url=msm.get("url");
	    HttpPost post = new HttpPost(url);
	    post.setHeader("Content-Type", "application/soap+xml; charset=UTF-8");
	    String soapData = buildSoapData(msm.get("userName"), msm.get("password"), msm.get("mobiles"),
		    msm.get("content"), extNumber, planSendTime);
	    StringEntity se = new StringEntity(soapData, "UTF-8");
	    post.setEntity(se);
	    HttpResponse response = httpClient.execute(post);

	    System.out.println(response.getStatusLine());
	    HttpEntity entity = response.getEntity();
	    // 转换成XML
	    String returnString = EntityUtils.toString(entity, "UTF-8");

	    SOAPMessage msg = formatSoapString(returnString);
	    msg.writeTo(System.out);
	    SOAPBody body = msg.getSOAPBody();
	    Map<String, String> map = new HashMap<String, String>();
	    Iterator<SOAPElement> iterator = body.getChildElements();
	    parseSoap(iterator, map);
	    String message = map.get("Description");

	    /*
	     * System.out.println("返回信息提示：" + map.get("Description"));
	     * System.out.println("返回状态为：" + map.get("StatusCode"));
	     * System.out.println("返回余额：" + map.get("Amount"));
	     * System.out.println("返回本次任务ID：" + map.get("MsgId"));
	     * System.out.println("返回成功短信数：" + map.get("SuccessCounts"));
	     */

	    return map;
	} catch (Throwable cause) {
	    throw new Exception(cause.getMessage());
	}
    }

    private static SOAPMessage formatSoapString(String soapString) {
	MessageFactory msgFactory;
	try {
	    msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
	    SOAPMessage reqMsg = msgFactory.createMessage(new MimeHeaders(),
		    new ByteArrayInputStream(soapString.getBytes("UTF-8")));
	    reqMsg.saveChanges();
	    return reqMsg;
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    private static void parseSoap(Iterator<SOAPElement> iterator, Map<String, String> map) {
	while (iterator.hasNext()) {
	    SOAPElement element = iterator.next();
	    if ("SendSmsResult".equals(element.getParentElement().getNodeName())) {
		map.put(element.getNodeName(), element.getValue());
	    }
	    if (null == element.getValue() && element.getChildElements().hasNext()) {
		parseSoap(element.getChildElements(), map);
	    }
	}
    }

    private static String buildSoapData(String userName, String password, String mobiles, String content,
	    String extNumber, String planSendTime) throws UnsupportedEncodingException {
	StringBuffer soap = new StringBuffer();

	soap.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
	soap.append(
		"<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">");
	soap.append("<soap12:Body>");
	soap.append("<SendSms xmlns=\"http://www.yysms.com/\">");
	soap.append("<userName>" + userName + "</userName>");
	soap.append("<password>" + password + "</password>");
	soap.append("<sms>");
	soap.append("<Msisdns>" + mobiles + "</Msisdns>");
	soap.append("<SMSContent><![CDATA[" + content + "]]></SMSContent>");
	soap.append("<ExtNumber>" + extNumber + "</ExtNumber>");
	soap.append("<PlanSendTime xsi:nil='true'>" + planSendTime + "</PlanSendTime>");
	soap.append("</sms>");
	soap.append("</SendSms>");
	soap.append("</soap12:Body>");
	soap.append("</soap12:Envelope>");
	return soap.toString();
    }

}
