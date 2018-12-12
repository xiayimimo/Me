<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>后台管理</title>
<link rel="stylesheet"
	href="../css/goodstudy.css">
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/layer.js"></script>
<script type="text/javascript" src="../js/myjs/manage.js"></script>
<script language="javascript">
	$(
		function(){
			//$("li").find("ul").prev(): 有 子节点 ul的 li的前一个节点,ul前的超链接 
			$("li").find("ul").prev().click(
				function(){
					//$(this).next()://嵌套的 ul
					//toggleClass("hide"),切换样式 
					$("[class='secondMenu display']").not($(this).next()).toggleClass("display");//隐藏之前展开的
					$(this).next().toggleClass("display");//展开本次单击的
				}
			);
		}
	);
	
	function gettime(){
  		var d = new Date();
  		
   		document.getElementById("time").innerHTML=d.toLocaleString();
   		window.setTimeout("gettime()",1000);
} 
  		window.onload=gettime; 
</script>
</head>
<body>
	<div>
		<!--头部-->
		<div id="mainheader">
		<font color="white" size=8px">
		欢迎：${sessionScope.user.getPower() }
		</font>
		<span style="float: right; margin-top: 15px;margin-right: 15px"><a href="login.jsp" ><font color="white" size="5px">退出</font></a></span>
		<span style="float: right; margin-top: 15px;margin-right: 15px"><font id="time" color="white" size="5px"></font></span>
		</div>
		<div id="mainaside">
			<ul id="listUL">
			<c:forTokens items="${sessionScope.manage }" delims="," var="name">
			<c:if test="${name == '问答管理'}">
				<li class="firstMenu"><a href="#" onclick="allQuestion()">问答管理</a>
					<ul class="secondMenu">
						<li><a href="asks.jsp" target="content" onclick="allQuestion()">问题管理</a></li>
					</ul>
				</li>
			</c:if>
			</c:forTokens>
			<c:forTokens items="${sessionScope.manage }" delims="," var="name">
			<c:if test="${name == '用户管理'}">
				<li class="firstMenu"><a href="#">用户管理</a>
					<ul class="secondMenu">
						<li><a href="users.do?param=人员管理" target="content" >人员管理</a></li>
						<c:if test="${sessionScope.user.getPower() == '超级管理员'}">
						<li><a href="users.do?param=角色管理" target="content" >角色管理</a></li>
						<li><a href="users.do?param=权限管理" target="content" >权限管理</a></li>
						</c:if>
					</ul>
				</li>
				</c:if>
				</c:forTokens>
				<li class="firstMenu"><a href="#">数据统计</a>
					<ul class="secondMenu">
						<li><a href="asktj.html" target="content">问题统计</a></li>
						<li><a href="jftj.html"  target="content">积分统计</a></li>
					</ul>
				</li>
				<c:forTokens items="${sessionScope.manage }" delims="," var="name">
				<c:if test="${name == '标签管理'}">
				<li class="firstMenu"><a href="#">基础资料</a>
					<ul class="secondMenu">
						<li><a href="tag.html" target="content">标签管理</a></li>
					</ul>
				</li>
				</c:if>
				</c:forTokens>
			</ul>
			
		</div>
		<div id="maincontent">
			<iframe src="" name="content" id="operate" frameborder="0"
				scrolling="no" height="1000px" width="100%" style="min-height:600px;overflow:scroll;">
			</iframe>
		</div>
	</div>
</body>
</html>