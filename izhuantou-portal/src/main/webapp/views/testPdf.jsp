<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" id="htmlId">
<head>
     <meta charset="UTF-8" />
    <style>
        .box{
            /* width: 1000px;
            border:1px solid red; */
            text-align: center;
             
        }
        table{
            width: 100%;
        }
        table, th, td {
            border: 1px solid grey;
            border-collapse: collapse;
            font-size: 12px;
        }
        table th {
            height: 40px;
            line-height: 40px;
            background: #cdcdcd;
            text-align: center;
        }
        table td {
            height: 40px;
            line-height: 40px;
            color: black;
            text-align: center;
        }
 
        /*如果要隔行变色把下面解开换色就行*/
     /*   table tr:nth-child(odd) {
            background-color: white;
        }
        table tr:nth-child(even) {
            background-color:#99ccff;
        }*/
        .civilRecord{
            margin-top: 20px;
        }
        /*这是固定某一列表格的宽度*/
        .civilRecord .th1{
            width: 70px;
        }
        .civilRecord .th3{
            width: 190px;
        }
        .civilRecord .th4{
            width: 190px;
        }
        .civilRecord .th5{
            width: 190px;
        }
        .protectionin{
         margin-top: 20px;
        }
         
        .bj{
            margin-left: 5%;
            margin-right: 5%;       
        }
 
    </style>
    <script type="text/javascript" charset="utf-8" src="/js/jquery-1.10.1.min.js"></script>
            <script type="text/javascript">
      function msg() {
             var form=$("<form>");//定义一个form表单
             form.attr("style","display:none");
             form.attr("target","");
             form.attr("method","post");
             form.attr("action","/portal/pdf/changetopdf");
             //form.attr("action","/rest/pdf/changetopdf");
             var input1=$("<input>");
             input1.attr("type","hidden");
             input1.attr("name","htmlContent");
             var html=$("#htmlId").html();
            // alert(html);
             input1.attr("value",html);
             $("#pdfBody").append(form);//将表单放置在web中
             form.append(input1);
             form.submit();//表单提交
       }
 
