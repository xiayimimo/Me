<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="utf-8">
    <title>人员管理</title>
	<link rel="stylesheet" href="../css/goodstudy.css">
	<script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript" src="../js/layer.js"></script>

  </head>
  
  <body>
    <div id="container">
		<input type="hidden" id="msg" value=""/>
		<div class="crumb">
			<span class="breadcrumb">
			  <a href="javascript:void(0);">后台</a>&gt;
			  <a href="javascript:void(0);">用户管理</a>&gt;
			  <a href="javascript:void(0);">人员管理</a>
			</span>
			<span class="operatebar">
				<input type="button" class="btn normal" value="新增" onclick="location.href='users.do?param=add'" id="new"/>
			</span>
		</div>
		<div id="tabledata">
			<table class="table">
				<tr>
					<th>序号</th>
					<th>用户账号</th>
					<th>用户名</th>
					<th>角色</th>
					<th>操作</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${requestScope.users}" var="user" varStatus="s">
					<tr id="${s.count}">
						<td>${s.count}</td>
						<td>${user.getAccount()}</td>
						<td>${user.getName()}</td>
						<td>${user.getPower()}</td>
						<td><a class="modify" href="javascript:void(0);">修改</a></td>
						<td><a class="del" href="javascript:void(0);">删除</a></td>
					</tr>
				</c:forEach>
			</table>
			<table>
				<tr>
					<td class="pagetools">
					<form action="/GoodStudy/base/tag.do" id="pageform" method="post">
							  

<script>
		function changeQuery(startIndex) {
			var formObj = document.getElementById("pageform");		
			//将下一页面第1条记录的起始位置赋值给隐藏域,后续提交给服务器
			document.getElementById("beginIndex").value = startIndex;
			formObj.submit();
		}
</script>
<style>
.page {
	display: inline-block;
	vertical-align: middle;
	margin: 10px 0;
	background-color: #fff;
	font-size:12px;
}
.page a {
	text-decoration: none
}
.page a,.page span {
	display: inline-block;
	vertical-align: middle;
	padding: 0 15px;
	border: 1px solid #e2e2e2;
	height: 28px;
	line-height: 28px;
	margin: 0 -1px -1px 0;
	color: #333;
	font-size: 12px;
}
.page span {
	color: #999;
	font-weight: 700
}
.page .page-curr {
	position: relative;
}
.page .page-curr em {
	position: relative;
	color: #fff;
	font-weight: 400
}
.page em{
	font-style: normal
}
.page .page-curr .page-em {
	position: absolute;
	left: -1px;
	top: -1px;
	padding: 1px;
	width: 100%;
	height: 100%;
	background-color: #009688
}

.page-em {
	border-radius: 2px
}

</style>

<!-- 进入页面时,此隐藏域保存的是当前页的起始位置,提交页面时, 此隐藏域保存的是下一页数据的起始位置-->
<input type="hidden" id="beginIndex" name="startIndex" value="0"/>
<div class="page">
		<!-- 判断本页的起始位置如果为0,，即为第1页,直接显示上一页 -->
		
			<a href="javascript:void(0)">上一页</a>
		
		<!-- 判断本页的起始位置如果大于0,，即不为第1页,显示可以单击上一页 -->
		
		
				
					<span class="page-curr" >
						<em class="page-em" style="background-color:#1E9FFF;"></em>
						<em>1</em>
					</span>
				
				
		
				
				
					<a href="javascript:void(0)" >
						2
					</a>
				
		
		<!-- 判断本页的起始位置如果为最后一页,直接显示下一页 -->
		
		<!-- 判断本页的起始位置如果不为最后一页,显示可以单击下一页 -->
		
			<a href="javascript:void(0)" >下一页</a>
		
</div>

						</form>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!--弹出div开始 -->
	<div id="sublayer">
		<div class="operatewin">
			<form class="editform" action="" method="post" target="content">
				<!-- 添加隐藏的文本框,避免文本框里输入回车自动提交表单的问题 -->
				<input style="display:none" />
				<div class="form-item">
					<div class="form-label">
						<label>标签编号:</label>
					</div>
					<div class="input-inline">
						<input type="text" class="txt" name="courseName" value=""
							id="tagid" /> <span class="errormsg">*</span>
					</div>
				</div>
				<div class="form-item">
					<div class="form-label">
						<label>标签名称:</label>
					</div>
					<div class="input-inline">
						<input type="text" class="txt" name="courseName" value="" id="tagname"/> <span
							class="errormsg">*</span>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 弹出div结束 -->
  </body>
</html>
