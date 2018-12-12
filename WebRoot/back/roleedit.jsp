<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>角色新增</title>
<link rel="stylesheet"
	href="../css/goodstudy.css">
<script type="text/javascript"
	src="../js/jquery.js"></script>
<script type="text/javascript"
	src="../js/layer.js"></script>
<script>
	$().ready(function() {

	});
</script>
</head>

<body>
	<div id="container">
		<input type="hidden" id="msg" value="" />
		<div class="crumb">
			<span class="breadcrumb"> <a href="javascript:void(0);">用户管理</a>&gt;
				<a href="javascript:void(0);">角色管理</a>&gt; <a
				href="javascript:void(0);">新增</a>
			</span>
		</div>
		<div class="operatewin">
			<form class="editform" action="add.do?param=roleAdd" method="post" style="width:360px;">
				<!-- 添加隐藏的文本框,避免文本框里输入回车自动提交表单的问题 -->
				<input style="display:none" />
				<div class="form-item">
					<div class="form-label">
						<label>角色编号:</label>
					</div>
					<div class="input-inline">
						<input type="text" class="txt" name="roleId" value="${requestScope.size}" readOnly/>
					</div>
				</div>
				<div class="form-item">
					<div class="form-label">
						<label>角色名称:</label>
					</div>
					<div class="input-inline">
						<input type="text" class="txt" name="roleName" value=""/>
					</div>
				</div>
				<div class="form-item">
					<div class="form-label">
						<label>当前权限:</label>
						
					</div>
					
				</div>
				<c:forEach items="${requestScope.userLimit}" var="limit" >
				<div>
					<input type="checkbox" name="rights" value="${limit.getName() }" checked/>${limit.getName() }
				</div>
				</c:forEach>
				<div class="form-item">
					<div class="form-label">
						<input type="submit" value="保存" class="btn normal" />
						<input type="reset" value="重置" class="btn normal" />
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>