</script>
</head>
<body id="pdfBody">
<div class="box" style="height:430px;overflow:auto">
    <div style="margin-left:80%;line-height: 28px;position: relative;">
    <input type="button" value="导出PDF" onclick="msg()"/>
        </div>
  <!-- <h3>三 公共信息明细</h3> -->
    <div class="taxesRecord bj">
        <p>个人担保基础信息</p>
        <table >
            <thead>
            <tr>
                <th>信息记录类型</th>
                <th>账户类型</th>
                <th>账户标识码</th>
                <th>信息报告日期</th>
            </tr>
            </thead>
            <tbody>
            <tr>
           <%--  <c:forEach var="assureinfos" items="${assureinfos}">
                <td>${assureinfos.infotypetext}</td>
                <td>${assureinfos.acctypetext}</td>
                <td>${assureinfos.acccode}</td>
                <td>${assureinfos.repdateStr}</td>
                </c:forEach> --%>
            </tr>
            </tbody>
        </table>
        <table>
         <thead>
            <tr>
                <th>报告时点说明代码</th>
                <th>债务人姓名</th>
                <th>债务人证件类型</th>
                <th>债务人证件号码</th>
                <th>业务管理机构代码</th>
            </tr>
            </thead>
            <tbody>
            <tr>
            		<%-- <c:forEach var="assureinfos" items="${assureinfos}">
                <td>${assureinfos.repcodetext}</td>
                <td>${assureinfos.debtorname}</td>
                <td>${assureinfos.debtoridtypetext}</td>
                <td>${assureinfos.debtoridnum}</td>
                <td>${assureinfos.organcode}</td>
                </c:forEach> --%>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="civilRecord bj">
        <p>担保人基本信息</p>
        <table>
            <thead>
            <tr>
                <th class="th1">担保业务大类</th>
                <th class="th1">担保业务种类细分</th>
                <th class="th1">开户日期</th>
                <th class="th1">信用额度</th>
                <th class="th1">币种</th>
            </tr>
            </thead>
            <tbody>
            <tr>
            <%-- <c:forEach var="assurebasicinfos" items="${assurebasicinfos}">
                <td>${assurebasicinfos.suretybusstext }</td>
                <td>${assurebasicinfos.suretybussspeartext}</td>
                <td>${assurebasicinfos.opendateStr }</td>
                <td>${assurebasicinfos.creditlimit }</td>
                <td>${assurebasicinfos.currencytypetext }</td>
             </c:forEach> --%>
            </tr>
            </tbody>
        </table>
        <table>
            <thead>
            <tr>
                <th class="th1">到期日期</th>
                <th class="th1">反担保方式</th>
                <th class="th1">其他还款保证方式</th>
                <th class="th1">保证金百分比</th>
                <th class="th1">担保合同文本编号</th>
            </tr>
            </thead>
            <tbody>
            <tr>
            <%-- <c:forEach var="assurebasicinfos" items="${assurebasicinfos}">
                <td>${assurebasicinfos.enddateStr }</td>
                <td>${assurebasicinfos.assuremodetext }</td>
                <td>${assurebasicinfos.otherasstext }</td>
                <td>${assurebasicinfos.marginpercent }</td>
                <td>${assurebasicinfos.assurenum }</td>
             </c:forEach> --%>
            </tr>
            </tbody>
        </table>
    </div>
     <div class="civilRecord bj">
        <p>在保责任信息</p>
        <table>
            <thead>
            <tr>
                <th class="th1">账户状态</th>
                <th class="th1">在保余额</th>
                <th class="th1">余额变化日期</th>
                <th class="th1">五级分类</th>
                <th class="th1">五级分类认定日期</th>
            </tr>
            </thead>
            <tbody>
            <tr>
           <%--  <c:forEach var="protectioninfos" items="${protectioninfos}">
                <td>${protectioninfos.accstatetext }</td>
                <td>${protectioninfos.protectionbal }</td>
                <td>${protectioninfos.balchangedateStr }</td>
                <td>${protectioninfos.classifytext }</td>
                <td>${protectioninfos.classifydateStr }</td>
             </c:forEach> --%>
            </tr>
            </tbody>
        </table>
        <table>
            <thead>
            <tr>
                <th class="th1">风险敞口</th>
                <th class="th1">代偿标志</th>
                <th class="th1">账户关闭日期</th>
                <th class="th1"></th>
                <th class="th1"></th>
            </tr>
            </thead>
            <tbody>
            <tr>
            <%-- <c:forEach var="protectioninfos" items="${protectioninfos}">
                <td>${protectioninfos.riskexposure }</td>
                <td>${protectioninfos.compenflagtext }</td>
                <td>${protectioninfos.accclosedateStr }</td>
                <td></td>
                <td></td>
             </c:forEach> --%>
            </tr>
            </tbody>
        </table>
    </div>
     <div class="civilRecord bj">
        <p>相关还款人信息</p>
        <table>
            <thead>
            <tr>
                <th class="th1">责任人个数</th>
                <th class="th1">身份类别</th>
                <th class="th1">责任人名称</th>
                <th class="th1">责任人身份标识类型</th>
                <th class="th1">责任人身份标识号码</th>
            </tr>
            </thead>
             <%-- <c:forEach var="rep" items="${repaymentinfos}">
            <tbody>
            <tr>
                <td>${rep.perliabnum}</td>
                <td>${rep.classifytypetext}</td>
                <td>${rep.perliabname}</td>
                <td>${rep.perliabtypetext}</td>
                <td>${rep.perliabid}</td>
            </tr>
            </tbody>
            </c:forEach> --%>
        </table>
        <table>
            <thead>
            <tr>
                <th class="th1">还款责任人类型</th>
                <th class="th1">还款责任金额</th>
                <th class="th1"></th>
                <th class="th1"></th>
                <th class="th1"></th>
            </tr>
            </thead>
            <%-- <c:forEach var="rep" items="${repaymentinfos}">
            <tbody>
            <tr>
                <td>${rep.reperliabtypetext}</td>
                <td>${rep.reperliabamot}</td>
                <td></td>
                <td></td>
                <td></td>
           
            </tr>
            </tbody>
            </c:forEach> --%>
        </table>
    </div>
     <div class="civilRecord bj">
        <p>抵押物信息</p>
        <table>
            <thead>
            <tr>
                <th class="th1">抵质押合同个数</th>
                <th class="th1">抵质押合同标识码</th>
                <th class="th1"></th>
                <th class="th1"></th>
                <th class="th1"></th>
            </tr>
            </thead>
             <%-- <c:forEach var="contractinfos" items="${contractinfos}">
            <tbody>
            <tr>
                <td>${contractinfos.contractnum }</td>
                <td>${contractinfos.contractid }</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            </tbody>
             </c:forEach> --%>
        </table>
    </div>
</div>
</body>
</html>