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
                    url: "api/classroom/list",
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
                            html += "<td>" + (pageNow+i+1) + "</td>" + "<td>" + result.classroom + "</td>" + "<td>" + result.catename + "</td>" +
								"<td>" + result.address + "</td>" + "<td>" + result.purpose + "</td>" +
                                "<td><a href='JavaScript:;' onclick='delect(" + result.id + ")' class='bg bgActive del'>删除</a>" +
                                "<a href='jslb_jslbjsxg.html?id=" + result.id + "' class='bg change'>修改</a>" +
                                "<a href='jslb_jslbsyls.html?id=" + result.id + "' class='bg change'>使用记录</a></td>";
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

    //删除
    function delect(id) {
		layui.use('layer', function () {
			layer.confirm('确认要删除吗？', function (index) {
				$.ajax({
					type: "get",
					async: false,
					url: "api/classroom/delete?id=" + id,
					success: function (data) {
						if(data.code==1){
							layer.msg('已删除!', {icon:1, time:1000});
						}
						DatatoLoad();
						if(data.code==0){
							layer.msg('删除失败!', {icon:2, time:1000});
						}
					},
                    error: function(error) {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg(error.responseText);
                        });
                    }
				});
				layer.close(index);
			});
		});
    }

    //查询
    function chaxun() {
        var name = $('#className').val();
        var classType = $('#classCategory').val();

        //分页
        var pageNum = 0;    //初始化页数为第一页
        var pageSize = 10;   //列表数量为10条
        layui.use('laypage', function () {
            var laypage = layui.laypage;
            var totalSize = 0; //初始化数据总数
            getQuery();

            function getQuery() {
                $.ajax({
                    type: 'GET',
                    url: "api/classroom/getClassroom",
                    async: false,
                    data: {
                        classroom: name,
                        catename: classType,
                        page: pageNum,
                        size: pageSize
                    },
                    dataType: "json",
                    xhrFields: {withCredentials: true},
                    success: function (data) {
                        if (data.data.list.length == 0) {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.alert('未查到相关信息', {area: ['200px']});
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
                            pageCurrent = data.data.pageNum;//获取当前页
                            pageNow =  (pageCurrent-1)*10; //当前页数的序号
                            $.each(data.data.list, function (i, result) {
                                chaXun += "<tr>";
                                chaXun += "<td>" + (pageNow+i+1) + "</td>" + "<td>" + result.classroom + "</td>" + "<td>" + result.catename + "</td>" +
                                    "<td>" + result.address + "</td>" + "<td>" + result.purpose + "</td>" +
                                    "<td><a href='JavaScript:;' onclick='delect(" + result.id + ")' class='bg bgActive del'>删除</a>" +
                                    "<a href='jslb_jslbjsxg.html?id=" + result.id + "' class='bg change'>修改</a>" +
                                    "<a href='jslb_jslbsyls.html?id=" + result.id + "' class='bg change'>使用记录</a></td>";
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