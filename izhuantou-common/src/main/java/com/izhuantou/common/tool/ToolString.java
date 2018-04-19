/* Generated by Together */
package com.izhuantou.common.tool;

import java.util.Map;
import java.util.UUID;

/**
 * 字符窜工具
 * 
 * @author zhangchi
 * @version 1.0
 */
public abstract class ToolString {
    public static final String UTF_8 = "UTF-8";

    public static final String ISO = "ISO-8859-1";

    public static final String GBK = "GBK";

    /**
     * 替换字符串中的数据。<br>
     * !#-字符-#!<br>
     * !$-数字-$!<br>
     * !:-日期**yyyy-mm-dd-:!<br>
     * !%-r-%! 回车<br>
     * !%-n-%! 换行
     * 
     * @return 返回替换后的字符串
     * @param strOld
     *            要替换的字符串
     * @param objData
     *            要替换的数据
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     */
    public static String replaceContent(String strOld, Object objData) throws Exception {
	try {
	    if (objData == null || strOld == null) {
		return strOld;
	    }
	    String strNew = strOld;
	    int iStartPlace;
	    // 回车替换
	    strNew = strNew.replaceAll("!%-r-%!", "\r");
	    // 换行替换
	    strNew = strNew.replaceAll("!%-n-%!", "\n");
	    // 字符替换
	    iStartPlace = strNew.indexOf("!#-");
	    while (iStartPlace != -1) {
		int iEndPlace = strNew.indexOf("-#!", iStartPlace);
		String strReplace = strNew.substring(iStartPlace + 3, iEndPlace);
		Object objContent = null;

		objContent = ((Map) objData).get(strReplace);

		String strContent = null;
		// 无内容
		if (objContent == null) {
		    strContent = "";
		} else {
		    strContent = objContent.toString();
		}

		strContent = strContent.replaceAll("\\\\", "\\\\\\\\");
		strContent = strContent.replaceAll("\\$", "\\\\\\$");

		strNew = strNew.replaceAll("!#-" + strReplace + "-#!", strContent);

		iStartPlace = strNew.indexOf("!#-");
	    }
	    // 数字替换
	    iStartPlace = strNew.indexOf("!$-");
	    while (iStartPlace != -1) {
		int iEndPlace = strNew.indexOf("-$!", iStartPlace);
		String strReplace = strNew.substring(iStartPlace + 3, iEndPlace);
		Object objContent = null;

		objContent = ((Map) objData).get(strReplace);

		String strContent = null;
		// 无内容
		if (objContent == null) {
		    strContent = "";
		} else {
		    strContent = objContent.toString();
		}

		strContent = strContent.replaceAll("\\\\", "\\\\\\\\");
		strContent = strContent.replaceAll("\\$", "\\\\\\$");

		strNew = strNew.replaceAll("!\\$-" + strReplace + "-\\$!", strContent);

		iStartPlace = strNew.indexOf("!$-");
	    }
	    // 日期替换
	    iStartPlace = strNew.indexOf("!:-");
	    while (iStartPlace != -1) {
		// int iFormatPlace = strNew.indexOf("**", iStartPlace);
		// if (iStartPlace < iFormatPlace && iFormatPlace < iEndPlace)
		// {
		// } else
		// {
		//
		// }
		iStartPlace = strNew.indexOf("!:-");
	    }
	    return strNew;
	} catch (Throwable cause) {
	    throw new Exception("替换内容失败", cause);
	}
    }

    /**
     * 替换字符串中的数据。<br>
     * !#-字符-#!<br>
     * !$-数字-$!<br>
     * !:-日期**yyyy-mm-dd-:!<br>
     * !%-r-%! 回车<br>
     * !%-n-%! 换行
     * 
     * @return 返回替换后的字符串
     * @param strOld
     *            要替换的字符串
     * @param mData
     *            要替换的数据
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     */
    public static String replaceContent(String strOld, Map mData) throws Exception {
	try {
	    return ToolString.replaceContent(strOld, (Object) mData);
	} catch (Throwable cause) {
	    throw new Exception("替换内容失败", cause);
	}
    }

    /**
     * 在字符的左边进行填充成固定长度字符串
     * 
     * @return 新的字符串
     * @param strOld
     *            老字符串
     * @param strFill
     *            填充字符串
     * @param iNewLength
     *            新字符串长度
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     */
    public static String addLengthAtLeft(String strOld, String strFill, int iNewLength)

    {

	if (strOld == null || strFill == null) {
	    return null;
	}
	StringBuffer strbOld = new StringBuffer(strOld);
	int iOldLength = ToolString.gainRealLenght(strOld);
	int iFillLength = ToolString.gainRealLenght(strFill);
	for (int i = 0; i < (iNewLength - iOldLength) / iFillLength; i++) {
	    strbOld.insert(0, strFill);
	}
	return strbOld.toString();

    }

    /**
     * 获取字串真实长度（中文算两个，英文算一个）
     * 
     * @return 字串长度
     * @param strOne
     *            一个字符串
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     */
    public static int gainRealLenght(String strOne) {
	int iCount = 0;
	int iLength = strOne.length();
	for (int i = 0; i < iLength; i++) {
	    String strTemp = strOne.substring(i, i + 1);
	    byte bTemp[] = strTemp.getBytes();
	    if (bTemp[0] < 0) {
		iCount += 2;
	    } else {
		iCount++;
	    }
	}
	return iCount;
    }

    /**
     * 根据UUID产生机制生成UUID
     * 
     * @return 生成完成的UUID
     * @exception ExceptionOperateToolFail
     *                使用工具失败异常
     * @param objClass
     *            生成第三部分所要的实例
     */
    public static String gainUUID(Object objClass) {
	try {

	    return UUID.randomUUID().toString().replace("-", "");
	} catch (Throwable cause) {
	    return null;
	}
    }

}