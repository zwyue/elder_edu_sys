
      //左侧导航
       layui.use('element', function(){
           var element = layui.element;
       });

       // 学期管理表格
        $(function () {
            DatatoLoad();
        })

        function DatatoLoad() {
            //分页
            var pageNum = 0;//初始化页数为第一页
            var pageSize = 10;
            layui.use('laypage', function () {
                var laypage = layui.laypage;
                var totalRecord = 0;//初始化总记录数
                getNewsData();

                function getNewsData(){
                    var lastdate;
                    $.ajax({
                        type: "get",
                        async: false,
                        //url: "api/term/termlist",
                        url: "api/term/termlist",
                        dataType: 'json',
                        xhrFields: {withCredentials: true},
                        data: {
                            page: pageNum,
                            size: pageSize
                        },
                        success: function (data) {
                          console.log(data);
                            totalRecord = data.data.total;//数据总条数
                            var html;
                            var i = 0;
                            pageCurrent = data.data.pageNum;//获取当前页
                            pageNow =  (pageCurrent-1)*10; //当前页数的序号
                            $.each(data.data.list, function (i, result) {
                                html += '<tr>';
                                html += '<td>' + (pageNow+i+1) + '</td>';
                                html += '<td>' + result.term + '</td>' + '<td>' + result.starttime + '</td>' + '<td>' + result.endtime + '</td>';
                                html += '<td><a onclick="sc_delect(' + result.id + ')" href="JavaScript:;" class="bg bgActive del detBtn">删除</a>' +
                                    '<a href="xqgl_xg.html?id=' + result.id + '" class="bg change chaBtn">修改</a>' +
                                    '<a href="javaScarpt:;" onclick="state(' + result.id + ')" id="zt'+result.id+'" class="bg change">'+result.isEnableTxt+'</a>' +
                                    '<a href="javaScarpt:;"><input type="hidden" id="activety'+result.id+'" value='+result.isenable+'></a>' +
                                    '</td>';
                                // <a href="memorabilia/export?id=' + result.id + '" class="bg look">导出</a>
                                html += '</tr>';

                            })
                            $("#list_xqgl").html(html);

                            //获得最后一个结束时间
                            if(data.data.list.length!=0){
                                lastdate = data.data.list[0].endtime;
                            }
                            $("#xzxq").attr("href", "xqgl_xz.html?lastdate=" + lastdate);


                            //isenable为0和4时的状态
                            var i;
                            for(i=1;i<data.data.list.length;i++){
                                var isenableval=data.data.list[i].isenable;
                                var Id=data.data.list[i].id;
                                if(isenableval==0){
                                    $("#zt"+Id).css('background-color','#f1f1f1');
                                    $("#zt"+Id).css('color','#999999');
                                    $("#zt"+Id).css('border-color','#999999');
                                    DatatoLoad();

                                }else if(isenableval==4) {
                                    $("#zt"+Id).css('background-color', '#f1f1f1');
                                    $("#zt"+Id).css('color', '#999999');
                                    $("#zt"+Id).css('border-color','#999999');
                                    DatatoLoad();
                                }
                            }
                        },
                        error: function(error){
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
                            $('#list_xqgl').empty();
                            getNewsData();
                        }
                    }
                });
            })
        }

      //删除
      function sc_delect(ids) {
          layui.use('layer', function () {
              layer.confirm('确认要删除吗？', function (index) {
                  if (ids.constructor === Array) {
                      console.log("is array");
                  } else {
                      var temp = ids;
                      ids = [];
                      ids.push(temp);
                  }
                  $.ajax({
                      type: 'post',
                      url: "api/term/deleteterm",
                      async: false,
                      data: {
                          idList: ids
                      },
                      dataType: 'json',
                      success: function (data) {
                          if(data.code==1){
                              layer.msg('已删除!', {icon:1, time:1000});
                          }else if(data.code==0){
                              layer.msg('删除失败!', {icon:2, time:1000});
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

      //修改状态
      function state(id) {
          var isenableVal=$("#activety"+id).val();
          if(isenableVal==2){
              $.ajax({
                  type: 'post',
                  url: "api/term/active",
                  async: false,
                  data: {
                      id: id
                  },
                  dataType: 'json',
                  success: function (data) {
                      var i;
                      var DataList=data.msg;
                      layui.use('layer', function () {
                          var layer = layui.layer;
                          layer.msg(DataList);
                      });
                      DatatoLoad();
                  },
                  error: function(error) {
		                layui.use('layer', function () {
		                    var layer = layui.layer;
		                    layer.msg(error.responseText);
		                });
									}
              });
          }else if(isenableVal==1){
              $.ajax({
                  type: 'post',
                  url: "api/term/disable ",
                  async: false,
                  data: {
                      id: id
                  },
                  dataType: 'json',
                  success: function (data) {
                      var i;
                      var DataList=data.msg;
                      layui.use('layer', function () {
                          var layer = layui.layer;
                          layer.msg(DataList);
                      });
                      DatatoLoad();
                  },
                  error: function(error) {
		                layui.use('layer', function () {
		                    var layer = layui.layer;
		                    layer.msg(error.responseText);
		                });
									}
              });
          }
      }

