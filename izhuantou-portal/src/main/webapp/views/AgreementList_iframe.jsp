<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>砖头网</title>
<link rel="stylesheet" type="text/css" href="/css/style-common.css">
<link rel="stylesheet" type="text/css" href="/css/personalcenter.css">
<link rel="stylesheet" type="text/css" href="/css/preother.css">
<Link rel="shortcut icon" href="/images/zhuan.ico" />
<script type="text/javascript" src="/js/jquery-1.10.1.min.js"></script>
<script src="/js/com.js"></script>
<script language="javascript" src="/js/menu.js"></script>
<!--<script src="/js/1-6-10.esl.js"></script>-->
<style>
.cd-popup-container1, .cd-popup-container2, .cd-popup-container4, .cd-popup-container5, .cd-popup-container6, .cd-popup-containerh{width:500px;padding: 10px 5px;}
.hide_liu{  width: 100%;  height: 400px;  text-align: center;  background:#fff url("images/hide_liu.jpg")no-repeat center;  display: none;  }
body{background: #fff;}
.xiyilist{text-align: left; line-height: 40px; padding: 20px;}
.xiyilist a{color:#55b0fd;}
</style>
</head>
<body>
<% try{ %>
<!--弹框-->
 <div class="xiyilist">        
        <ul>
        <%System.out.println("--------------------------------------------"+("正在回款中".equals(request.getParameter("productStatus")))); %>
        <%if("2".equals(request.getParameter("type"))){%>
        <li><a href="agreement_zCenter.jsp?type=<%=request.getParameter("type") %>&DOID=<%=request.getParameter("DOID") %>" target="_blank">《砖头网头笔赚服务协议》</a></li>
        <%}else if("3".equals(request.getParameter("type"))){%>
        <li><a href="agreement_zCenter.jsp?type=<%=request.getParameter("type") %>&DOID=<%=request.getParameter("DOID") %>" target="_blank">《砖头网点点投服务协议》</a></li>
        <%}else if("5".equals(request.getParameter("type"))){%>
        <li><a href="agreement_zCenter.jsp?type=<%=request.getParameter("type") %>&DOID=<%=request.getParameter("DOID") %>" target="_blank">《砖头网转转投服务协议》</a></li>
        <%}%>
        <%if(("zzhkz".equals(request.getParameter("productStatus")) || "yq".equals(request.getParameter("productStatus")) || "ywc".equals(request.getParameter("productStatus"))) && "5".equals(request.getParameter("type")) ){%> 
         
        <li><a href="agreement_zCenter.jsp?type=7&biOID=<%=request.getParameter("biOID") %>" target="_blank">《砖头网债权转让协议》</a></li>
        <li><a href="agreement_zCenter.jsp?type=8&biOID=<%=request.getParameter("biOID") %>" target="_blank">《砖头网债权预回购协议》</a></li>

        <%}else if(("zzhkz".equals(request.getParameter("productStatus")) || "yq".equals(request.getParameter("productStatus")) || "ywc".equals(request.getParameter("productStatus"))) && "2".equals(request.getParameter("type")) ){%>
        <% if ("true".equals(request.getParameter("fullscale"))){ %> 
        <%if("0".equals(request.getParameter("ifzz"))){ %>
          <li><a href="agreement_zCenter.jsp?type=6&biOID=<%=request.getParameter("biOID") %>" target="_blank">《砖头网借款协议》</a></li>
          <%}else{ %>
          <%if("first".equals(request.getParameter("charge"))){ %>
          <li><a href="agreement_zCenter.jsp?type=6&biOID=<%=request.getParameter("biOID") %>" target="_blank">《砖头网借款协议》</a></li>
          <%}else{ %>
          <li><a href="agreement_zCenter.jsp?type=7&biOID=<%=request.getParameter("biOID") %>" target="_blank">《砖头网债权转让协议》</a></li>
          <%} %>
          <%} %>
        <li><a href="agreement_zCenter.jsp?type=8&biOID=<%=request.getParameter("biOID") %>" target="_blank">《砖头网债权预回购协议》</a></li>
        <%} %>
        <%}else if(("zzhkz".equals(request.getParameter("productStatus")) || "yq".equals(request.getParameter("productStatus"))  || "ywc".equals(request.getParameter("productStatus"))) && "3".equals(request.getParameter("type")) ){%>
       	<% if ("true".equals(request.getParameter("fullscale"))){ %> 
        <li><a href="agreement_zCenter.jsp?type=11&biOID=<%=request.getParameter("biOID") %>" target="_blank">《砖头网借款协议》</a></li>
        <li><a href="agreement_zCenter.jsp?type=13&biOID=<%=request.getParameter("biOID") %>" target="_blank">《砖头网债权预回购协议》</a></li>
   	  <%} %>
      <%} %>
        <!--借款记录-->
        <%if("jk".equals(request.getParameter("type")) && "1".equals(request.getParameter("xyType"))){%>
        <li><a href="agreement_zCenter.jsp?type=6&biOID=<%=request.getParameter("biOID") %>&loanNumber=<%=request.getParameter("loanNumber") %>" target="_blank">《砖头网借款协议》 </a></li>
        <li><a href="agreement_zCenter.jsp?type=8&biOID=<%=request.getParameter("biOID") %>&loanNumber=<%=request.getParameter("loanNumber") %>" target="_blank">《砖头网债权预回购协议》</a></li>
        <li><a href="agreement_zCenter.jsp?type=9&biOID=<%=request.getParameter("biOID") %>&loanNumber=<%=request.getParameter("loanNumber") %>" target="_blank">《砖头网借款咨询服务协议》  </a></li>
        <%} else if("jk".equals(request.getParameter("type")) && "2".equals(request.getParameter("xyType"))){%>
        <li><a href="agreement_zCenter.jsp?type=11&biOID=<%=request.getParameter("biOID") %>&loanNumber=<%=request.getParameter("loanNumber") %>" target="_blank">《砖头网借款协议》 </a></li>
        <li><a href="agreement_zCenter.jsp?type=13&biOID=<%=request.getParameter("biOID") %>&loanNumber=<%=request.getParameter("loanNumber") %>" target="_blank">《砖头网债权预回购协议》</a></li>
        <li><a href="agreement_zCenter.jsp?type=12&biOID=<%=request.getParameter("biOID") %>&loanNumber=<%=request.getParameter("loanNumber") %>" target="_blank">《砖头网借款咨询服务协议》  </a></li>
        <%} else if("jk".equals(request.getParameter("type")) && "3".equals(request.getParameter("xyType"))){%>
        <li><a href="agreement_zCenter.jsp?type=16&biOID=<%=request.getParameter("biOID") %>&loanNumber=<%=request.getParameter("loanNumber") %>" target="_blank">《砖头网借款协议》 </a></li>
        <li><a href="agreement_zCenter.jsp?type=18&biOID=<%=request.getParameter("biOID") %>&loanNumber=<%=request.getParameter("loanNumber") %>" target="_blank">《砖头网债权预回购协议》</a></li>
        <li><a href="agreement_zCenter.jsp?type=19&biOID=<%=request.getParameter("biOID") %>&loanNumber=<%=request.getParameter("loanNumber") %>" target="_blank">《砖头网借款咨询服务协议》  </a></li>
        <%} %>
        </ul>
         
        </div> 
<%}catch(Exception e){
	response.sendRedirect("error.jsp");
    return;  
} %>
</body>

</html>
