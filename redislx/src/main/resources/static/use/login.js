layui.use(['jquery','form','layer'],function () {

    let $ = layui.jquery;
    let form = layui.form;
    let layer = layui.layer;
    //
    // form.on('submit(*)', function(data){
    //     console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
    //     console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
    //     console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
    //     return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    // })
    $("#login").on('click',function () {
        debugger
        let username = $("input[name='username']").val();
        let password = $("input[name='password']").val();
        $.ajax({
            url:'/login',
            type:'post',
            data:{
                userName:username,
                password:password
            },
            success:function (res) {
                // let amsg = [${msg}]
                // alert(amsg)
                window.location.href = res
            },
            error:function (res) {
                // console.log(res);
                // if(res.status == 200){
                //     alert("ok")
                //     window.location.href = "/loginIn"
                // }else{
                //     alert("error");
                //     window.location.href = "/login"
                // }
            }
        })
    })
});