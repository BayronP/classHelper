/**
 * Created by myski on 2015/6/16.
 */

//$.ajax({
//    type:"POST",
//    url:problemset,
//    dataType: "json",
//    success:function(resultData) {
//    $.each(resultData, function(i, item) {
//        alert(item);
//    })
var url_data;


function get_url() {
    $.ajax({
        type:"GET",
        url:"http://127.0.0.1:8000/.json",
        dataType:"json",
        success:function(data){
            //$.each(data, function(i, item) {
            //    document.write(i, item);
            //});
            url_data = data;
            console.log(url_data);
        }
    });
}

get_url();


$(document).ready(function(){
    $('#button').click(function(){
        $.ajax({
            type:"GET",
            url:"http://127.0.0.1:8000/.json",
            dataType:"json",
            success:function(data){
                //$.each(data, function(i, item) {
                //    document.write(i, item);
                //});
                url_data = data;
                document.write(url_data.problemset);
            }
        });
    });
});
