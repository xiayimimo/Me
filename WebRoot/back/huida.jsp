<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
	<meta charset="utf-8">
    <title>My JSP 'test.jsp' starting page</title>
	<link rel="stylesheet" href="../css/goodstudy.css">
	<script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript" src="../js/layer.js"></script>
	<script type="text/javascript" src="../js/myjs/answermanage.js"></script>
	<script>
		$().ready(
			function(){
				$(".modify").each(//给每个修改超链接注册单击事件
					function(index,domEle){//index:表示迭代的元素在集合中的下标,domEle:表示当前迭代的Dom元素
						$(domEle).bind("click",function(){
							var tr=$(domEle).parent().parent();
							 var id=tr.attr("id");//得到要修改的记录的ID

							var jsonData=JSON.stringify({"tagId":id});//stringify将json对象转成字符串
							//var name=tr.children().eq(1).html();
							//var status=tr.children().eq(2).html();
							//parent.layer.alert(id+":"+name+":"+status);


							parent.layer.open({
								type: 1,
								title:'测试窗口',
								//skin: 'layui-layer-rim', //加上边框
								//area: ['340px', '210px'], //宽高
								content: $('#sublayer').html(),//content: $('#sublayer').html()会丢失事件
								closeBtn:1,
								btn: ['确定', '取消'],
								btnAlign: 'c',//按钮对齐方式
								yes: function(index, layero){//layero：弹出的DOM,单击确定回调方法

									/*
									$.ajax({
										url:"/GoodStudy/base/tagmodify.do",
										type:"post",
										data:"param="+jsonData,
										dataType:"json",
										beforeSend:function(){

										},
										success:function(data){
											//$("#tagid").val(data.tagId);

										},
										error:function(){
											parent.layer.alert("未查到数据");
										},
										complete:function(){

										}
									});

									*/
								},
								success:function(layero,index){//动态层加载完毕后调用,layero:弹出层的DOM,
									$(layero).find("#tagid").val(id);
									$(layero).find("#tagname").val(id);
								}
							});



						});
					}	
				);
				
			}		
		);


		function add(){

			layer.open({
				type: 1,
				title:'测试窗口',
				//skin: 'layui-layer-rim', //加上边框
				//area: ['340px', '210px'], //宽高
				content: $('#sublayer').html(),//content: $('#sublayer').html()会丢失事件
				closeBtn:1,
				btn: ['确定', '取消'],
				btnAlign: 'c',//按钮对齐方式
				yes: function(index, layero){//layero：弹出的DOM,单击确定回调方法

					//$(layero).find(".editform").submit();
					//parent.layer.close(index);//关闭窗口
				}
			});



		}
	
	</script>
</head>
  
  <body>
    <div id="container">
		<input type="hidden" id="msg" value=""/>
		<div class="crumb">
			<span class="breadcrumb">
			  <a href="javascript:void(0);">后台</a>&gt;
			  <a href="javascript:void(0);">基础资料</a>&gt;
			  <a href="javascript:void(0);">问题管理</a>
			</span>
			<span class="operatebar">

			</span>
		</div>
		<div id="tabledata">
			<table class="table">
				<tr>
					<th>序号</th>
					<th>回答内容</th>
					<th>回答者</th>
					<th class="timedata">回答时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${sessionScope.pageControl.getCurrentContent()}" var="answer" varStatus="s">
					<tr id="${s.count}">
						<td>${s.count}</td>
						<td>${answer.getContent() }</td>
						<td>${answer.getUserName() }</td>
						<td>${answer.getAnswerTime() }</td>
						<td><a class="del" href="javascript:void(0);" onclick="delAnswer('${answer.getId() }',this)">删除</a></td>
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
		<c:if test="${sessionScope.pageControl.getCurrentPage() eq 1}">
			<a href="#">上一页</a>
		</c:if>
		<c:if test="${sessionScope.pageControl.getCurrentPage() gt 1}">
			<a href="getAnswer.do?currentPage=${sessionScope.pageControl.getCurrentPage()- 1}">上一页</a>
		</c:if>
		<!-- 判断本页的起始位置如果大于0,，即不为第1页,显示可以单击上一页 -->
		
				<c:forEach begin="1" end="${sessionScope.pageControl.getPageCount()}" var="p">
					<c:if test="${sessionScope.pageControl.getCurrentPage() eq p}">	
					<span class="page-curr" >
						<em class="page-em" style="background-color:#1E9FFF;"></em>
						<em>${p}</em>
					</span>
					</c:if>	
					<c:if test="${sessionScope.pageControl.getCurrentPage() ne p}">		
					<a href="getAnswer.do?currentPage=${p}">${p}</a>
					</c:if>
				</c:forEach>
		
		<!-- 判断本页的起始位置如果为最后一页,直接显示下一页 -->
		
		<!-- 判断本页的起始位置如果不为最后一页,显示可以单击下一页 -->
		<c:if test="${sessionScope.pageControl.getCurrentPage() eq sessionScope.pageControl.getPageCount()}">
			<a href="#" >下一页</a>
		</c:if>
		<c:if test="${sessionScope.pageControl.getCurrentPage() lt sessionScope.pageControl.getPageCount()}">
		    <a href="getAnswer.do?currentPage=${sessionScope.pageControl.getCurrentPage() + 1}">下一页</a>
		</c:if>
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
