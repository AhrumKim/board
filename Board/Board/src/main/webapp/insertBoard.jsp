<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid #ddd;
	padding: 1px;
	text-align: left;
}

.delete {
	cursor: pointer;
}
</style>
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

		// 페이지 로딩시 쿠키에 있는 아이디값 작성자 태그에 넣기
		var sessionID = getCookie("sessionID");
		$('#author').text(sessionID);

		//작성완료 클릭시 실행
		$('#insertComplete').on('click', function() {

			let postTitle = $('#postTitle').val();
			let author = getCookie("sessionID");
			let postContent = $('#postContent').val();
			
			let resultData = {
				"postTitle" : $('#postTitle').val(),
				"author" : getCookie("sessionID"),
				"postContent":$('#postContent').val()
			};
			//window.location.href = "/Board/board/insertBoard?postTitle=" + postTitle + "&author=" + author + "&postContent=" + postContent;
			//window.location.href = "/Board/board/insertBoard?resultData=" + resultData; 객체로 보낼시 에러
			$.ajax({ //jquery ajax
				type : "post", //post방식으로 가져오기 (get 요청시 get 기재 / default get)
				url : "/Board/api/insertBoard", //서버에 요청할 경로(컨트롤러 매핑주소)
				data : resultData, // vo 객체를 JSON문자열로 변환 (클라이언트 -> 서버)
				success : function(result) { //요청 성공시 실행될 영역 (서버(컨트롤러)에서 보낸 데이터가 result에 담김)
					console.log("통신성공");
					//console.log(result);
					alert('게시글 작성 성공하여 작성한 게시글 상세페이지로 이동');
					window.location.href = "/Board/board/getOneBoard?postId="+result;
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
	<h1>글작성 페이지</h1>
	<table>
		<tr>
			<td>글 제목 :</td>
			<td><textarea id="postTitle" rows="1" cols="60"></textarea></td>
		</tr>
		<tr>
			<td>작성자 :</td>
			<td id="author" colspan="3"></td>
		</tr>
		<tr>
			<td colspan="3"><textarea id="postContent" rows="10" cols="100"></textarea></td>
		</tr>
	</table>
	<input type="button" id="insertComplete" value="작성완료">
	<input type="button" value="뒤로가기"
		onclick="window.location.href='/Board/main.jsp'">

</body>
</html>