
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
                $("#name").text(data.data.tname);
                $("#class").text(data.data.classname);
                $("#sqTime").text(data.data.leavedate);
                $("#time").text(data.data.starttime + "--" + data.data.endtime);
                $("#bkTime").text(data.data.bkkssj + "--" + data.data.bkjssj);
                $("#bkClass").text(data.data.roomname);
                $("#suggest").text(data.data.issure);
            },
            error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
            }
        });
    });