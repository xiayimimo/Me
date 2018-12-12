$(function(){
		
		//登录
		$("#logbtn").bind("click",function(){
					$.ajax({
						url:"login.do",
						type:"post",
						async:false,
						dataType:"text",
						data:({'account':$("[name=login-account]").val(),'password':$("[name=login-password]").val()}),
						success:function(msg){
						if(msg == "登录成功") {
//							alert("登录成功");
							layer.closeAll();
							$("#loglink").parent().text('欢迎：'+'${user.getName()}');
							
						}else{
						document.getElementById("sp").innerHTML="账号和密码不一致";
					}
						},
						error:function(XMLHttpRequest, textStatus, errorThrown){
							
							alert(textStatus)
						},
					});
		
		});
		//提交回答
		$("#answersubbtn").click(function(){
//			alert("回答do");
				$.ajax({
					url:"answer.do",
					type:"post",
					async:true,
					data:{"content":CKEDITOR.instances.editor.document.getBody().getText(),
				},
					success:function(msg){
						window.location.href="questiondetail.jsp";
					},
					error:function(){
						alert("错误");
					}
				});
			});
		
		
	})