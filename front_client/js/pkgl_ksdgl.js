
		var courselistData ;  //接收查看课时段列表返回的data
			
		//添加按钮出现弹窗
		$('#ksdxz').click(function(){
			layui.use('layer', function(){
	            var layer = layui.layer;
				layer.open({
				  type: 2, 
				  title:'添加课时段',
				  area: ['700px', '450px'],
				  content: ['pkgl_ksdxz.html','no'], //这里content是一个普通的String
				  btn: ['确定'],
				  yes:function(index,layero){
			        	//当点击‘确定’按钮的时候，获取弹出层返回的值
					    var callBackData = window["layui-layer-iframe" + index].callbackdata();
					    //打印返回的值，看是否有我们想返回的值。
					    (callBackData);
					    
						if(callBackData.startTime<callBackData.endTime){
							$.ajax({
								async: false,    //同步
								url: "api/schedule-class/pre/coursetimebyone ",
								type: "POST",
								data:{
									coursename:callBackData.courseName,
									time:callBackData.time
								},
								dataType: "json",
								success: function(data){
									if(data.code == 1){
										layui.use('layer', function(){
										  var layer = layui.layer;					  
										  layer.msg('添加课时段成功');
										});
										setTimeout(function(){
								    		layer.close(index);	
								    		location.reload();
								    	},800)
									}else{
										layui.use('layer', function(){
										  var layer = layui.layer;					  
										  layer.msg(data.msg);
										});  
										setTimeout(function(){
								    		layer.close(index);	
								    		location.reload();
								    	},800)
									}
									
								},
								error: function(error) {
					                layui.use('layer', function () {
					                    var layer = layui.layer;
					                    layer.msg(error.responseText);
					                });
					                setTimeout(function(){
								    		layer.close(index);	
								    		location.reload();
								    	},800);
								}				
							});
						}else{
							layui.use('layer', function(){
							  var layer = layui.layer;					  
							  layer.msg('课时段结束时间应大于课时段开始时间');
							});   
						}
				  }				  
				});
			});
		});
			
		//查询课时段列表并回填
        $(function(){    	
            ajaxPLists();
        });
        function ajaxPLists(){ 
        	//分页
            layui.use('laypage', function (){
                var laypage = layui.laypage;
                var totalRecord = 0;    //初始化总记录数 
                var pageNum = 1, pageSize = 10;  //初始化页数为第一页
                //每页显示条数
                getNewsData();
                
                function getNewsData(){
                    $.ajax({
                        type: "get",
                        async: false,
                        url: "api/schedule-class/pre/list",
                        data: {
                            page: pageNum,
                            size: pageSize
                        },
                        success: function (data){
                            totalRecord = data.data.total;//数据总条数
                            var html ="";             
                            courselistData = data.data.list; 
                            
                            $.each(courselistData, function (i, item){
                            	 pageCurrent = data.data.pageNum; //获取当前页
							     pageNow = (pageCurrent - 1) * 10; //当前页数的序号
                                html += '<tr>';
                                html += '<td>' + (pageNow+i+1) + '</td>' +'<td>' + item.coursename + '</td>' + '<td>' + item.time.split('-')[0] + '</td>'+ '<td>' + item.time.split('-')[1] + '</td>'+'<td style="display:none">' + courselistData.id + '</td>';
                                html += '<td class="td-manage">' +
                                	'<a title="删除" class="layui-btn layui-btn-xs layui-btn-danger detBtn" onclick="delCourse('+item.id+')" lay-event="del" >删除</a>' +
                                    '<a title="修改" href="javascript:;" onclick="reviseCourse('+item.id+')"  class="layui-btn layui-btn-xs chaBtn" lay-event="edit" id="'+item.id+'">修改</a>' + 
                                    '</td>';
                                html += '</tr>';
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
        //修改
        function reviseCourse(id){
        	 layui.use('layer', function(){
	            var layer = layui.layer;
				layer.open({
				  type: 2, 
				  title:'修改课时段',
				  area: ['700px', '450px'],
				  content: ['pkgl_ksdxg.html','no'], //这里content是一个普通的String
				  btn: ['确定'],
				  success:function(layero,index){
				   		//查看已有的数据
						$.ajax({
				            type: "get",
				            async: false,
				            url: "api/schedule-class/pre/detail?id="+id,
				            success: function (data){  
				            	var iframe = window['layui-layer-iframe' + index];
				            	var detailData = data.data;
				            	var newTime = detailData.time.split('-');
				            	//调用子页面的方法，将拿到的值通过形参带给父页面
				            	iframe.childData(detailData.coursename,newTime[0],newTime[1]);
				            },
				            error: function(error) {
				                layui.use('layer', function () {
				                    var layer = layui.layer;
				                    layer.msg(error.responseText);
				                });
							}  
				        });
				  },
				  yes:function(index,layero){
				  	  //当点击‘确定’按钮的时候，获取弹出层返回的值
					    var callBackData = window["layui-layer-iframe" + index].callbackdata();				    
						if(callBackData.startTime<callBackData.endTime){
							$.ajax({
								async: false,    //同步
								url:  "api/schedule-class/pre/update?id="+id,
								type: "get",
								data:{
									coursename:callBackData.courseName,
									time:callBackData.time
								},
								dataType: "json",
								success: function(data){									
									if(data.code == 1){
										layui.use('layer', function(){
										  var layer = layui.layer;					  
										  layer.msg('修改课时段成功');
										});   
										setTimeout(function(){
								    		layer.close(index);	
								    		location.reload();
								    	},800)
									}else{
										layui.use('layer', function(){
										  var layer = layui.layer;					  
										  layer.msg(data.msg);
										});   
										setTimeout(function(){
								    		layer.close(index);	
								    		location.reload();
								    	},800)
									}
									
								},
								error: function(error) {
					                layui.use('layer', function () {
					                    var layer = layui.layer;
					                    layer.msg(error.responseText);
					                });
								}  				
							});
						}else{
							layui.use('layer', function(){
							  var layer = layui.layer;					  
							  layer.msg('课时段结束时间应大于课时段开始时间');
							});   
						}
				 }		  
				});
			});
        }

        //删除课时段
        function delCourse(id){
			layui.use('layer', function () {
				layer.confirm('确认要删除吗？', function (index) {
					$.ajax({
						type: "get",
						async: false,
						url: "api/schedule-class/pre/delete?id=" + id,
						success: function (data) {
							if(data.code==1){
								layer.msg('已删除!', {icon:1, time:1000});
							}
							ajaxPLists();
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
        };