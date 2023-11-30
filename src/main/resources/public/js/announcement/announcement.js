layui.use(['table', 'layer','jquery', 'jquery_cookie'], function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        cookie = layui.jquery_cookie($);
    // Function to get logged-in user's information
    function getLoggedInUserName() {
        return $.cookie('userName');
    }
    var loggedInUserName = getLoggedInUserName();
    // Initialize userMap to hold userID: userName pairs
    var userMap = {};
    $.ajax({
        url: ctx+'/user/list', // Replace with your actual endpoint
        type: 'GET',
        async : false,
        success: function(response) {
            var users = response.data;
            for (var i = 0; i < users.length; i++) {
                userMap[users[i].id] = users[i].userName;
            }
        },
        error: function(err) {
            // Handle any errors
            console.error("Error fetching users:", err);
        }
    });

    // Render the announcements table
    var tableIns = table.render({
        id: 'announcementTable',
        elem: '#announcementList',
        height: 'full-125',
        cellMinWidth: 95,
        url: ctx+'/announcement/all', // URL to fetch announcements
        page: true,
        limit: 10,
        limits: [10, 20, 30, 40, 50],
        toolbar: '#toolbarDemo',
        cols: [[
            {field: 'uid', title: 'Posted By', align: 'center', templet: function(d) {
                    // Use the userMap to get the userName for each uid
                    return userMap[d.uid]; // Replace 'Unknown' as needed
                }},
            {field: 'subject', title: 'Subject', align: 'center'},
            {field: 'content', title: 'Content', align: 'center'},
            {field: 'modifiedTime', title: 'Modified Time', align: 'center'},
            {title:'Operate',templet: function(d) {
                    // Check if the announcement's user ID matches the logged-in user's ID
                    if (userMap[d.uid] === loggedInUserName) {
                        return '<a class="layui-btn layui-btn-xs" lay-event="edit">Edit</a>' +
                            '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">Delete</a>';
                    } else {
                        return 'View Only'; // No buttons if user IDs don't match
                    }
                }, fixed: 'right', align:'center', minWidth:150}
        ]]
    });

    // Toolbar event handling
    table.on('toolbar(announcements)', function(data) {
        if (data.event === "add") {
            openAddOrUpdateAnnouncementDialog(data.data);
        } else if (data.event === "del") {
            var checkStatus = table.checkStatus(data.config.id);
            deleteAnnouncements(checkStatus.data);
        }
    });

    // Function to delete announcements
    function deleteAnnouncement(id) {
        layer.confirm('Confirm to delete?',{icon:3, title:"Announcement Management"}, function (index) {
            layer.close(index);

            $.ajax({
                type:"delete",
                url:ctx + "/announcement/delete/"+id,
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

    // Function to handle add or update announcement dialog
    function openAddOrUpdateAnnouncementDialog(id) {
        // Implementation similar to openAddOrUpdateUserDialog in your user.js
        var title = "<h3>Announcement - Add Announcement</h3>";
        var url = ctx + "/announcement/toAddOrUpdateAnnouncementPage";

        // if (id != null && id != '') {
        //     title = "<h3>Announcement - Update Announcement</h3>";
        //     url+= "?id="+id;
        // }

        layui.layer.open({
            type: 2,
            title: title,
            area: ['650px', '400px'],
            content: url,
            maxmin:true
        });
    }

    // Row event handling
    table.on('tool(announcements)', function(data) {
        if (data.event === "edit") {
            openAddOrUpdateAnnouncementDialog(data.id);
        } else if (data.event === "del") {
            deleteAnnouncement(data.data.id);
        }
    });

    // Function to delete a single announcement
    function deleteAnnouncements(id) {
        // Implementation similar to deleteUser in your user.js
    }
});
