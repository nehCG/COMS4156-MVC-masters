<!DOCTYPE html>
<html>
<head>
    <title>Announcement</title>
    <#include "../common.ftl"> <!-- Assuming common.ftl includes necessary CSS and JS files -->
</head>
<body class="childrenBody">

<!-- Search and filter options for announcements -->
<#--<form class="layui-form">-->
<#--    <blockquote class="layui-elem-quote quoteBox">-->
<#--        <div class="layui-inline">-->
<#--            <div class="layui-input-inline">-->
<#--                <input type="text" name="title" class="layui-input searchVal" placeholder="Title" />-->
<#--            </div>-->
<#--            <div class="layui-input-inline">-->
<#--                <input type="text" name="date" class="layui-input searchVal" placeholder="Date (YYYY-MM-DD)" />-->
<#--            </div>-->
<#--            <a class="layui-btn search_btn" data-type="reload">-->
<#--                <i class="layui-icon">&#xe615;</i>-->
<#--                Search-->
<#--            </a>-->
<#--        </div>-->
<#--    </blockquote>-->
<#--</form>-->

<!-- Table to display announcements -->
<table id="announcementList" class="layui-table" lay-filter="announcements"></table>

<!-- Toolbar for actions like adding a new announcement -->
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <a class="layui-btn layui-btn-normal addAnnouncement_btn" lay-event="add">
            <i class="layui-icon">&#xe608;</i>
            Add Announcement
        </a>
<#--        <a class="layui-btn layui-btn-danger delAnnouncement_btn" lay-event="del">-->
<#--            <i class="layui-icon">&#xe640;</i>-->
<#--            Delete Announcement-->
<#--        </a>-->
    </div>
</script>

<!-- Script template for each row in the announcement table -->
<script id="announcementListBar" type="text/html">
    <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">Edit</a>
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">Delete</a>
</script>

<script type="text/javascript" src="${ctx}/js/announcement/announcement.js"></script>
</body>
</html>
