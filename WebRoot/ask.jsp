<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title></title>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/layer.js"></script>
		<script type="text/javascript" src="js/search.js"></script>
		<script type="text/javascript" src="js/ask.js"></script>
		<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
		<script src="highlight/highlight.pack.js"></script>
		<link rel="stylesheet" href="highlight/styles/default.css">
		<link rel="stylesheet" href="css/daydayup.css" />
		<link rel="stylesheet" href="css/ask.css" />
		<link rel="stylesheet" href="css/loginregister.css" />
		<link rel="stylesheet" href="css/common.css" />
		<script>
			$(function() {
				$("#menu a").each(function(index, ele) {
					$(ele).bind("mouseover", function() {
						$("#menu a[class='menu-item']").removeClass(); //已经有menu-item的移除
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
					<li>
						<a href="index.jsp">首页</a>
					</li>
					<li>
						<a href="questiondetail.jsp">问答</a>
					</li>
				</ul>
				<ul id="log">
					<c:if test="${!empty sessionScope.user}">	
					<li>欢迎：<a href="#" >${user.getName()}</a></li>
				</c:if>
				<c:if test="${empty sessionScope.user}">	
					<li><a href="#" id="loglink">登录</a></li>
				</c:if>	
					<li><span></span></li>
					<li>
						<a href="#" id="registerlink">注册</a>
					</li>
				</ul>
			</div>
		</header>
		<section id="title">
			<div id="titlebar">
				<div id="leftquestion">
					<a href="#">我要提问</a>
				</div>
				<div id="rightquestion">
					<input type="text" placeholder="请输入查询关键字" />
					<a href="search.jsp" id="search"><img src="img/ic_search.svg" /></a>
				</div>
			</div>
		</section>
		<section id="main">
			<div id="content">
				<div class="toptitle">
					<nav class="questionnav">
						<a href="#">怎么办</a>
						>
						<a href="indexmain.do?tagId=${requestScope.question.tag1.tagId }">Java</a>
					</nav>
				</div>
				<div class="form-item">
					<div class="form-label">
						<label>标题:</label>
					</div>
					<div class="form-input-inline">
						<input type="text" class="ask" name="roleId" placeholder="你有什么技术问题，请在此输入" />
					</div>
				</div>
				<div class="form-item">
					<div class="form-label">
						<label>分类:</label>
					</div>
					<div class="form-input-inline">
						<span class="choice">
								<input type="radio" id="rdo1" name="radio" checked="checked"/>
								<label for="rdo1">技术问答</label>
						</span>
						<span class="choice">
								<input type="radio" id="rdo3" name="radio" />
								<label for="rdo3">面试题</label>
							</span>
						<span class="choice">
								<input type="radio" id="rdo4" name="radio" />
								<label for="rdo4">即时问答</label>
							</span>

					</div>
				</div>
				<div class="form-item">
					<div class="form-label-small">
						<label>标签:</label>
					</div>
					<div class="form-input-inline-small">
					<!-- 遍历标签 -->
					<c:forEach items="${sessionScope.labelList}" var="label" varStatus="s">
					<c:if test="${s.count lt 8}">
						<span class="tagchoice">
								<input type="checkbox" id="check${s.index}" name="check"/>
								<label for="check${s.index}">${label.getName()}</label>
						</span>
						</c:if>
					</c:forEach>
					</div>
				</div>
				<div class="form-item">
					<div class="form-label-small">
						<label></label>
					</div>
					<div class="form-input-inline-small">
					<c:forEach items="${sessionScope.labelList}" var="label" varStatus="s">
					 <c:if test="${s.count gt 8}">
						<span class="tagchoice">
								<input type="checkbox" id="check${s.index}" name="check" />
								<label for="check${s.index}">${label.getName()}</label>
						</span>
						</c:if>
				   </c:forEach>
					</div>
				</div>
				<div style="margin-top: 50px;">
					<textarea id="editor"></textarea>
					<script type="text/javascript">
						CKEDITOR.replace("editor");
					</script>
				</div>
				<div class="answersub">
					<input id="answersubbtn" type="button" value="提交回答">
				</div>
			</div>
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
						<input name="login-account" maxlength="15" class="input_cont" type="text" placeholder="用户名/邮箱/手机号" />
					</div>
					<div class="input_password">
						<input name="login-password" type="password" maxlength="15" class="input_cont" placeholder="密码 " />
					</div>
					<div class="input_checkbox">
						<input type="checkbox" style="display: none" id="remember" /><label class="checkbox" for="remember">记住密码</label>
					</div>
					<div class="btn">
						<input type="submit" id="logbtn" class="btn" value="登录" />
					</div>
				</form>
			</div>
			<div class="msg">Xxxxxxxx</div>
		</div>
		<script>
			$(function() {
				$("#loglink").bind("click", function() {
					layer.open({
						type: 1,
						title: '登录',
						//skin: 'layui-layer-rim', //加上边框
						area: ['398px', '345px'], //宽高:398,222;
						//content: $('#sublayer'),//content: $('#sublayer').html()会丢失事件
						resize: false,
						content: $("#login"),
						closeBtn: 1,
					});
				});
				
			});
		</script>
		<!--注册div结束-->
		<!--登录div开始-->
		<div class="loginregister" style="height:280px;" id="register">
			<div>
				<form>
					<div class="input_box">
						<input name="userInfo.account" maxlength="15" class="input_cont" type="text" placeholder="用户名/邮箱/手机号" />
						<span class="mark">*</span>
					</div>
					<div class="input_password">
						<input type="password" maxlength="15" class="input_cont" placeholder="密码" />
						<span class="mark">*</span>
					</div>
					<div class="input_password">
						<input type="password" maxlength="15" class="input_cont" placeholder="密码确认" />
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
			$(function() {
				$("#registerlink").bind("click", function() {
					layer.open({
						type: 1,
						title: '注册',
						//skin: 'layui-layer-rim', //加上边框
						area: ['398px', '345px'], //宽高:398,282;
						//content: $('#sublayer'),//content: $('#sublayer').html()会丢失事件
						resize: false,
						content: $("#register"),
						closeBtn: 1,
					});
				});
				$("#registerbtn").bind("click", function() {
					layer.closeAll();
				});
			});
		</script>
		<!--登录div结束-->
	</body>

</html>