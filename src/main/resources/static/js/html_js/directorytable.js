/*

 *Author 靳亚东
 *
 *Date 20181005
 *
 *Description  此文件作为member_list.html前端需要与后端交互数据的相关代码
 
 *Note 后续根据需要改进 1.jpa改成mybatis 

 */


/*       控制显示的工作目录列表div等样式             */


$(function(){	
	//导航切换
	$(".disklist li").click(function(){
		$(".disklist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	

/*       ******************************             */



/*           前端后端通过ajax交互数据，显示工作目录列表                               */

var item;
 $.ajax({
       type: 'get',
       url: 'http://127.0.0.1:8080/Directories',
       dataType: 'json',
	   
       success:function(data){
		   
        $.each(data,function(i,result){
		//item="<li><span><img src="+result['picture_dir']+" /></span><div class='lright'><h2>"+result['picture_workspace']+ "</h2><p>编号："+result['picture_number']+ "<br />类型："+result['picture_type']+ "<br />损失情况：轻微</p><a class='enter'>查看详细</a></div></li>";
		item="<li><div class='dleft'></div><div class='dright'><h2>"+result['directory_name']+"(30)</h2><div class='dinfo'><span style='width:50px;'></span></div><p>1120 MB 空间,共 5100 个文件</p></div></li>"; 
		$('.disklist').append(item);
		});
		
		},error:function(data){
                alert("系统错误");
				alert(XMLHttpRequest);
				alert(textStatus);
				alert(errorThrown);
				}
});
/*          *********************************************************               */