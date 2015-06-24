/**
 * Created by myski on 2015/6/24.
 */

 function get_csrf() {
    var token = $("#csrf-token");
    return $(token.html()).val();
}

get_csrf();

function form_submit() {
    var the_username = $("#the_username").val();
    var the_password = $("#the_password").val();
    var csrf_value = get_csrf();
    console.log(csrf_value);

    $.ajax({
        type:"POST",
        url:"http://127.0.0.1:8000/api-auth/login/?next=/",
        data:{
            username:the_username,
            password:the_password,
            csrfmiddlewaretoken:csrf_value
        },
        dataType:"json",
        success:function(data){
                //$.each(data, function(i, item) {
                //    document.write(i, item);
                //});
        alert("AAAAAAAAAAAAAAA");
        window.location.href="/home";
}
});


}