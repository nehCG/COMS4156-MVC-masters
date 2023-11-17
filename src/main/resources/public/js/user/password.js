layui.use(['form', 'jquery', 'jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    form.on('submit(saveBtn)', function (data) {
        $.ajax({
            type: "post",
            url: ctx + "/user/updatePwd",
            data: {
                oldPassword: data.field.old_password,
                newPassword: data.field.new_password,
                repeatPassword: data.field.again_password
            },
            success: function (result) {
                if (result.code == 200) {
                    layer.msg("Password changed successfully, exit in 3 seconds...", function () {
                        $.removeCookie("userIdStr", {domain: "localhost", path: "/ems"});
                        $.removeCookie("userName", {domain: "localhost", path: "/ems"});
                        $.removeCookie("trueName", {domain: "localhost", path: "/ems"});

                        window.parent.location.href = ctx + "/index";
                    });
                } else {
                    layer.msg(result.msg, {icon: 5})
                }
            }
        });
    });
});