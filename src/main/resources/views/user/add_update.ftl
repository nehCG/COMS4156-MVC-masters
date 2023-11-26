<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <input name="id" type="hidden" value="${(userInfo.id)!}"/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">Username</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="userName" id="userName"  value="${(userInfo.userName)!}" placeholder="Please enter username">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">True Name</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userName"
                           lay-verify="required" name="trueName" id="trueName" value="${(userInfo.trueName)!}" placeholder="Please enter true name">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">Email</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userEmail"
                           lay-verify="email" name="email" value="${(userInfo.email)!}"
                           id="email"
                           placeholder="Please enter email">
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">Phone</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input userEmail"
                           lay-verify="phone" name="phone" value="${(userInfo.phone)!}" id="phone" placeholder="Please enter phone">
                </div>
            </div>

            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">Role</label>
                <div class="layui-input-block">
                    <select name="roleIds"  xm-select="selectId">
                    </select>
                </div>
            </div>


            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="addOrUpdateUser">Confirm
                    </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">Cancel</button>
                </div>
            </div>
        </form>

    <script type="text/javascript" src="${ctx}/js/user/add.update.js"></script>
    </body>
</html>