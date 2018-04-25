<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/include/url.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/preother.css">
<Link rel="shortcut icon" href="<%=pathUrl %>/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>

<script src="/js/com.js"></script>
<%-- <script language="javascript" src="/js/menu.js"></script> --%>

<style>
.HHdet{ height:45px;   font-size:14px;}
.HHdet.tit{ background:#f3f7fb; height:35px; line-height:35px;}
.HHdet.value{ border-bottom:1px solid #eaeaea;}
.HHdet.value a{ color:#55b0fd; white-space:nowrap; overflow:hidden; text-overflow:ellipsis; display:block;}

.HHdet td.ddtit a {line-height: 20px;color: #333;}
tr.HHdet td{border-bottom:1px solid #eaeaea; text-align:center;height:35px;}
tr.HHdet.tit td{border-bottom:0;}
.seedetails{margin-top: 10px;}
.hhxiyi{ height:35px;}
.hhxiyi a{color:#55b0fd;}
.tableloanLest {
    height: 408px;
    overflow-y: auto;
    overflow-x: hidden;
}
</style>
</head>
<body style="width:100%;min-width:100%; background:#fff;">
<% try{ %>       
        <div class="seedetails">
        <div class="hhxiyi"><a  href="/portal/personal/agreement_zCenter.jsp?type=<%=request.getParameter("type")%>&OID=<%=request.getParameter("OID")%>&CashPoolOID=<%=request.getParameter("CashPoolOID")%>" target="_blank">《环环投服务协议》</a></div>       
        <table width="100%" cellpadding="0" cellspacing="0" algin="center">
        
        <tr class="rowbox02 tit">
        <td style="width:190px">借款名称</td>
        <td style="width:157px">借款金额（元）</td>
        <td style="width:141px">借款利率</td>
        <td style="width:115px">状态</td>       
        <td style="width:197px">协议</td>
        <td></td>        
        </tr>
        </table>
        <div class="tableloanLest">
        <table  width="100%" cellpadding="0" cellspacing="0" algin="center" class="">
        
        </table>
        </div>
        </div> 
  
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>
<script>
$(function(){
	var Request = new Object();
	Request = GetRequest2();
	var CashPoolOID=Request["CashPoolOID"];
	$.ajax({
        type: "get",//请求方式
        url: "/portal/mylend/hhhavingDetails?cashPoolOID="+CashPoolOID,//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        console.log(result);
        //资金流水留白
        for(var i=0;i<result.dataValue.length;i++){
        	var jkmc=result.dataValue[i].jkmc;
        	jkmc=jkmc.substring(0,3);
        	var str='<tr class="HHdet value"><td style="width:190px"><a title="ZTXXXX" >'+result.dataValue[i].jkmc+'</a></td><td style="width:157px">'+(result.dataValue[i].jkje).toFixed(2)+'</td><td style="width:141px">'+(result.dataValue[i].jkll).toFixed(2)+'%</td> <td class="ddtit" style="width:115px"><a>'+result.dataValue[i].yhnum+'/'+result.dataValue[i].term+'期</a></td>';
        	if(result.dataValue[i].productStatus=='2'){
        		if(jkmc=="ICD"){
        			if(result.dataValue[i].financetransfertype=="financetransfer"){
        				str+='<td style="width:197px"><a href="/portal/personal/agreement_zCenter.jsp?type=17&biOID='+result.dataValue[i].oid+'" target="_blank">《砖头网抵押债权转让协议》</a><a href="/portal/personal/agreement_zCenter.jsp?type=18&biOID='+result.dataValue[i].oid+'" target="_blank">《砖头网抵押债权预回购协议》</a></td>';
        			}else{
        				str+='<td style="width:197px"><a href="/portal/personal/agreement_zCenter.jsp?type=16&biOID='+result.dataValue[i].oid+'" target="_blank">《砖头网抵押借款协议》</a><a href="/portal/personal/agreement_zCenter.jsp?type=18&biOID='+result.dataValue[i].oid+'" target="_blank">《砖头网抵押债权预回购协议》</a></td>';
        			}
        		}else{
        			if(result.dataValue[i].financetransfertype=="financetransfer"){
        				str+='<td style="width:197px"><a href="/portal/personal/agreement_zCenter.jsp?type=7&biOID='+result.dataValue[i].oid+'" target="_blank">《砖头网债权转让协议》</a><a href="/portal/personal/agreement_zCenter.jsp?type=8&biOID='+result.dataValue[i].oid+'" target="_blank">《砖头网债权预回购协议》</a></td>';
        			}else{
        				str+='<td style="width:197px"><a href="/portal/personal/agreement_zCenter.jsp?type=6&biOID='+result.dataValue[i].oid+'" target="_blank">《砖头网借款协议》</a><a href="/portal/personal/agreement_zCenter.jsp?type=8&biOID='+result.dataValue[i].oid+'" target="_blank">《砖头网债权预回购协议》</a></td>';
        			}
        		}
        	}else{
        		str+='<td style="width:197px"></td></tr>';
        	}
        	str+='';
        	$(".tableloanLest table").append(str);
        }
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
