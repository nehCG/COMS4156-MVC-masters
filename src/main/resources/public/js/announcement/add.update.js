layui.use(['form', 'layer', 'formSelects'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var formSelects = layui.formSelects;

    form.on('submit(addOrUpdateAnnouncement)', function (data) {

        var index = top.layer.msg("Submitting...",{
            icon:16,
            time:false,
            shade:0.8
        });

        var formData = data.field;
        console.log("hereee", formData);


        var url = ctx + "/announcement/post";

        // if ($("[name='id']").val()) {
        //     var url = ctx + "/user/update";
        // }

        //
        $.post(url, formData, function (result) {
            if (result.code == 200) {
                top.layer.msg("Success！",{icon:6});
                top.layer.close(index);
                layer.closeAll("iframe");
                parent.location.reload();
            } else {
                layer.msg(result.msg, {icon:5});
            }
        });
        return false;
    });

    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

    var userId = $("[name='id']").val();
    formSelects.config("selectId",{
        type:"post",
        searchUrl: ctx+"/role/queryAllRoles?userId="+userId,
        keyName: 'roleName',
        keyVal: 'id'
    }, true);

});