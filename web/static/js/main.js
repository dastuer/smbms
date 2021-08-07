function nameValidate(){
    $.post(
        {
            url:"${pageContext.request.contextPath}/getState",
            data:{"username":$("#usr").val()},
            success:function (data){
                data=JSON.parse(data);
                let  uif=$("#uif");
                if (data.uif==="ok"){
                    uif.css("color","green");
                    uif.html("ok");
                }
                else if (data.uif==="none"){
                    uif.css("color","red");
                    uif.html("用户不存在");
                }

            }})

}
function pwdValidate(){
    $.post(
        {
            url:"${pageContext.request.contextPath}/getState",
            data:{"username":$("#usr").val(),"pwd":$("#pwd").val()},
            success:function (data){
                data=JSON.parse(data);
                let  pif=$("#pif");
                if (data.pif==="ok"){
                    pif.css("color","green");
                    pif.html("ok");
                }
                else if (data.pif==="wrong"){
                    pif.css("color","red");
                    pif.html("密码错误");
                }

            }})
}


