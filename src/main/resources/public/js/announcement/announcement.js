layui.use(['table', 'layer', 'jquery', 'jquery_cookie'], function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        cookie = layui.jquery_cookie($);

    function getLoggedInUserName() {
        return $.cookie('userName');
    }

    var loggedInUserName = getLoggedInUserName();
    var userMap = {};
    var tableIns;

    async function fetchUserData() {
        try {
            let response = await $.ajax({
                url: ctx + '/user/list',
                type: 'GET'
            });

            response.data.forEach(user => {
                userMap[user.id] = user.userName;
            });

            let loggedInUserId = Object.keys(userMap).find(key => userMap[key] === loggedInUserName);
            return loggedInUserId;
        } catch (err) {
            console.error("Error fetching users:", err);
            return null;
        }
    }

    async function checkIfAdmin(userId) {
        try {
            let data = await $.ajax({
                type: "GET",
                url: ctx + `/role/queryAllRoles?userId=${userId}`
            });
            return data.some(role => role.roleName === 'admin' && role.selected === 'selected');
        } catch (error) {
            console.error('Error fetching roles:', error);
            return false;
        }
    }

    async function initializeTable() {
        let loggedInUserId = await fetchUserData();
        let isAdmin = false;
        if (loggedInUserId) {
            isAdmin = await checkIfAdmin(loggedInUserId);
        }
        renderTable(isAdmin);
    }
    function renderTable(isAdmin) {
        tableIns = table.render({
            id: 'announcementTable',
            elem: '#announcementList',
            height: 'full-125',
            cellMinWidth: 95,
            url: ctx + '/announcement/all', // URL to fetch announcements
            page: true,
            limit: 10,
            limits: [10, 20, 30, 40, 50],
            toolbar: '#toolbarDemo',
            cols: [[
                {field: 'uid', title: 'Posted By', align: 'center', templet: function(d) {
                        return userMap[d.uid] || 'Unknown'; // Use the userMap to get the userName for each uid
                    }},
                {field: 'subject', title: 'Subject', align: 'center'},
                {field: 'content', title: 'Content', align: 'center'},
                {field: 'modifiedTime', title: 'Last Updated On', align: 'center', templet: function(d) {
                        // Use modifiedTime if it's not empty, otherwise use createdTime
                        var dateStr = d.modifiedTime || d.createdTime;
                        if (dateStr) {
                            var date = new Date(dateStr);
                            var year = date.getFullYear();
                            var month = date.getMonth() + 1; // getMonth() returns 0-11
                            var day = date.getDate();
                            var hour = date.getHours();
                            var minute = date.getMinutes();

                            // Format month, day, hour, and minute to ensure they are always two digits
                            month = ('0' + month).slice(-2);
                            day = ('0' + day).slice(-2);
                            hour = ('0' + hour).slice(-2);
                            minute = ('0' + minute).slice(-2);

                            return year + '-' + month + '-' + day + ' ' + hour + ':' + minute; // Format: YYYY-MM-DD HH:MM
                        } else {
                            return ''; // or return some default value or message
                        }
                    }},
                {title:'Operate', templet: function(d) {
                        if (userMap[d.uid] === loggedInUserName || isAdmin) {
                            console.log("uid",d.uid, "loggedinUserName",loggedInUserName, $.cookie())
                            return '<a class="layui-btn layui-btn-xs" lay-event="edit">Edit</a>' +
                                '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">Delete</a>';
                        } else {
                            return 'View Only'; // No buttons if user IDs don't match
                        }
                    }, fixed: 'right', align:'center', minWidth:150}
            ]]
        });

    }



// Fetch user data, then render the table
    initializeTable();

    window.userMap = userMap;
    top.userMap = userMap;
    console.log(userMap, window.userMap)

    // Toolbar event handling
    table.on('toolbar(announcements)', function(data) {
        if (data.event === "add") {
            openAddOrUpdateAnnouncementDialog(data.data);
        }
        // else if (data.event === "del") {
        //     var checkStatus = table.checkStatus(data.config.id);
        //     deleteAnnouncements(checkStatus.data);
        // }
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
    function openAddOrUpdateAnnouncementDialog(announcementId) {
        // redirect url and populate title based on announcementID presence
        var title = announcementId ? "<h3>Announcement - Update Announcement</h3>" : "<h3>Announcement - Add Announcement</h3>";
        var url = ctx + "/announcement/toAddOrUpdateAnnouncementPage" + (announcementId ? "?id=" + announcementId : "");

        layui.layer.open({
            type: 2,
            title: title,
            area: ['650px', '400px'],
            content: url,
            maxmin:true
        });
    }
    function fetchAnnouncementData(announcementId) {
        $.ajax({
            url: ctx + '/announcement/' + announcementId,
            type: 'GET',
            success: function(response) {
                // Populate the form with the response data
                console.log(response)
                populateAnnouncementForm(response);

            },
            error: function(err) {
                console.error("Error fetching announcement:", err);
            }
        });
    }
    function populateAnnouncementForm(data) {
        // Populate the form fields with the data
        console.log(data)
        $('#Subject').val(data.subject);
        $('#Content').val(data.content);
    }
    // Row event handling
    table.on('tool(announcements)', function(data) {
        if (data.event === "edit") {
            openAddOrUpdateAnnouncementDialog(data.data.id);
        } else if (data.event === "del") {
            deleteAnnouncement(data.data.id);
        }
    });

    // Function to delete a single announcement
    // function deleteAnnouncements(id) {
    //     // Implementation similar to deleteUser in your user.js
    // }

});
