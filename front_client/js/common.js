
    //from表单select 通用
    layui.use('form', function(){
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });

    //JavaScript代码区域 左侧列表部分
    layui.use('element', function(){
        var element = layui.element;
    });
    var listData; //定义全局变量来接收后台返回的data
    //根据账号判断权限
    $.ajax({
        url: "/api/power/query-menu",
        type: 'GET',
        dataType: 'json',
        //同步
        async: false,
        //解决跨域问题
        //xhrFields: {withCredentials: true},
        success: function (data) {
            var userName = data.data.user.tname;
            $('#user').append(userName);

            //将后台返回的data.data赋值给全局变量listData
            listData = data.data.menu;
            //调用一级分类
            getCategory(listData);

        },
        error: function(error) {
            self.location='/loginStudy.html';
            // layui.use('layer', function () {
            //     var layer = layui.layer;
            //     layer.msg(error.responseText);
            // });
        }
    });

    //获取一级分类
    function getCategory(data) {
        var html = "";
        $.each(listData, function (i, result) {
            //转换日期格式，引入想对应的js
            html += '<li class="layui-nav-item nav_li" onclick="getChildCategory(' + result.id + ')">'
            html += '<a class="nav_li_a" href="javascript:;"><i class="' + result.iconcls + '"></i>&nbsp;&nbsp;' + result.powername + '</a>'
            html += '<dl class="layui-nav-child nav_erli" id=childCategory' + result.id + '>'
            html += '</dl>'
            html += "</li>"
        });
        $("#navTree").append(html);
    };

    //获取二级分类
    function getChildCategory(id) {
        //遍历一级分类的ID
        for (var num = 0; num < listData.length; num++) {
            //获取一级分类的ID
            var getChildId = listData[num].id;
            //若点击当前li的id等于循环出来的getChildId,就将拿到二级分类回显到当下的li
            if (id == getChildId) {
                var htmlChild = "";
                var childCategoryArr = listData[num].childPowers;
                for (var childNum = 0; childNum < childCategoryArr.length; childNum++) {
                        htmlChild += '<dd class="list leftdaohang layui-nav-item" data-url="' + childCategoryArr[childNum].address + '" mytitle="' + childCategoryArr[childNum].powername + '">'
                        htmlChild += '<a href="javascript:;" id="' + childCategoryArr[childNum].id + '">' + childCategoryArr[childNum].powername + '</a>'
                        htmlChild += '</dd>'

                }
                $('#childCategory' + id).html(htmlChild);
            } else {
                console.log('二级分类显示失败');
            }
        };

        layui.use('element', function () {
            var $ = layui.jquery,
                element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

            //触发事件
            var active = {
                //在这里给active绑定几项事件，后面可通过active调用这些事件
                tabAdd: function () {
                    //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                    //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                    var htmlurl = $(this).attr('data-url');
                    var mytitle = $(this).attr('mytitle');
                    var arrayObj = new Array();　    //创建一个数组  
                    $(".layui-tab-title").find('.list').each(function () {
                        var y = $(this).attr("lay-id");
                        arrayObj.push(y);
                    });
                    //alert("遍历取到的数组："+arrayObj);
                    var have = $.inArray(mytitle, arrayObj);  //返回 3,
                    if (have >= 0) {
                        element.tabChange('demo', mytitle); //切换到当前点击的页面
                    } else {
                        //没有相同tab
                        element.tabAdd('demo', {
                            title: mytitle, //用于演示
                            content: '<iframe style="width: 100%;height:99.5%;" scrolling="no" src=' + htmlurl + ' ></iframe>',
                            id: mytitle //实际使用一般是规定好的id，这里以时间戳模拟下
                        })
                        element.tabChange('demo', mytitle); //切换到当前点击的页面
                        $(".layui-tab-item.layui-show").find("iframe").attr("src", htmlurl);
                    }
                }
            }

            $(".leftdaohang").click(function (event) {
                var type = "tabAdd";
                var othis = $(this);
                active[type] ? active[type].call(this, othis) : '';

                   event.stopPropagation();
                  $(this).addClass('layui-this').siblings().removeClass('layui-this');
                  $(this).parent().parent().siblings().children().children().removeClass('layui-this');

                  $(".layui-tab-content").removeClass("bgPicture");
            });
        });
    };


    // 登出
    function logout() {
        layui.use('layer', function() {
            var layer = layui.layer;
            layer.open({
                title: '注销提醒',
                type: 2,
                area: ['400px', '300px'],
                fixed: false,
                maxmin: true, 
                content: '/logout.html',
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    $.ajax({
                        url: "/api/index/logout",
                        type: 'POST',
                        dataType: 'json',
                        //同步
                        async: false,
                        //解决跨域问题
                        success: function(data) {
                            layui.use('form', function () {
                                var form = layui.form;
                                layer.msg('用户已注销', {area: ["170px", "50px"]});
                            })
                            setTimeout(function () {
                                window.location.href="../loginStudy.html"
                            },1500)
                        },
                        error: function(error) {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg(error.responseText);
                            });
                        }
                    });

                    //最后关闭弹出层
                    layer.close(index);

                }
                , btn2: function (index, layero) {

                }
            });
        })



        
    }

    //修改密码
    function xgPassword() {
        layui.use('layer', function() {
            var layer = layui.layer;
            layer.open({
                title: '修改密码',
                type: 2,
                area: ['500px', '400px'],
                fixed: false, //不固定
                maxmin: true, //开启最大化最小化按钮
                content: '/loginPassword.html',
                btn: ['确定', '取消'],
                yes: function (index, layero) {//该回调携带两个参数，分别为当前层索引、当前层DOM对象
                    //当点击‘确定’按钮的时候，获取弹出层返回的值
                    var res = window["layui-layer-iframe" + index].callbackdata();
                    //打印返回的值，看是否有我们想返回的值。
                    var xPassword = res.password;
                    $.ajax({
                        type: 'POST',
                        url: "/api/teacher/update-psw",
                        async: false,
                        data: {
                            newPsw: xPassword,
                        },
                        dataType: "json",
                        //xhrFields: {withCredentials: true},
                        success: function (data) {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg('新密码设置成功！', {area: ["180px", "50px"]});
                            });
                            setTimeout(function () {
                                window.location.href="../loginStudy.html"
                            },1500)
                        },
                        error: function(error) {
                            layui.use('layer', function () {
                                var layer = layui.layer;
                                layer.msg(error.responseText);
                            });
                        }
                    })

                    //最后关闭弹出层
                    layer.close(index);

                }
                , btn2: function (index, layero) {

                }
            });
        })
    }
