<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="contentEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
	    	<tr>
		<td>商品类目:</td>
		<td>
		     <a href="javascript:void(0)"  onclick="change()" class="easyui-linkbutton selectItemCat">选择类目</a> 
			<input type="hidden" id="a" name="cid" style="width: 280px;"></input>
		</td>
	</tr>
	<ul>
	<li>
 	  <tr>
	       <td>商品类目ID:</td>
	            <td><input class="easyui-textbox" type="text" name="itemCatId" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        </li>
	        <li>
	       <td>商品类目:</td>
	            <td><input class="easyui-textbox" type="text" name="itemCatName" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        </li>
	        </ul>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
<!-- 	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="contentEditPage.clearForm()">重置</a> -->
	</div>
</div>
<script type="text/javascript">
/* var contentEditEditor ;
$(function(){
	contentEditEditor = TT.createEditor("#contentEditForm [name=content]");
	TT.initOnePicUpload();
}); */

function submitForm(){
			if(!$('#contentEditForm').form('validate')){
				$.messager.alert('提示','表单还未填写完成!');
				return ;
			}
			var paramJson = [];
			$("#contentEditForm .params li").each(function(i,e){
				var trs = $(e).find("tr");
				var group = trs.eq(0).text();
				var ps = [];
				for(var i = 1;i<trs.length;i++){
					var tr = trs.eq(i);
					ps.push({
						"k" : $.trim(tr.find("td").eq(0).find("span").text()),
						"v" : $.trim(tr.find("input").val())
					});
				}
				paramJson.push({
					"group" : group,
					"params": ps
				});
			});
			paramJson = JSON.stringify(paramJson);
			$("#contentEditForm [name=itemCatName]").val(paramJson);
			alert(paramJson);
			$.post("/item/param/edit",$("#contentEditForm").serialize(), function(data){
				if(data.status == 200){
					$.messager.alert('提示','修改参数成功!','info',function(){
						$("#itemEditWindow").window('close');
						$("#itemParamList").datagrid("reload");
					});
				}
			});
		}
function change(){
	var b=$("#contentEditForm [name=itemCatId]").val();
	alert(b);
}
</script>