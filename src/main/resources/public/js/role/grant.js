$(function () {
    loadModuleData();
});

var zTreeObj;

function loadModuleData() {
    var setting = {
        check:{
            enable:true
        },
        data:{
            simpleData:{
                enable: true
            }
        },
        callback: {
            onCheck: zTreeOnCheck
        }
    };

    $.ajax({
        type:"get",
        url:ctx + "/module/queryAllModules",
        data:{
            roleId:$("[name='roleId']").val()
        },
        dataType:"json",
        success:function (data) {
            zTreeObj = $.fn.zTree.init($("#test1"), setting, data);
        }
    });
}

function zTreeOnCheck(event, treeId, treeNode) {
    var nodes = zTreeObj.getCheckedNodes(true);
    if (nodes.length > 0) {
        var mIds = "mIds=";
        for (var i = 0; i < nodes.length; i++) {
            if (i < nodes.length-1) {
                mIds += nodes[i].id + "&mIds=";
            } else {
                mIds += nodes[i].id;
            }
        }
        console.log(mIds);
    }

    var roleId = $("[name='roleId']").val();

    $.ajax({
        type:"post",
        url:ctx + "/role/addGrant",
        data:mIds+"&roleId="+roleId,
        dataType:"json",
        success:function (data) {
            console.log(data);
        }
    });
};
