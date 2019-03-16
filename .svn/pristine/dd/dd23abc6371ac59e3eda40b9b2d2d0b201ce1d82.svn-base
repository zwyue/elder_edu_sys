
        $(function(){
            ajaxPLists();           
        });
        var roleIdShow;
        function ajaxPLists(){
            layui.use('laypage', function (){
                var laypage = layui.laypage;
                var totalRecord = 0;    //初始化总记录数   
                var pageNum = 1,
                pageSize = 10; //初始化页数为第一页
                //每页显示条数
                getNewsData();

                function getNewsData(){
                    $.ajax({
                        type: "get",
                        async: false,
                        url: "api/teacher/list",
                        xhrFields: {withCredentials: true},
                        data: {
                            page: pageNum,
                            size: pageSize
                        },
                        success: function (data){
                            totalRecord = data.data.total;//数据总条数
//                          if(totalRecord!=0){
                            var html = "";
                            $.each(data.data.list, function (i, item) {
                                roleIdShow = data.data.list[i].roleid;
                                if (item.majorname == null) {
                                    var newMajorname = ''
                                    //年龄和专业需要调整
                                    pageCurrent = data.data.pageNum; //获取当前页
							       pageNow = (pageCurrent - 1) * 10; //当前页数的序号
                                    var addTime = new Date(item.crttime).Format("yyyy-MM-dd");
                                    html += '<tr>';
                                    html += '<td>' + (pageNow+i + 1) + '</td>' + '<td>' + item.tname + '</td>' + '<td>' + item.tnumber + '</td>' + '<td>' + item.workunit + '</td>' + '<td>' + item.age + '</td>' + '<td>' + newMajorname + '</td>' + '<td>' + addTime + '</td>' + '<td style="display:none">' + item.id + '</td>';
                                    html += '<td class="td-manage">' +
                                        '<a title="绑定角色" href="javaScript:;" onclick="bdjs(' + item.id + ')" class="layui-btn layui-btn-xs layui-btn-primary">绑定角色</a>' +
                                        //                                  '<a title="绑定班级" href="yhgl_lsgl_fzbj.html?id=' + item.id + '" class="layui-btn layui-btn-xs layui-btn-primary">绑定班级</a>' +
                                        '</td>';
                                    html += '</tr>';
                                } else {
                                    //年龄和专业需要调整
                                     pageCurrent = data.data.pageNum; //获取当前页
							         pageNow = (pageCurrent - 1) * 10; //当前页数的序号
                                    var addTime = new Date(item.crttime).Format("yyyy-MM-dd");
                                    html += '<tr>';
                                    html += '<td>' + (pageNow+i + 1) + '</td>' + '<td>' + item.tname + '</td>' + '<td>' + item.tnumber + '<td>' + item.workunit + '</td>' + '<td>' + item.age + '</td>' + '<td>' + item.majorname + '</td>' + '<td>' + addTime + '</td>' + '<td style="display:none">' + item.id + '</td>';
                                    html += '<td class="td-manage">' +
                                        '<a title="绑定角色" href="javaScript:;" onclick="bdjs(' + item.id + ')" class="layui-btn layui-btn-xs layui-btn-primary">绑定角色</a>' +
                                        //                                  '<a title="绑定班级" href="yhgl_lsgl_fzbj.html?id=' + item.id + '" class="layui-btn layui-btn-xs layui-btn-primary">绑定班级</a>' +
                                        '</td>';
                                    html += '</tr>';
                                }
                            })
                            $('#yhTableList').html(html);
                        },
                        error: function (error) {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg(error.responseText);
                            });
                        }
                    })
                    //执行一个laypage实例
                    laypage.render({
                        elem: 'text1' //注意，这里的 test1 是 ID，不用加 #号
                        , count: totalRecord //数据总数，从服务端得到
                        , curr: pageNum
                        , limit: pageSize
                        , jump: function (obj, first) {
                            //obj包含了当前分页的所有参数，比如：
                            pageNum = obj.curr;//得到当前页，以便向服务端请求对应页的数据。
                            pageSize = obj.limit;//得到每页显示的条数
                            //首次不执行，清除上一页数据
                            if (!first) {
                                //do something
                                $('#yhTableList').empty();
                                getNewsData();
                            }
                        }
                    });
                }

            })
        }


        //点击绑定角色按钮出现弹窗
        function bdjs(id){
			layui.use('layer', function(){
	            var layer = layui.layer;
				layer.open({
				  type: 2, 
				  title:'绑定角色',
				  area: ['500px', '450px'],
				  content: ['jsgl_jsbd.html','no'], //这里content是一个普通的String
				  btn: ['确定', '取消'],
                  success:function(layero, index){
                      $.ajax({
                          type: 'get',
                          url: "api/role/detail",
                          async: false,
                          data: {
                              roleId: roleIdShow
                          },
                          dataType: "json",
                          xhrFields: {withCredentials: true},
                          success: function (data) {
                              layui.use(['layer', 'form'], function () {
                                  var form = layui.form;
                                  //获得这条数据的id和value,并带到子页面弹窗中
                                  var ids = data.data.role.id;
                                  if (ids == roleIdShow) {
                                      var roleid = data.data.role.id;
                                      var roleIdVal = data.data.role.rolename;
                                      var iframe = window['layui-layer-iframe' + index];
                                      // 向子页面的全局函数child传参
                                      iframe.child(roleid, roleIdVal);
                                  }
                              });
                          },
                          error: function () {
                              layui.use('layer', function () {
                                  var layer = layui.layer;
                                  layer.msg('获取失败！');
                              });
                          }
                      })
                  },
				  yes:function(index,layero){
			        	//当点击‘确定’按钮的时候，获取弹出层返回的值
					    var callBackRoleId = window["layui-layer-iframe" + index].callbackdata();
					    // //打印返回的值，看是否有我们想返回的值。
					    // console.log(callBackRoleId);
					    if(callBackRoleId.roleId ==''){
					    	layui.use('layer', function(){
							  var layer = layui.layer;					  
							  layer.msg('请绑定角色');
							});  
					    }else{
					    	$.ajax({
								async: false,    //同步
								url: "api/role/allocate",
								type: "POST",
								data:{
									userId:id,  //用户ID 
									roleId:callBackRoleId.roleId   //角色ID
								},
								dataType: "json",
								success: function(data){
									layui.use('layer', function(){
									  var layer = layui.layer;					  
									  layer.msg('角色绑定成功');
									});   
							    	setTimeout(function(){
							    		layer.close(index);	
							    	},800)									
								},
                                error: function(error) {
                                    layui.use('layer', function () {
                                        var layer = layui.layer;
                                        layer.msg(error.responseText);
                                    });
                                    setTimeout(function(){
                                        layer.close(index);
                                    },800)
                                }
							});
					    }
						
				 	}				  
				});
			});
		};

