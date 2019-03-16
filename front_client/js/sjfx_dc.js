    /*导出*/
    var picBase64Info = myChart.getDataURL();
    //创建form提交图片数据
    var postDownLoadFile = function (options){
        var config = $.extend(true, { method: 'post' }, options);
        var $iframe = $('<iframe id="down-file-iframe" />');
        var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
        $form.attr('action', config.url);
        //图片
        $form.append('<input type="hidden" name="base64Info" value="' + config.data + '" />');

        $iframe.append($form);
        $(document.body).append($iframe);
        $form[0].submit();
        $iframe.remove();
    }
     
    //触发下载功能
    $('#dcBtn').click(function(){
    	picBase64Info = myChart.getDataURL();
    	  var param={};
            postDownLoadFile({
                url:"api/statistics/student/base/wordoutput",
                data:picBase64Info,
                method:'post'               
            });
    })
	/*导出 end*/
	
//	$('#dcBtn').click(function(){
//		var picBase64Info = myChart.getDataURL();
//	//	const imgUrl = 'data:image/png;base64,...'
//		// 如果浏览器支持msSaveOrOpenBlob方法（也就是使用IE浏览器的时候），那么调用该方法去下载图片
//		if (window.navigator.msSaveOrOpenBlob) {
//		 var bstr = atob(picBase64Info.split(',')[1])
//		 var n = bstr.length
//		 var u8arr = new Uint8Array(n)
//		 while (n--) {
//		  u8arr[n] = bstr.charCodeAt(n)
//		 }
//		 var blob = new Blob([u8arr])
//		 window.navigator.msSaveOrOpenBlob(blob, 'chart-download' + '.' + 'png')
//		} else {
//		 // 这里就按照chrome等新版浏览器来处理
//		 const a = document.createElement('a')
//		 a.href = picBase64Info
//		 a.setAttribute('download', 'chart-download')
//		 a.click()
//		}
//})