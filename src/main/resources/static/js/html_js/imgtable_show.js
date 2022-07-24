// var loc = location.href;
// var n1 = loc.length;//地址的总长度
// var n2 = loc.indexOf("=");//取得=号的位置
// var id = decodeURI(loc.substr(n2+1, n1-n2));//从=号后面的内容
// //alert(id);
//
// var item;
//
// $.ajax({
//     type: 'post',
//     url: 'http://127.0.0.1:8080/Imgtable_show',
//     dataType: 'json',
//     data: {
//         id: id
//     },
//     success:function(data){
//
//         //alert(JSON.stringify(data));
//         //alert(data.picture_ps);
//
//
//         //item="<tr><td>"+data[3]+"</td>"+"<td>*****"+data[4]+"</td><tr>";
//
//         $('.imgtable').append(item);
//
//
//     },error:function(data){
//         alert("系统错误");
//         alert(XMLHttpRequest);
//         alert(textStatus);
//         alert(errorThrown);
//     }
// });