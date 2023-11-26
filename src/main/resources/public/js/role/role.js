layui.use(['table','layer'],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

       var tableIns = table.render({
              id:'roleTable'
              ,elem: '#roleList'
              ,height: 'full-125'
              ,cellMinWidth:95
              ,url: ctx + '/role/list'
              ,page: true
              ,limit:10
              ,limits:[10,20,30,40,50]
              ,toolbar:'#toolbarDemo'
              ,cols: [[
                     {type:'checkbox', fixed:'center'}
                     ,{field: 'id', title: 'id',  sort: true, fixed: 'left'}
                     ,{field: 'roleName', title: 'Role Name', align:'center'}
                     ,{field: 'roleRemark', title: 'Role Remark', align:'center'}
                     ,{field: 'createDate', title: 'Create Date', align:'center'}
                     ,{field: 'updateDate', title: 'Update Date', align:'center'}
                     ,{title:'Operate',templet:'#roleListBar', fixed: 'right', align:'center', minWidth:150}
              ]]
       });

    $(".search_btn").click(function () {

        tableIns.reload({
            where: {
                roleName: $("[name='roleName']").val()
            }
            ,page: {
                curr: 1
            }
        });
    });

    table.on('toolbar(roles)',function (data) {
        if (data.event == "add") {
            openAddOrUpdateRoleDialog();
        } else if (data.event == "grant") {
            var checkStatus = table.checkStatus(data.config.id);
            openAddGrantDialog(checkStatus.data);
        }
    });

    function openAddOrUpdateRoleDialog(roleId) {
        var title = "<h3>Role Mgmt - Add Role</h3>"
        var url = ctx + "/role/toAddOrUpdateRolePage";

        if (roleId != null && roleId != '') {
            title = "<h3>Role Mgmt - Role Update</h3>";
            url += "?roleId=" + roleId;
        }

        layui.layer.open({
            title:title,
            content:url,
            area:["500px","400px"],
            type:2,
            maxmin:true
        });
    }

    table.on('tool(roles)',function (data) {
        if (data.event == "edit") {
            openAddOrUpdateRoleDialog(data.data.id);
        } else if (data.event == "del") {
            deleteRole(data.data.id);
        }
    });

    function deleteRole(roleId) {
        layer.confirm('Confirm to delete?',{icon:3, title:"Role Mgmt"}, function (index) {
            layer.close(index);
            $.ajax({
                type:"post",
                url:ctx + "/role/delete",
                data:{
                    roleId:roleId
                },
                success:function (result) {
                    if (result.code == 200) {
                        layer.msg("Deleted!",{icon:6});
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon:5});
                    }
                }
            });
        });
    }

    function openAddGrantDialog(data) {
        // 判断是否选择了角色记录
        if (data.length == 0) {
            layer.msg("Please select the role you want to authorize!",{icon:5});
            return;
        }
        // 只支持单个角色授权
        if (data.length > 1) {
            layer.msg("Multiple role authorize is not supported yet!",{icon:5});
            return;
        }

        var url = ctx + "/module/toAddGrantPage?roleId="+data[0].id;
        var title = "<h3>Role Mgmt - Role Authorize</h3>";
        layui.layer.open({
            title:title,
            content:url,
            type:2,
            area:["600px","600px"],
            maxmin: true
        });

    }
});
