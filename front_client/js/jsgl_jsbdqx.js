
		$(function(){
            ajaxPLists();
        });
        function ajaxPLists(){ 
        	//分页
            var pageNum = 0, pageSize;  //初始化页数为第一页
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
                        url: "api/role/role-list",
//                      xhrFields: {withCredentials: true}, 
                        data: {
                            page: pageNum,
                            size: pageSize
                        },
                        success: function (data){
                            totalRecord = data.data.total;//数据总条数
                            var html ="";
                            pageCurrent = data.data.pageNum;//获取当前页
                            pageNow =  (pageCurrent-1)*10; //当前页数的序号
                            $.each(data.data.list, function (i, item){
                            	//年龄和专业需要调整
//                              var addTime = new Date(item.crttime).Format("yyyy-MM-dd");
                                html += '<tr>';
                                html += '<td>' + (pageNow+i+1) + '</td>' +'<td>' + item.rolename + '</td>' +'<td style="display:none">' + item.id + '</td>';
                                html += '<td class="td-manage">' +
                                    '<a title="删除" class="layui-btn layui-btn-xs layui-btn-danger detBtn" onclick="delRole('+item.id+')" lay-event="del" >删除</a>' +
                                    '<a title="编辑" href="jsgl_jsxg.html?id='+item.id+'"  class="layui-btn layui-btn-xs chaBtn" lay-event="edit" >修改</a>' +
                                    '<a title="查看" href="jsgl_jsck.html?id='+item.id+'" class="layui-btn layui-btn-xs layui-btn-primary viewBtn">查看</a>' +
                                    '</td>';
                                html += '</tr>';
                            })
                            $('tbody').html(html);
                            //打印总条数
                           // ('总条数：'+totalRecord);
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
                    , jump: function (obj, first){
                        //obj包含了当前分页的所有参数，比如：
                        pageNum = obj.curr;//得到当前页，以便向服务端请求对应页的数据。
                        pageSize = obj.limit;//得到每页显示的条数
                        //首次不执行，清除上一页数据
                        if (!first){
                            //do something
                            $('tbody').empty();
                            getNewsData();
                        }
                    }
                });
            });
        };

		//删除数据
        function delRole(id){
            layui.use('layer', function () {
                layer.confirm('确认要删除吗？', function (index) {
                    $.ajax({
                        type: "get",
                        async: false,
//              xhrFields: {withCredentials: true}, 
                        url: "api/role/delete?roleId=" + id,
                        success: function (data) {
                            if (data.code == 1) {
                                layer.msg('已删除!', {icon: 1, time: 1000});
                            } else if (data.code == 0) {
                                layer.msg('班主任或管理员角色不可删除!', {icon: 2, time: 1000});
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
	