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
            //花名册
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
                                url: "api/roster/list",
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

                                    var returnAge=data.data.list;
                                    $.each(data.data.list, function (i, item) {
                                        html += '<tr>';
                                        html += '<td>' + (pageNow + i + 1) + '</td>';
                                        html += '<td>' + item.stuname + '</td>'+'<td>' + item.stunumber + '</td>'+'<td class="loadAge" style="color:#fff;" id="color'+item.id+'">' + returnAge[i].age + '</td>'+
                                                '<td>' + item.termname + '</td>'+'<td>'+item.phone+'</td>'+'<td id="job('+item.id+')">' + item.isleader + '</td>' ;
                                        html+=  '<td><a href="javaScript:;" onclick="szzwh('+item.id+')" class="bg bgActive del">设置职务</a></td>'
                                        html += '</tr>';
                                        $("#classId").val(returnAge[i].classid);
                                        $("#termId").val(returnAge[i].termid);
                                    })
                                    $("#list_hmc").html(html);

                                    for(var num=0;num<returnAge.length;num++){
                                        var ageColor = returnAge[num].age;
                                        var ageId = returnAge[num].id;
                                        var newAge = parseInt(ageColor);
                                        if(50>=newAge && newAge<=55){
                                            $('#color'+ageId).css('background-color','#020cfa');
                                        }else if(56>=newAge && newAge<=60){
                                            $('#color'+ageId).css('background-color','#1f0ce4');
                                        }else if(61>=newAge && newAge<=65){
                                            $('#color'+ageId).css('background-color','#410ac8');
                                        }else if(65>=newAge && newAge<=70){
                                            $('#color'+ageId).css('background-color','#5f06ac');
                                        }else if(71>=newAge && newAge<=75){
                                            $('#color'+ageId).css('background-color','#7f0693');
                                        }else if(newAge==76){
                                            $('#color'+ageId).css('background-color','#99057d');
                                        }else if(newAge==77){
                                            $('#color'+ageId).css('background-color','#b80360');
                                        }else if(newAge==78){
                                            $('#color'+ageId).css('background-color','#d40249');
                                        }else if(newAge==79){
                                            $('#color'+ageId).css('background-color','#e80139');
                                        }else if(newAge>=80){
                                            $('#color'+ageId).css('background-color','#fe0000');
                                        }
                                    }

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
                                    $('#list_hmc').empty();
                                    getNewsData();
                                }
                            }
                        });
                    })
            }
            //下拉框
            $.ajax({
                type:'get',
                async:false,
                url:'api/roster/clist',
                dataType: 'json',
                xhrFields: {withCredentials: true},
                success:function (data) {
                    console.log(data);
                    var i=0;
                    $.each(data.data,function (i,item) {
                        $("#name").append("<option value="+item.id+">"+item.classname+"</option>")
                    })
                    $("#name").val(data.data[0].id);
                },
                error: function(error) {
	                layui.use('layer', function () {
	                    var layer = layui.layer;
	                    layer.msg(error.responseText);
	                });	
            	}
            })

            //查询
            function getTitle() {
                //班级查询
                var classname = $('#name option:selected').val();
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
                            url: "api/roster/query",
                            async: false,
                            data: {
                                classid: classname,
                                page: pageNum,
                                size: pageSize
                            },
                            dataType: 'json',
                            xhrFields: {withCredentials: true},
                            success: function (data) {
                                if (data.data.list.length == 0) {
                                    layui.use('layer', function () {
                                        var layer = layui.layer;
                                        layer.alert('未查到相关信息', {area: ['200px', '180px']});
                                    });
                                    $("#list_hmc").html('');
                                    return false;
                                }
                                else {
                                    totalRecord = data.data.total;//数据总条数
                                    var chaxun;
                                    var len = data.data.list.length;
                                    for (var i = 1; i < len + 1; i++) {
                                        $('table tr:eq(' + i + ')td:first').text(i);
                                    }
                                    var returnAge = data.data.list;

                                    pageCurrent = data.data.pageNum;//获取当前页
                                    pageNow =  (pageCurrent-1)*10; //当前页数的序号

                                    $.each(data.data.list, function (i, item) {
                                        chaxun += '<tr>';
                                        chaxun += '<td>' + (pageNow + i + 1) + '</td>';
                                        chaxun += '<td>' + item.stuname + '</td>' + '<td>' + item.stunumber + '</td>' + '<td id="color' + item.id + '"  style="color:#fff;" >' + returnAge[i].age + '</td>' +
                                            '<td>' + item.termname + '</td>' + '<td>' + item.phone + '</td>' + '<td id="job(' + item.id + ')">' + item.isleader + '</td>';
                                        chaxun += '<td><a href="javaScript:;" onclick="szzwh(' + item.id + ')" class="bg bgActive del">设置职务</a></td>'
                                        chaxun += '</tr>';
                                        $("#classId").val(returnAge[i].classid);
                                        $("#termId").val(returnAge[i].termid);
                                    })
                                    $("#list_hmc").html(chaxun);

                                    for(var num=0;num<returnAge.length;num++){
                                        var ageColor = returnAge[num].age;
                                        var ageId = returnAge[num].id;
                                        var newAge = parseInt(ageColor);
                                        if(50>=newAge && newAge<=55){
                                            $('#color'+ageId).css('background-color','#020cfa');
                                        }else if(56>=newAge && newAge<=60){
                                            $('#color'+ageId).css('background-color','#1f0ce4');
                                        }else if(61>=newAge && newAge<=65){
                                            $('#color'+ageId).css('background-color','#410ac8');
                                        }else if(65>=newAge && newAge<=70){
                                            $('#color'+ageId).css('background-color','#5f06ac');
                                        }else if(71>=newAge && newAge<=75){
                                            $('#color'+ageId).css('background-color','#7f0693');
                                        }else if(newAge==76){
                                            $('#color'+ageId).css('background-color','#99057d');
                                        }else if(newAge==77){
                                            $('#color'+ageId).css('background-color','#b80360');
                                        }else if(newAge==78){
                                            $('#color'+ageId).css('background-color','#d40249');
                                        }else if(newAge==79){
                                            $('#color'+ageId).css('background-color','#e80139');
                                        }else if(newAge>=80){
                                            $('#color'+ageId).css('background-color','#fe0000');
                                        }
                                    }
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
                                $('#list_hmc').empty();
                                getQuery();
                            }
                        }
                    });
                })
            }

            //设置组委会
            function szzwh(id) {
                layui.use('layer', function() {
                    var layer = layui.layer;
                    layer.open({
                        title: '设置职务',
                        type: 2,
                        area: ['500px', '400px'],
                        fixed: false, //不固定
                        maxmin: true, //开启最大化最小化按钮
                        content: 'bzrgl_hmc_alert.html',
                        btn: ['确定', '取消'],
                        yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                            //当点击‘确定’按钮的时候，获取弹出层返回的值
                            var res = window["layui-layer-iframe" + index].callbackdata();
                            //打印返回的值，看是否有我们想返回的值。
                            var jobName = res.zhiwu;
                            var jobNameId = res.zhiwuVal;
                            if(jobName=='请选择'){
                                layui.use('layer', function () {
                                    var layer = layui.layer;
                                    layer.alert('请选择职务', {area: ['200px','180px']});
                                });
                                return false;
                            }else{
                                $.ajax({
                                    type: 'POST',
                                    url: "api/roster/save",
                                    async: false,
                                    data: {
                                        id: id,
                                        isleader: jobName,
                                        classid: jobNameId
                                    },
                                    dataType: "json",
                                    xhrFields: {withCredentials: true},
                                    success: function (data) {
                                        //window.location.href = "bzrgl_hmc.html";
                                        layui.use('layer', function () {
                                            var layer = layui.layer;
                                            layer.msg('设置职务成功！', {area: ["150px", "50px"]});
                                        });
                                        DatatoLoad();
                                    },
                                    error: function(error) {
						                layui.use('layer', function () {
						                    var layer = layui.layer;
						                    layer.msg(error.responseText);
						                });	
					            	}
                                })
                            }

                            //最后关闭弹出层
                             layer.close(index);

                        }
                        , btn2: function (index, layero) {

                        }
                    });
                })
            }

            //导出
            function dxExport() {
                var classesId = $("#classId").val();
                var termId = $("#termId").val();

                    window.location.href="api/roster/exportcheckinform?classesId="+classesId+"&termId="+termId;

            }


            //导出到学籍
            function qyExport() {
                var classid=$("#classId").val();
                var termid=$("#termId").val();
                $.ajax({
                    type: 'POST',
                    url: "api/roster/conversion",
                    async: false,
                    data: {
                        classid: classid,
                        termid:termid
                    },
                    dataType: "json",
                    xhrFields: {withCredentials: true},
                    success: function (data) {
                        layui.use('layer', function () {
                            var layer = layui.layer;
                            layer.msg('迁移到学籍成功！', {area: ["150px", "50px"]});
                        });
                    },
                    error: function(error) {
		                layui.use('layer', function () {
		                    var layer = layui.layer;
		                    layer.msg(error.responseText);
		                });	
	            	}
                })
            }


