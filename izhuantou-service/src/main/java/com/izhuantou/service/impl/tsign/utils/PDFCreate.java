package com.izhuantou.service.impl.tsign.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * @author vastsea
 *
 */
public class PDFCreate {

    // 利用模板生成pdf
    public static byte[] fillTemplate(String templatePath, String nameCN, String mobile, String name, String cardNO) {
	// 模板路径
	// String templatePath = "pdf/test1.pdf";
	// 生成的新文件路径
	// String newPDFPath = "pdf/test2.pdf";
	PdfReader reader;
	// FileOutputStream out;
	ByteArrayOutputStream bos;
	PdfStamper stamper;
	try {
	    // out = new FileOutputStream(newPDFPath);// 输出流
	    reader = new PdfReader(templatePath);// 读取pdf模板
	    bos = new ByteArrayOutputStream();
	    stamper = new PdfStamper(reader, bos);
	    AcroFields form = stamper.getAcroFields();// PDF表单

	    form.setField("nameCN", nameCN);
	    form.setField("mobile", mobile);
	    form.setField("name", name);
	    form.setField("cardNO", cardNO);
	    /*
	     * String[] str = {"15068298235","15068298235","421125199103152019",
	     * "高高高"}; int i = 0; java.util.Iterator<String> it =
	     * form.getFields().keySet().iterator(); while (it.hasNext()) {
	     * String textName = it.next().toString(); form.setField(textName,
	     * str[i++]); }
	     */
	    stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
	    stamper.close();
	    return bos.toByteArray();
	    /*
	     * Document doc = new Document(); PdfCopy copy = new PdfCopy(doc,
	     * out); doc.open(); int count = reader.getNumberOfPages();//原PDF总页数
	     * for(int j=1;j<=count;j++){ PdfImportedPage importPage =
	     * copy.getImportedPage(new PdfReader(bos.toByteArray()), j);
	     * copy.addPage(importPage); }
	     * 
	     * doc.close();
	     */

	} catch (IOException e) {
	    e.printStackTrace();
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
	return null;

    }

    public static void creatpdf() {
	String pdfPath = "pdf/test11.pdf";
	String filePath = "pdf/test.html";
	Document document = new Document();
	try {
	    StyleSheet st = new StyleSheet();
	    st.loadTagStyle("body", "leading", "16,0");
	    st.loadStyle("pdfFont", "face", "times-roman");
	    PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
	    document.open();
	    Reader reader = new StringReader("<p>协议编号:ZRXY-201707021</p>" +

		    "<p><strong>债权转让</strong><strong>协议</strong></p>" +

		    "<p><strong>甲方（转让方）：ZTW13820731222</strong></p>" +

		    "<p>地址：天津市</p>" +

		    "<p>姓名：张金</p>" +

		    "<p>身份证号：120103198112041412</p>" +

		    "<p><strong>乙方（受让方）：详见附表1</strong></p>" +

		    "<p><strong>丙方（</strong><strong>居间</strong><strong>服务方）：</strong><strong>爱砖头（天津）网络科技有限公司</strong></p>"
		    +

		    "<p><strong>注册号：91120116MA05KNAA9Y</strong></p>" +

		    "<p>地址：&nbsp;天津市滨海高新区华苑产业区海泰华科三路1号5-2-401-3</p>" +

		    "<p>法定代表人：王晨松</p>" +

		    "<p>&nbsp;</p>" +

		    "<p><strong>鉴于：</strong></p>" +

		    "<p>1、丙方系一家根据中国法律注册成立且合法存续的有限责任公司，拥有砖头网（域名为www.izhuantou.com，以下简称&ldquo;丙方平台&rdquo;）的所有权、经营权、管理权和提供互联网信息服务的资质。丙方受甲乙方委托，为债权转让提供居间服务。</p>"
		    +

		    "<p>2、甲方提供有实力的担保方，为其所有义务和责任向乙丙方提供连带责任保证担保。</p>" +

		    "<p>现甲乙丙三方本着互惠互利的原则，经过平等协商就乙方受让甲方债权相关事宜特签订本《债权转让协议》（以下简称&ldquo;本协议&rdquo;），并承诺共同遵守。</p>" +

		    "<p><strong>第一条 释义</strong></p>" +

		    "<p>1.1&nbsp;<strong>债务人：</strong>指有一定的资金需求，经过甲方严格审核通过后，与甲方就借款一事达成共识的具有完全民事权利/行为能力的自然人或根据中国法律注册成立且合法存续的企业。</p>"
		    +

		    "<p>1.2&nbsp;<strong>基础合同：</strong>指甲方与债务人签订的由甲方向债务人提供借款的相关合同或协议。</p>" +

		    "<p>1.3&nbsp;<strong>债权：</strong>指甲方根据与债务人达成的基础合同，对债务人享有的到期收取本息及其他相关款项的权利。</p>" +

		    "<p>1.4&nbsp;<strong>债权资产</strong>：指单笔债权和债权资产包的统称。</p>" +

		    "<p>1.5&nbsp;<strong>债权资产转让价款：</strong>指甲方在丙方平台上公布的转让其债权资产的金额。</p>" +

		    "<p>1.6&nbsp;<strong>债权资产受让支付价款：</strong>指乙方受让债权资产所支付的对价。</p>" +

		    "<p>1.7&nbsp;<strong>分账户：</strong>指在丙方第三方支付账户中为甲方、乙方设立的分账户，甲方、乙方可以通过第三方支付机构及/或其他支付通道对各自分账户进行充值、提现。</p>"
		    +

		    "<p>1.8&nbsp;<strong>丙方第三方支付账户：</strong>以丙方名义在与丙方合作的第三方支付机构里开设的支付账户。</p>" +

		    "<p>1.9&nbsp;<strong>丙方平台银行监管账户：</strong>以丙方名义在与丙方合作的监管银行开设的独立于丙方自有资金的监管账户。</p>" +

		    "<p><strong>第二条 转让的债权资产详情及还款计划</strong></p>" +

		    "<p><strong>2.1转让的债权资产详情</strong></p>" +

		    "<table border=\"1\" style=\"width:90%\">" + "<tbody>" + "	<tr>" + "		<td>"
		    + "		<p><strong>转让的债权资产本金金额</strong></p>" + "		</td>"
		    + "		<td>13593.86</td>" + "		<td>"
		    + "		<p><strong>债权转让垫付利息</strong></p>" + "		</td>" + "		<td>28.61</td>"
		    + "		<td>" + "		<p><strong>债权资产转让日期</strong></p>" + "		</td>"
		    + "		<td>2017-07-02</td>" + "	</tr>" + "	<tr>" + "		<td>"
		    + "		<p><strong>借款利率</strong></p>" + "		</td>"
		    + "		<td>12.80<strong>%</strong></td>" + "		<td>"
		    + "		<p><strong>还款方式</strong></p>" + "		</td>"
		    + "		<td colspan=\"3\">一次性还本付息</td>" + "	</tr>" + "	<tr>" + "		<td>"
		    + "		<p><strong>原担保措施</strong></p>" + "		</td>"
		    + "		<td colspan=\"6\">" + "		<p><strong>债务人正常还款</strong></p>" + "		</td>"
		    + "	</tr>" + "	<tr>" + "		<td>" + "		<p><strong>手续费</strong></p>"
		    + "		</td>" + "			<td colspan=\"6\">0.00</td>" + "		</tr>"
		    + "		<tr>" + "			<td>"
		    + "			<p><strong>债权资产转让价款</strong></p>" + "			</td>"
		    + "			<td colspan=\"6\">13622.47</td>" + "		</tr>" + "	</tbody>"
		    + "</table>" +

		    "<p><strong>附表1：受让方信息</strong></p>" +

		    "<table border=\"1\" style=\"text-align:center; width:90%\">" + "<tbody>" + "	<tr>"
		    + "		<th style=\"width:18%x\">姓名</th>" + "		<th style=\"width:18%\">用户名</th>"
		    + "		<th style=\"width:18%\">转让本金（元）</th>"
		    + "		<th style=\"width:18%\">转让垫付利息（元）</th>" + "	<th style=\"width:18%\">转让价款（元）</th>"
		    + "</tr>" + "	<tr>"
		    + "<td colspan=\"5\"><tr><td>孟**</td><td>177****3402</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>刘*</td><td>187****0161</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>陈*</td><td>131****0826</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>金**</td><td>152****1431</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>徐**</td><td>181****1917</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>邱**</td><td>159****3743</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>许**</td><td>151****1256</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>顾**</td><td>137****6456</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>许**</td><td>152****8882</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>张**</td><td>157****9908</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>杨**</td><td>170****0411</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>李**</td><td>155****7556</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>朱**</td><td>177****2942</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>白**</td><td>182****6155</td><td>621.16</td><td>1.31</td><td>622.47</td></tr></td>"
		    + "</tr>" + "</tbody>" + "</table>");
	    BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
	    Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
	    Paragraph t = new Paragraph();

	    List<Element> p = HTMLWorker.parseToList(reader, st);
	    for (int k = 0; k < p.size(); ++k) {
		t.setFont(FontChinese);
		t.add((Element) p.get(k));
	    }
	    document.add(t);
	    document.close();
	    System.out.println("文档创建成功");
	} catch (Exception e) {
	    e.printStackTrace();
	}
	/*
	 * Document doc = new Document(PageSize.A4); try { String htmlCode=
	 * "<!DOCTYPE html>"+ "<html>"+ "<head>"+ "<meta charset=\"UTF-8\">"+
	 * "<title>Insert title here</title>"+ "</head>"+ "<body>"+
	 * "<p>协议编号:ZRXY-201707021</p>"+
	 * 
	 * "<p><strong>债权转让</strong><strong>协议</strong></p>"+
	 * 
	 * "<p><strong>甲方（转让方）：ZTW13820731222</strong></p>"+
	 * 
	 * "<p>地址：天津市</p>"+
	 * 
	 * "<p>姓名：张金</p>"+
	 * 
	 * "<p>身份证号：120103198112041412</p>"+
	 * 
	 * "<p><strong>乙方（受让方）：详见附表1</strong></p>"+
	 * 
	 * "<p><strong>丙方（</strong><strong>居间</strong><strong>服务方）：</strong><strong>爱砖头（天津）网络科技有限公司</strong></p>"+
	 * 
	 * "<p><strong>注册号：91120116MA05KNAA9Y</strong></p>"+
	 * 
	 * "<p>地址：&nbsp;天津市滨海高新区华苑产业区海泰华科三路1号5-2-401-3</p>"+
	 * 
	 * "<p>法定代表人：王晨松</p>"+
	 * 
	 * "<p>&nbsp;</p>"+
	 * 
	 * "<p><strong>鉴于：</strong></p>"+
	 * 
	 * "<p>1、丙方系一家根据中国法律注册成立且合法存续的有限责任公司，拥有砖头网（域名为www.izhuantou.com，以下简称&ldquo;丙方平台&rdquo;）的所有权、经营权、管理权和提供互联网信息服务的资质。丙方受甲乙方委托，为债权转让提供居间服务。</p>"+
	 * 
	 * "<p>2、甲方提供有实力的担保方，为其所有义务和责任向乙丙方提供连带责任保证担保。</p>"+
	 * 
	 * "<p>现甲乙丙三方本着互惠互利的原则，经过平等协商就乙方受让甲方债权相关事宜特签订本《债权转让协议》（以下简称&ldquo;本协议&rdquo;），并承诺共同遵守。</p>"+
	 * 
	 * "<p><strong>第一条 释义</strong></p>"+
	 * 
	 * "<p>1.1&nbsp;<strong>债务人：</strong>指有一定的资金需求，经过甲方严格审核通过后，与甲方就借款一事达成共识的具有完全民事权利/行为能力的自然人或根据中国法律注册成立且合法存续的企业。</p>"+
	 * 
	 * "<p>1.2&nbsp;<strong>基础合同：</strong>指甲方与债务人签订的由甲方向债务人提供借款的相关合同或协议。</p>"+
	 * 
	 * "<p>1.3&nbsp;<strong>债权：</strong>指甲方根据与债务人达成的基础合同，对债务人享有的到期收取本息及其他相关款项的权利。</p>"+
	 * 
	 * "<p>1.4&nbsp;<strong>债权资产</strong>：指单笔债权和债权资产包的统称。</p>"+
	 * 
	 * "<p>1.5&nbsp;<strong>债权资产转让价款：</strong>指甲方在丙方平台上公布的转让其债权资产的金额。</p>"+
	 * 
	 * "<p>1.6&nbsp;<strong>债权资产受让支付价款：</strong>指乙方受让债权资产所支付的对价。</p>"+
	 * 
	 * "<p>1.7&nbsp;<strong>分账户：</strong>指在丙方第三方支付账户中为甲方、乙方设立的分账户，甲方、乙方可以通过第三方支付机构及/或其他支付通道对各自分账户进行充值、提现。</p>"+
	 * 
	 * "<p>1.8&nbsp;<strong>丙方第三方支付账户：</strong>以丙方名义在与丙方合作的第三方支付机构里开设的支付账户。</p>"+
	 * 
	 * "<p>1.9&nbsp;<strong>丙方平台银行监管账户：</strong>以丙方名义在与丙方合作的监管银行开设的独立于丙方自有资金的监管账户。</p>"+
	 * 
	 * "<p><strong>第二条 转让的债权资产详情及还款计划</strong></p>"+
	 * 
	 * "<p><strong>2.1转让的债权资产详情</strong></p>"+
	 * 
	 * "<table border=\"1\" style=\"width:90%\">"+ "<tbody>"+ "	<tr>"+
	 * "		<td>"+
	 * "		<p><strong>转让的债权资产本金金额</strong></p>"+ "		</td>"+
	 * "		<td>13593.86</td>"+ "		<td>"+
	 * "		<p><strong>债权转让垫付利息</strong></p>"+ "		</td>"+
	 * "		<td>28.61</td>"+ "		<td>"+
	 * "		<p><strong>债权资产转让日期</strong></p>"+ "		</td>"+
	 * "		<td>2017-07-02</td>"+ "	</tr>"+ "	<tr>"+
	 * "		<td>"+ "		<p><strong>借款利率</strong></p>"+
	 * "		</td>"+
	 * "		<td>12.80<strong>%</strong></td>"+ "		<td>"+
	 * "		<p><strong>还款方式</strong></p>"+ "		</td>"+
	 * "		<td colspan=\"3\">一次性还本付息</td>"+ "	</tr>"+
	 * "	<tr>"+ "		<td>"+
	 * "		<p><strong>原担保措施</strong></p>"+ "		</td>"+
	 * "		<td colspan=\"6\">"+
	 * "		<p><strong>债务人正常还款</strong></p>"+ "		</td>"+
	 * "	</tr>"+ "	<tr>"+ "		<td>"+
	 * "		<p><strong>手续费</strong></p>"+ "		</td>"+
	 * "			<td colspan=\"6\">0.00</td>"+ "		</tr>"+
	 * "		<tr>"+ "			<td>"+
	 * "			<p><strong>债权资产转让价款</strong></p>"+
	 * "			</td>"+
	 * "			<td colspan=\"6\">13622.47</td>"+
	 * "		</tr>"+ "	</tbody>"+ "</table>"+
	 * 
	 * 
	 * 
	 * "<p><strong>附表1：受让方信息</strong></p>"+
	 * 
	 * "<table border=\"1\" style=\"text-align:center; width:90%\">"+
	 * "<tbody>"+ "	<tr>"+
	 * "		<th style=\"width:18%x\">姓名</th>"+
	 * "		<th style=\"width:18%\">用户名</th>"+
	 * "		<th style=\"width:18%\">转让本金（元）</th>"+
	 * "		<th style=\"width:18%\">转让垫付利息（元）</th>"+
	 * "	<th style=\"width:18%\">转让价款（元）</th>"+ "</tr>"+ "	<tr>"+
	 * "<td colspan=\"5\"><tr><td>孟**</td><td>177****3402</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>刘*</td><td>187****0161</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>陈*</td><td>131****0826</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>金**</td><td>152****1431</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>徐**</td><td>181****1917</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>邱**</td><td>159****3743</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>许**</td><td>151****1256</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>顾**</td><td>137****6456</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>许**</td><td>152****8882</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>张**</td><td>157****9908</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>杨**</td><td>170****0411</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>李**</td><td>155****7556</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>朱**</td><td>177****2942</td><td>997.90</td><td>2.10</td><td>1000.00</td></tr><tr><td>白**</td><td>182****6155</td><td>621.16</td><td>1.31</td><td>622.47</td></tr></td>"
	 * + "</tr>"+ "</tbody>"+ "</table>"+
	 * 
	 * "</body>"+ "</html>"; PdfWriter.getInstance(doc, new
	 * FileOutputStream(pdfPath)); doc.open(); // 解决中文问题 BaseFont bfChinese
	 * = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
	 * BaseFont.NOT_EMBEDDED); Font FontChinese = new Font(bfChinese, 12,
	 * Font.NORMAL); Paragraph t = new Paragraph(htmlCode, FontChinese);
	 * doc.add(t); doc.close(); System.out.println("文档创建成功");
	 * }catch(Exception e) { e.printStackTrace(); }
	 */
    }

    public static void main(String[] args) {
	// fillTemplate("pdf/test1.pdf","欣哲","15068298235","15068298235","421125199103152019");
	creatpdf();
    }
}