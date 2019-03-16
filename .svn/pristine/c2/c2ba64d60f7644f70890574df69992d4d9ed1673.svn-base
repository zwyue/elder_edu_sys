
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
                    url: "api/teachmanager/list",
                    data: {
                        types: 1,
                        page: pageNum,
                        size: pageSize
                    },
                    dataType: "json",
                    xhrFields: {withCredentials: true},
                    success: function (data) {
                        totalSize = data.data.total; //获得数据总条数
                        var html = '';
                        $.each(data.data.list, function (i, result) {
                        	pageCurrent = data.data.pageNum;//获取当前页
                            pageNow =  (pageCurrent-1)*10; //当前页数的序号
                            html += "<tr>";
                            html += "<td>" + (pageNow+i + 1) + "</td>" + "<td>" + result.tname + "</td>" + "<td>" + result.weeks + "</td>" +
                                "<td>" + result.leavedate + "</td>" +
                                "<td><a href='jxgl_tk_ck.html?id=" + result.id + "' class='bg change'>查看</a>" +
                                "<a href='api/teachmanager/export?id=" + result.id + "' class='bg change'>导出</a></td>";
                            html += "</tr>";
                        })
                        $("#jxgl_tk").html(html);
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
                        $("#jxgl_tk").empty();
                        getDatasSize();
                    }
                }
            })
        })
    }

    //查询
    function chaxun() {
        var textName = $('#name').val();

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
                    url: "api/teachmanager/getNameList",
                    async: false,
                    data: {
                        name: textName,
                        type: 1,
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
                            $("#jxgl_tk").html('');
                            return false;
                        }
                        else {
                            totalSize = data.data.total;
                            var chaXun;
                            $.each(data.data.list, function (i, result) {
                            	pageCurrent = data.data.pageNum;//获取当前页
                            	pageNow =  (pageCurrent-1)*10; //当前页数的序号
                                chaXun += "<tr>";
                                chaXun += "<td>" + (pageNow+i + 1) + "</td>" + "<td>" + result.tname + "</td>" + "<td>" + result.weeks + "</td>" +
                                    "<td>" + result.leavedate + "</td>" +
                                    "<td><a href='jxgl_tk_ck.html?id=" + result.id + "' class='bg change'>查看</a>" +
                                    "<a href='api/teachmanager/export?id=" + result.id + "' class='bg change'>导出</a></td>";
                                chaXun += "</tr>";
                            })
                            $("#jxgl_tk").html(chaXun);
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
                        $("#jxgl_tk").empty();
                        getQuery();
                    }
                }
            })
        })
    }
