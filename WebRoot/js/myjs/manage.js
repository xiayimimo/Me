

//问题管理  
function allQuestion(){
//	console.log("获取问题");
	$.ajax({
		url:'getQuestion.do',
		async:true,
		success:function(){
		
		},
		error:function(){
			
		}
	});
	
}

//问题删除
function delQuestion(id,obj){
	if(!confirm("确定删除吗？")){
		return;
	}
	$.ajax({
		url:'getQuestion.do',
		async:true,
		data:{'id':id},
		success:function(b){
		if(b == 'true'){
			$(obj).parent().parent().remove();
			allQuestion();
		}else{
			//删除失败
		}
		},
		error:function(){
			
		}
	});
}
//页数操作
	$(function(){
	
		$(".page-em").click(function(){
//		alert($(this).text());
		$.ajax({
			url:"page.do",
			async:false,
			data:{"currentPage":$(this).text()},
			success:function(){
				
			},
			error:function(){
				
			}
		});
		});
		//翻页操作
		$(".changePage").click(function(){
			var currentPage = parseInt($("#currentPage").text());
		if($(this).text() == "下一页"){
			currentPage = currentPage + 1;
		}else{
			currentPage = currentPage - 1;
		}
//		alert(currentPage);
			$.ajax({
			url:"page.do",
			async:false,
			data:{"currentPage":currentPage},
			success:function(){
				
			},
			error:function(){
				
			}
		});
		});
		
	});
	



