package com.izhuantou.portal.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class TestFile {

    public static void main(String[] args) {
	int bytesum = 0;
	int byteread = 0;
	try {
	    File oldfile = new File("D:/pdf/2.png");
	    if (oldfile.exists()) { // 文件存在时
		InputStream inStream = new FileInputStream("D:/pdf/2.png"); // 读入原文件
		FileOutputStream fs = new FileOutputStream("D:/pdf/3.png");
		byte[] buffer = new byte[1444];
		int length;
		while ((byteread = inStream.read(buffer)) != -1) {
		    bytesum += byteread; // 字节数 文件大小
		    System.out.println(bytesum);
		    fs.write(buffer, 0, byteread);
		}
		inStream.close();

	    }
	} catch (Exception e) {

	}

    }
}
