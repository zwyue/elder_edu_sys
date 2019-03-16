
    //左侧导航
    layui.use('element', function () {
        var element = layui.element;
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
                    url: "api/identity/list",
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
                        pageCurrent = data.data.pageNum;//获取当前页
                        pageNow =  (pageCurrent-1)*10; //当前页数的序号
                        $.each(data.data.list, function (i, result) {
                            html += "<tr>";
                            html += "<td>" + (pageNow+i+1) + "</td>" + "<td>" + result.duties + "</td>" + "<td>" + result.crttime + "</td>" +
                                "<td><a onclick='delect("+result.id+")' class='bg bgActive del'>删除</a>" +
                                "<a onclick='revise("+result.id+")'  class='bg change'>修改</a></td>";
                            html += "</tr>";
                        })
                        $("#list").html(html);
                    },
                    error: function () {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg('请求数据失败！');
                        });
                    },
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
                        $("#list").empty();
                        getDatasSize();
                    }
                }
            })
        })
    }

    //删除
    function delect(id) {
        layui.use('layer', function () {
            layer.confirm('确认要删除吗？', function (index) {
                $.ajax({
                    type: 'get',
                    url: "api/identity/delete",
                    async: false,
                    data: {
                        id: id
                    },
                    dataType: "json",
                    xhrFields: {withCredentials: true},
                    success: function (data) {
                        if(data.code==1){
                            layer.msg('已删除!', {icon:1, time:1000});
                        }else if(data.code==0){
                            layer.msg('删除失败!', {icon:2, time:1000});
                        }
                        DatatoLoad();
                    },
                    error: function () {
                        layer.msg('请求删除接口失败!');
                    }
                })
                layui.close(index);
            });
        });
    }

    //新增
    function xzzw() {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                type: 2,
                skin: '',
                title: "新增职务",
                fixed: false, //不固定
                maxmin: true, //开启最大化最小化按钮
                area: ['450px', '350px'],
                content:'bzrgl_bwh_xz.html',
                btn: ['确定', '取消'],
                yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                    //当点击‘确定’按钮的时候，获取弹出层返回的值
                    var res = window["layui-layer-iframe" + index].callbackdata();
                    //打印返回的值，看是否有我们想返回的值。
                    var name=res.username;
                    $.ajax({
                        type: 'post',
                        url: "api/identity/save",
                        async: false,
                        data: {
                            duties: name
                        },
                        dataType: "json",
                        xhrFields: {withCredentials: true},
                        success: function (data) {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg('新增成功!', {area: ['150px', '50px']});
                            });
                            setTimeout(function () {
                                window.location.href = "bzrgl_bwh.html";
                            }, 2000)
                        },
                        error: function () {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg('新增失败！');
                            });
                        }
                    })
                    //最后关闭弹出层
                    // layer.close(index);
                }
                , btn2: function (index, layero) {

                },
            })
        });
    };


    //修改职务弹窗
    function revise(id) {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                type: 2,
                skin: '',
                title: "修改职务",
                fixed: false, //不固定
                maxmin: true, //开启最大化最小化按钮
                area: ['450px', '350px'],
                content:'bzrgl_bwh_xg.html',
                btn: ['确定', '取消'],
                success:function(layero, index){
                    $.ajax({
                        type: 'get',
                        url: "api/identity/query",
                        async: false,
                        data: {
                            id:id
                        },
                        dataType: "json",
                        xhrFields: {withCredentials: true},
                        success: function (data) {
                            var xgName=data.data.duties;
                            var iframe = window['layui-layer-iframe' + index];
                            // 向子页面的全局函数child传参
                            iframe.child(xgName);
                        },
                        error: function () {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg('获取失败！');
                            });
                        }
                    })
                },
                yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                    //当点击‘确定’按钮的时候，获取弹出层返回的值
                    var res = window["layui-layer-iframe" + index].callbackdata();
                    //打印返回的值，看是否有我们想返回的值。
                    var name2=res.updatename;
                    $.ajax({
                        type: 'post',
                        url: "api/identity/update",
                        async: false,
                        data: {
                            id:id,
                            duties: name2
                        },
                        dataType: "json",
                        xhrFields: {withCredentials: true},
                        success: function (data) {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg('修改成功!', {area: ['150px', '50px']});
                            });
                            setTimeout(function () {
                                window.location.href = "bzrgl_bwh.html";
                            }, 2000)
                        },
                        error: function () {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg('修改失败！');
                            });
                        }
                    })
                    //最后关闭弹出层
                    // layer.close(index);
                }
                , btn2: function (index, layero) {

                },
            })
        });
    };
