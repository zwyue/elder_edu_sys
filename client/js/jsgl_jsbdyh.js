
		$(function(){			
            ajaxPLists();           
        });
        
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
                            	var html ="";             
	                            $.each(data.data.list, function (i, item){
                            	if(item.majorname==null){
                            		var newMajorname=''
                            		//年龄和专业需要调整
                                var addTime = new Date(item.crttime).Format("yyyy-MM-dd");
                                html += '<tr>';
                                html += '<td>' + (i+1) + '</td>' +'<td>' + item.tname + '</td>'+'<td>' + item.tnumber + '</td>' + '<td>' + item.workunit + '</td>' + '<td>' + item.age + '</td>' + '<td>' + newMajorname+ '</td>' +'<td>' + addTime + '</td>'+'<td style="display:none">' + item.id + '</td>';
                                html += '<td class="td-manage">' +
                                    '<a title="绑定角色" href="javaScript:;" onclick="bdjs('+item.id+')" class="layui-btn layui-btn-xs layui-btn-primary">绑定角色</a>' +
//                                  '<a title="绑定班级" href="yhgl_lsgl_fzbj.html?id=' + item.id + '" class="layui-btn layui-btn-xs layui-btn-primary">绑定班级</a>' +
                                    '</td>';
                                html += '</tr>';
                            	}else{
                            		//年龄和专业需要调整
	                                var addTime = new Date(item.crttime).Format("yyyy-MM-dd");
	                                html += '<tr>';
	                                html += '<td>' + (i+1) + '</td>' +'<td>' + item.tname + '</td>'+'<td>' + item.tnumber + '<td>' + item.workunit + '</td>' + '<td>' + item.age + '</td>' + '<td>' + item.majorname + '</td>' +'<td>' + addTime + '</td>'+'<td style="display:none">' + item.id + '</td>';
	                                html += '<td class="td-manage">' +
                                        '<a title="绑定角色" href="javaScript:;" onclick="bdjs('+item.id+')" class="layui-btn layui-btn-xs layui-btn-primary">绑定角色</a>' +
	//                                  '<a title="绑定班级" href="yhgl_lsgl_fzbj.html?id=' + item.id + '" class="layui-btn layui-btn-xs layui-btn-primary">绑定班级</a>' +
	                                    '</td>';
	                                html += '</tr>';
                            	}
                            })
	                            $('tbody').html(html);                       		
                        },
                        error: function(error) {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg(error.responseText);
                            });
                        }
                    });
                };
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
                            $('tbody').empty();
                            getNewsData();
                        }
                    }
                });
            });
        };
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
				  yes:function(index,layero){
			        	//当点击‘确定’按钮的时候，获取弹出层返回的值
					    var callBackRoleId = window["layui-layer-iframe" + index].callbackdata();
					    //打印返回的值，看是否有我们想返回的值。
					    console.log(callBackRoleId);
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
         	 var name=document.getElementById("name").value;
         	 var idCard = document.getElementById("idCard").value;
             $.ajax({
                 type: 'GET',
                 url: "api/teacher/list",
                 async: false, //同步
                 data: {
                     name: name,
                     idCard: idCard
                 },
                 dataType: 'json',
                 success: function (data) {
                     if (data.data.total == 0) {
                         layui.use('layer', function () {
                             var layer = layui.layer;
                             layer.msg('该用户不存在！');
                         });
                     } else {
                         layui.use('laypage', function () {
                             var laypage = layui.laypage;
                             var totalRecord = 0;    //初始化总记录数
                             var pageNum = 1,
                                 pageSize = 10; //初始化页数为第一页
                             totalRecord = data.data.total;//数据总条数
                             var chaXunHtml;
                             var len = data.data.list.length;
                             for (var i = 1; i < len + 1; i++) {
                                 $('table tr:eq(' + i + ')td:first').text(i);
                             }
                             $.each(data.data.list, function (i, item) {
                                 if (item.majorname == null) {
                                     var newMajorname = ''
                                     //年龄和专业需要调整
                                     var addTime = new Date(item.crttime).Format("yyyy-MM-dd");
                                     chaXunHtml += '<tr>';
                                     chaXunHtml += '<td>' + (i + 1) + '</td>' + '<td>' + item.tname + '</td>'+'<td>' + item.tnumber + '<td>' + item.workunit + '</td>' + '<td>' + item.age + '</td>' + '<td>' + newMajorname + '</td>' + '<td>' + addTime + '</td>' + '<td style="display:none">' + item.id + '</td>';
                                     chaXunHtml += '<td class="td-manage">' +
                                         '<a title="绑定角色" href="javaScript:;" onclick="bdjs(' + item.id + ')" class="layui-btn layui-btn-xs layui-btn-primary">绑定角色</a>' +
                                         //                                  '<a title="绑定班级" href="yhgl_lsgl_fzbj.html?id=' + item.id + '" class="layui-btn layui-btn-xs layui-btn-primary">绑定班级</a>' +
                                         '</td>';
                                     chaXunHtml += '</tr>';
                                 } else {
                                     //年龄和专业需要调整
                                     var addTime = new Date(item.crttime).Format("yyyy-MM-dd");
                                     chaXunHtml += '<tr>';
                                     chaXunHtml += '<td>' + (i + 1) + '</td>' + '<td>' + item.tname + '</td>' +'<td>' + item.tnumber + '<td>' + item.workunit + '</td>' + '<td>' + item.age + '</td>' + '<td>' + item.majorname + '</td>' + '<td>' + addTime + '</td>' + '<td style="display:none">' + item.id + '</td>';
                                     chaXunHtml += '<td class="td-manage">' +
                                         '<a title="绑定角色" href="javaScript:;" onclick="bdjs(' + item.id + ')" class="layui-btn layui-btn-xs layui-btn-primary">绑定角色</a>' +
                                         //                                  '<a title="绑定班级" href="yhgl_lsgl_fzbj.html?id=' + item.id + '" class="layui-btn layui-btn-xs layui-btn-primary">绑定班级</a>' +
                                         '</td>';
                                     chaXunHtml += '</tr>';
                                 }
                             })
                             $("tbody").html(chaXunHtml);
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
                                         $('tbody').empty();
//			                            getNewsData();
                                     }
                                 }
                             });
                         });
                     }
                 },
                 error: function(error) {
                     layui.use('layer', function () {
                         var layer = layui.layer;
                         layer.msg(error.responseText);
                     });
                 }
             });
         });
	