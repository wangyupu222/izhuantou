package com.izhuantou.service.impl.tsign.utils;

import java.awt.Insets;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

/**
 * 
 * @author vastsea
 *
 */
public class HtmlToPdfUtil {

    private static final Logger LOG = LoggerFactory.getLogger(HtmlToPdfUtil.class);

    public static byte[] generatePDF(String content) {
	ByteArrayOutputStream os = null;
	try {
	    // FileOutputStream fos = new FileOutputStream(outputPDFFile);
	    os = new ByteArrayOutputStream();
	    PD4ML pd4ml = new PD4ML();
	    pd4ml.setPageInsets(new Insets(20, 10, 20, 10)); // 上左下右的空白
	    pd4ml.setHtmlWidth(900);
	    pd4ml.setPageSize(PD4Constants.A4); // A4竖向显示
						// pd4ml.changePageOrientation(PD4Constants.A4)横向显示
	    pd4ml.useTTF("java:fonts", true);
	    pd4ml.setDefaultTTFs("SimHei", "YouYuan", "SimSun"); // 字体
	    pd4ml.enableDebugInfo();
	    StringReader strReader = new StringReader(content);
	    pd4ml.render(strReader, os);
	    return os.toByteArray();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    LOG.error(e.getMessage());
	    throw new RuntimeException("找不到fonts字体包");
	} catch (IOException e) {
	    e.printStackTrace();
	    LOG.error(e.getMessage());
	    throw new RuntimeException("Html字符串转换成pdf失败");
	} finally {

	    try {
		if (os != null)
		    os.close();
	    } catch (IOException e) {
		e.printStackTrace();
		throw new RuntimeException("HtmlToPdfUtil中关闭流失败");
	    }
	}

    }

}
