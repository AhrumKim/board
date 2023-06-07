<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function() {
		//쿠키 값 가져오기
		function getCookie(name) {
			var cookieName = name + "=";
			var decodedCookie = decodeURIComponent(document.cookie);
			var cookieArray = decodedCookie.split(";");

			for (var i = 0; i < cookieArray.length; i++) {
				var cookie = cookieArray[i].trim();
				if (cookie.indexOf(cookieName) === 0) {
					return cookie.substring(cookieName.length, cookie.length);
				}
			}
			return null;
		}

		//세션에 아이디 담기
		var sessionID = getCookie("sessionID");
		
		if(sessionID !=null){
			alert('로그인중에는 회원가입 페이지에 접근 할 수 없습니다 들어가고 싶다면 로그아웃 하세요 ^^')
			history.back();
		}
		
		
		$("#memberJoinBtn").hide();
		$("#pwdPtag").hide();

		// 회원 아이디 중복체크
		$('#idChk').on('click', function() {
			let loginData = {
				"user_id" : $("#user_id").val()
			};
			$.ajax({ //jquery ajax
				type : "post", //post방식으로 가져오기 (get 요청시 get 기재 / default get)
				url : "/Board/api/idChk", //서버에 요청할 경로(컨트롤러 매핑주소)
				data : loginData, // vo 객체를 JSON문자열로 변환 (클라이언트 -> 서버)
				success : function(result) { //요청 성공시 실행될 영역 (서버(컨트롤러)에서 보낸 데이터가 result에 담김)
					console.log("통신성공");
					console.log(result);
					
					if(result == 0){ // 아이디 중복 없음
						alert('가입 가능한 아이디 입니다. 아이디태그가 readonly가 됩니다.');
						$("#memberJoinBtn").show();
						$("#pwdPtag").show();
						$('#idChk').hide();
						$("#user_id").prop("readonly", true);
					}else{ // 아이디 중복
						alert('중복된 아이디 입니다.');
					}
					
				},
				error : function(xhr) { //요청 실패시 실행될 영역
					console.log("통신에러");
					console.log(xhr.status);
				}
			});
		});

		// 회원가입 버튼 클릭
		$("#memberJoinBtn").on("click", function() {
			let loginData = {
				"user_id" : $("#user_id").val(),
				"user_pwd" : $("#user_pwd").val()
			};
			$.ajax({ //jquery ajax
				type : "post", //post방식으로 가져오기 (get 요청시 get 기재 / default get)
				url : "/Board/api/join", //서버에 요청할 경로(컨트롤러 매핑주소)
				data : loginData, // vo 객체를 JSON문자열로 변환 (클라이언트 -> 서버)
				success : function(result) { //요청 성공시 실행될 영역 (서버(컨트롤러)에서 보낸 데이터가 result에 담김)
					console.log("통신성공");
					console.log(result);
					window.location.href = "login.jsp";
				},
				error : function(xhr) { //요청 실패시 실행될 영역
					console.log("통신에러");
					console.log(xhr.status);
				}
			});
		});
	});
</script>
</head>
<body>
	<h1>회원가입 페이지</h1>
	ID:
	<input type="text" id="user_id">
	<br> 
	<p id="pwdPtag">
	PWD:
	<input type="password" id="user_pwd">
	</p>
	<br>
	<input type="button" id="memberJoinBtn" value="가입하기">
	<input type="button" id="idChk" value="아이디 중복체크 ">
	<button type="button" onclick="window.location='main.jsp'">메인으로</button>
</body>
</html>