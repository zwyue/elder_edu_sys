// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
//计算两个日期间间隔月数
var getBetweenMonth = function(date1,date2){
	var year1 =  date1.getFullYear();
	var year2 =  date2.getFullYear(); 
	var month1 = date1.getMonth()+1;
	var month2 = date2.getMonth()+1;
	return (year2-year1)*12+(month2-month1);
}

var getWeekDesc = function(date){
	var now = new Date();
	if(now.Format('yyyy-MM-dd')==date.Format('yyyy-MM-dd')){
		return '\u4ECA\u5929';
	}
	now.setDate(now.getDate()-1);
	if(now.Format('yyyy-MM-dd')==date.Format('yyyy-MM-dd')){
		return '\u6628\u5929';
	}
	switch (date.getDay()) {
		case 1: return '\u5468\u4E00';break;
		case 2: return '\u5468\u4E8C';break;
		case 3: return '\u5468\u4E09';break;
		case 4: return '\u5468\u56DB';break;
		case 5: return '\u5468\u4E94';break;
		case 6: return '\u5468\u516D';break;
		case 0: return '\u5468\u65E5';break;
	}
}
var getMonthDesc = function(date){
	var now = new Date();
	if(now.Format('yyyy-MM')==date.Format('yyyy-MM')){
		return '\u672C\u6708';
	}
	switch (date.getMonth()+1) {
		case 1: return '\u4E00\u6708';break;
		case 2: return '\u4E8C\u6708';break;
		case 3: return '\u4E09\u6708';break;
		case 4: return '\u56DB\u6708';break;
		case 5: return '\u4E94\u6708';break;
		case 6: return '\u516D\u6708';break;
		case 7: return '\u4E03\u6708';break;
		case 8: return '\u516B\u6708';break;
		case 9: return '\u4E5D\u6708';break;
		case 10: return '\u5341\u6708';break;
		case 11: return '\u5341\u4E00\u6708';break;
		case 12: return '\u5341\u4E8C\u6708';break;
	}
}
var getDateDesc = function(date){
	var now = new Date();
	if(now.Format('yyyy-MM-dd')==date.Format('yyyy-MM-dd')){
		return date.Format("hh:mm");
	}
	now.setDate(now.getDate()-1);
	if(now.Format('yyyy-MM-dd')==date.Format('yyyy-MM-dd')){
		return date.Format("hh:mm");
	}
	return date.Format("MM-dd");
}

