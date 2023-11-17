<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Change Password</title>
    <#include "../common.ftl">
    <style>
        .layui-form-item .layui-input-company {
            width: auto;
            padding-right: 10px;
            line-height: 38px;
        }
    </style>
</head>

<body>
    <div class="layuimini-container">

        <div class="layuimini-main">

            <div class="layui-form layuimini-form">

                <div class="layui-form-item">
                    <label class="layui-form-label required">Old Password</label>
                    <div class="layui-input-block">
                        <input type="password" name="old_password" lay-verify="required" lay-reqtext="Old Password cannot be empty"
                               placeholder="Please enter old password" value="" class="layui-input">
                        <tip>Enter the old password of your account.</tip>
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label required">New Password</label>
                    <div class="layui-input-block">
                        <input type="password" name="new_password" lay-verify="required" lay-reqtext="New Password cannot be empty"
                               placeholder="Please enter new password" value="" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label required">New Password</label>
                    <div class="layui-input-block">
                        <input type="password" name="again_password" lay-verify="required" lay-reqtext="Confirm New Password cannot be empty"
                               placeholder="Please confirm new password" value="" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit lay-filter="saveBtn">Update Password</button>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <script type="text/javascript" src="${ctx}/js/user/password.js"></script>

</body>
</html>