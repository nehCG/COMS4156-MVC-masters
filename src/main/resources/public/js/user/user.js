layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    var tableIns = table.render({
        id:'userTable'
        ,elem: '#userList'
        ,height: 'full-125'
        ,cellMinWidth:95
        ,url: ctx + '/user/list'
        ,page: true
        ,limit:10
        ,limits:[10,20,30,40,50]
        ,toolbar:'#toolbarDemo'
        ,cols: [[
            {type:'checkbox', fixed:'center'}
            ,{field: 'id', title: 'id',  sort: true, fixed: 'left'}
            ,{field: 'userName', title: 'Username', align:'center'}
            ,{field: 'trueName', title: 'True Name', align:'center'}
            ,{field: 'email', title: 'Email', align:'center'}
            ,{field: 'phone', title: 'Phone', align:'center'}
            ,{title:'Operate',templet:'#userListBar', fixed: 'right', align:'center', minWidth:150}
        ]]
    });


    $(".search_btn").click(function () {

        tableIns.reload({
            where: {
                userName: $("[name='userName']").val()
                ,email: $("[name='email']").val()
                ,phone:$("[name='phone']").val()
            }
            ,page: {
                curr: 1
            }
        });

    });

    table.on('toolbar(users)', function (data) {

        if (data.event == "add") {

            openAddOrUpdateUserDialog();

        } else if (data.event == "del") {
            var checkStatus = table.checkStatus(data.config.id);
            console.log(checkStatus);
            deleteUsers(checkStatus.data);
        }

    });


    function deleteUsers(userData) {
        if (userData.length == 0) {
            layer.msg("Please select the records to delete!", {icon:5});
            return;
        }

        layer.confirm('Confirm to delete?',{icon:3, title:'User Mgmt'}, function (index) {
            layer.close(index);
            var ids = "ids=";
            for(var i = 0; i < userData.length; i++) {
                if(i < userData.length -1) {
                    ids = ids + userData[i].id + "&ids="
                } else {
                    ids = ids + userData[i].id;
                }
            }

            $.ajax({
                type:"post",
                url:ctx + "/user/delete",
                data:ids,
                success:function (result) {
                    if (result.code == 200) {
                        layer.msg("Deleted！",{icon:6});
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon:5});
                    }
                }
            });
        });

    }


    table.on('tool(users)', function (data) {

        if (data.event == "edit") {

            openAddOrUpdateUserDialog(data.data.id);

        } else if (data.event == "del") {
            deleteUser(data.data.id);
        }

    });

    function deleteUser(id) {
        layer.confirm('Confirm to delete?',{icon:3, title:"User Mgmt"}, function (index) {
            layer.close(index);

            $.ajax({
                type:"post",
                url:ctx + "/user/delete",
                data:{
                    ids:id
                },
                success:function (result) {
                    if (result.code == 200) {
                        layer.msg("Deleted！",{icon:6});
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon:5});
                    }
                }
            });
        });
    }


    function openAddOrUpdateUserDialog(id) {
        var title = "<h3>User Mgmt - Add User</h3>";
        var url = ctx + "/user/toAddOrUpdateUserPage";

        if (id != null && id != '') {
            title = "<h3>User Mgmt - Update User</h3>";
            url+= "?id="+id;
        }

        layui.layer.open({
            type: 2,
            title: title,
            area: ['650px', '400px'],
            content: url,
            maxmin:true
        });
    }
});