
package com.izhuantou.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.izhuantou.common.tool.ToolMath;

public final class ImageUtil {
    public static Object[] createImage() throws Exception {
	int iValidateCode = 0;
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
		'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	String cstr = "";
	for (int i = 0; i < 4; i++) {
	    iValidateCode = ToolMath.gainRandomInt(0, 34);
	    cstr += codeSequence[iValidateCode];
	}
	// 在内存中创建图象
	int iWidth = 60, iHeight = 20;
	BufferedImage image = new BufferedImage(iWidth, iHeight, BufferedImage.TYPE_INT_RGB);
	// 获取图形上下文
	Graphics g = image.getGraphics();
	// 设定背景色
	// g.setColor(ImagesUtil.gainRandomColor(200, 250));
	g.setColor(new Color(179, 213, 240));

	g.fillRect(0, 0, iWidth, iHeight);
	// 设定字体
	g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
	g.setColor(ImageUtil.gainRandomColor(120, 150));
	for (int i = 0; i < 50; i++) {
	    int x = ToolMath.gainRandomInt(0, iWidth);
	    int y = ToolMath.gainRandomInt(0, iHeight);
	    int xl = ToolMath.gainRandomInt(0, 5);
	    int yl = ToolMath.gainRandomInt(0, 5);
	    g.drawLine(x, y, x + xl, y + yl);
	}
	// 取随机产生的认证码
	for (int i = 0; i < cstr.length(); i++) {
	    String strChar = cstr.substring(i, i + 1);
	    // 将认证码显示到图象中
	    g.setColor(ImageUtil.gainRandomColor(50, 80));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
	    g.drawString(strChar, 13 * i + 6, 16);
	}
	return new Object[] { cstr, image };

    }

    /**
     * 获取随机颜色
     * 
     * @return 随机颜色
     * @param iBeginColor
     *            一个数值
     * @param iEndColor
     *            一个数值
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     */
    public static Color gainRandomColor(int iBeginColor, int iEndColor) throws Exception {
	try {
	    if (iBeginColor > 255)
		iBeginColor = 255;
	    if (iEndColor > 255)
		iEndColor = 255;
	    int iRed = ToolMath.gainRandomInt(iBeginColor, iEndColor);
	    int iGreen = ToolMath.gainRandomInt(iBeginColor, iEndColor);
	    int iBlue = ToolMath.gainRandomInt(iBeginColor, iEndColor);
	    return new Color(iRed, iGreen, iBlue);
	} catch (Throwable cause) {
	    throw new Exception("获取随机颜色失败", cause);
	}
    }

    public static void main(String[] args) {
	try {
	    Object[] objs = createImage();
	    BufferedImage image = (BufferedImage) objs[1];
	    // /home/soft01/1.png
	    OutputStream os = new FileOutputStream("d:/1.png");
	    ImageIO.write(image, "png", os);
	    os.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
