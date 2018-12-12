

//答案删除
function delAnswer(id,obj){
	if(!confirm("确定删除吗？")){
		return;
	}
	$.ajax({
		url:'getAnswer.do',
		async:true,
		data:{'answerId':id},
		success:function(b){
		if(b == 'true'){
			$(obj).parent().parent().remove();
			
		}else{
			//删除失败
			
		}
		},
		error:function(){
			alert("错误");
		}
	});
}




