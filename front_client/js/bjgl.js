	$(function() {
		ajaxPLists();
	});

	function ajaxPLists() {
		//分页
		layui.use('laypage', function() {
			var laypage = layui.laypage;
			var totalRecord = 0; //初始化总记录数             
			var pagenum = 1,
				pagesize = 10; //初始化页数为第一页
			var i=0;    //表格序号
			//每页显示条数
			getNewsData();

			function getNewsData(){
				$.ajax({
					type: "get",
					async: false,
					url: "api/classes/class-list",
					data: {
						page: pagenum,
						size: pagesize
					},
					success: function(data){
						totalRecord = data.data.total; //数据总条数
						var html = "";					
						$.each(data.data.list, function(i, item){
							pageCurrent = data.data.pageNum;//获取当前页
							pageNow =  (pageCurrent-1)*10; //当前页数的序号
							html += '<tr>';
							html += '<td>' + (pageNow+i+1) + '</td>' + '<td>' + item.classname + '</td>' + '<td>' + item.majorname + '</td>' + '<td>' + item.plansize + '</td>' + '<td>' + item.headmastername + '</td>' + '<td style="display:none">' + item.id + '</td>';
							html += '<td class="td-manage">' +
								'<a title="删除" class="detBtn" onclick="delClass(' + item.id + ')" lay-event="del" >删除</a>' +
								'<a title="编辑" href="bjgl_xg.html?id=' + item.id + '" class="chaBtn" lay-event="edit" >修改</a>' +
								'<a title="查看" href="bjgl_ck.html?id=' + item.id + '" class="viewBtn">查看</a>' +
								'</td>';
							html += '</tr>';
							$('#bjglList').html(html);
						})						
					},
					error: function(error){
                        layui.use('layer', function(){
                            var layer = layui.layer;
                            layer.msg(error.responseText);
                        });
					}
				});
			};
			//执行一个laypage实例
			laypage.render({
				elem: 'text1' //注意，这里的 test1 是 ID，不用加 #号
					,
				count: totalRecord //数据总数，从服务端得到
					,
				curr: pagenum,
				limit: pagesize,
				jump: function(obj, first) {
					//                  //obj包含了当前分页的所有参数，比如：
					pagenum = obj.curr; //得到当前页，以便向服务端请求对应页的数据。
					pagesize = obj.limit; //得到每页显示的条数
					//首次不执行，清除上一页数据
					if(!first) {
						//do something
						$('#bjglList').empty();
						getNewsData();
					}
				}
			});
		});
	};

	//删除数据
	function delClass(id) {
        layui.use('layer', function () {
            layer.confirm('确认要删除吗？', function (index) {
                $.ajax({
                    type: "get",
                    async: false,
                    url: "api/classes/delete?id=" + id,
                    success: function (data) {
                    	if(data.code==1){
                            layer.msg('已删除!', {icon:1, time:1000});
						}else if(data.code==0){
                            layer.msg('删除失败!', {icon:2, time:1000});
                        }
                        ajaxPLists();
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
	};

	//查询
	$('#chaXun').click(function() {
        var className = document.getElementById("className").value;
        layui.use('laypage', function () {
            var laypage = layui.laypage;
            var totalRecord = 0; //初始化总记录数
            var pagenum = 1,
                pagesize = 10; //初始化页数为第一页
            getQuery();

            function getQuery() {
                $.ajax({
                    type: 'GET',
                    url: "api/classes/class-list",
                    async: false, //同步
                    data: {
                        className: className,
                        page: pagenum,
                        size: pagesize
                    },
                    dataType: 'json',
                    success: function (data) {
                        if (data.data.total == 0) {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg('该班级不存在！');
                            });
                        } else {
                            totalRecord = data.data.total; //数据总条数
                            var chaXunHtml;
                            var len = data.data.list.length;
                            for (var i = 1; i < len + 1; i++) {
                                $('table tr:eq(' + i + ')td:first').text(i);
                            }
                            pageCurrent = data.data.pageNum;//获取当前页
                            pageNow = (pageCurrent - 1) * 10; //当前页数的序号
                            $.each(data.data.list, function (i, item) {
                                chaXunHtml += '<tr>';
                                chaXunHtml += '<td>' + (pageNow + i + 1) + '</td>' + '<td>' + item.classname + '</td>' + '<td>' + item.majorname + '</td>' + '<td>' + item.plansize + '</td>' + '<td>' + item.headmastername + '</td>' + '<td style="display:none">' + item.id + '</td>';
                                chaXunHtml += '<td class="td-manage">' +
                                    '<a title="删除" class="detBtn" onclick="delClass(' + item.id + ')" lay-event="del" >删除</a>' +
                                    '<a title="编辑" href="bjgl_xg.html?id=' + item.id + '" class="chaBtn" lay-event="edit" >修改</a>' +
                                    '<a title="查看" href="bjgl_ck.html?id=' + item.id + '" class="viewBtn">查看</a>' +
                                    '</td>';
                                chaXunHtml += '</tr>';
                            })
                            $("#bjglList").html(chaXunHtml);
                        }
                    },
                    error: function (error) {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg(error.responseText);
                        });
                    },

                })
            }

            //执行一个laypage实例
            laypage.render({
                elem: 'text1' //注意，这里的 test1 是 ID，不用加 #号
                ,
                count: totalRecord //数据总数，从服务端得到
                ,
                curr: pagenum,
                limit: pagesize,
                jump: function (obj, first) {
                    //                  //obj包含了当前分页的所有参数，比如：
                    pagenum = obj.curr; //得到当前页，以便向服务端请求对应页的数据。
                    pagesize = obj.limit; //得到每页显示的条数
                    //首次不执行，清除上一页数据
                    if (!first) {
                        //do something
                        $('#bjglList').empty();
                        getQuery();
                    }
                }
            });
        })
    })