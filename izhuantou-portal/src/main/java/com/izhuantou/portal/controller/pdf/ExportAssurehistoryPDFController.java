package com.izhuantou.portal.controller.pdf;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.izhuantou.portal.pdf.HtmlToPdf;

@Controller
@RequestMapping("pdf")
public class ExportAssurehistoryPDFController {

    // 业务逻辑现将jsp页面转换成为html页面，然后再将生成的html页面转换为PFD文件
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String index() {
	return "testPdf";
    }

    /*
     * 将html转成pdf
     * 
     * @param request
     * 
     * @param response
     * 
     * @return
     */
    @RequestMapping(value = "/changetopdf")
    @ResponseBody
    public ModelAndView exportAssurehistoryPDF(HttpServletRequest request, HttpServletResponse response) {
	try {
	    request.setCharacterEncoding("UTF-8");
	} catch (UnsupportedEncodingException e2) {
	    e2.printStackTrace();
	}
	String h = request.getParameter("htmlContent");
	// jsp生成html页面
	String pPath = this.writeHtmlToFile(h, "assurehistory");
	String typePaht = UUID.randomUUID().toString();
	HtmlToPdf.convert(pPath + "/assurehistory.html", "F:" + typePaht + ".jpg");
	try {
	    response.setContentType("application/pdf");
	    response.addHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("测试.pdf", "UTF-8"));
	    File file = new File("F:" + typePaht + ".jpg");
	    InputStream fis = new BufferedInputStream(new FileInputStream(file));
	    byte[] buffer = new byte[fis.available()];
	    fis.read(buffer);
	    fis.close();
	    OutputStream os = response.getOutputStream();
	    os.write(buffer);
	    os.flush();
	    os.close();

	} catch (UnsupportedEncodingException e1) {
	    e1.printStackTrace();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return null;
    }

    /**
     * 将内容写入html文件
     * 
     * @param content
     * @param htmlName
     * @return
     */
    private String writeHtmlToFile(String content, String htmlName) {
	String path = "";
	try {
	    // 获取资源路径
	    path = URLDecoder.decode("F:", "utf-8");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	System.out.println(path);
	content = content.replace("<div class=\"box\" style=\"height:430px;overflow:auto\">",
		"<div class=\"box\" style=\"overflow:auto\">");

	content = content.replace("button", "hidden").replace("HEIGHT: 430px", "");
	File file = new File(path + htmlName + ".html");
	BufferedWriter bw = null;
	try {
	    bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
	    bw.write("<!DOCTYPE html><html lang=\"en\">");
	    bw.write(content);
	    bw.write("</html>");
	    bw.flush();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (bw != null) {
		try {
		    bw.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
	return file.getParent();
    }
}
