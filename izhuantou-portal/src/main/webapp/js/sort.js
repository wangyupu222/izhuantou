/*
        var k=0;

        function sortTable(sTableId,iCol,sDataType){
            var oTable=document.getElementById(sTableId);//获取表格的ID
            var oTbody=oTable.tBodies[0]; //获取表格的tbody
            var colDataRows=oTbody.rows; //获取tbody里的所有行的引用
            var aTRs=new Array(); //定义aTRs数组用于存放tbody里的行
            for(var i=0;i<colDataRows.length;i++){//依次把所有行放如aTRs数组
                aTRs.push(colDataRows[i]);
            }

            if(oTable.sortCol==iCol){//非首次排序
                aTRs.reverse();
            }
            else{ //首次排序
                if(k%2==0){//升序
                    aTRs.sort(generateCompareTRs(iCol,sDataType));
                }
                else if(k%2==1)//降序
                {
                    aTRs.sort(generateCompareTRs1(iCol,sDataType));
                }
            }

            var oFragment=document.createDocumentFragment();//创建文档碎片/
            for(var i=0;i<aTRs.length;i++){ //把排序过的aTRs数组成员依次添加到文档碎片
                oFragment.appendChild(aTRs[i]);
            }
            oTbody.appendChild(oFragment); //把文档碎片添加到tbody,完成排序后的显示更新
            oTable.sortCol=iCol;//把当前列号赋值给sortCol,以此来区分首次排序和非首次排序,//sortCol的默认值为-1
        };
        //比较函数，用于两项之间的排序
        //升序
        function generateCompareTRs(iCol,sDataType){
            return   function compareTRs(oTR1,oTR2){
                var vValue1=convert(oTR1.cells[iCol].firstChild.nodeValue,sDataType);
                alert(oTR1.cells[iCol])
                alert(vValue1)
                var vValue2=convert(oTR2.cells[iCol].firstChild.nodeValue,sDataType);
                if(vValue1<vValue2){
                    return -1;
                }
                else if(vValue1>vValue2){
                    return 1;
                }
                else{
                    return 0;
                }
            };
        };
        //数据类型转换函数
        function convert(sValue,sDataType){
            switch(sDataType){
                case "int":return parseInt(sValue);
                case "float": return parseFloat(sValue);
                case "date":return new Date(Date.parse(sValue));
                default:return sValue.toString();
            }
        };*/




/* ************************************************* */
$(function(){
var table = document.getElementById('tblSort');
            var tbody = table.tBodies[0];
            var rows = tbody.rows;
            var j=5;
            var cells = rows[0].cells;

                cells[5].onclick = function ()
                {
                    var asc = this.asc = !!this.asc ? -this.asc : -1;
                    var array = [];
                    array.index = this.cellIndex;
                    for ( var i = 1; i < rows.length; i++)
                    {
                        array.push (rows[i]);
                    }
                    array.sort (function (a, b)
                    {
                        var n1 = a.cells[array.index].firstChild.nodeValue;
                        var n2 = b.cells[array.index].firstChild.nodeValue;
                        if (n1 > n2)
                        {
                            return -asc;
                        }
                        else if (n1 < n2)
                        {
                            return asc;
                        }
                        else
                        {
                            return 0;
                        }
                    });

                    for ( var i = 0; i < array.length; i++)
                    {
                        tbody.appendChild (array[i]);
                    }
                }
                cells[5].click();
})