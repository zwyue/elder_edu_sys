//from表单select 通用
   layui.use('form', function(){
      var form = layui.form;
      //监听提交
      form.on('submit(formDemo)', function(data){
         layer.msg(JSON.stringify(data.field));
         return false;
      });
   });
   //左侧导航
   layui.use('element', function(){
       var element = layui.element;
   });
   //分页
   layui.use(['laypage', 'layer'], function(){
       var laypage = layui.laypage,layer = layui.layer;
       laypage.render({
          elem: 'demo5'
          ,count: 500
          ,curr: location.hash.replace('#!fenye=', '') //获取hash值为fenye的当前页
          ,hash: 'fenye' //自定义hash值
       });
   });

/*教学管理-请假-内容 教学管理-请假-内容 教学管理-请假-内容*/
    //表格增删改查
    var tableList = [
      { id: '1', name: '张三',  zc:'第一周',  jltime:'2018-11-11', sc:'一天', },
      { id: '2', name: '张三',  zc:'第一周',  jltime:'2018-11-11', sc:'一天', },
      { id: '3', name: '张三',  zc:'第一周',  jltime:'2018-11-11', sc:'一天', },
      { id: '4', name: '张三',  zc:'第一周',  jltime:'2018-11-11', sc:'一天', },
      { id: '5', name: '张三',  zc:'第一周',  jltime:'2018-11-11', sc:'一天', },
      { id: '6', name: '张三',  zc:'第一周',  jltime:'2018-11-11', sc:'一天', },
      { id: '7', name: '张三',  zc:'第一周',  jltime:'2018-11-11', sc:'一天',},
    ];
    $.each(tableList, function(){
      $('<tr id="'+this.id+'">' +
        '<td>'+this.id+'</td>' +
        '<td>'+this.name+'</td>' +
        '<td>'+this.zc+'</td>' +
        '<td>'+this.jltime+'</td>' +
        '<td>'+this.sc+'</td>' +
        '<td><a href="jxgl_qj_ck.html" class="bg change">查看</a><a onclick="" class="bg change">导出</a></td>'+
        '</tr>').appendTo('#jxgl_qj');
    });
/*教学管理-请假-内容 教学管理-请假-内容 教学管理-请假-内容end*/

/*教学管理-倒课-内容 教学管理-倒课-内容 教学管理-倒课-内容*/
    //表格增删改查
    var tableList = [
       { id: '1', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
       { id: '2', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
       { id: '3', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
       { id: '4', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
       { id: '5', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
       { id: '6', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
       { id: '7', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
    ];
    $.each(tableList, function(){
       $('<tr id="'+this.id+'">' +
        '<td>'+this.id+'</td>' +
        '<td>'+this.name+'</td>' +
        '<td>'+this.zc+'</td>' +
        '<td>'+this.jltime+'</td>' +
        '<td><a href="jxgl_qj_ck.html" class="bg change">查看</a><a onclick="" class="bg change">导出</a></td>'+
        '</tr>').appendTo('#jxgl_dk');
    });
/*教学管理-倒课-内容 教学管理-倒课-内容 教学管理-倒课-内容 end*/

/*教学管理-停课-内容 教学管理-停课-内容 教学管理-停课-内容*/
    //表格增删改查
     var tableList = [
       { id: '1', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
       { id: '2', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
       { id: '3', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
       { id: '4', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
       { id: '5', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
       { id: '6', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
       { id: '7', name: '张三',  zc:'第一周',  jltime:'2018-11-11',  },
     ];
     $.each(tableList, function(){
       $('<tr id="'+this.id+'">' +
        '<td>'+this.id+'</td>' +
        '<td>'+this.name+'</td>' +
        '<td>'+this.zc+'</td>' +
        '<td>'+this.jltime+'</td>' +
        '<td><a href="jxgl_qj_ck.html" class="bg change">查看</a><a onclick="" class="bg change">导出</a></td>'+
        '</tr>').appendTo('#jxgl_tk');
     });
    //请假-新增弹窗
    function qjrpeople() {
      layui.use('layer', function(){
        var layer = layui.layer;
        layer.open({
            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            type: 1,
            skin: '',
            title: "选择人员",
            area: ['50%', '50%'],
            content: $("#popSearchRoleTest-jxgl-qj").html()
        })
      });
    };
/*教学管理-停课-内容 教学管理-停课-内容 教学管理-停课-内容 end*/