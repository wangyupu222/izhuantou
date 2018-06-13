
  //"use strict";
  //Global variables
  var params = null;  		//Parameters
  var colsEdi =null;
  var newColHtml = '<div class="btn-group pull-right">'+
'<button id="bEdit" type="button" class="btn btn-sm btn-default" onclick="rowEdit(this);">' +
'<span class="glyphicon glyphicon-pencil" > </span>'+
'</button>'+
'<button id="bElim" type="button" class="btn btn-sm btn-default" onclick="rowElim(this);">' +
'<span class="glyphicon glyphicon-trash" > </span>'+
'</button>'+
'<button id="bAcep" type="button" class="btn btn-sm btn-default" style="display:none;" onclick="rowAcep(this);">' +
'<span class="glyphicon glyphicon-ok" > </span>'+
'</button>'+
'<button id="bCanc" type="button" class="btn btn-sm btn-default" style="display:none;" onclick="rowCancel(this);">' +
'<span class="glyphicon glyphicon-remove" > </span>'+
'</button>'+
    '</div>';
  var colEdicHtml = '<td data-field="operate"><div class="layui-table-cell laytable-cell-1-operate"><a class="operaBtn editBtn" lay-event="">编辑</a> <a class="operaBtn resetPwdBtn" lay-event="edit">重置密码</a> <a class="operaBtn lockBtn" lay-event="">锁定</a> <a class="operaBtn operaDel" lay-event="del">删除</a> <a class="operaBtn" onclick="saveBtn(this);" style="display: none">保存</a><a class="operaBtn" onclick="rowCancel(this);" style="display: none">取消</a></div></td>';
    
  $.fn.SetEditable = function (options) {
    var defaults = {
        columnsEd: null,         //Index to editable columns. If null all td editables. Ex.: "1,2,3,4,5"
        $addButton: null,        //Jquery object of "Add" button
        onEdit: function() {},   //Called after edition
		onBeforeDelete: function() {}, //Called before deletion
        onDelete: function() {}, //Called after deletion
        onAdd: function() {}     //Called when added a new row
    };
    params = $.extend(defaults, options);
    this.find('thead tr').append('<th name="buttons"></th>');  //encabezado vacío
    this.find('tbody tr').append(colEdicHtml);
	var $tabedi = this;   //Read reference to the current table, to resolve "this" here.
    //Process "addButton" parameter
    if (params.$addButton != null) {
        //Se proporcionó parámetro
        params.$addButton.click(function() {
            rowAddNew($tabedi.attr("id"));
        });
    }
    //Process "columnsEd" parameter
    if (params.columnsEd != null) {
        //Extract felds
        colsEdi = params.columnsEd.split(',');
    }
  };
