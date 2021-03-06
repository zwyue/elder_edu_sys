    //左侧导航
    layui.use('element', function () {
       var element = layui.element;
    });

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

    $(function () {
       DatatoLoad();
    });

    function DatatoLoad() {
        //分页
        var pageNum = 0;    //初始化页数为第一页
        var pageSize = 10;   //列表数量为7条
        layui.use('laypage', function () {
            var laypage = layui.laypage;
            var totalSize = 0; //初始化数据总数
            getDatasSize();

            function getDatasSize() {
                $.ajax({
                    type: 'get',
                    async: false,
                    url: "api/classroom/clsRmUsageHistory",
                    data: {
                        roomid:id,
                        page: pageNum,
                        size: pageSize
                    },
                    dataType: "json",
                    xhrFields: {withCredentials: true},
                    success: function (data) {
                        totalSize = data.data.total; //获得数据总条数
                        var html = '';
                        var len = data.data.length;
                        for (var i = 1; i < len + 1; i++) {
                            $('table tr:eq(' + i + ')td:first').text(i);
                        }
                        $.each(data.data.list, function (i, result) {
                            html += "<tr>";
                            html += "<td>" + (i + 1) + "</td>" + "<td>" + result.classname + "</td>" + "<td>" + result.starttime + "</td>" +
                                "<td>" + result.endtime + "</td>" + "<td>" + result.tname + "</td>"+ "<td>" + result.coursename + "</td>";
                            html += "</tr>";
                        })
                        $("#listSyls").html(html);
                    },
                    error: function(error) {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg(error.responseText);
                        });
                    }
                })
            }

            //执行一个laypage
            laypage.render({
                elem: 'demo5',    //分页容器，此处是id
                count: totalSize, //数据总数，从服务端得到
                curr: pageNum,
                limit: pageSize,
                jump: function (obj, first) {
                    pageNum = obj.curr;     //得到当前页，以便向服务端请求对应页的数据。
                    pageSize = obj.limit;   //得到每页显示的条数

                    //首次不执行
                    if (!first) {
                        $("#listSyls").empty();
                        getDatasSize();
                    }
                }
            })
        })
    }

