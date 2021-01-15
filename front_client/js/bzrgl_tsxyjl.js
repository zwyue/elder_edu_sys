            //from表单select 通用
            layui.use('form', function(){
                var form = layui.form;
                //监听提交
                form.on('submit(formDemo)', function (data) {
                    layer.msg(JSON.stringify(data.field));
                    return false;
                });
            });

            //左侧导航
		    layui.use('element', function(){
		        var element = layui.element;
		    });

            $(function () {
                DatatoLoad();
            });
            //特殊学员记录
            function DatatoLoad() {
                    //分页
                    var pageNum = 0;//初始化页数为第一页
                    var pageSize = 10;
                    layui.use('laypage', function () {
                        var laypage = layui.laypage;
                        var totalRecord = 0;//初始化总记录数
                        getNewsData();

                        function getNewsData() {
                            $.ajax({
                                type: "get",
                                async: false,
                                url: "api/specialmanager/list",
                                data: {
                                    page: pageNum,
                                    size: pageSize
                                },
                                dataType: 'json',
                                xhrFields: {withCredentials: true},
                                success: function (data) {
                                    totalRecord = data.data.total;//数据总条数
                                    var html;
                                    var len = data.data.list.length;
                                    for (var i = 1; i < len + 1; i++) {
                                        $('table tr:eq(' + i + ')td:first').text(i);
                                    }

                                    pageCurrent = data.data.pageNum;//获取当前页
                                    pageNow =  (pageCurrent-1)*10; //当前页数的序号

                                    $.each(data.data.list, function (i, item) {
                                        var types=item.types;
                                        if(types==0){
                                            var ydResult='退班';
                                        }
                                        if(types==1){
                                            var ydResult='转班';
                                        }
                                        if(types==2){
                                            var ydResult='休学';
                                        }
                                        if(types==3){
                                            var ydResult='开除';
                                        }
                                        html += '<tr>';
                                        html += '<td>' + (pageNow + i + 1) + '</td>';
                                        html += '<td>' + item.sname + '</td>'+'<td>' + item.stunumber + '</td>'+'<td>' + item.classname + '</td>'+
                                                '<td>' + item.phone + '</td>'+'<td>' + item.filtime + '</td>'+'<td>' + ydResult + '</td>' ;
                                        html += '<td><a onclick="sc_delect(' + item.id + ')" href="JavaScript:;" class="bg bgActive del">删除</a>' +
                                            '<a href="bzrgl_tsxyjl_xg.html?id=' + item.id + '" class="bg change">修改</a>' +
                                            '<a href="bzrgl_tsxyjl_ck.html?id=' + item.id + '" class="bg change">查看</a>' +
                                            '<a href="api/specialmanager/export?id=' + item.id + '" class="bg look">导出</a></td>';
                                        html += '</tr>';
                                    })
                                    $("#list_tsxyjl").html(html);
                                },
                                error: function (XMLHttpRequest, textStatus, errorThrown) {
                                    console.log(XMLHttpRequest.status);
                                    console.log(XMLHttpRequest.readyState);
                                    console.log(textStatus);
                                }
                            });
                        }

                        //执行一个laypage实例
                        laypage.render({
                            elem: 'demo5' //注意，这里的 test1 是 ID，不用加 #号
                            , count: totalRecord //数据总数，从服务端得到
                            , curr: pageNum
                            , limit: pageSize
                            , jump: function (obj, first) {
                                //obj包含了当前分页的所有参数，比如：
                                pageNum = obj.curr;//得到当前页，以便向服务端请求对应页的数据。
                                pageSize = obj.limit;//得到每页显示的条数

                                //首次不执行
                                if (!first) {
                                    //do something
                                    $('#list_tsxyjl').empty();
                                    getNewsData();
                                }
                            }
                        });
                    })
            }
            //删除
            function sc_delect(id) {
                layui.use('layer', function () {
                    layer.confirm('确认要删除吗？', function (index) {
                        $.ajax({
                            type: 'GET',
                            url: "api/specialmanager/delete",
                            async: false,
                            data: {
                                id: id
                            },
                            dataType: 'json',
                            xhrFields: {withCredentials: true},
                            success: function (data) {
                                if (data.code == 1) {
                                    layer.msg('已删除!', {icon: 1, time: 1000});
                                } else if (data.code == 0) {
                                    layer.msg('删除失败!', {icon: 2, time: 1000});
                                }
                                DatatoLoad();
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
            function getTitle() {
                //验证真实姓名
                var title = $('#name').val();
                var resultValue = $('#result option:selected').val();

                //分页
                var pageNum = 0;//初始化页数为第一页
                var pageSize = 10;
                layui.use('laypage', function () {
                    var laypage = layui.laypage;
                    var totalRecord = 0;//初始化总记录数
                    getQuery();

                    function getQuery() {
                        $.ajax({
                            type: 'GET',
                            url: "api/specialmanager/getStuByName",
                            async: false,
                            data: {
                                sname: title,
                                types: resultValue,
                                page: pageNum,
                                size: pageSize
                            },
                            dataType: 'json',
                            xhrFields: {withCredentials: true},
                            success: function (data) {
                                if (data.data.list.length == 0) {
                                    layui.use('layer', function () {
                                        var layer = layui.layer;
                                        layer.alert('该学生未查到相关信息', {area: ['200px']});
                                    });
                                    $("#list_tsxyjl").html('');
                                    return false;
                                }
                                else {
                                    totalRecord = data.data.total;
                                    var chaXun;
                                    var len = data.data.list.length;
                                    for (var i = 1; i < len + 1; i++) {
                                        $('table tr:eq(' + i + ')td:first').text(i);
                                    }

                                    pageCurrent = data.data.pageNum;//获取当前页
                                    pageNow =  (pageCurrent-1)*10; //当前页数的序号

                                    $.each(data.data.list, function (i, item) {
                                        var types = item.types;
                                        if (types == 0) {
                                            var ydResult = '退班';
                                        }
                                        if (types == 1) {
                                            var ydResult = '转班';
                                        }
                                        if (types == 2) {
                                            var ydResult = '休学';
                                        }
                                        if (types == 3) {
                                            var ydResult = '开除';
                                        }
                                        chaXun += '<tr>';
                                        chaXun += '<td>' + (pageNow + i + 1) + '</td>';
                                        chaXun += '<td>' + item.sname + '</td>' + '<td>' + item.stunumber + '</td>' + '<td>' + item.classname + '</td>' +
                                            '<td>' + item.phone + '</td>' + '<td>' + item.filtime + '</td>' + '<td>' + ydResult + '</td>';
                                        chaXun += '<td><a onclick="sc_delect(' + item.id + ')" href="JavaScript:;" class="bg bgActive del">删除</a>' +
                                            '<a href="bzrgl_tsxyjl_xg.html?id=' + item.id + '" class="bg change">修改</a>' +
                                            '<a href="bzrgl_tsxyjl_ck.html?id=' + item.id + '" class="bg change">查看</a>' +
                                            '<a href="api/specialmanager/export?id=' + item.id + '" class="bg look">导出</a></td>';
                                        chaXun += '</tr>';

                                    })
                                    $("#list_tsxyjl").html(chaXun);
                                }
                            },
                            error: function(error) {
                                layui.use('layer', function () {
                                    var layer = layui.layer;
                                    layer.msg(error.responseText);
                                });
                            }
                        });
                    }

                    //执行一个laypage实例
                    laypage.render({
                        elem: 'demo5' //注意，这里的 test1 是 ID，不用加 #号
                        , count: totalRecord //数据总数，从服务端得到
                        , curr: pageNum
                        , limit: pageSize
                        , jump: function (obj, first) {
                            //obj包含了当前分页的所有参数，比如：
                            pageNum = obj.curr;//得到当前页，以便向服务端请求对应页的数据。
                            pageSize = obj.limit;//得到每页显示的条数

                            //首次不执行
                            if (!first) {
                                //do something
                                $('#list_tsxyjl').empty();
                                getQuery();
                            }
                        }
                    });
                })

            }


