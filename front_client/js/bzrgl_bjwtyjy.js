

            //左侧导航
		    layui.use('element', function(){
		          var element = layui.element;
		     });

            $(function () {
                DatatoLoad();
            });
            //班级问题与建议
            function DatatoLoad() {
                    //分页
                    var pageNum = 0;//初始化页数为第一页
                    var pageSize = 10;
                    layui.use('laypage', function () {
                        var laypage = layui.laypage;
                        var totalRecord = 0;//初始化总记录数

                        //每页显示列数限制
                        if(totalRecord>0 && totalRecord<6){
                            var pageSize = 5;  //限定每页显示5页
                            $("#showPage").html(pageSize);
                        }else{
                            var pageSize = 10;
                            $("#showPage").html(pageSize);
                        };
                        getNewsData();
                        function getNewsData() {
                            $.ajax({
                                type: "get",
                                async: false,
                                url: "api/classsuggest/list",
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
                                        html += '<tr>';
                                        html += '<td><input type="checkbox"  name="box" onclick="selectAll()" value='+item.id+'> </td>';
                                        html += '<td>' + (pageNow + i + 1) + '</td>';
                                        html += '<td>' + item.title + '</td>'+'<td>' + item.classname + '</td>'+'<td>' + item.suggesttime + '</td>';
                                        html += '<td><a onclick="sc_delect(' + item.id + ')" href="JavaScript:;" class="bg bgActive del">删除</a>' +
                                            '<a href="bzrgl_bjwtyjy_xg.html?id=' + item.id + '" class="bg change">修改</a>' +
                                            '<a href="bzrgl_bjwtyjy_ck.html?id=' + item.id + '" class="bg change">查看</a></td>';
                                        html += '</tr>';
                                    })
                                    $("#list_bjwtyjy").html(html);
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
                                    $('#list_bjwtyjy').empty();
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
                            url: "api/classsuggest/delete",
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

            //全选
            function checkAll(obj) {
                var userids = document.getElementsByName("box");
                for (var i = 0; i < userids.length; i++) {
                    userids[i].checked = obj.checked;
                }
            }
            function selectAll(){
                var userids=document.getElementsByName("box");
                var count=0;
                //遍历所有的复选框
                for(var i=0; i<userids.length;i++){
                    if(userids[i].checked){
                        count++;
                    }
                }
                if(count===userids.length){
                    document.getElementById("all").checked=true;
                }
                else{
                    document.getElementById("all").checked=false;
                }
            }

            //导出
            function dxExport(){
                var selectedItems = new Array();
                $("input[name='box']:checked").each(function () {
                    selectedItems.push($(this).val());//push可向数组的末尾添加一个或多个元素，并返回新的长度
                });
                if (selectedItems.length > 5) {
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('最多只能选择5条数据！');
                    });
                }
                else if(selectedItems.length ==0){
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.msg('请选择数据！');
                    });
                }
                else {

                    window.location.href="api/classsuggest/exportclzissuerecmd?ids="+selectedItems;

                }
            }

            //查询
            function getTitle() {
                //验证真实姓名
                var title = $('#name').val();

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
                            url: "api/classsuggest/getTitle",
                            async: false,
                            data: {
                                title: title,
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
                                    $("#list_bjwtyjy").html('');
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
                                        chaXun += '<tr>';
                                        chaXun += '<td><input type="checkbox"  name="box" onclick="selectAll()" value=' + item.id + '> </td>';
                                        chaXun += '<td>' + (pageNow + i + 1) + '</td>';
                                        chaXun += '<td>' + item.title + '</td>' + '<td>' + item.classname + '</td>' + '<td>' + item.suggesttime + '</td>';
                                        chaXun += '<td><a onclick="sc_delect(' + item.id + ')" href="JavaScript:;" class="bg bgActive del">删除</a>' +
                                            '<a href="bzrgl_bjwtyjy_xg.html?id=' + item.id + '" class="bg change">修改</a>' +
                                            '<a href="bzrgl_bjwtyjy_ck.html?id=' + item.id + '" class="bg change">查看</a></td>';
                                        chaXun += '</tr>';

                                    })
                                    $("#list_bjwtyjy").html(chaXun);
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
                                $('#list_bjwtyjy').empty();
                                getQuery();
                            }
                        }
                    });
                })

            }


