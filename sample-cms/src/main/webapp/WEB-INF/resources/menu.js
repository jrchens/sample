layui.use(['element'], function() {
    var $ = layui.$,
    element = layui.element;

    element.on('nav(first_level_menu)', function(elem) {
        var first_level_menu_id = $(elem).attr('id');
        layui.data('first_level_menu',{key:'first_level_menu_id',value:first_level_menu_id});
    });

    element.on('nav(second_level_menu)', function(elem) {
        var second_level_menu_id = $(elem).attr('id');
        layui.data('second_level_menu',{key:'second_level_menu_id',value:second_level_menu_id});
    });


    var lsd_first_level_menu_id = layui.data('first_level_menu').first_level_menu_id;
    $('#first_level_menu > li').each(function(idx,elem){
        var id = $(elem).attr('id');
        if(lsd_first_level_menu_id == id){
            $(elem).addClass('layui-this');
        }
    });

    var lsd_second_level_menu_id = layui.data('second_level_menu').second_level_menu_id;
    $('#second_level_menu > li').each(function(idx,elem){
        var id = $(elem).attr('id');
        if(lsd_second_level_menu_id == id){
            $(elem).addClass('layui-this');
        }
    });

});