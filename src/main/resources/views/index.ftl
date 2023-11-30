<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Star Preschool</title>
    <#include "common.ftl">
    <link rel="stylesheet" href="${ctx}/css/index.css" media="all">
</head>

<body>
    <div class="layui-container">
        <div class="admin-login-background">
            <div class="layui-form login-form">
                <form class="layui-form" action="">
                    <div class="layui-form-item logo-title">
                        <h1>Star Preschool</h1>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-icon layui-icon-username" for="username"></label>
                        <input type="text" name="username" lay-verify="required|account" placeholder="Username"
                               autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-icon layui-icon-password" for="password"></label>
                        <input type="password" name="password" lay-verify="required|password" placeholder="Password"
                               autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-form-item">
                        <input type="checkbox" name="rememberMe" id="rememberMe" value="true" lay-skin="primary" title="Remember Password">
                    </div>
                    <div class="layui-form-item">
                        <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="login">Sign In</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="${ctx}/js/index.js" charset="utf-8"></script>
</body>
</html>