layui.use(['table','layer'], function() {
    var layer = layui.layer;
    var table = layui.table;
    var $ = layui.$;
    

    var querySysRoleForm = $('#querySysRoleForm');

    // height: 472,
    var tableIns = table.render({
        id: 'sys_role_list',
        elem: '#sys_role_list',
        url: querySysRoleForm.attr('action'),
        cols: [
            [
                { field: 'id', title: 'ID', width: 80 },
                { field: 'rolename', title: '角色名称', width: 300 },
                { field: 'viewname', title: '显示名称', width: 200, sort: true, edit: true },
                { fixed: 'right', width: 80, align: 'center', toolbar: '#sys_role_list_toolbar' }
            ]
        ],
        page: true
    });

    table.on('tool(sys_role_list)', function(obj) {
        // console.log(obj);
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function(index) {
                layer.close(index);
                $.post(SITE_CONTEXT_PATH + '/sys_role/remove.json',{id: data.id},function(data,textStatus,jqXHR){
                    if(data.code == 0){
                        obj.del();
                    } else {
                        layer.msg(data.msg, {icon: 5}); 
                    }
                },'json');
            });
        }
    });

    table.on('edit(sys_role_list)', function(obj) {
        // console.log(obj);
        var data = obj.data;
        var field = obj.field;
        var value = obj.value;

        var updateData = {};
        updateData['id'] = data.id;
        updateData[field] = value;

        if(data[field] != value){
            $.post(SITE_CONTEXT_PATH + '/sys_role/update.json',updateData,function(data,textStatus,jqXHR){
                    if(data.code == 0){
                        data[field] = value
                    } else {
                        layer.msg(data.msg, {icon: 5}); 
                    }
                },'json');
        }

        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function(index) {
                layer.close(index);
                $.post(SITE_CONTEXT_PATH + '/sys_role/remove.json',{id: data.id},function(data,textStatus,jqXHR){
                    if(data.code == 0){
                        obj.del();
                    } else {
                        layer.msg(data.msg, {icon: 5}); 
                    }
                },'json');
            });
        }
    });


    $('#querySysRoleBtn').click(function(event) {
        // var queryData = querySysRoleForm.serializeJSON();
        var queryData = {viewname: jQuery('#viewname',querySysRoleForm).val()};
        tableIns.reload({
            where: queryData
        });
    });


    $('#addSysRoleBtn').click(function(event) {
        // var queryData = querySysRoleForm.serializeJSON();
        var content = $('#sys_role_index_save').html();
        layer.open({type:1,title: false,content: content});
    });

});