function IterarCamposEdit($cols, tarea) {
//Itera por los campos editables de una fila
    var n = 0;
    $cols.each(function() {
        n++;
        if ($(this).attr('name')=='buttons') return;  //excluye columna de botones
        if (!EsEditable(n-1)) return;   //noe s campo editable
        tarea($(this));
    });
    
    function EsEditable(idx) {
    //Indica si la columna pasada está configurada para ser editable
        if (colsEdi==null) {  //no se definió
            return true;  //todas son editable
        } else {  //hay filtro de campos
//alert('verificando: ' + idx);
            for (var i = 0; i < colsEdi.length; i++) {
              if (idx == colsEdi[i]) return true;
            }
            return false;  //no se encontró
        }
    }
}
function FijModoNormal(but) {
    $(but).parent().find('.operaBtn').eq(0).show();
    $(but).parent().find('.operaBtn').eq(1).show();
    $(but).parent().find('.operaBtn').eq(2).show();
    $(but).parent().find('.operaBtn').eq(3).show();
    $(but).parent().find('.operaBtn').eq(4).hide();
    $(but).parent().find('.operaBtn').eq(5).hide();
    var $row = $(but).parents('tr');  //accede a la fila
    $row.attr('id', '');  //quita marca
}
function FijModoEdit(but) {
    $(but).parent().find('.operaBtn').eq(0).hide();
    $(but).parent().find('.operaBtn').eq(1).hide();
    $(but).parent().find('.operaBtn').eq(2).hide();
    $(but).parent().find('.operaBtn').eq(3).hide();
    $(but).parent().find('.operaBtn').eq(4).show();
    $(but).parent().find('.operaBtn').eq(5).show();
    var $row = $(but).parents('tr');  //accede a la fila
    $row.attr('id', 'editing');  //indica que está en edición
}
function ModoEdicion($row) {
    if ($row.attr('id')=='editing') {
        return true;
    } else {
        return false;
    }
}
function rowAcep(but) {
//Acepta los cambios de la edición
    var $row = $(but).parents('tr');  //accede a la fila
    var $cols = $row.find('td').find(".layui-table-cell");  //lee campos
    var userName="";
    var userNumber="";
    var name="";
    var duty="";
    var phone="";
    var roles="";
    var remark="";
    var department="";
    
    IterarCamposEdit($cols, function($td) {  //itera por la columnas
        if($td.parent("td").index()==1){
        	userName=$td.find(".layui-input").val();
        }else if($td.parent("td").index()==2){
        	userNumber=$td.find(".layui-input").val();
        }else if($td.parent("td").index()==3){
        	name=$td.find(".layui-input").val();
        }else if($td.parent("td").index()==4){
        	duty=$td.find(".layui-input").val();
        }else if($td.parent("td").index()==5){
        	phone=$td.find(".layui-input").val();
        }else if($td.parent("td").index()==6){
        	roles=$td.find(".chosen-select").val();
        	if(roles!=undefined && roles!=null && roles!=""){
        		roles=roles.join(",");
        	}
        }else if($td.parent("td").index()==7){
        	remark=$td.find(".layui-input").val();
        }else if($td.parent("td").index()==8){
        	department=$td.find(".depart-select").val();
        }
    });
	var user={
			userName:userName,
			userNumber:userNumber,
			name:name,
			duty:duty,
			phone:phone,
			remark:remark,
			department:department,
			roles:roles
    }
    $.ajax({
        url : "/manager/user/adduser",
        type : "get",
        dataType : "json",
        data:user,
        success : function(result) {
        	console.log(result);
            if(result.status==2){
            	layer.msg(result.message, {icon: 5});
            }else{
            	
            	if (!ModoEdicion($row)) return;  //Ya está en edición
                //Está en edición. Hay que finalizar la edición
                IterarCamposEdit($cols, function($td) {  //itera por la columnas
                    if($td.parent("td").index()==0 || $td.parent("td").index()==9 ){

                    }else if($td.parent("td").index()==6){
                        var cont = $(".chosen-select").find("option:selected").text();
                        $td.html(cont);
                    }else if($td.parent("td").index()==8){
                        var cont = $(".depart-select").find("option:selected").text();
                        $td.html(cont);
                    }else{
                        var cont = $td.find('input').val(); //lee contenido del input
                        $td.html(cont);  //fija contenido y elimina controles
                    }
                });
                FijModoNormal(but);
                //params.onEdit();
            	
            }
        },
        error:function(result){
        	alert("失败！");
        }
    });
	

    
}
function rowCancel(but) {
//Rechaza los cambios de la edición
    var $row = $(but).parents('tr');  //accede a la fila
    var $cols = $row.find('td').find(".layui-table-cell");  //lee campos
    var userData=$row.find("td").eq(9).find("em").attr("user-data");
    if (!ModoEdicion($row)) return;  //Ya está en edición
    //Está en edición. Hay que finalizar la edición
    if(userData==undefined){
    	$row.find("td").eq(9).find(".operaDel").click();
    }
    IterarCamposEdit($cols, function($td) {  //itera por la columnas
        if($td.parent("td").index()==0 || $td.parent("td").index()==9 ){

        }else{
            var cont = $td.find('div').html(); //lee contenido del div
            $td.html(cont);  //fija contenido y elimina controles
        }

    });
    FijModoNormal(but);
}
  function rowEdit(but) {  //Inicia la edición de una fila
      var $row = $(but).parents('tr');  //accede a la fila
      var $colsIndex=$row.find('td');
      var $cols = $row.find('td').find(".layui-table-cell");  //lee campos
      if (ModoEdicion($row)) return;  //Ya está en edición
      //Pone en modo de edición
      IterarCamposEdit($cols, function($td) {  //itera por la columnas
          if($td.parent("td").index()==0 || $td.parent("td").index()==9 ){

          }else if ($td.parent("td").index()==6){
        	  $.ajax({
                  type: "get",//请求方式
                  url: "/manager/roles/list",//地址，就是json文件的请求路径
                  dataType: "json",//数据类型可以为 text xml json  script  jsonp
                  data:{},
                  success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
                  var result=result.dataValue;
                  console.log(result.length);
                  var branchStr="";
                  for(var i=0;i<result.length;i++){
                	  var optionData=null;
                	  if(result[i].roleList.length>0){
                		  for(var j=0;j<result[i].roleList.length;j++){
                			  optionData+='<option value="'+result[i].roleList[j].oid+'" hassubinfo="true">'+result[i].roleList[j].name+'</option>';
                		  }
                	  }
                  	branchStr+='<optgroup label="'+result[i].name+'">'+optionData+'</optgroup>';
                  	var cont = $td.html(); //lee contenido
                    var div = '<div style="display: none;">' + cont + '</div>';  //guarda contenido
                    var input = '<select data-placeholder="请选择角色" class="chosen-select" multiple style="width:350px;" tabindex="4">' +branchStr+'</select>';
                    
                  }
                  $td.html(div + input);  //fija contenido

                  var config = {
                      ".chosen-select": {},
                  };
                  for (var selector in config) $(selector).chosen(config[selector]);
                  $('.chosen-drop').getNiceScroll().show();
                  $('.chosen-drop').getNiceScroll().resize();
                  $(".chosen-drop").niceScroll(".chosen-drop .chosen-results",{boxzoom:false});  // hw acceleration enabled when using wrapper
                  },
          		error:function(result){
              	}
              });
        	  
          }else if ($td.parent("td").index()==8){
        	  $.ajax({
                  type: "get",//请求方式
                  url: "/manager/group/tree",//地址，就是json文件的请求路径
                  dataType: "json",//数据类型可以为 text xml json  script  jsonp
                  data:{},
                  success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
                  var result=result.dataValue;
                  console.log(result.length);
                  var branchStr="";
                  for(var i=0;i<result.length;i++){
                	  var optionData='';
                	  if(result[i].userGroupList.length>0){
                		  for(var j=0;j<result[i].userGroupList.length;j++){
                			  optionData+='<option value="'+result[i].userGroupList[j].oid+'">'+result[i].userGroupList[j].name+'</option>';
                		  }
                	  }
                  	branchStr+='<optgroup label="'+result[i].name+'">'+optionData+'</optgroup>';
                  	var cont = $td.html(); //lee contenido
                    var div = '<div style="display: none;">' + cont + '</div>';  //guarda contenido
                    var input = '<select name="quiz" class="depart-select"><option value="">请选择部门</option>' +branchStr+'</select>';
                    
                  }
                  $td.html(div + input);  //fija contenido
                  layui.use(['table','form'], function(){
                      var table = layui.table,
                          form = layui.form
                          ,element = layui.element;
                      form.render();
                  })
                  
                  },
          		error:function(result){
              	}
              });
              
          }else{
              var cont = $td.html(); //lee contenido
              var div = '<div style="display: none;">' + cont + '</div>';  //guarda contenido
              var input = '<input class="layui-input"  value="' + cont + '">';
              $td.html(div + input);  //fija contenido
          }
      });
      FijModoEdit(but);
  }
