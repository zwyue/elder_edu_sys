/**
 * jeDate 演示
 */
    var enLang = {                            
        name  : "en",
        month : ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
        weeks : [ "SUN","MON","TUR","WED","THU","FRI","SAT" ],
        times : ["Hour","Minute","Second"],
        timetxt: ["Time","Start Time","End Time"],
        backtxt:"Back",
        clear : "Clear",
        today : "Now",
        yes   : "Confirm",
        close : "Close"
    }

// jeDate("#testblue1",{
//     theme:{bgcolor:"#00A1CB",pnColor:"#00CCFF"},
//     multiPane:false,
//     range:" ~ ",
//     format: "YYYY-MM-DD hh:mm:ss"
// });
jeDate("#testblue1",{
    theme:{bgcolor:"#00A1CB",pnColor:"#00CCFF"},
    festival:true,
    minDate:"1900-01-01",              //最小日期
    maxDate:"2099-12-31",              //最大日期
    method:{
        choose:function (params) {
        }
    },
    format: "YYYY-MM-DD hh:mm:ss"
});
jeDate("#testblue2",{
    theme:{bgcolor:"#00A1CB",pnColor:"#00CCFF"},
    festival:true,
    minDate:"1900-01-01",              //最小日期
    maxDate:"2099-12-31",              //最大日期
    method:{
        choose:function (params) {
        }
    },
    format: "YYYY-MM-DD hh:mm:ss"
});
jeDate("#testblue3",{
    theme:{bgcolor:"#00A1CB",pnColor:"#00CCFF"},
    festival:true,
    minDate:"1900-01-01",              //最小日期
    maxDate:"2099-12-31",              //最大日期
    method:{
        choose:function (params) {
        }
    },
    format: "YYYY-MM-DD hh:mm:ss"
});
jeDate("#testblue4",{
    theme:{bgcolor:"#00A1CB",pnColor:"#00CCFF"},
    festival:true,
    minDate:"1900-01-01",              //最小日期
    maxDate:"2099-12-31",              //最大日期
    method:{
        choose:function (params) {
        }
    },
    format: "YYYY-MM-DD hh:mm:ss"
});


    
    