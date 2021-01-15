   //from表单select 通用
   layui.use('form', function(){
     var form = layui.form;
     //监听提交
     form.on('submit(formDemo)', function(data){
        layer.msg(JSON.stringify(data.field));
        return false;
     });
   });


//班委会职务列表 班委会职务列表 班委会职务列表
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

  //表格增删改查
  var tableList = [
    { id: '1', zwname: '班长', cjtime:'2018-11-11'},
    { id: '2', zwname: '学习委员', cjtime:'2018-11-11'},
    { id: '3', zwname: '学习委员', cjtime:'2018-11-11'},
    { id: '4', zwname: '学习委员', cjtime:'2018-11-11'},
    { id: '5', zwname: '学习委员', cjtime:'2018-11-11'},
    { id: '6', zwname: '学习委员', cjtime:'2018-11-11'},
    { id: '7', zwname: '学习委员', cjtime:'2018-11-11'}
  ];
  $.each(tableList, function(){
     $('<tr id="'+this.id+'">' +
        '<td>'+this.id+'</td>' +
        '<td>'+this.zwname+'</td>' +
        '<td>'+this.cjtime+'</td>' +
        '<td><a href="JavaScript:;" class="bg bgActive del">删除</a><a onclick="xgzw()" class="bg change">修改</a></td>'+
        '</tr>').appendTo('#list');
});
 //删除
 $('.del').click(function(){
     // layui.use('layer', function() {
     //     var layer = layui.layer;
     //     layer.confirm('确认删除本条记录吗?', {
     //         btn: ['是', '否'],
     //         btn1: function (index) {
     //             $(this).parents('tr').remove();
     //         },
     //         btn2: function () {
     //             layer.close(index);
     //         }
     //     })
     // });
         if (confirm('确定要删除吗')){
             $(this).parents('tr').remove();
         }

 });

 //班委会职务弹窗
 //新增职务弹窗
 function xzzw() {
     layui.use('layer', function(){
         var layer = layui.layer;
         layer.open({
             //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
             type: 1,
             skin: '',
             title: "新增职务",
             area: ['50%', '50%'],
             content: $("#popSearchRoleTest").html()
         })
     });
 };
 //修改职务弹窗
 function xgzw() {
     layui.use('layer', function(){
         var layer = layui.layer;
         layer.open({
             //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
             type: 1,
             skin: '',
             title: "修改职务",
             area: ['50%', '50%'],
             content: $("#popSearchRoleTest-xg").html()
         })
     });
 };

 //班委会职务列表 班委会职务列表 班委会职务列表end
 //花名册 花名册 花名册 花名册 花名册 花名册
    //表格增删改查
     var tableList = [
        { id: '1', name: '张三', xuehao:'器乐',age:'50', rxtime:'2018-11-11', linktype:'',zw:'班长'},
        { id: '2', name: '张三', xuehao:'器乐',age:'50', rxtime:'2018-11-11', linktype:'',zw:'班长'},
        { id: '3', name: '张三', xuehao:'器乐',age:'50', rxtime:'2018-11-11', linktype:'',zw:'班长'},
        { id: '4', name: '张三', xuehao:'器乐',age:'50', rxtime:'2018-11-11', linktype:'',zw:'班长'},
        { id: '5', name: '张三', xuehao:'器乐',age:'50', rxtime:'2018-11-11', linktype:'',zw:'班长'},
        { id: '6', name: '张三', xuehao:'器乐',age:'50', rxtime:'2018-11-11', linktype:'',zw:'班长'},
        { id: '7', name: '张三', xuehao:'器乐',age:'50', rxtime:'2018-11-11', linktype:'',zw:'班长'}
     ];
     $.each(tableList, function(){
         $('<tr id="'+this.id+'">' +
            '<td>'+this.id+'</td>' +
            '<td>'+this.name+'</td>' +
            '<td>'+this.xuehao+'</td>' +
            '<td>'+this.age+'</td>' +
            '<td>'+this.rxtime+'</td>' +
            '<td>'+this.linktype+'</td>' +
            '<td>'+this.zw+'</td>' +
            '</tr>').appendTo('#list_hmc');
     });
     //删除
     $('.del').click(function(){
         if (confirm('确定要删除吗')){
          $(this).parents('tr').remove();
        }
     });

     //设置组委员弹窗
     function szzwy() {
      layui.use('layer', function(){
        var layer = layui.layer;
        layer.open({
            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            type: 1,
            skin: '',
            title: "设置组委员",
            area: ['50%', '50%'],
            content: $("#popSearchRoleTest-SzBwh").html()
        })
      });
    };
 //花名册 花名册 花名册 花名册 花名册 花名册end

 //班主任工作总结 班主任工作总结 班主任工作总结 班主任工作总结
   //表格增删改查
     var tableList = [
        { id: '1', designation: '张三',  jltime:'2018-11-11', },
        { id: '2', designation: '张三',  jltime:'2018-11-11', },
        { id: '3', designation: '张三',  jltime:'2018-11-11', },
        { id: '4', designation: '张三',  jltime:'2018-11-11', },
        { id: '5', designation: '张三',  jltime:'2018-11-11', },
        { id: '6', designation: '张三',  jltime:'2018-11-11', },
        { id: '7', designation: '张三',  jltime:'2018-11-11', }
     ];
     $.each(tableList, function(){
       $('<tr id="'+this.id+'">' +
        '<td>'+this.id+'</td>' +
        '<td>'+this.designation+'</td>' +
        '<td>'+this.jltime+'</td>' +
        '<td><a href="JavaScript:;" class="bg bgActive del">删除</a><a href="bzrgl_bzrgzzj_xg.html" class="bg change">修改</a>' +
        '<a href="bzrgl_bzrgzzj_ck.html" class="bg change">查看</a><a onclick="" class="bg change">导出</a></td>'+
        '</tr>').appendTo('#list_bzrgzzj');
      });
      //删除
      $('.del').click(function(){
       if (confirm('确定要删除吗')){
         $(this).parents('tr').remove();
       }
     });
 //班主任工作总结 班主任工作总结 班主任工作总结 班主任工作总结end

 //班级工作计划 班级工作计划 班级工作计划 班级工作计划 班级工作计划
   //表格增删改查
      var tableList = [
        { id: '1', designation: '张三', jhclaass:'舞蹈班',  jltime:'2018-11-11', },
        { id: '2', designation: '张三', jhclaass:'舞蹈班',  jltime:'2018-11-11', },
        { id: '3', designation: '张三', jhclaass:'舞蹈班',  jltime:'2018-11-11', },
        { id: '4', designation: '张三', jhclaass:'舞蹈班',  jltime:'2018-11-11', },
        { id: '5', designation: '张三', jhclaass:'舞蹈班',  jltime:'2018-11-11', },
        { id: '6', designation: '张三', jhclaass:'舞蹈班',  jltime:'2018-11-11', },
        { id: '7', designation: '张三', jhclaass:'舞蹈班',  jltime:'2018-11-11', }
      ];
      $.each(tableList, function(){
        $('<tr id="'+this.id+'">' +
           '<td>'+this.id+'</td>' +
           '<td>'+this.designation+'</td>' +
           '<td>'+this.jhclaass+'</td>' +
           '<td>'+this.jltime+'</td>' +
           '<td><a href="JavaScript:;" class="bg bgActive del">删除</a><a href="bzrgl_bjgzjh_xg.html" class="bg change">修改</a>' +
           '<a href="bzrgl_bjgzjh_ck.html" class="bg change">查看</a><a onclick="" class="bg change">导出</a></td>'+
           '</tr>').appendTo('#list_bjgzjh');
      });
     //删除
      $('.del').click(function(){
         if (confirm('确定要删除吗')){
          $(this).parents('tr').remove();
         }
      });
      //班级工作计划-新增弹窗
      function xzteacher() {
         layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
              //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
               type: 1,
               skin: '',
               title: "选择班级",
               area: ['50%', '50%'],
               content: $("#popSearchRoleTest-xzteacher").html()
            })
         });
      };
 //班级工作计划 班级工作计划 班级工作计划 班级工作计划 班级工作计划end

 //特殊学员记录 特殊学员记录 特殊学员记录 特殊学员记录 特殊学员记录
   //表格增删改查
   var tableList = [
       { id: '1', designation: '张三', xuehao:'001', jhclaass:'舞蹈班', iphone:'15588800011',  jltime:'2018-11-11', yd:'退学', },
       { id: '2', designation: '张三', xuehao:'001', jhclaass:'舞蹈班', iphone:'15588800011',  jltime:'2018-11-11', yd:'退学', },
       { id: '3', designation: '张三', xuehao:'001', jhclaass:'舞蹈班', iphone:'15588800011',  jltime:'2018-11-11', yd:'退学', },
       { id: '4', designation: '张三', xuehao:'001', jhclaass:'舞蹈班', iphone:'15588800011',  jltime:'2018-11-11', yd:'退学', },
       { id: '5', designation: '张三', xuehao:'001', jhclaass:'舞蹈班', iphone:'15588800011',  jltime:'2018-11-11', yd:'退学', },
       { id: '6', designation: '张三', xuehao:'001', jhclaass:'舞蹈班', iphone:'15588800011',  jltime:'2018-11-11', yd:'退学', },
       { id: '7', designation: '张三', xuehao:'001', jhclaass:'舞蹈班', iphone:'15588800011',  jltime:'2018-11-11', yd:'退学', }
   ];
   $.each(tableList, function(){
       $('<tr id="'+this.id+'">' +
           '<td>'+this.id+'</td>' +
           '<td>'+this.designation+'</td>' +
           '<td>'+this.xuehao+'</td>' +
           '<td>'+this.jhclaass+'</td>' +
           '<td>'+this.iphone+'</td>' +
           '<td>'+this.jltime+'</td>' +
           '<td>'+this.yd+'</td>' +
           '<td><a href="JavaScript:;" class="bg bgActive del">删除</a><a href="bzrgl_tsxyjl_xg.html" class="bg change">修改</a>' +
           '<a href="bzrgl_tsxyjl_ck.html" class="bg change">查看</a><a onclick="" class="bg change">导出</a></td>'+
           '</tr>').appendTo('#list_tsxyjl');
   });
   //删除
   $('.del').click(function(){
       if (confirm('确定要删除吗')){
           $(this).parents('tr').remove();
       }
   });
   //班级工作计划-新增弹窗
   function xzteacher() {
       layui.use('layer', function(){
           var layer = layui.layer;
           layer.open({
               //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
               type: 1,
               skin: '',
               title: "选择班级",
               area: ['50%', '50%'],
               content: $("#popSearchRoleTest-xzteacher").html()
           })
       });
   };
   //特殊学员记录 特殊学员记录 特殊学员记录 特殊学员记录 特殊学员记录end

   //获奖记录 获奖记录 获奖记录 获奖记录 获奖记录
   //表格增删改查
   var tableList = [
       { id: '1', jlname: '名称一',  jhclaass:'老师一',  jltime:'2018-11-11',  },
       { id: '2', jlname: '名称一',  jhclaass:'老师一',  jltime:'2018-11-11',  },
       { id: '3', jlname: '名称一',  jhclaass:'老师一',  jltime:'2018-11-11',  },
       { id: '4', jlname: '名称一',  jhclaass:'老师一',  jltime:'2018-11-11',  },
       { id: '5', jlname: '名称一',  jhclaass:'老师一',  jltime:'2018-11-11',  },
       { id: '6', jlname: '名称一',  jhclaass:'老师一',  jltime:'2018-11-11',  },
       { id: '7', jlname: '名称一',  jhclaass:'老师一',  jltime:'2018-11-11',  },
   ];
   $.each(tableList, function(){
       $('<tr id="'+this.id+'">' +
           '<td>'+this.id+'</td>' +
           '<td>'+this.jlname+'</td>' +
           '<td>'+this.jhclaass+'</td>' +
           '<td>'+this.jltime+'</td>' +
           '<td><a href="JavaScript:;" class="bg bgActive del">删除</a><a href="bzrgl_hjjl_xg.html" class="bg change">修改</a>' +
           '<a href="bzrgl_hjjl_ck.html" class="bg change">查看</a><a onclick="" class="bg change">导出</a></td>'+
           '</tr>').appendTo('#list_hjjl');
   });
   //删除
   $('.del').click(function(){
       if (confirm('确定要删除吗')){
           $(this).parents('tr').remove();
       }
   });
   //获奖记录 获奖记录 获奖记录 获奖记录 获奖记录

   //学校活动记录 学校活动记录 学校活动记录 学校活动记录
   //表格增删改查
   var tableList = [
       { id: '1', jlname: '名称一',    jltime:'2018-11-11',  },
       { id: '2', jlname: '名称一',    jltime:'2018-11-11',  },
       { id: '3', jlname: '名称一',    jltime:'2018-11-11',  },
       { id: '4', jlname: '名称一',    jltime:'2018-11-11',  },
       { id: '5', jlname: '名称一',    jltime:'2018-11-11',  },
       { id: '6', jlname: '名称一',    jltime:'2018-11-11',  },
       { id: '7', jlname: '名称一',    jltime:'2018-11-11',  },
   ];
   $.each(tableList, function(){
       $('<tr id="'+this.id+'">' +
           '<td>'+this.id+'</td>' +
           '<td>'+this.jlname+'</td>' +
           '<td>'+this.jltime+'</td>' +
           '<td><a href="JavaScript:;" class="bg bgActive del">删除</a><a href="bzrgl_xxhdjl_xg.html" class="bg change">修改</a>' +
           '<a href="bzrgl_xxhdjl_ck.html" class="bg change">查看</a><a onclick="" class="bg change">导出</a></td>'+
           '</tr>').appendTo('#list_xxhdjl');
   });
   //删除
   $('.del').click(function(){
       if (confirm('确定要删除吗')){
           $(this).parents('tr').remove();
       }
   });
   //学校活动记录 学校活动记录 学校活动记录 学校活动记录 end

   //班级问题与建议 班级问题与建议 班级问题与建议 班级问题与建议
   //表格增删改查
   var tableList = [
       { id: '1', jlname: '名称一', bjclass:'班级一',  jltime:'2018-11-11',  },
       { id: '2', jlname: '名称一', bjclass:'班级一',   jltime:'2018-11-11',  },
       { id: '3', jlname: '名称一', bjclass:'班级一',   jltime:'2018-11-11',  },
       { id: '4', jlname: '名称一', bjclass:'班级一',   jltime:'2018-11-11',  },
       { id: '5', jlname: '名称一', bjclass:'班级一',   jltime:'2018-11-11',  },
       { id: '6', jlname: '名称一', bjclass:'班级一',   jltime:'2018-11-11',  },
       { id: '7', jlname: '名称一', bjclass:'班级一',   jltime:'2018-11-11',  },
   ];
   $.each(tableList, function(){
       $('<tr id="'+this.id+'">' +
           '<td>'+this.id+'</td>' +
           '<td>'+this.jlname+'</td>' +
           '<td>'+this.bjclass+'</td>' +
           '<td>'+this.jltime+'</td>' +
           '<td><a href="JavaScript:;" class="bg bgActive del">删除</a><a href="bzrgl_bjwtyjy_xg.html" class="bg change">修改</a>' +
           '<a href="bzrgl_bjwtyjy_ck.html" class="bg change">查看</a><a onclick="" class="bg change">导出</a></td>'+
           '</tr>').appendTo('#list_bjwtyjy');
   });
   //删除
   $('.del').click(function(){
       if (confirm('确定要删除吗')){
           $(this).parents('tr').remove();
       }
   });
   //问题与建议-新增弹窗
   function wtjy_xzclass() {
       layui.use('layer', function(){
           var layer = layui.layer;
           layer.open({
               //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
               type: 1,
               skin: '',
               title: "选择班级",
               area: ['50%', '50%'],
               content: $("#popSearchRoleTest-wtyjy").html()
           })
       });
   };
   //班级问题与建议 班级问题与建议 班级问题与建议 班级问题与建议 end

   //班会记录 班会记录 班会记录 班会记录 班会记录 班会记录
   //表格增删改查
   var tableList = [
       { id: '1', jlname: '主题一', bjclass:'舞蹈班', zc:'第一周',  jltime:'2018-11-11',  },
       { id: '2', jlname: '主题一', bjclass:'班级一', zc:'第一周',  jltime:'2018-11-11',  },
       { id: '3', jlname: '主题一', bjclass:'班级一', zc:'第一周',  jltime:'2018-11-11',  },
       { id: '4', jlname: '主题一', bjclass:'班级一', zc:'第一周',  jltime:'2018-11-11',  },
       { id: '5', jlname: '主题一', bjclass:'班级一', zc:'第一周',  jltime:'2018-11-11',  },
       { id: '6', jlname: '主题一', bjclass:'班级一', zc:'第一周',  jltime:'2018-11-11',  },
       { id: '7', jlname: '主题一', bjclass:'班级一', zc:'第一周',  jltime:'2018-11-11',  },
   ];
   $.each(tableList, function(){
       $('<tr id="'+this.id+'">' +
           '<td>'+this.id+'</td>' +
           '<td>'+this.jlname+'</td>' +
           '<td>'+this.bjclass+'</td>' +
           '<td>'+this.zc+'</td>' +
           '<td>'+this.jltime+'</td>' +
           '<td><a href="JavaScript:;" class="bg bgActive del">删除</a><a href="bzrgl_bhjl_xg.html" class="bg change">修改</a>' +
           '<a href="bzrgl_bhjl_ck.html" class="bg change">查看</a><a onclick="" class="bg change">导出</a></td>'+
           '</tr>').appendTo('#list_bhjl');
   });
   //删除
   $('.del').click(function(){
       if (confirm('确定要删除吗')){
           $(this).parents('tr').remove();
       }
   });
   //问题与建议-新增弹窗
   function xzclass() {
       layui.use('layer', function(){
           var layer = layui.layer;
           layer.open({
               //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
               type: 1,
               skin: '',
               title: "选择班级",
               area: ['50%', '50%'],
               content: $("#popSearchRoleTest-bhjl").html()
           })
       });
   };
   //班会记录 班会记录 班会记录 班会记录 班会记录 end

   //班主任工作手记 班主任工作手记 班主任工作手记 班主任工作手记 班主任工作手记
   //表格增删改查
   var tableList = [
       { id: '1', jlname: '主题一',    jltime:'2018-11-11',  },
       { id: '2', jlname: '主题一',    jltime:'2018-11-11',  },
       { id: '3', jlname: '主题一',    jltime:'2018-11-11',  },
       { id: '4', jlname: '主题一',    jltime:'2018-11-11',  },
       { id: '5', jlname: '主题一',    jltime:'2018-11-11',  },
       { id: '6', jlname: '主题一',    jltime:'2018-11-11',  },
       { id: '7', jlname: '主题一',    jltime:'2018-11-11',  },
   ];
   $.each(tableList, function(){
       $('<tr id="'+this.id+'">' +
           '<td>'+this.id+'</td>' +
           '<td>'+this.jlname+'</td>' +
           '<td>'+this.jltime+'</td>' +
           '<td><a href="JavaScript:;" class="bg bgActive del">删除</a><a href="bzrgl_bzrgzsj_xg.html" class="bg change">修改</a>' +
           '<a href="bzrgl_bzrgzsj_ck.html" class="bg change">查看</a><a onclick="" class="bg change">导出</a></td>'+
           '</tr>').appendTo('#list_bzrgzsj');
   });
   //删除
   $('.del').click(function(){
       if (confirm('确定要删除吗')){
           $(this).parents('tr').remove();
       }
   });
   //问题与建议-新增弹窗
   function xzclass() {
       layui.use('layer', function(){
           var layer = layui.layer;
           layer.open({
               //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
               type: 1,
               skin: '',
               title: "选择班级",
               area: ['50%', '50%'],
               content: $("#popSearchRoleTest-bhjl").html()
           })
       });
   };
   //班主任工作手记 班主任工作手记 班主任工作手记 班主任工作手记  end