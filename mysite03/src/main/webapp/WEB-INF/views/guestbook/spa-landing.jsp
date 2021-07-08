<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-spa.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
/* guesbook application based on jQuery */
 /*
 과제 ex1: 리스트
 - no 기준의 리스트를 부분적(3개씩) 가져와서 렌더링
 - 버튼 이벤트 구현 -> 스크롤 이벤트 바꾼다.
 - no 기준으로 동적쿼리를 레포지토리에 구현한다.
 - 렌더링 참고 : /ch08/test/gb/ex1
 
 과제 ex2: 메세지 등록(add)
 - validation
 - form submit 막기
 - message 기반 dialog plugin 사용법
 - 데이터 하나를 렌더링(prepend)
 - 참고: /ch08/test/gb/ex2
 
 과제 ex3: 메세지 삭제(delete)
 - a tag 기본동작 막기
 - live event
 - form 기반 dialog plugin 사용법
 - 응답에 대해 해당 li 삭제
 - 비밀번호가 틀린 경우(삭제실패, no=0) 사용자에게 알려주는 UI
 - 삭제 성공한 경우(no>0) data-no=10인 li element 삭제
 */
 
 var fetch = function(){
	var no = $("#list-guestbook li:last").data("no");
	if (no == null) {
		no = 0;
	}
	 $.ajax({
		url : "${pageContext.request.contextPath }/guestbook/api/list/"+ no,
		dataType : "json", // 받을 때 format
		type : "get", // 요청 method
		success : function(response) {
			response.data.forEach(function(vo){
				
			html =
				"<li data-no='" + vo.no + "'>" +
				"<strong>" + vo.name + "</strong>" +
				"<p>" + vo.message + "</p>" +
				"<strong></strong>" +
				"<a href='' data-no='" + vo.no + "'>삭제</a>" +
				"</li>";
				
				$("#list-guestbook").append(html);
				console.log("fetch");
			});
			}
		});
	}
 
 $(function(){
	 var event = true;
	 
	 $(window).scroll(function(){
			var $window = $(this);
			var windowHeight = $window.height();
			var scrollTop = $window.scrollTop();
			var documentHeight = $(document).height();
			if(scrollTop + windowHeight + 10 > documentHeight && event==true){
				fetch();
				event = false;
			} else if(scrollTop +30 > (documentHeight/2)){
				event = true;
			}
		});
	
	// 추가
		
		$("#add-form").submit(function(event){
			event.preventDefault();
			
			vo = {}
			
			vo.name = $("#input-name").val();
			// validation name
			if(vo.name == "") {
				// alert("이름이 비어 있습니다.");
				$("#dialog-message").dialog({
					modal: true,
					buttons: {
						"확인": function(){
							$(this).dialog("close");
						}
					}				
				});
				return;
			}
			vo.password = $("#input-password").val();
			// validation password
			vo.message = $("#tx-content").val();
			// validation message
		
			// 데이터 등록
			$.ajax({
				url: "${pageContext.request.contextPath }/guestbook/api/add",
				dataType: "json",
				type: "post",
				contentType: "application/json",   
				data: JSON.stringify(vo),
				success: function(response){
					var vo = response.data;
					
					html =
						"<li data-no='" + vo.no + "'>" + 
							"<strong>" + vo.name + "</strong>" +
							"<p>" + vo.message + "</p>" +
							"<strong></strong>" + 
							"<a href='' data-no='" + vo.no + "'>삭제</a>" + 
						"</li>";
						
					$("#list-guestbook").prepend(html);	
					
					$("#input-name").val("");
					$("#input-password").val("");
					$("#tx-content").val("");
				}
			});	
		});
			
	 
	// live event: 존재하지 않는 element의 이벤트 핸들러를 미리 등록
		// delegation(위임) -> document
		$(document).on("click", "#list-guestbook li a", function(event){
			event.preventDefault();
			let no = $(this).data("no");
			$("#hidden-no").val(no);
			
			deleteDialog.dialog("open");
		});
		
	// 삭제 다이알로그 만들기
		const deleteDialog = $("#dialog-delete-form").dialog({
			autoOpen: false,
			width: 300,
			height: 220,
			modal: true,
			buttons: {
				"삭제": function(){
					const no = $("#hidden-no").val();
					const password = $("#password-delete").val();
					$.ajax({
						url: "${pageContext.request.contextPath }/guestbook/api/delete/"+no,
						dataType: "json",
						type: "post",
						data: "password=" + password,
						success : function(response) {
							if(response.data == -1){
								// password 틀린 경우
								$(".validateTips.error").show();
								return;
							} 
							
							$("#list-guestbook li[data-no=" + response.data + "]").remove();
							deleteDialog.dialog("close");
						}
			 });
		},
				"취소": function(){
					$(this).dialog("close");
				}
			},
			close: function(){
				//1. password 비우기
				$("#password-delete").val("");
				//2. no 비우기
				$("hidden-no").val("");
				//3. error message 숨기기.
				$(".validateTips.error").hide();
				
				console.log("다이알로그 폼 데이터 정리 작업");
				
			}
		});
	
	 fetch(); // 초기 리스트
 });
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<h1>방명록</h1>
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름">
					<input type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" />
				</form>
				<ul id="list-guestbook">

					
				</ul>
				<!-- <div style="margin:20px 0 0 0">
					<button id="btn-fetch">다음 가져오기</button>
				</div> -->
			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display:none">
  				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
  				<p class="validateTips error" style="display:none">비밀번호가 틀립니다.</p>
  				<form>
 					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all">
					<input type="hidden" id="hidden-no" value="">
					<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
  				</form>
			</div>
			<div id="dialog-message" title="" style="display:none">
  				<p></p>
			</div>						
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-ajax"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>