$(function(){
	$("#check0").attr("checked",'true');
		$("#answersubbtn").click(function(){
				$.ajax({
					url:"ask.do",
					type:"post",
					async:true,
					data:{"title":$(".form-input-inline>[name=roleId]").val(),
					"classify":$("input[name='radio']:checked").next().text(),
					"labels":$("input[name=check]:checked").next().text(),
					"content":CKEDITOR.instances.editor.document.getBody().getText(),
				},
					success:function(msg){
						if(msg == "添加成功"){
	//						alert("添加成功");
							window.location.href="index.jsp";
						}else{
							alert("添加失败，标签不能为空");
						}
					},
					error:function(){
						alert("错误");
					}
				});
			});
		
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
		
	})