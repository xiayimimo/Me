//登录
function manageLogin(a){
		console.log("进入管理员登录后台");
		if(a == 0){
		
			return;	
		}
	$.ajax({
		url:'manageLogin.do',
		data:{"account":$("#txtAccount").val(),"password":$("#txtPwd").val()},
		type:'post',
		async:true,
		success:function(msg){
			if(msg == "登录成功"){
				window.location.href="main.jsp";
			}else{
				console.log("账号密码不一致");
			$("#info>font").text("账号密码不一致");
			}
			
		},
		error:function(err){
			console.log(err);
		},
		
	});
	
	
	
}