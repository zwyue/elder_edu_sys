
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
                    url: "api/studymeet/list",
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
                            html += "<tr id=add" + result.id + ">";
                            html += "<td>" + (i + 1) + "</td>" + "<td>" + result.sname + "</td>" + "<td>" + result.classname + "</td>" +
                                "<td>" + result.phone + "</td>" + "<td>" + result.emergency + "</td>" + "<td>" + result.crttime + "</td>" +
                                "<td>" + result.business + "</td>" +
                                "<td>" +
                                "<a href='JavaScript:;' onclick='addBusiness(" + result.id + ")'  class='bg bgActive Add'>添加职务</a>" +
                                "<a href='JavaScript:;' onclick='delect(" + result.id + ")' class='bg bgActive del'>删除</a>" +
                                "</td>"
                            html += "</tr>";
                        })
                        $("#list").html(html);
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
                        $("#list").empty();
                        getDatasSize();
                    }
                }
            })
        })
    }

    //添加职务
    function addBusiness(id) {
        $("#hide").val(id);
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                type: 1,
                skin: '',
                title: "新增职务",
                area: ['40%', '40%'],
                content: $("#addjob")
            })
        })
    }

    function add() {
        var business = $("#business").val();
        var id = $("#hide").val();
		if(business ==''){
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert('请将内容填写完整', {area: ['200px']});
            });
            return false;
		}else{
			$.ajax({
				type: 'POST',
				url: "api/studymeet/addduties",
				async: false,
				data: {
					id: id,
					business: business,
				},
				dataType: "json",
				xhrFields: {withCredentials: true},
				success: function (data) {
					window.location.href = "xwhgl.html";
				},
				error: function(error) {
	                layui.use('layer', function () {
	                    var layer = layui.layer;
	                    layer.msg(error.responseText);
	                });
				}
			})
		}
    }

    //删除
    function delect(id) {
		layui.use('layer', function () {
            layer.confirm('确认要删除吗？', function (index) {
                $.ajax({
                    type: "get",
                    async: false,
                    url: "api/studymeet/delete?id=" + id,
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
        var name = $('#name').val();

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
                    url: "api/studymeet/query",
                    async: false,
                    data: {
                        sname: name,
                        page: pageNum,
                        size: pageSize
                    },
                    dataType: "json",
                    xhrFields: {withCredentials: true},
                    success: function (data) {
                        if (data.data.list.length == 0) {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.alert('该学生未查到相关信息', {area: ['200px']});
                            });
                            $("#list").html('');
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
                                chaXun += "<td>" + (i + 1) + "</td>" + "<td>" + result.sname + "</td>" + "<td>" + result.classname + "</td>" +
                                    "<td>" + result.phone + "</td>" + "<td>" + result.emergency + "</td>" + "<td>" + result.crttime + "</td>" +
                                    "<td>" + result.business + "</td>" +
                                    "<td>" +
                                    "<a href='JavaScript:;' onclick='addBusiness(" + result.id + ")' class='bg bgActive Add'>添加职务</a>" +
                                    "<a href='JavaScript:;' onclick='delect(" + result.id + ")' class='bg bgActive del'>删除</a>" +
                                    "</td>"
                                chaXun += "</tr>";
                            })
                            $("#list").html(chaXun);
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
                        $("#list").empty();
                        getQuery();
                    }
                }
            })
        })
    }
