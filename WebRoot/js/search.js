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
		
	})