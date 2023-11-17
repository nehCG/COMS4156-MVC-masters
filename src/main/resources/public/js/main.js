layui.use(['element', 'layer', 'layuimini', 'jquery', 'jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();

    $(".login-out").click(function () {
        layer.confirm('Are you sure to exit？', {icon: 3, title: 'System Prompt'}, function (index) {
            layer.close(index);

            $.removeCookie("userIdStr", {domain: "localhost", path: "/ems"});
            $.removeCookie("userName", {domain: "localhost", path: "/ems"});
            $.removeCookie("trueName", {domain: "localhost", path: "/ems"});

            window.parent.location.href = ctx + "/index";
        });
    });
});