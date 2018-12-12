<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/layer.js" ></script>
		<link rel="stylesheet" href="css/daydayup.css"/>
		<link rel="stylesheet" href="css/loginregister.css"/>
		<link rel="stylesheet" href="css/common.css"/>
		<script>
			$(function(){
				$("#menu a").each(function(index,ele){
					$(ele).bind("mouseover",function(){
						$("#menu a[class='menu-item']").removeClass();//已经有menu-item的移除
						$(ele).toggleClass("menu-item");
					});
				});
			});
		</script>
	</head>
	<body>
		<header>
			<div id="headerbar">
				<ul id="navigation">			
					<li><a href="#">首页</a></li>
				</ul>
				<ul id="log">		
				<c:if test="${!empty sessionScope.user}">	
					<li>欢迎：<a href="#" >${user.getName()}</a></li>
				</c:if>
				<c:if test="${empty sessionScope.user}">	
					<li><a href="#" id="loglink">登录</a></li>
				</c:if>	
					<li><span></span></li>
					<li><a href="#" id="registerlink">注册</a></li>
				</ul>
			</div>
		</header>
		<section id="title">
			<div id="titlebar">
				<div id="leftquestion">
					<a href="ask.jsp">我要提问</a>
				</div>
				<div id="rightquestion">
					<input type="text" placeholder="请输入查询关键字"/>
					<a href="search.jsp" id ="search"><img src="img/ic_search.svg"/></a>
				</div>
			</div>
		</section>
		<section id="main">
			<section id="content">
				<div id="headlist">
					<div id="menu">
						<a href="" class="menu-item">技术问答</a>
						<a href="">面试题</a>
						<a href="">即时问答</a>
					</div>
					<div id="order">
						<span>排列:</span>
						<a href="">最新提问</a>
						<a href="">尚未回答</a>
						<a href="">热门</a>
					</div>
				</div>
	<!-- 遍历所有问题 -->
	<section class="box" id ="s1">
	<c:forEach items="${sessionScope.questionList}" var="question">
				
					<div class="box-f1">					
							<ul>      
								<li>
									${question.getTicketCount()}
									<span>投票</span>
								</li>
								<li style="border:1px solid #4eaa4c; color: #4eaa4c;">
									${question.getAnswerCount()}
									<span>回答</span>
								</li>
								<li>
									${question.getBroswerCount()}
									<span>浏览</span>
								</li>
							</ul>					
					</div>
					<div class="box-f2">
						<div class="question_title">
							<a href="#">${question.getTitle()}</a>
						</div>
						<div class="question_content">
						${question.getContent()}
						</div>
						<div class="question_time">
							<span>
								发布人：${question.getUserName()}发布时间：${question.getDate()}
							</span>
							<span class="tag">${question.getLabels()}</span>
						</div>
					</div>
					<div class="box-f3">
						<img src="img/header2.jpg" />
					</div>
	</c:forEach>
	</section>
			<aside>
				<h4>所有标签：</h4>
				<!-- 遍历所有标签 -->
				<c:forEach items="${sessionScope.labelList}" var="label">
				<div class="tag">${label.getName()}</div>
				</c:forEach><br/>
				<font size="6px">${sessionScope.time}</font>
			</aside>
		</section>
		<!--注册div开始-->
		<div class="loginregister" id="login">
			<div>
		 	<form method="post"> 
						<span id="sp"></span>
					<div class="input_box">
						<input name="login-account" maxlength="15" class="input_cont" type="text" placeholder="用户名/邮箱/手机号"/>
					</div>
					<div class="input_password">
						<input name ="login-password" type="password" maxlength="15" class="input_cont" placeholder="密码 "/>
					</div>
					<div class="input_checkbox">
						<input type="checkbox" style="display: none" id="remember"/><label class="checkbox" for="remember">记住密码</label>
					</div>
					<div class="btn">
						<input type="submit" id="logbtn" class="btn" value="登录" />
					</div>
					
			</form> 
			</div>
			<div class="msg">Xxxxxxxx</div>
		</div>
		<script>
			$(function(){
				//问题分类
				$("#menu").children().bind("click",function(){
	//				alert("方法进入");
					$.ajax({
						url:"classify.do",
						type:"get",
						async:false,
						data:({classify:$(this).text()}),
						success:function(){
	//						alert("成功了");
	//						$("#s1").load(location.href+" #s1");
						},
						error:function(XMLHttpRequest, textStatus, errorThrown){
							alert(textStatus);
						}
					});
	//全局刷新				window.location.reload();
				});
				
				
				//登录
				$("#loglink").bind("click",function(){
					layer.open({
						  type:1,
						  title:'登录',
						  //skin: 'layui-layer-rim', //加上边框
						  area: ['398px', '345px'], //宽高:398,222;
						  //content: $('#sublayer'),//content: $('#sublayer').html()会丢失事件
						  resize:false,
						  content:$("#login"),
						  closeBtn:1,
					});
				});
				$("#logbtn").bind("click",function(){
//				alert('${user.getName()}');
					$.ajax({
						url:"login.do",
						type:"post",
						async:false,
						dataType:"text",
						data:({'account':$("[name=login-account]").val(),'password':$("[name=login-password]").val()}),
						success:function(msg){
	//					alert(msg);
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
			});
		</script>
		<!--注册div结束-->
		<!--登录div开始-->
		<div class="loginregister" style="height:350px;" id="register">
		    <div>
		    	<form>
					  <div class="input_box">
				        <input id="userInfo-account" maxlength="15" class="input_cont" type="text" placeholder="用户名/邮箱/手机号" />
				        <span class="mark">*</span>
				      </div>
				      <div class="input_password">
				        <input id = "userInfo-password" type="password" maxlength="15" class="input_cont" placeholder="密码"/>
				        <span class="mark">*</span>
				      </div>
				      <div class="input_password">
				        <input id = "userInfo-passwordCk" type="password" maxlength="15" class="input_cont" placeholder="密码确认"/>
				        <span class="mark">*</span>
				      </div>

					<div class="input_box">
						<input id = "userInfo-name" type="text" maxlength="15" class="input_cont" placeholder="姓名"/>
						<span class="mark">*</span>
					</div>
				      <div class="btn">
				    	<input type="submit" class="btn" id="registerbtn" value="注册" />
				      </div>
			    </form>
		    </div>
			<div class="msg">Xxx</div>
		</div>
		<script>
			$(function(){
				$("#registerlink").bind("click",function(){
					layer.open({
						  type:1,
						  title:'注册',
						  //skin: 'layui-layer-rim', //加上边框
						  area: ['398px', '345px'], //宽高:398,282;
						  //content: $('#sublayer'),//content: $('#sublayer').html()会丢失事件
						  resize:false,
						  content:$("#register"),
						  closeBtn:1,
					});
				});
				//验证用户是否存在
				$("#userInfo-account").bind("change",function(){
				$.ajax({
						url:"ck.do",
						type:"get",
						async:true,
						data:({'account':$("#userInfo-account").val()}),
						success:function(msg){
							$("#userInfo-account").next().text(msg)
						},
						error:function(){
							alert("后台访问错误");
						},
					});
					
				});
				//验证俩次密码是否一致
				$("#userInfo-passwordCk").bind("change",function(){
					$.ajax({
						url:"ckpwd.do",
						type:"get",
						async:true,
						data:({'password':$("#userInfo-password").val(),'passwordCk':$("#userInfo-passwordCk").val()}),
						success:function(msg){
							$("#userInfo-passwordCk").next().text(msg)
						},
						error:function(){
							alert("后台访问错误");
						},
					});
				});
				$("#registerbtn").bind("click",function(){
					//注册业务
					$.ajax({
						url:"reg.do",
						type:"post",
						async:true,
						data:({'account':$("#userInfo-account").val(),'password':$("#userInfo-password").val(),'passwordCk':$("#userInfo-passwordCk").val(),'name':$("#userInfo-name").val()}),
						success:function(msg){
							alert(msg)
						},
						error:function(){
							alert("后台访问错误");
						},
					});
					layer.closeAll();
				});
			});
		</script>
		<!--登录div结束-->
		<!-- 问题排列 -->
		<script type="text/javascript">
		$(function(){
			//最新提问
			$("#order>a").click(function(){
//				alert($(this).text());
				$.ajax({
					url:"sort.do",
					type:"get",
					data:{"operation":$(this).text()},
					async:false,
					success:function(){
//						$("#s1").load(location.href+" #s1");
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						alert(textStatus);
					},
				});
			});
			
		});
		
		</script>
		<script type="text/javascript">
		//搜索问题
		$(function(){
			$("#search").click(function(){
//				alert("搜索");
				$.ajax({
					url:"search.do",
//					type:"get",
					async:false,
					data:{"param":$("#search").prev().val()},
					success:function(){
						
					},
					error:function(){
						alert("错误");
					}
				});
			});
		//点击标题显示内容	
		
		$(".question_title>a").bind("click",function(){
//			alert("具体信息");
			$.ajax({
					url:"detail.do",
					type:"get",
					async:false,
					data:{"title":$(this).text()},
					success:function(){
					$(location).attr('href', "questiondetail.jsp");
					},
					error:function(){
						alert("错误");
					}
				});
			
		});
		
		});
		</script>
	</body>
</html>
