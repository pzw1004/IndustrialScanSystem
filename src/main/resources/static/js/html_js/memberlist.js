/*

 *Author 靳亚东
 *
 *Date 20180929
 *
 *Description  此文件作为member_list.html前端需要与后端交互数据的相关代码
 
 *Note 后续根据需要改进 1.jpa改成mybatis 

 */

/*       控制显示的管理人员列表div等样式             */
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
/*       ******************************             */



/*           前端后端通过ajax交互数据，显示管理人员列表                               */

// var item;
//  $.ajax({
//        type: 'get',
//        url: 'http://127.0.0.1:8080/Members',
//        dataType: 'json',
//
//        success:function(data){
//
//         $.each(data,function(i,result){
// 		//item="<li><span><img src="+result['picture_dir']+" /></span><div class='lright'><h2>"+result['picture_workspace']+ "</h2><p>编号："+result['picture_number']+ "<br />类型："+result['picture_type']+ "<br />损失情况：轻微</p><a class='enter'>查看详细</a></div></li>";
// 		item="<tr><td><input name='' type='checkbox' value='' /></td><td>"+result['member_number']+"</td><td>"+result['member_name']+"</td><td>"+result['member_sex']+"</td><td>"+result['member_phone']+"</td><td>"+result['member_jointime']+"</td><td>"+result['member_address']+"</td><td><a href='#' class='tablelink'>查看</a>     <a href='#' class='tablelink'> 删除</a></td></tr>";
// 		$('.tablelist').append(item);
// 		});
//
// 		},error:function(data){
//                 alert("系统错误");
// 				alert(XMLHttpRequest);
// 				alert(textStatus);
// 				alert(errorThrown);
// 				}
// });
/*          *********************************************************               */