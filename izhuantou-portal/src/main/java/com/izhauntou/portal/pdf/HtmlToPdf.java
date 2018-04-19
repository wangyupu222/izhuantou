package com.izhauntou.portal.pdf;

import java.io.File;

public class HtmlToPdf {

    /**
     * html转pdf
     * 
     * @param srcPath
     *            html路径，可以是硬盘上的路径，也可以是网络路径
     * @param destPath
     *            pdf保存路径
     * @return 转换成功返回true
     */
    private static final String topdfTool = "F:/pdfFile/wkhtmltopdf/bin/wkhtmltoimage.exe";

    public static boolean convert(String srcPath, String destPath) {
	// wkhtmltopdf在系统中的路径

	File file = new File(destPath);
	File parent = file.getParentFile();
	// 如果pdf保存路径不存在，则创建路径
	if (!parent.exists()) {
	    parent.mkdirs();
	}
	StringBuilder cmd = new StringBuilder();
	cmd.append(topdfTool);
	cmd.append(" ");
	cmd.append(srcPath);
	cmd.append(" ");
	cmd.append(destPath);
	boolean result = true;
	try {
	    Process proc = Runtime.getRuntime().exec(cmd.toString());
	    HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
	    HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());
	    error.start();
	    output.start();
	    proc.waitFor();
	} catch (Exception e) {
	    result = false;
	    e.printStackTrace();
	}

	return result;
    }
}