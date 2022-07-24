var page = 1; //当前页
 
Load(); //加载数据
LoadXinXi(); //加载分页信息
 
//查询
$("#chaxun").click(function() {
    page = 1;
    Load(); //加载数据
    LoadXinXi(); //加载分页信息
})
 
function Load() {
    var key = $("#key").val(); //查询条件
 
    $.ajax({
        url: "http://127.0.0.1:8080/test_index",
        data: {
            page: page,
            key: key
        },
        type: "POST",
        dataType: "JSON",
        success: function(data) {
            var str = "";
            for(var k in data) {
                //str += "&lt;tr>&lt;td>" + data[k].picture_number + "&lt;/td>&lt;td>" + data[k].picture_type + "&lt;/td>&lt;td>" + data[k].picture_ps + "&lt;/td>&lt;/tr>";


                str += "<tr><tr><td><input name='' type='checkbox' value=''  width='30%'/></td><td class='imgtd'><a href='imgtable_show.html'><img src="+data[k].picture_dir+ "></a></td><td><a href='imgtable_show.html'>"+data[k].picture_ps+"</a><p>录入时间："+data[k].picture_entrytime+"</p></td><td>"+data[k].picture_number+"<p>ID: 82122</p></td><td>"+data[k].picture_workspace+"</td><td>"+data[k].picture_datastaff+"</td><td>"+data[k].picture_type+"</td><td>"+data[k].picture_AIresult+"</td><td><a href='#' class='tablelink'>查看</a>     <a href='#' class='tablelink'> 删除</a></td></tr>";

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
        async: false,
        url: "http://127.0.0.1:8080/zys",
        data: {
            key: key
        },
        type: "POST",
        dataType: "TEXT",
        success: function(d) {
            maxys = d;
        }
    });
 
    str += "<td>总共：" + maxys + "页</td>  ";
    str += "<td id='prev'>上一页</td>";
 
    for(var i = page - 2; i < page + 3; i++) {
        if(i >= minys && i <= maxys) {
            if(i == page) {
                str += "<td class='dangqian' bs='" + i + "'>" + i + "</td>  ";
            } else {
                str += "<td class='list' bs='" + i + "'>" + i + "</td>  ";
            }
 
        }
    }
 
    str += "<td id='next'>下一页</td>";
 
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