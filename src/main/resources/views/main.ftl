<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>EMS</title>
    <#include "common.ftl">
</head>

<body class="layui-layout-body layuimini-all">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header header">
            <div class="layui-logo">
                <a href="">
                    <img src="images/logo.png" alt="logo">
                    <h1>EMS</h1>
                </a>
            </div>
            <a>
                <div class="layuimini-tool"><i title="Expand" class="fa fa-outdent" data-side-fold="1"></i></div>
            </a>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                    <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
                </li>
                <li class="layui-nav-item layuimini-setting">
                    <a href="javascript:;">${(user.userName)!""}</a>

                    <dl class="layui-nav-child">
                        <dd>
                            <a href="javascript:;" data-iframe-tab="${ctx}/user/toSettingPage" data-title="User Info"
                               data-icon="fa fa-gears">User</a>
                        </dd>
                        <dd>
                            <a href="javascript:;" data-iframe-tab="${ctx}/user/toPasswordPage" data-title="Change Password"
                               data-icon="fa fa-gears">Pwd</a>
                        </dd>
                        <dd>
                            <a href="javascript:;" class="login-out">Logout</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                    <a href="javascript:;"></a>
                </li>
            </ul>
        </div>

        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll layui-left-menu">
                <#if permissions??>
                <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">
                    <#if permissions?seq_contains("10")>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-desktop"></i><span
                                    class="layui-left-nav"> Shared Space</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <#if permissions?seq_contains("1010") >
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-5"
                                   data-tab="announcement/index" target="_self"><i
                                            class="fa fa-tachometer"></i><span class="layui-left-nav"> Announcement</span></a>
                            </dd>
                            </#if>
                        </dl>
                    </li>
                    </#if>
                    <#if permissions?seq_contains("20") >
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span
                                    class="layui-left-nav"> Management</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <#if permissions?seq_contains("2010")>
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                   data-tab-mpi="m-p-i-11" data-tab="user/index" target="_self"><i
                                            class="fa fa-user"></i><span class="layui-left-nav"> User Mgmt</span></a>
                            </dd>
                            </#if>
                            <#if permissions?seq_contains("2020")>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd"
                                   data-tab-mpi="m-p-i-12" data-tab="role/index" target="_self"><i
                                            class="fa fa-tachometer"></i><span class="layui-left-nav"> Role Mgmt</span></a>
                            </dd>
                            </#if>
                        </dl>
                    </li>
                    </#if>
                    <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
                </ul>
                </#if>
            </div>
        </div>

        <div class="layui-body">
            <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
                <ul class="layui-tab-title" id="top_tabs">
                    <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i>
                        <span>Main Page</span></li>
                </ul>

                <ul class="layui-nav closeBox">
                    <li class="layui-nav-item">
                        <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> Page</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i>
                                    Others</a></dd>
                            <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i>
                                    All</a></dd>
                        </dl>
                    </li>
                </ul>
                <div class="layui-tab-content clildFrame">
                    <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                    </div>
                </div>
            </div>
        </div>

    </div>

    <script type="text/javascript" src="${ctx}/js/main.js"></script>
</body>
</html>