function rowElim(but) {  //Elimina la fila actual
    var $row = $(but).parents('tr');  //accede a la fila
    params.onBeforeDelete($row);
    $row.remove();
    params.onDelete();
}
function rowAddNew(tabId) {  //Agrega fila a la tabla indicada.
var $tab_en_edic = $("." + tabId);  //Table to edit
    var $filas = $tab_en_edic.find('tbody tr');
    if ($filas.length==0) {
        //No hay filas de datos. Hay que crearlas completas
        var $row = $tab_en_edic.find('thead tr');  //encabezado
        var $cols = $row.find('th');  //lee campos
        //construye html
        var htmlDat = '';
        $cols.each(function() {
            if($(this).index()==9){
                htmlDat=htmlDat + colEdicHtml;
            }else {
                htmlDat = htmlDat + '<td></td>';
            }
        });
        $tab_en_edic.find('tbody').prepend('<tr>'+htmlDat+'</tr>');
    } else {
        //Hay otras filas, podemos clonar la última fila, para copiar los botones
        var $ultFila = $tab_en_edic.find('tr:first');
        $ultFila.clone().appendTo($ultFila.parent());  
        $ultFila = $tab_en_edic.find('tr:first');
        var $cols = $ultFila.find('td');  //lee campos
        var htmlDat = '';
        $cols.each(function() {
            if($(this).index()==0){
            	 htmlDat = htmlDat + '<td><div class="layui-table-cell"><input type="checkbox" name="layTableCheckbox" lay-skin="primary"><div class="layui-unselect layui-form-checkbox" lay-skin="primary"><i class="layui-icon"></i></div></div></td>';
            }else if($(this).index()==9){
               htmlDat=htmlDat + colEdicHtml;
            }else {
                htmlDat = htmlDat + '<td><div class="layui-table-cell"></div></td>';
            }
        });
        $tab_en_edic.find('tbody').prepend('<tr>'+htmlDat+'</tr>');
        $tab_en_edic.find('tbody tr').eq(0).find("td").eq(9).find(".editBtn").click();
    }
	params.onAdd();
}
function TableToCSV(tabId, separator) {  //Convierte tabla a CSV
    var datFil = '';
    var tmp = '';
	var $tab_en_edic = $("#" + tabId);  //Table source
    $tab_en_edic.find('tbody tr').each(function() {
        //Termina la edición si es que existe
        if (ModoEdicion($(this))) {
            $(this).find('#bAcep').click();  //acepta edición
        }
        var $cols = $(this).find('td');  //lee campos
        datFil = '';
        $cols.each(function() {
            if ($(this).attr('name')=='buttons') {
                //Es columna de botones
            } else {
                datFil = datFil + $(this).html() + separator;
            }
        });
        if (datFil!='') {
            datFil = datFil.substr(0, datFil.length-separator.length); 
        }
        tmp = tmp + datFil + '\n';
    });
    return tmp;
}
