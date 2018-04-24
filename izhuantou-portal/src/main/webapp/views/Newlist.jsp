<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网-我的出借</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/preother.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<!--<script src="/js/1-6-10.esl.js"></script>-->
<style>
.hide_liu{  width: 100%;  height: 500px;  text-align: center;  background:#fff url("images/hide_liu.jpg"); background-repeat: no-repeat; background-position:center center; display: none;  }
.cd-popup-container1, .cd-popup-container2, .cd-popup-container4, .cd-popup-container5, .cd-popup-container6, .cd-popup-containerh{width:500px;padding: 10px 5px;}
.xiyilist{text-align: left; line-height: 40px; padding: 20px;}
.xiyilist a{color:#55b0fd;}
a.transfer_ht {color:#04b277;}
table.ddttable tr.loanrow.loanrow01 td {
    width: 16.2%;
}
table.ddttable tr.loanrow.loanrow01 td a.new_trigger {
    display: block;
    line-height: 20px;
    margin-top: -20px;
    color: #55b0fd;
}
.manbiao_ti{
position: absolute;
right:50px;
top:-31px;
width:230px;
height:28px;
background-color:#d1d1d1;
text-align:center;
color:#fff;
border-radius: 15px;
font-size:12px;
line-height:28px;
}
</style>
</head>
<body>
<% try{ %>

<!--common header-->
<%@ include file="header.jsp" %>
<!--弹框-->
<div class="cd-popup6">
<div class="cd-popup-container1">
<!--充值-->
	<div class="comnei ">
        <a class="close cd-popup-close"></a>
        <p class="commit-1"><span>协议列表</span></p>
        <iframe src="AgreementList_iframe.jsp?DOID=&biOID=" width="99%" height="160" id="iframepage" name="iframe" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
        
    </div>

   <div class="comnei hide">
       <a class="close cd-popup-close"></a>
       <p class="commit-1"><span>充值</span></p>
       <div class="yunull">
          	<div class="imgczcg"><img src="/images/czok.png" width="313" height="269"></div>
            <div class="textmes01">恭喜！充值成功</div>
            <div class="textmes02"><span>5</span>秒后自动跳转至出借页面</div>
           
        </div>
        <p class="commit-2"><span>理财有风险，投资需谨慎</span><a class="colseok" href="#">确认</a></p>
   </div>    
       <!--充值成功结束-->
       
        
     </div>
</div>


<!--common end-->
<div class="main minhh">
	<div class="con personcon">
    	<div class="smallnav">
    	<a >当前位置</a><span>:</span><a>个人中心</a><span>&gt;</span><a >我的出借</a><span>&gt;</span><a >头笔赚</a>
        </div>
        <div class="con xqdetial">
    	<%@ include file="left_percenter.jsp" %>
        
    	<div class="xqdeleft my_loan">        
        	<div class="my_loantop"><a href="#" class="active">持有中</a><a href="#">已完成</a><span class="L2">&nbsp;</span></div>
            <div class="clear"></div>
            <div class="loanrecord">
            	
            	<div class="loanrecord01">
                	<div>
                    
                    <div class="tabopa" style="position: relative;">                    
                    
                
                 <!-- <div class="loanrow tit chi_you"> -->
                 <div class="manbiao_ti">*标的满标后，即可显示完整信息。</div>
                 <table class="ddttable newchiyou" width="100%" algin="center" cellspacing="0" cellpadding="0" >
                 <thead>
                 <tr class="loanrow tit chi_you">
                 <td style="width:17.2%">名称</td>
                 <td style="width:12.2%">出借金额(元)</td>
                 <td style="width:9.0%">预期年化利率</td>
                 <td style="width:10.2%">出借时间</td>
                 <td style="width:10.2%">计息时间</td>
                 <td style="width:10.2%">到期时间</td>
                 <td style="width:10.0%">下个回款日</td>
                 <td style="width:11.0%">应收本息</td>
                 <td style="width:10.0%">状态</td>
                 </tr>
                 </thead>
                 <tbody>
                 </tbody>
                 </table>
                 <div class="hide_liu loanrow_liu"></div> 
                 <web:PartPage2 name="dtocListCyzCollection" customProperty = "class=\"page\""  customSelectProperty="class=\"active\""/>
                <!-- </div> -->
              
                

                    </div>
                    
                    <div class="tabopa hide ">                    
                    <table class="ddttable newWC" >
                    <thead>
                 <tr class="loanrow loanrow01 tit jilu">
                 <td style="width:17.6%;">名称</td><td>出借金额(元)</td><td>预期年化利率</td><td>出借时间</td><td>到期时间</td><td>结算时间</td>
                 </tr>
                 </thead>
                 <tbody>
                 </tbody>
                 </table>
                    
                    
                      <div class="hide_liu loanrow01_liu"></div>
                   <!--分页-->
	               <web:PartPage2 name="dtocListchujieCollection" customProperty = "class=\"page\""  customSelectProperty="class=\"active\""/>
	               <!-- <div class="page"><a href="" >首页</a><a href="" class="active">1</a><a href="">2</a><a href="">3</a><a href="">4</a><a href="">5</a><a href="">6</a><a href="">7</a><a href="">下一页</a><a href="">末页</a></div> -->
	               <!--分页结束-->
                    </div>
                    
                    </div>
                     <!-- <div class="nonemore">没有更多记录</div> -->
               
                </div>
                
                
                
            </div>
            
      		
            
            
            
            
            <div class="clear"></div>
        </div>
        
    </div>
    </div>
</div>
<!-----------------中间部分结束----------------> 
<!--bottom common-->
<%@ include file="footer.jsp" %>

<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>

<!--<script src="/js/tabmenu.js"></script>-->
<script>
 $(function(){
    if($(".chi_you").next().find(".ddtit").length<1){
        $(".loanrow_liu").css("display","block");
    }
    if($(".jilu").next().find("td").length<1){
        $(".loanrow01_liu").css("display","block");
    }
 
    $.ajax({
        type: "get",//请求方式
        url: "/portal/mylend/tbzList",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        console.log(result);
        //资金流水留白
        if(result.dataValue.length<1){
        	$(".loanrow_liu").css("display","block");
        }
        for(var i=0;i<result.dataValue.length;i++){
        	var str='<tr class="loanrow loanrowList"><td class="ddtit" style="width:17.2%" ><a href="Newtzdetails.jsp?OID='+result.dataValue[i].oid+'">'+result.dataValue[i].xmmc+'</a><a href="#" class="new_trigger" onclick="xiyieOID(\'2\',\''+result.dataValue[i].doid+'\',\''+result.dataValue[i].cond+'\',\''+result.dataValue[i].hkzt+'\',\''+result.dataValue[i].fullscale+'\',\''+result.dataValue[i].ifzz+'\',\''+result.dataValue[i].charge+'\')" >查看协议</a></td><td style="width:12.2%">'+result.dataValue[i].cjje+'</td><td id="nhll" style="width:9.0%">'+result.dataValue[i].nhll+'%</td><td class="ddtit" style="width:10.2%">'+result.dataValue[i].cjsjTime+'</td>';
        	if(result.dataValue[i].hkzt=='wmb'){
        		str+='<td style="width:10.2%"></td><td style="width:10.2%"></td><td style="width:10.0%"></td><td style="width:11.0%"></td><td style="width:10.0%"></td>';
        	}else{
        		str+='<td style="width:10.2%">'+result.dataValue[i].jxsj+'</td><td style="width:10.2%">'+result.dataValue[i].dqsj+'</td><td style="width:10.0%">'+result.dataValue[i].xghkr+'</td><td  style="width:11.0%">'+result.dataValue[i].ysbx+'</td>';
        		if(result.dataValue[i].hkzt=='zzhkz'){
        			str+='<td style="width:10.0%"><a class="transfer_ht">正在回款中</a></td></tr>';
        		}else if(result.dataValue[i].hkzt=='yq'){
        			str+='<td style="width:10.0%"><a class="transfer_ht">逾期</a></td></tr>';
        		}
        		
        	}
        	
        	$(".newchiyou tbody").append(str);
        }        
        },
		error:function(result){
    	}
    });
    
    $.ajax({
        type: "get",//请求方式
        url: "/portal/mylend/tbzWCList",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
        success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        console.log(result);
        //资金流水留白
        if(result.dataValue.length<1){
        	 $(".loanrow01_liu").css("display","block");
        }
        for(var i=0;i<result.dataValue.length;i++){
        	var str='<tr class="loanrow loanrow01"><td style="width:17.6%;">'+result.dataValue[i].xmmc+'<a href="#" class="new_trigger" onclick="xiyieOID(\'2\',\''+result.dataValue[i].doid+'\',\''+result.dataValue[i].cond+'\',\''+result.dataValue[i].hkzt+'\',\''+result.dataValue[i].fullscale+'\',\''+result.dataValue[i].ifzz+'\',\''+result.dataValue[i].charge+'\')" >查看协议</a></td>';
        	str+='<td>'+result.dataValue[i].cjje+'</td><td>'+result.dataValue[i].nhll+'%</td><td class="ddtit">'+result.dataValue[i].cjsjTime+'</td><td>'+result.dataValue[i].dqsj+'</td><td>'+result.dataValue[i].Timejssj+'</td></tr>';
        	
        	$(".newWC tbody").append(str);
        }        
        },
		error:function(result){
    	}
    });
    
})
//   头笔赚协议打开窗口
        $(document).on('click','.new_trigger',function(event){
            event.preventDefault();
            $('.cd-popup6').addClass('is-visible1');
            //$(".dialog-addquxiao").hide()
        });
function xiyieOID(type,DOID,biOID,productStatus,fullscale,ifzz,charge){
	 console.log(productStatus);
	 var iframeurl ="AgreementList_iframe.jsp?type="+type+"&DOID="+DOID+"&biOID="+biOID+"&productStatus="+productStatus+"&fullscale="+fullscale+"&ifzz="+ifzz+"&charge="+charge;
		$("#iframepage").attr("src",iframeurl);
 }
</script>
</html>
