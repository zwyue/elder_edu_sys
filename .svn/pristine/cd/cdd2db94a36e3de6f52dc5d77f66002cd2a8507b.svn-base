        layui.use('form', function(){
            var form = layui.form;
            //鐩戝惉鎻愪氦
            form.on('submit(formDemo)', function (data) {
                layer.msg(JSON.stringify(data.field));
                return false;
            });
        });        

        $(function(){
            ajaxPLists();
        });
        function ajaxPLists(){ 
            var pageNum = 1,
            pageSize = 10; //初始化页数为第一页
            //分页
            layui.use('laypage', function (){
                var laypage = layui.laypage;
                var totalRecord = 0;             //初始化总记录数 
                //每页显示条数
                getNewsData();

                function getNewsData(){
                    $.ajax({
                        type: "get",
                        async: false,
                        url: "api/student/list",
                        data: {
                            page: pageNum,
                            size: pageSize
                        },
                        success: function(data){                        
                            totalRecord = data.data.total;    //数据总条数
                            var html ="";             
                            $.each(data.data.list, function (i, item){
                                pageCurrent = data.data.pageNum; //获取当前页
                                 pageNow = (pageCurrent - 1) * 10; //当前页数的序号
                                //年龄和专业需要调整
//                              var addTime = new Date(item.crttime).Format("yyyy-MM-dd");
                                html += '<tr>';
                                html += '<td>' + (pageNow+i+1) + '</td><td>' + item.stuname + '</td><td>' + item.cateName + '</td><td>' + item.majorName + '</td><td>' + item.classname + '</td><td>' + item.phone + '</td><td>' + item.emergency + '</td><td>' + item.headMasterName + '</td><td style="display:none">' + item.id + '</td>';
                                html += '<td class="td-manage">' +
                                    '<a title="删除" class="layui-btn layui-btn-xs layui-btn-danger detBtn" onclick="delStudent('+item.id+')" lay-event="del" >删除</a>' +
                                    '<a title="编辑" href="yhgl_xsgl_xsxg.html?id=+' + item.id + '"  class="layui-btn layui-btn-xs chaBtn" lay-event="edit" >修改</a>' +
                                    '<a title="查看" href="yhgl_xsgl_xsck.html?id=+' + item.id + '" class="layui-btn layui-btn-xs layui-btn-primary viewBtn">查看</a>' +
                                    '</td>';
                                html += '</tr>';
                            })
                            $('tbody').html(html);
                        },
                        error: function (error){
                            layui.use('layer', function (){
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
        function delStudent(id){
            layui.use('layer', function(){
                layer.confirm('确认要删除吗？', function (index){
                    $.ajax({
                        type: "get",
                        async: false,
                        url: "api/student/delete?stuId="+id,
                        success: function (data){
                            if (data.code == 1){
                                layer.msg('已删除!', {icon: 1, time: 1000});
                            } else if (data.code == 0){
                                layer.msg('删除失败!', {icon: 2, time: 1000});
                            }
                            ajaxPLists();
                        },
                        error: function(error){
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg(error.responseText);
                            });
                        }
                    });
                    layui.close(index);
                });
            });
        };

//查询
$('#chaXun').click(function(){
    //拿到值
    var name = document.getElementById("name").value;
    var idCard = document.getElementById("idCard").value;
    var termStatus = $('#xq option:selected').val();

    //分页
    var pageNum = 1; //初始化页数为第一页
    var pageSize = 10;
    layui.use('laypage', function() {
        var laypage = layui.laypage;
        var totalRecord = 0; //初始化总记录数
        getQuery();

        function getQuery(){
            $.ajax({
                type: 'GET',
                url: "api/student/list",
                async: false,
                data: {
                    termStatus: termStatus,
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
                            layer.msg('未查询到数据')
                        });
                        $("tbody").html('');
                        return false;
                    } else {
                        totalRecord = data.data.total;//数据总条数
                        var chaXunHtml = '';
                        $.each(data.data.list, function (i, item){
                                pageCurrent = data.data.pageNum; //获取当前页
                                 pageNow = (pageCurrent - 1) * 10; //当前页数的序号
                                //年龄和专业需要调整
//                              var addTime = new Date(item.crttime).Format("yyyy-MM-dd");
                                chaXunHtml += '<tr>';
                                chaXunHtml += '<td>' + (pageNow+i+1) + '</td><td>' + item.stuname + '</td><td>' + item.cateName + '</td><td>' + item.majorName + '</td><td>' + item.classname + '</td><td>' + item.phone + '</td><td>' + item.emergency + '</td><td>' + item.headMasterName + '</td><td style="display:none">' + item.id + '</td>';
                                chaXunHtml += '<td class="td-manage">' +
                                    '<a title="删除" class="layui-btn layui-btn-xs layui-btn-danger detBtn" onclick="delStudent('+item.id+')" lay-event="del" >删除</a>' +
                                    '<a title="编辑" href="yhgl_xsgl_xsxg.html?id=+' + item.id + '"  class="layui-btn layui-btn-xs chaBtn" lay-event="edit" >修改</a>' +
                                    '<a title="查看" href="yhgl_xsgl_xsck.html?id=+' + item.id + '" class="layui-btn layui-btn-xs layui-btn-primary viewBtn">查看</a>' +
                                    '</td>';
                                chaXunHtml += '</tr>';
                            })
                        $("tbody").html(chaXunHtml);
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
                    $('tbody').empty();
                    getQuery();
                }
            }
        });
    })
})