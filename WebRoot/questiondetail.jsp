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
		<script type="text/javascript" src="js/vote.js"></script>
		<script type="text/javascript" src="js/answer.js"></script>
		<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
		<script src="highlight/highlight.pack.js"></script>
		<link rel="stylesheet" href="highlight/styles/default.css">
		<link rel="stylesheet" href="css/daydayup.css" />
		<link rel="stylesheet" href="css/loginregister.css" />
		<link rel="stylesheet" href="css/questiondetail.css" />
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
						<a href="#">问答</a>
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
					<a href="ask.jsp">我要提问</a>
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
					<!-- 传统模式跳转 -->
						<a href="indexmain.do?classify=${question.getClassify() }">${question.getClassify() }</a>
						&gt;
						<a href="indexmain.do?tagId=">${question.getLabels() }</a>
					</nav>
					<a href="#answereditor">
						<div class="total_answer">
							${answerList.size()}<br />
							<span>回答</span>
						</div>
					</a>
					<span class="question_title_link">${question.getTitle() }</span>
				</div>
				<section class="content-container">
					<section class="questioncontainer">
						<div>
							<div class="vote_collect_wrapper">
								<div class="vote_collect">
									<a class="vote_up" onclick="questionvote(${sessionScope.question.getId() },1)" title="顶">
									</a>
									<span id="questionVoteCount" class="vote_count">${sessionScope.question.getTicketCount() }</span>
									<a class="vote_down" onclick="questionvote(${sessionScope.question.getId() },-1)" title="踩">
									</a>
								</div>
							</div>
							<div class="question-content-detail">
								<section>
									<p>${sessionScope.question.getContent() }
									</p>
								</section>

							</div>
						</div>
						<div class="question-userdetail">
							<div class="userinfo">
								<span class="uname">${sessionScope.question.getUserName() }</span>
								<span class="edittime">${sessionScope.question.getDate() }</span>
							</div>
							<div id="bigheader">
								<img src="img/header2.jpg" />
							</div>
						</div>
					</section>
					<div class="answer_content">
						<div class="answer_count">
						<c:if test="${answerList.size() eq 0}">
						<span>总共有0条回答</span>
						</c:if>
						<c:if test="${answerList.size() ne 0}">
						<span>总共有${answerList.size() }条回答</span>
						</c:if>	
							<span class="answer_order"><a class="selected" href="#">按票数排序</a> <a href="#">显示最新答案</a></span>
						</div>
						<div>
							<section class="answer_list">
							<!-- 遍历回答 -->
							<c:forEach items="${sessionScope.answerList}" var="answer" varStatus="s">
								<article class="answer_detail">
									<div class="vote_collect_wrapper">
										<div class="vote_collect">
											<a class="vote_up" onclick="answervote(this,${answer.getId() },1)" title="顶">
											</a>
											<span id="answervotecount${s.index}" class="vote_count">${answer.getTicketCount() }</span>
											<a class="vote_down" onclick="answervote(this,${answer.getId() },-1)" title="踩">
											</a>
										</div>
									</div>
									<div class="answer-content-detail">
										<section>
											<p>${answer.getContent() }</p>
										</section>
									</div>
									<div class="userdetail">
										<div class="userinfo">
											<span class="uname">${answer.getUserName() }</span>
											<span class="edittime">${answer.getAnswerTime() }</span>
										</div>
										<div>
											<img src="img/header2.jpg" />
										</div>
									</div>
								</article><hr/>
							</c:forEach>	
							</section>
							<section>
								<textarea id="editor"></textarea>
								<script type="text/javascript">
									CKEDITOR.replace("editor");
								</script>
								<div class="answersub">
									<input id="answersubbtn" type="button" value="提交回答">
								</div>
							</section>
						</div>
					</div>
				</section>
			</div>
			<aside>
				<h4>所有标签：</h4>
				<div class="tag">${sessionScope.question.getLabels() }</div>
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
				$("#logbtn").bind("click", function() {
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