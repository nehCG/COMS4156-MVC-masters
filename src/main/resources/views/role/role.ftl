<!DOCTYPE html>
<html>
<head>
	<title>Role Mgmt</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="roleName"
						   class="layui-input
					searchVal" placeholder="Rolename" />
				</div>
				<a class="layui-btn search_btn" data-type="reload"><i
							class="layui-icon">&#xe615;</i> Search</a>
			</div>
		</form>
	</blockquote>

	<table id="roleList" class="layui-table"  lay-filter="roles"></table>

	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
				<i class="layui-icon">&#xe608;</i>
				Add role
			</a>
			<a class="layui-btn layui-btn-normal delNews_btn" lay-event="grant">
				<i class="layui-icon">&#xe672;</i>
				Authorize
			</a>
		</div>
	</script>

	<script id="roleListBar" type="text/html">
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">Edit</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">Delete</a>
	</script>
</form>
<script type="text/javascript" src="${ctx}/js/role/role.js"></script>

</body>
</html>