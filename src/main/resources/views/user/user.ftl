<!DOCTYPE html>
<html>
    <head>
        <title>User Mgmt</title>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" >
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" name="userName" class="layui-input searchVal" placeholder="Username" />
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" name="email" class="layui-input searchVal" placeholder="Email" />
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" name="phone" class="layui-input searchVal" placeholder="Phone" />
                        </div>
                        <a class="layui-btn search_btn" data-type="reload">
                            <i class="layui-icon">&#xe615;</i>
                            Search
                        </a>
                    </div>
                </form>
            </blockquote>

            <table id="userList" class="layui-table"  lay-filter="users"></table>

            <script type="text/html" id="toolbarDemo">
                <div class="layui-btn-container">
<#--                    <#if permissions?seq_contains("201001")>-->
                    <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                        <i class="layui-icon">&#xe608;</i>
                        Add User
                    </a>
<#--                    </#if>-->
<#--                    <#if permissions?seq_contains("201004")>-->
                    <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                        <i class="layui-icon">&#xe608;</i>
                        Delete User
                    </a>
<#--                    </#if>-->
                </div>
            </script>

            <script id="userListBar" type="text/html">
<#--                <#if permissions?seq_contains("201003")>-->
                <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">Edit</a>
<#--                </#if>-->
<#--                <#if permissions?seq_contains("201004")>-->
                <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">Delete</a>
<#--                </#if>-->
            </script>
        </form>

    <script type="text/javascript" src="${ctx}/js/user/user.js"></script>
    </body>
</html>