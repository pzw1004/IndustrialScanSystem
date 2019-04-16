/*

 *Author 靳亚东
 *
 *Date 20180928
 *
 *Description  此文件作为index.html前端需要与后端交互数据的相关代码
 
 *Note 后续根据需要改进 1.jpa改成mybatis 

 */

/*       控制显示的影像图列表div等样式             */

$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	

$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});

/*          ***************************                */
 
 
/*           前端后端通过ajax交互数据，显示影像图列表                            

var item;
 $.ajax({
       type: 'post',
       url: 'http://127.0.0.1:8080/index',
       dataType: 'json',
       success:function(data){
		   
        $.each(data,function(i,result){
		
		item="<tr><tr><td><input name='' type='checkbox' value=''  width='30%'/></td><td class='imgtd'><a href='imgtable_show.html'><img src="+result['picture_dir']+ "></a></td><td><a href='imgtable_show.html'>"+result['picture_ps']+"</a><p>录入时间："+result['picture_entrytime']+"</p></td><td>"+result['picture_number']+"<p>ID: 82122</p></td><td>"+result['picture_workspace']+"</td><td>"+result['picture_datastaff']+"</td><td>"+result['picture_type']+"</td><td>"+result['picture_AIresult']+"</td><td><a href='#' class='tablelink'>查看</a>     <a href='#' class='tablelink'> 删除</a></td></tr>";
		
		$('.imgtable').append(item);
		});
		
		},error:function(data){
                alert("系统错误");
				alert(XMLHttpRequest);
				alert(textStatus);
				alert(errorThrown);
				}
});
/*          *********************************************************               */