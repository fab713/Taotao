var TT = TAOTAO = {
	checkLogin : function(){
		var _ticket = $.cookie("TT_TOKEN");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://localhost:8084/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "����ӭ�������ԣ�<a href=\"http://www.taotao.com/user/logout.html\" class=\"link-logout\">[�˳�]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	}
}

$(function(){
	// �鿴�Ƿ��Ѿ���¼������Ѿ���¼��ѯ��¼��Ϣ
	TT.checkLogin();
});