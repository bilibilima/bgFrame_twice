<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>JQuery easyui demo</title>

<style type="text/css">
#user_fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}

.fitem input {
	width: 160px;
}
</style>


<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.1/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.1/themes/color.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.1/demo/demo.css">
<script type="text/javascript" src="jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
   <!-- 
	<div style="height: 70px; width: 100%;">
		<form id="ff" method="post">
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="name">用户名:</label> <input class="easyui-validatebox"
					type="text" name="name" />
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<label for="email">邮箱:</label> <input class="easyui-validatebox"
					type="text" name="email" data-options="validType:'email'" />
			</div>
			<div style="float: left; padding: 20px 0 0 20px;">
				<input class="easyui-validatebox" type="button" value="查询" />
			</div>
		</form>
	</div>
	 -->
	
	<table id="user_dg" class="easyui-datagrid" style="height: 470px;"
		url="usermessage/findAll.do" toolbar="#user_toolbar" pagination="true"
		rownumbers="true" fitColumns="true" singleSelect="true"
		data-options="fit:false,border:false,pageSize:5,pageList:[5,10,15,20]">
		<thead>
			<tr>

				<th field="userNo" width="50">编号</th>
				<th field="userName" width="50">用户名</th>
				<th field="nickName" width="50">昵称</th>
				<th field="password" width="50">密码</th>
				<th field="userPhone" width="50">电话</th>
				<th field="userEmail" width="50">邮箱</th>
				<th field="userDate" width="50">使用日期</th>
				
			</tr>
		</thead>
	</table>
	<div id="user_toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newUser()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
	</div>

<!-- 对话框 -->
	<div id="user_dlg" class="easyui-dialog" style="padding: 10px 20px"
		closed="true" buttons="#user_dlg-buttons">
		<div class="ftitle">详细信息</div>
		<form id="user_fm" method="post" novalidate>
			<div class="fitem">
				<label>用户名:</label> <input name="userName" class="easyui-textbox"
					required="true">
			</div>
			<div class="fitem">
				<label>昵称:</label> <input name="nickName" class="easyui-textbox"
					required="true">
			</div>
			<div class="fitem">
				<label>密码:</label> <input name="password" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>电话:</label> <input name="userPhone" class="easyui-textbox"
					validType="email">
			</div>
			<div class="fitem">
				<label>邮箱:</label> <input name="userEmail" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>使用日期:</label> <input name="userDate" class="easyui-textbox"
					validType="email">
			</div>			
		</form>
	</div>
	<div id="user_dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveUser()" style="width: 90px">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#user_dlg').dialog('close')"
			style="width: 90px">取消</a>
	</div>
	<script type="text/javascript">
		var url;
		function newUser() {
			$('#user_dlg').dialog('open').dialog('setTitle', '新增');
			$('#user_fm').form('clear');
			url = 'users/addUsers.do';
		}
		function editUser() {
			var row = $('#user_dg').datagrid('getSelected');
			if (row) {
				$('#user_dlg').dialog('open').dialog('setTitle', '编辑');
				$('#user_fm').form('load', row);
				url = 'users/updateUsers.do?id=' + row.id;
			}
		}
		function saveUser() {
			$('#user_fm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.errorMsg) {
						$.messager.show({
							title : 'Error',
							msg : result.errorMsg
						});
					} else {
						$('#user_dlg').dialog('close'); // close the dialog
						$('#user_dg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyUser() {
			var row = $('#user_dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('Confirm', '确定要删除吗?', function(r) {
					if (r) {
						$.post('users/deleteUsers.do', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$('#user_dg').datagrid('reload'); // reload the user data
							} else {
								$.messager.show({ // show error message
									title : 'Error',
									msg : result.errorMsg
								});
							}
						}, 'json');
					}
				});
			}
		}
	</script>

</body>
</html>