//获取浏览地址id
(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
})(jQuery);
var id = $.getUrlParam('id');
// 查询到id，并把表格页面的值带到修改页面
$(function () {
    $.ajax({
        type: 'GET',
        url: "api/teachmanager/query",
        async: false,
        data: {
            id: id
        },
        dataType: 'json',
        xhrFields: {withCredentials: true},
        success: function (data) {
            console.log(data);
            $("#leaveName").val(data.data.auditorname);
            $("#testblue1").val(data.data.starttime);
            $("#testblue2").val(data.data.endtime);
            $("#qjbj").val(data.data.classname);
            $("#testblue3").val(data.data.bkkssj);
            $("#testblue4").val(data.data.bkjssj);
            $("#MakeupClass").val(data.data.roomname);
            $("#qjzc").val(data.data.weeks);
            $("#suggest").text(data.data.issure);
            $('input').attr("disabled",true).addClass("layui-disabled");
            $('textarea').attr("disabled",true).addClass("layui-disabled");
            $('select').attr("disabled",true).addClass("layui-disabled");
        },
        error: function(error) {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg(error.responseText);
            });
        }
    });
});