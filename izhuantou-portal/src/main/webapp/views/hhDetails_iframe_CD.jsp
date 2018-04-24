<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/CapitalDetailed.css">
<link rel="stylesheet" href="/css/zoom.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<!--日期时间-->
<script language="javascript" type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">


/**详情**/

.boxxqjs{ overflow:hidden;  width:99%; margin:auto; margin-top:40px;}
.title-p { font-size:16px; color:#489ae3; line-height:30px; overflow:hidden;}
.title-p span{ display: inline-block;background-color:#489ae3;  height:17px; width:6px; background-color:"#489ae3"; float:left; margin-top:7px; margin-right:9px;!important;}
.boxxqjs p.p-content{ font-size:14px; line-height:22px; margin:10px auto; width:97%; }
.messagebox{ overflow:hidden;}
.messagebox dl{  float:left; margin-left:5%; text-align:left; font-size:14px; line-height:30px; color:#333333; margin-top:15px;}
.messagebox dl:first-child{ margin-left:18px;}
.messagebox dl .work_city{width: 135px;line-height: 20px;display: inline-block;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;position: relative;top: 4px;}
.boxshehe{  width:96%; font-size:14px; margin:auto; margin-top:20px;margin-bottom:20px; border-top:1px solid #e5e5e5; border-left:1px solid #e5e5e5;}
.boxshehe .th div{ background-color:#55b0fd; border-right:1px solid #e5e5e5; color:#fff;}
.thbox{ height:38px; overflow:hidden; line-height:38px; text-align:center;border-bottom:1px solid #e5e5e5;}
.thbox div{ font-size:14px; color:#333333; border-right:1px solid #e5e5e5; float:left; }
.thbox div.box01{ width:10%; height:38px;	}
.thbox div.box02{ width:80%;}
.thbox div.box03{ width:9.7%; cursor:pointer;}
.yunullimg { padding:20px 0px;}
.yunullimg img{ width:80%; margin:0px auto;}
.hhdetail{ width:95%; margin:0 auto;}
.bingtitle{ font-size: 20px;}
.commit-1{ margin: 0 auto;border-bottom:0; height: 30px;}
.commit-1 span{width: auto;font: 14px/20px "微软雅黑";}
.xyjk{margin: 0px 5px;}
.comtop-t{width:95%;  }
.media_body .tabhhdetail{margin: 0px auto;}
.media_body .tabhhdetail tr td{ line-height: 25px;}
.media_body .boxxqjs {margin-top: 4px;}
.media_body .messagebox dl {line-height: 26px;margin-top: 0px;}
</style>
</head>
<body style="width:100%;min-width:100%; background:#fff;">
<% try{ %>		
		<div class="comtop-t">
		  <a href="agreement_z.jsp?contractType=16" target="_blank" class="xyjk">《以租代购协议模板》</a>
		 
		<p class="commit-1"><span>借款编号：</span></p>
		</div>
		<div class="hhdetail">
          <div class="bingtitle hide"></div>
          <table class="tabhhdetail" border="0" cellpadding="0" cellspacing="0">
          	<tr class="dataMain">
          		<td></td>
          		<td></td>
          		<%-- <td><%=request.getAttribute("repaymentType") %></td> --%>
          		<td>等本等息</td>
          		<td></td>
          		<td></td>
          	</tr>
          	<tr>
          		<td>借款总额</td>
          		<td>还款进度</td>
          		<td>还款方式</td>
          		<td>还款状态</td>
          		<td>借款利率</td>
          	</tr>
          </table>
          
          <div class="boxxqjs">
                        	<div class="title-p"><span></span>标的详情</div>
                            <p class="p-content"></p>
                        </div>
                        
                        <div class="boxxqjs">
                        	<div class="title-p"><span></span>借款人信息</div>
                            <div class="messagebox messagebox02" style="min-height: 150px;">
                            	<dl>
                                	<dd>姓名：<span>
                                	                          	
                                	</span></dd>
                                    <dd>性别：<span>
                                                                      
                                    </span></dd>
                                </dl>
                                <dl>
                                	<dd>身份证号：<span>
                                	                              	
                                	</span></dd>
                                    <dd>手机号：<span>
                                                                       
                                    </span></dd>
                                </dl>
                                <dl>
                                	<dd>户籍所在地：<span class="work_city">
                                    
                                    </span></dd>
                                    <dd>个人年收入：<span>
                                    
                                    </span></dd>
                                </dl>
                                <dl>
                                	<dd>最高学历：<span>
                                    
                                    </span></dd>
                                    <dd>年龄：<span>
                                                                      
                                    </span></dd> 
                                </dl>
                            </div>
                        </div>
                        <div class="boxxqjs">
                        	<div class="title-p"><span></span>风控审核信息</div>
                            <div class="information_pilu">
                            <div class="btn_left"></div>
                            <div class="informationImg_main">
                            <div class="informationImg_wrap">
                            </div>
                            </div>
                            <div class="btn_right"></div>
                            <div class="clear"></div>
                            </div>
                        </div>
          
        </div> 

<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<!--验证表单-->
<script src="/js/zoomHH.js?v=110"></script>
<script>
$(function(){
	var Request = new Object();
	Request = GetRequest2();
	var biddingOID=Request["biddingOID"];
	var syqs=Request["syqs"];
	var zqs=Request["zqs"];
	$.ajax({
        type: "get",
        url: "/portal/detial/hhtZZInfo/?OID="+biddingOID,
        dataType: "json",
        success: function(result){
        	
        	$(".dataMain td").eq(0).text((result.jkze).toFixed(2)+"元");
        	$(".dataMain td").eq(1).text(syqs+"/"+zqs+"个月");
        	$(".dataMain td").eq(2).text(result.hkfs);
        	$(".dataMain td").eq(3).text(result.hkzt);
        	$(".dataMain td").eq(4).text(result.nhll+"%");
        	
        	
        },
		error:function(result){    	
    	}
    });
	
	$.ajax({
        type: "get",
        url: "/portal/detial/exBorrowHHTCDZInfo/?OID="+biddingOID,
        dataType: "json",
        success: function(result){
        	console.log(result);
        	$(".messagebox02 dl").eq(0).find('dd').eq(0).find('span').text(result.realName);
        	$(".messagebox02 dl").eq(0).find('dd').eq(1).find('span').text(result.gender);
        	
        	$(".messagebox02 dl").eq(1).find('dd').eq(0).find('span').text(result.idCard);
        	$(".messagebox02 dl").eq(1).find('dd').eq(1).find('span').text(result.mobile);
        	
        	$(".messagebox02 dl").eq(2).find('dd').eq(0).find('span').text(result.hkszd);
        	$(".messagebox02 dl").eq(2).find('dd').eq(1).find('span').text(result.grysr+'元');
        	
        	$(".messagebox02 dl").eq(3).find('dd').eq(0).find('span').text(result.highesteduback);
        	$(".messagebox02 dl").eq(3).find('dd').eq(1).find('span').text(result.age+'岁');

        	$(".p-content").text(result.loanuse);
        	$(".commit-1 span").text("借款编号："+result.loanNumber);
        },
		error:function(result){    	
    	}
    });
    $.ajax({
        type: "get",
        url: "/portal/detial/auditCDHHTInfo/?OID="+biddingOID,
        dataType: "json",
        success: function(result){
        	console.log(result);
        	for(var i=0;i<result.length;i++){
        		var str='<div class="informationImg_box"><a href="/p2p/cn/com/hoonsoft/servlet/ServletAction?handleClassName=fileDownload&OID='+result[i].photoOID+'"><img src="/p2p/cn/com/hoonsoft/servlet/ServletAction?handleClassName=fileDownload&OID='+result[i].photoOID+'"><p class="psnr hide">'+result[i].nameCN+'</p></a></div>';
        		$(".informationImg_wrap").append(str);
        	}
        	$(".informationImg_wrap").append('<div class="clear"></div>');
        	
        	$("body").append('<script src="/js/zoomHH.js?v=110"><\/script>');
        },
		error:function(result){    	
    	}
    });
})



function GetRequest2() {
	  
	  var url = location.search; //获取url中"?"符后的字串
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      //alert(strs);
	      for(var i = 0; i < strs.length; i ++) {
	         theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
	      }
	   }
	   return theRequest;
	}

</script>
</html>
