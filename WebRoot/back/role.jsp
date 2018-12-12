<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="utf-8">
    <title>角色管理</title>
	<link rel="stylesheet" href="../css/goodstudy.css">
	<script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript" src="../js/layer.js"></script>
	<script>
		$().ready(
			function(){
				$(".modify").each(//给每个修改超链接注册单击事件
					function(index,domEle){//index:表示迭代的元素在集合中的下标,domEle:表示当前迭代的Dom元素
						$(domEle).bind("click",function(){
							var tr=$(domEle).parent().parent();
							var id=tr.attr("id");//得到要修改的记录的ID
							location.replace("/GoodStudy/worker/modifyrole.do?id="+id);
						});
					}	
				);
			}		
		);
	</script>
  </head>
  
  <body>
    <div id="container">
		<div class="crumb">
			<span class="breadcrumb">
			  <a href="javascript:void(0);">后台</a>&gt;
			  <a href="javascript:void(0);">用户管理</a>&gt;
			  <a href="javascript:void(0);">角色管理</a>
			</span>
			<span class="operatebar">
				<input type="button" class="btn normal" value="新增" id="new" onclick="location.href='users.do?param=addlimit'"/>
			</span>
		</div>
		<div id="tabledata">
			<table class="table">
				<tr>
					<th>序号</th>
					<th>角色名称</th>
					<th>操作</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${requestScope.userRole}" var="role" varStatus="s">
				<tr id="${s.count}">
					<td>${s.count}</td>
					<td>${role.getRole()}</td>
					<td><a class="modify" href="roleedit.jsp">修改</a></td>
					<td><a class="del" href="javascript:void(0);">删除</a></td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div class="rightmsg">
			
		</div>
		<div class="errormsg">
			
		</div>
	</div>
  </body>
</html>

