<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" type = "text/javascript" ></script>
	
<script>
$(function(){
	btn = $('#btn-check');
	btn.click(function(){
		var email = $("#email").val();
		if(email == ""){
			return;
		}
		
		$.ajax({
			url: "/mysite03/user/api/checkemail?email=" + email,
			type: "get",
			dataType: "json",
			
			error:function(xhr,status,e) {
				console.error(status,e);
			},
			
			success:function(response) {
				console.log(response);
				
				if(response.result != "success"){
					console.error(response.message);
					reutrn;
				}
				
				if(response.data){
					alert("존재하는 이메일입니다. 다른 이메일을 사용해주세요.");
					$("#email").val("");
					$("#email").focus("");
					return;
				}
				$("#btn-check").hide();
				$("#img-check").show();
			}
		});
	});
});
</script>

</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath }/user/join">
					<input type="hidden" name="a" value="join" />
					<label class="block-label" for="name">이름</label><br>
					<input id="name" name="name" type="text" value=""><br>

					<label class="block-label" for="email">이메일</label><br>
					<input id="email" name="email" type="text" value="">
					<input id="btn-check" type="button" value="중복체크">
					<img id="img-check" src="${pageContext.request.contextPath }/assets/images/check.png" style="width:30px; vertical-align: bottom; display: none">
					<br>
					<label class="block-label">패스워드</label><br>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>