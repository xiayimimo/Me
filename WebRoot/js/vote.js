
//问题投票
	function questionvote(id,a){
//		alert("问题投票");
		var httpRequest = new XMLHttpRequest();
		httpRequest.onreadystatechange = function(){
			if(httpRequest.readyState==4 && httpRequest.status==200){
				var msg=httpRequest.responseText;
				$("#questionVoteCount").text(msg);
			}
		}
		httpRequest.open("get","questionVote.do?questionId="+id+"&a="+a);
		httpRequest.send(null);
	};
 //答案投票
	function answervote(obj,id,a){
		
		var httpRequest = new XMLHttpRequest();
		httpRequest.onreadystatechange = function(){
			if(httpRequest.readyState==4 && httpRequest.status==200){
				var msg=httpRequest.responseText;
				//怎么实现异步改变票数????????????????
				if(a == 1){
					obj.nextElementSibling.innerHTML = msg;
				}else{
					obj.previousElementSibling.innerHTML = msg;
				}
				
				}
		}
		httpRequest.open("get","answerVote.do?answerId="+id+"&a="+a);
		httpRequest.send(null);
	};
	
$(function(){
	//答案排序
	$(".answer_order>a").siblings().click(function(){
//		alert("排序答案");
		$.ajax({
			url:"answer_order.do",
			type:"get",
			data:{"operation":$(this).text()},
			async:true,
			success:function(){
			
			$(".answer_list").load(location.href+" .answer_list");
			
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
			
				alert(textStatus);
			},
			
		});
		
	});
})