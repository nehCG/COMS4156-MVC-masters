<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(role.id)!}"/>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Role Name</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="roleName" id="roleName"  value="${(role.roleName)!}" placeholder="Role Name">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">Comment</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="roleRemark" id="roleRemark" value="${(role.roleRemark)!}" placeholder="Comment">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateRole">Confirm
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">Cancel</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/role/add.update.js"></script>
</body>
</html>