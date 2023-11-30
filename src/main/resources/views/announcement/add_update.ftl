<!DOCTYPE html>
<html>
    <head>
        <#include "../common.ftl">
    </head>
    <body class="childrenBody">
        <form class="layui-form" style="width:80%;">
            <input name="id" type="hidden" value="${(sharedDataInfo.id)!}"/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">Subject</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input subject"
                           lay-verify="required" name="subject" id="subject"  value="${(sharedDataInfo.subject)!}" placeholder="Announcement subject">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">Content</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input content"
                           lay-verify="required" name="content" id="content" value="${(sharedDataInfo.content)!}" placeholder="Announcement content">
                </div>
            </div>
            <div class="layui-form-item layui-row layui-col-xs12">
                <label class="layui-form-label">Uid</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input uid"
                           lay-verify="uid" name="uid" value="${(sharedDataInfo.uid)!}"
                           id="uid"
                           placeholder="Please enter uid">
                </div>
            </div>

<#--            <div class="layui-form-item layui-row layui-col-xs12">-->
<#--                <label class="layui-form-label">Phone</label>-->
<#--                <div class="layui-input-block">-->
<#--                    <input type="text" class="layui-input userEmail"-->
<#--                           lay-verify="phone" name="phone" value="${(userInfo.phone)!}" id="phone" placeholder="Please enter phone">-->
<#--                </div>-->
<#--            </div>-->

<#--            <div class="layui-form-item layui-row layui-col-xs12">-->
<#--                <label class="layui-form-label">Role</label>-->
<#--                <div class="layui-input-block">-->
<#--                    <select name="roleIds"  xm-select="selectId">-->
<#--                    </select>-->
<#--                </div>-->
<#--            </div>-->


            <br/>
            <div class="layui-form-item layui-row layui-col-xs12">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-lg" lay-submit=""
                            lay-filter="addOrUpdateAnnouncement">Confirm
                    </button>
                    <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">Cancel</button>
                </div>
            </div>
        </form>

    <script type="text/javascript" src="${ctx}/js/announcement/add.update.js"></script>
    </body>
</html>