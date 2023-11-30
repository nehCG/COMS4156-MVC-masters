layui.use(['form', 'layer', 'formSelects','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
        cookie = layui.jquery_cookie($);
    var formSelects = layui.formSelects;
    var userMap = parent.userMap || top.userMap;

    function getUserIdFromUsername() {
        username = $.cookie('userName')
        console.log(username)
        for (var userId in userMap) {
            if (userMap[userId] === username) {
                return userId;
            }
        }
        return null; // Return null if not found
    }

    form.on('submit(addOrUpdateAnnouncement)', function (data) {

        var index = top.layer.msg("Submitting...",{
            icon:16,
            time:false,
            shade:0.8
        });

        var formData = data.field;
        var loggedInUserId = getUserIdFromUsername(); // Replace with actual logged-in username
        console.log(loggedInUserId)
        formData.uid = loggedInUserId;

        // Determine the URL based on whether it's an add or update operation
        var announcementId = $("[name='id']").val(); // Assuming 'id' is the field for announcement ID
        var isUpdate = announcementId !== undefined && announcementId !== "";

        // Set the URL and method based on whether it's an add or update operation
        var url = ctx + (isUpdate ? "/announcement/update/" + announcementId : "/announcement/post");
        var method = isUpdate ? "PUT" : "POST";
        console.log(url, method, formData)
        $.ajax({
            type: method,
            url: url,
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: function (result) {
                if (result.code == 200) {
                    top.layer.msg("Success!", {icon: 6});
                    top.layer.close(index);
                    layer.closeAll("iframe");
                    parent.location.reload();
                } else {
                    layer.msg(result.msg, {icon: 5});
                }
            },
            error: function (err) {
                layer.msg('Error occurred.', {icon: 5});
                console.error("Error:", err);
            }
        });

        return false; // Prevent default form submission
    });

    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

    // var userId = $("[name='id']").val();
    // formSelects.config("selectId",{
    //     type:"post",
    //     searchUrl: ctx+"/role/queryAllRoles?userId="+userId,
    //     keyName: 'roleName',
    //     keyVal: 'id'
    // }, true);

});