//查询
$('#chaXun').click(function(){
	//拿到值
	var name = document.getElementById("name").value;
	var idCard = document.getElementById("idCard").value;

	//分页
	var pageNum = 0; //初始化页数为第一页
	var pageSize = 10;
	layui.use('laypage', function() {
		var laypage = layui.laypage;
		var totalRecord = 0; //初始化总记录数
		getQuery();

		function getQuery(){
			$.ajax({
				type: 'GET',
				url: "api/teacher/list",
				async: false,
				data: {
					name: name,
					idCard: idCard,
					page: pageNum,
					size: pageSize
				},
				dataType: 'json',
				//xhrFields: {withCredentials: true},
				success: function(data){
					if(data.data.total == 0){
						layui.use('layer', function(){
							var layer = layui.layer;
							layer.msg('该老师不存在')
						});
						$("tbody").html('');
						return false;
					} else {
						totalRecord = data.data.total;//数据总条数
                        var chaXunHtml = "";

                        $.each(data.data.list, function (i, item){
                           if (item.majorname == null){
                               var newMajorname = ''
                               //年龄和专业需要调整
                               pageCurrent = data.data.pageNum; //获取当前页
							   pageNow = (pageCurrent - 1) * 10; //当前页数的序号
                               var addTime = new Date(item.crttime).Format("yyyy-MM-dd");
                               chaXunHtml += '<tr>';
                               chaXunHtml += '<td>' + (pageNow+i + 1) + '</td>' + '<td>' + item.tname + '</td>' + '<td>' + item.tnumber + '<td>' + item.workunit + '</td>' + '<td>' + item.age + '</td>' + '<td>' + newMajorname + '</td>' + '<td>' + addTime + '</td>' + '<td style="display:none">' + item.id + '</td>';
                               chaXunHtml += '<td class="td-manage">' +
                                   '<a title="绑定角色" href="javaScript:;" onclick="bdjs(' + item.id + ')" class="layui-btn layui-btn-xs layui-btn-primary">绑定角色</a>' +
                                   //                                  '<a title="绑定班级" href="yhgl_lsgl_fzbj.html?id=' + item.id + '" class="layui-btn layui-btn-xs layui-btn-primary">绑定班级</a>' +
                                   '</td>';
                               chaXunHtml += '</tr>';
                            } else {
                               //年龄和专业需要调整
                                pageCurrent = data.data.pageNum; //获取当前页
							   pageNow = (pageCurrent - 1) * 10; //当前页数的序号
                               var addTime = new Date(item.crttime).Format("yyyy-MM-dd");
                               chaXunHtml += '<tr>';
                               chaXunHtml += '<td>' + (pageNow+i + 1) + '</td>' + '<td>' + item.tname + '</td>' + '<td>' + item.tnumber + '<td>' + item.workunit + '</td>' + '<td>' + item.age + '</td>' + '<td>' + item.majorname + '</td>' + '<td>' + addTime + '</td>' + '<td style="display:none">' + item.id + '</td>';
                               chaXunHtml += '<td class="td-manage">' +
                                   '<a title="绑定角色" href="javaScript:;" onclick="bdjs(' + item.id + ')" class="layui-btn layui-btn-xs layui-btn-primary">绑定角色</a>' +
                                   //                                  '<a title="绑定班级" href="yhgl_lsgl_fzbj.html?id=' + item.id + '" class="layui-btn layui-btn-xs layui-btn-primary">绑定班级</a>' +
                                   '</td>';
                               chaXunHtml += '</tr>';
                           }
                           $("#yhTableList").html(chaXunHtml);
                       })
					}
				},
				error: function(error){
					layui.use('layer', function(){
						var layer = layui.layer;
						layer.msg(error.responseText);
					});
				}
			});
		}

		//执行一个laypage实例
		laypage.render({
			elem: 'text1' //注意，这里的 test1 是 ID，不用加 #号
				,
			count: totalRecord //数据总数，从服务端得到
				,
			curr: pageNum,
			limit: pageSize,
			jump: function(obj, first){
				//obj包含了当前分页的所有参数，比如：
				pageNum = obj.curr; //得到当前页，以便向服务端请求对应页的数据。
				pageSize = obj.limit; //得到每页显示的条数

				//首次不执行
				if(!first){
					//do something
					$('#yhTableList').empty();
					getQuery();
				}
			}
		});
	})
})