

    //from表单select 通用
    layui.use('form', function(){
        var form = layui.form;
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
                    url: "api/studentrecord/getlist",
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

                        pageCurrent = data.data.pageNum;//获取当前页
                        pageNow =  (pageCurrent-1)*10; //当前页数的序号

                        $.each(data.data.list, function (i, result) {
                            html += "<tr>";
                            html += "<td>" + (pageNow + i + 1) + "</td>" + "<td>" + result.stuname + "</td>" + "<td>" + result.classname + "</td>"
                            html += "<td>" + result.phone + "</td>" + "<td>" + result.famPhone + "</td>" + "<td>" + result.termname + "</td>";
                            html += "</tr>";
                        })
                        $("#listXjda").html(html);
                    },
                    error: function(error) {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            var msg = error.responseText.replace(/^\s*|\s*$/g,"");
                            msg = msg==""?"系统错误"?msg;
                            layer.msg(msg);
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
                        $("#listXjda").empty();
                        getDatasSize();
                    }
                }
            })
        })
    }

    //时间查询下拉框
    $.ajax({
        type:'get',
        async:false,
        url:'api/studentrecord/termlist',
        dataType: 'json',
        xhrFields: {withCredentials: true},
        success:function (data) {
            var i=0;
            $.each(data.data,function (i,item) {
                $("#time").append("<option value="+item.id+">"+item.term+"</option>")
            })
        },
        error: function(error) {
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.msg(error.responseText);
            });
        }
    })

    //班级选择
    function selectClass() {
        layer.open({
            title: '选择班级',
            type: 2,
            area: ['500px', '400px'],
            fixed: false, //不固定
            maxmin: true, //开启最大化最小化按钮
            content: 'bzrgl_tsxyjl_xz_tuiban_alert.html',
            btn: ['确定', '取消'],
            yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                //当点击‘确定’按钮的时候，获取弹出层返回的值
                var res = window["layui-layer-iframe" + index].callbackdata();
                //打印返回的值，看是否有我们想返回的值。
                (res);
                $("#filterClass").val(res.username);
                $("#filterClassId").val(res.usernameid);
                //最后关闭弹出层
                layer.close(index);
            }
            , btn2: function (index, layero) {

            }
        });
    }


	//查询
	function chaxun() {
        var termId = $('#time option:selected').val();
        var textclassId = $('#filterClassId').val();
        if(termId=="" || textclassId==""){
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert('请选择时间和班级', {area: ['200px', '160px']});
            });
        }
        else{
            //分页
            var pageNum = 0;//初始化页数为第一页
            var pageSize = 10;
            layui.use('laypage', function () {
                var laypage = layui.laypage;
                var totalRecord = 0;//初始化总记录数
                getQuery();

                function getQuery() {
                    $.ajax({
                        type: 'get',
                        url: 'api/studentrecord/stulist',
                        async: false,
                        data: {
                            termid: termId,
                            classid: textclassId,
                            page: pageNum,
                            size: pageSize
                        },
                        dataType: "json",
                        xhrFields: {withCredentials: true},
                        success: function (data) {
                            if (data.data.list.length == 0) {
                                layui.use('layer', function () {
                                    var layer = layui.layer;
                                    layer.alert('该学生未查到相关信息', {area: ['200px', '160px']});
                                });
                                $("#listXjda").html('');
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

                                $.each(data.data.list, function (i, result) {
                                    chaXun += "<tr>";
                                    chaXun += "<td>" + (pageNow + i + 1) + "</td>" + "<td>" + result.stuname + "</td>" + "<td>" + result.classname + "</td>"
                                    chaXun += "<td>" + result.phone + "</td>" + "<td>" + result.famPhone + "</td>" + "<td>" + result.termname + "</td>";
                                    chaXun += "</tr>";
                                })
                                $("#listXjda").html(chaXun);
                            }
                        },
                        error: function(error) {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg(error.responseText);
                            });
                        }
                    })
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
                                $('#listXjda').empty();
                                getQuery();
                            }
                        }
                    });
                }
            })
        }
    }