/*

 *Author 靳亚东
 *
 *Date 20180927
 *
 *Description  此文件作为imgtable_report.html前端需要与后端交互数据的相关代码
 
 *Note 后续根据需要改进 1.jpa改成mybatis 

 */

/*       控制显示的已有诊断报告影像图列表div等样式             */

$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})
/*       ******************************             */


/*           前端后端通过ajax交互数据，显示已有报告影像图列表                       */

var item;
 $.ajax({
       type: 'post',
       url: 'http://127.0.0.1:8080/DiagnosticResults',
       dataType: 'json',
	   data: {"picture_AIresult":"是"},
       success:function(data){
		   
        $.each(data,function(i,result){

		item="<li><span><img src="+result['picture_dir']+" /></span><div class='lright'><h2>"+result['picture_workspace']+ "</h2><p>编号："+result['picture_number']+ "<br />类型："+result['picture_type']+ "<br />损失情况：轻微</p><a class='enter'>查看详细</a></div></li>";
		$('.classlist').append(item);
		});
		
		},error:function(data){
                alert("系统错误");
				alert(XMLHttpRequest);
				alert(textStatus);
				alert(errorThrown);
				}
});
/*          *********************************************************               */