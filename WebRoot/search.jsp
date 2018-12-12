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
					<li><a href="index.jsp">首页</a></li>
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
					<a href="#"><img src="img/ic_search.svg"/></a>
				</div>
			</div>
		</section>
		<section id="main">
			<section id="content">
				<div id="headlist">

					<div id="order">
					<c:if test="${sessionScope.questionList.size() ne 0}">	
					<span>已经搜索到${sessionScope.questionList.size() }条相关的记录</span>
				    </c:if>
					<c:if test="${sessionScope.questionList.size() eq 0}">	
					<span>已经搜索到0条相关的记录</span>
				    </c:if>
					</div>
				</div>
				<section class="box">
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
			</section>
			<aside>
				<h4>所有标签：</h4>
				<c:forEach items="${sessionScope.labelList}" var="label">
				<div class="tag">${label.getName()}</div>
				</c:forEach>
			</aside>
		</section>

		<!--注册div开始-->
		<div class="loginregister" id="login">
			<div>
				<form>
					<div class="input_box">
						<input name="userInfo.account" maxlength="15" class="input_cont" type="text" placeholder="用户名/邮箱/手机号"/>
					</div>
					<div class="input_password">
						<input type="password" maxlength="15" class="input_cont" placeholder="密码 "/>
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
					layer.closeAll();
				});
			});
		</script>
		<!--注册div结束-->
		<!--登录div开始-->
		<div class="loginregister" style="height:280px;" id="register">
		    <div>
		    	<form>
					  <div class="input_box">
				        <input name="userInfo.account" maxlength="15" class="input_cont" type="text" placeholder="用户名/邮箱/手机号"/>
				        <span class="mark">*</span>
				      </div>
				      <div class="input_password">
				        <input type="password" maxlength="15" class="input_cont" placeholder="密码"/>
				        <span class="mark">*</span>
				      </div>
				      <div class="input_password">
				        <input type="password" maxlength="15" class="input_cont" placeholder="密码确认"/>
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
				$("#registerbtn").bind("click",function(){
					layer.closeAll();
				});
				//标题点击
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
		<!--登录div结束-->
	</body>
</html>
