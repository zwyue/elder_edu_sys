    //左侧导航
    layui.use('element', function () {
        var element = layui.element;
    });
    //from表单select 通用
    layui.use('form', function(){
        var form = layui.form;
    });

    //时间控件
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        lay('.test-item').each(function () {
            laydate.render({
                elem: this
                , type: 'datetime'
            });
        });
    });

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
                    url: "api/classroom/queryvacantclsroom",
                    data: {
                        page: pageNum,
                        size: pageSize
                    },
                    dataType: "json",
                    xhrFields: {withCredentials: true},
                    success: function (data) {
                        totalSize = data.data.total; //获得数据总条数
                        var html = '';
                        var len = data.data.list.length;
                        for (var i = 1; i < len + 1; i++) {
                            $('table tr:eq(' + i + ')td:first').text(i);
                        }
                        $.each(data.data.list, function (i, result) {
                            html += "<tr>";
                            html += "<td>" + (i + 1) + "</td>" + "<td>" + result.classroom + "</td>" + "<td>" + result.catename + "</td>" +
                                "<td>" + result.address + "</td>" + "<td>" + result.purpose + "</td>";
                            html += "</tr>";
                        })
                        $("#listJslb").html(html);
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
                        $("#listJslb").empty();
                        getDatasSize();
                    }
                }
            })
        })
    }

    //获得文本框时间段
    $(function () {
        $.ajax({
            type:"get",
            url:"api/classroom/courselist",
            dataType: "json",
            async: false,
            xhrFields: {withCredentials: true},
            success:function(data){
                var i=0;
                $.each(data.data,function (i,result) {
                    var index=data.data[i].id;
                    var text=data.data[i].time;
                    $("#timeDun").append('<option value="'+index+'">'+text+'</option>');
                })
            },
            error: function(error) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    layer.msg(error.responseText);
                });
            }
        });
    })

    //查询
    function chaxun() {
        //验证查询文本框
        var weeklyDay = $('#weekDay option:selected').val();
        var timeDun = $('#timeDun option:selected').text();
        var startTime = $('#test04').val();
        var endTime = $('#test05').val();
        if (weeklyDay == '' || timeDun=='' || startTime=='' || endTime=='') {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert('请选择查询条件', {area: ['200px']});
            });
            return false;
        }
        else {
            //分页
            var pageNum = 0;    //初始化页数为第一页
            var pageSize = 10;   //列表数量为7条
            layui.use('laypage', function () {
                var laypage = layui.laypage;
                var totalSize = 0; //初始化数据总数
                getQuery();

                function getQuery() {

                    $.ajax({
                        type: 'GET',
                        url: "api/classroom/queryvacantclsroom",
                        async: false,
                        data: {
                            week: weeklyDay,
                            date: timeDun,
                            starttime: startTime,
                            endtime: endTime,
                            page: pageNum,
                            size: pageSize
                        },
                        dataType: "json",
                        xhrFields: {withCredentials: true},
                        success: function (data) {
                            if (data.data.list.length == 0) {
                                layui.use('layer', function () {
                                    var layer = layui.layer;
                                    layer.alert('请选择全部日期', {area: ['200px']});
                                });
                                $("#listJslb").html('');
                                return false;
                            }
                            else {
                                totalSize = data.data.total;
                                var chaXun;
                                var len = data.data.list.length;
                                for (var i = 1; i < len + 1; i++) {
                                    $('table tr:eq(' + i + ')td:first').text(i);
                                }
                                $.each(data.data.list, function (i, result) {
                                    chaXun += "<tr>";
                                    chaXun += "<td>" + (i + 1) + "</td>" + "<td>" + result.classroom + "</td>" + "<td>" + result.catename + "</td>" +
                                        "<td>" + result.address + "</td>" + "<td>" + result.purpose + "</td>";
                                    chaXun += "</tr>";
                                })
                                $("#listJslb").html(chaXun);
                            }
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
                            $("#listJslb").empty();
                            getQuery();
                        }
                    }
                })
            })
        }
    }

