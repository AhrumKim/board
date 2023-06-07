<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function(){
		$("#loginbtn").on("click", function(){
			let loginData = {
					"user_id": $("#user_id").val(),
					"user_pwd": $("#user_pwd").val()
				};
				$.ajax({ //jquery ajax
					type:"post", //post방식으로 가져오기 (get 요청시 get 기재 / default get)
					url:"/Board/api/login", //서버에 요청할 경로(컨트롤러 매핑주소)
					//contentType: "application/json; charset=utf-8", // 서버로 보낼 데이터의 타입 (json), 인코딩 타입(UTF-8)
					dataType: "text", //서버로부터 받은 데이터(result)는 자동으로 JavaScript 객체로 변환됨 종류: xml ,json,script,html	
					data : loginData, // vo 객체를 JSON문자열로 변환 (클라이언트 -> 서버)
					success: function(result){   //요청 성공시 실행될 영역 (서버(컨트롤러)에서 보낸 데이터가 result에 담김)
						console.log("통신성공");
						console.log(result);
						//window.location.href = "login.jsp";
						//console.log(typeof(result));  String
						if(result === '1'){
							alert("로그인 성공");
							// 사용 예시
							setCookie("sessionID", $("#user_id").val(), 1);
							window.location.href = "main.jsp";
						} else if(result === '2'){
							alert("비밀번호 불일치! 로그인 실패");
						} else if(result === '3'){
							alert("아이디, 비밀번호 모두 불일치! 로그인 실패");
						} else {
							alert("알수없는 오류");
						}
					},
					error:function(xhr){	   //요청 실패시 실행될 영역
						console.log("통신에러");
						console.log(xhr.status);
					}
				});
		});
		// 쿠키 설정
		function setCookie(name, value, days) {
		  var expires = "";
		  if (days) {
		    var date = new Date();
		    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		    expires = "; expires=" + date.toUTCString();
		  }
		  document.cookie = name + "=" + value + expires + "; path=/";
		}
		
		
	});
</script>
</head>
<body>
	<h1>로그인 페이지입니다</h1>
	ID: <input type="text" id="user_id"><br>
	PWD: <input type="password" id="user_pwd"><br>
	<input type="button" id="loginbtn" value="로그인">
</body>
</html>