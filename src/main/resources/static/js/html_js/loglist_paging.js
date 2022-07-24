var page = 1; //当前页
//var key = "";
function LoadFenye(){

Load(); //加载数据
LoadXinXi(); //加载分页信息

}

 
function Load() {
    var key = $("#key").val(); //查询条件
    //alert(key);
    $.ajax({
        url: "/loglist",
        data: {
            page: page,
            key: key
        },
        type: "POST",
        //开启ajax异步请求
        async: true,
        dataType: "JSON",
        success: function(data) {

            var i = 0;
            var quanxian = "";
            var str = "";
            for(var k in data) {
                i = i + 1;
                if (data[k].log_role==1)quanxian="管理员";
                if (data[k].log_role==2)quanxian="一级审核员";
                if (data[k].log_role==3)quanxian="二级审核员";
                if (data[k].log_role==4)quanxian="三级审核员";
                if (data[k].log_role==5)quanxian="扫描员";
                //str += "&lt;tr>&lt;td>" + data[k].picture_number + "&lt;/td>&lt;td>" + data[k].picture_type + "&lt;/td>&lt;td>" + data[k].picture_ps + "&lt;/td>&lt;/tr>";
                str += "<tr><td style='text-align:center;'>"+i+"</td><td style='text-align:center;'>"+timestampToTime(data[k].log_time)+"</td><td style='text-align:center;'>"+data[k].log_operator+"</td><td style='text-align:center;'>"+quanxian+"</td><td>"+data[k].log_operation+"</td></tr>";

            }
            $("#nr").html(str);
        }
    });
}


function LoadXinXi() {
    var str = "";
    var minys = 1;
    var maxys = 1;
    var key = $("#key").val();

    //查总页数
    $.ajax({
        url: "/loglistZys",
        data: {
            key: key
        },
        type: "POST",
        //开启ajax同步请求
        async: false,
        dataType: "TEXT",
        success: function(d) {
            maxys = d;

        }
    });
 
    str += "<td>总共：" + maxys + "条记录&nbsp; &nbsp;&nbsp; &nbsp;</td>";
    str += "<td id='prev' style='cursor:pointer;'>上一页&nbsp; &nbsp;&nbsp; &nbsp;</td>";
 
    for(var i = page - 2; i < page + 3; i++) {
        if(i >= minys && i <= maxys) {
            if(i == page) {
                str += "<td style='cursor:pointer;' class='dangqian' bs='" + i + "'>" + i + "&nbsp; &nbsp;&nbsp; &nbsp;</td>  ";
            } else {
                str += "<td style='cursor:pointer;' class='list' bs='" + i + "'>" + i + "&nbsp; &nbsp;&nbsp; &nbsp;</td>  ";
            }
 
        }
    }
 
    str += "<td id='next' style='cursor:pointer;'>下一页&nbsp; &nbsp;&nbsp; &nbsp;</td>";
    //alert("fenye");
    $("#xinxi").html(str);
 
    //给上一页添加点击事件
    $("#prev").click(function() {
            page = page - 1;
            if(page < 1) {
                page = 1;
            }
            Load(); //加载数据
            LoadXinXi(); //加载分页信息
        })
        //给下一页加点击事件
    $("#next").click(function() {
            page = page + 1;
            if(page > maxys) {
                page = maxys;
            }
            Load(); //加载数据
            LoadXinXi(); //加载分页信息
        })
        //给中间的列表加事件
    $(".list").click(function() {
        page = parseInt($(this).attr("bs"));
        Load(); //加载数据
        LoadXinXi(); //加载分页信息
    })
}


function timestampToTime(timestamp) { //解析java后端传递的13位时间戳
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '年';
    M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date
            .getMonth() + 1)
        + '月';

    D = (date.getDate() + 1 < 10 ? '0' + (date.getDate() + 1) : date
            .getDate() )
        + '号&nbsp; ';
    h = (date.getHours() + 1 < 10 ? '0' + (date.getHours() + 1) : date
            .getHours() )
        + ':';
    m = (date.getMinutes() + 1 < 10 ? '0' + (date.getMinutes() + 1) : date
            .getMinutes() )
        + ':';
    s = (date.getSeconds() + 1 < 10 ? '0' + (date.getSeconds() + 1) : date
            .getSeconds() + 1)
        ;
    return Y + M + D + h + m + s;
}

// Load(); //加载数据
// LoadXinXi(); //加载分页信息

//查询
$("#chaxun").click(function() {
    //page = 1;
    Load(); //加载数据
    LoadXinXi(); //加载分页信息
})

