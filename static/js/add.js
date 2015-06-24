/**
 * Created by myski on 2015/6/24.
 */

function get_csrf() {
    var token = $("#csrf_code");
    return $(token.html()).val();
}

function post_problem(){
    var problemCode = $("#problem_code").val();
    var problemDec = $("#problem_dec").val();
    var problemAns = $("#problem_ans").val();
    var csrf_tokens = get_csrf();

    $.ajax({
        type:"POST",
        url:"http://127.0.0.1:8000/problemset/",
        data:{
            problemSetCode:problemCode,
            problemSetDesc:problemDec,
            problemsAns:problemAns,
            csrfmiddlewaretoken:csrf_tokens
        },
        dataType:"json",
        success:function(data){
                //$.each(data, function(i, item) {
                //    document.write(i, item);
                //});
        alert("上传题目成功");
        window.location.href="/add";
}
});
}
