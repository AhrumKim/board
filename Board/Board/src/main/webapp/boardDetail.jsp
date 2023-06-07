<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	window.onload = function() {
		$("#updateComplete").hide();
		$("#updateCancel").hide();

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
		console.log("세션아이디");
		console.log(sessionID);

		let author = document.getElementById("author").textContent;
		//console.log(author);
		if (author === sessionID) {
			//글쓴이가 맞다면
			$(".btn").show();
		} else {
			$(".btn").hide();
		}

		$("#updatebtn").on("click", function() {
			$("#updateComplete").show();
			$("#updateCancel").show();
			$("#postContent").prop("readOnly", false);
			$("#postTitle").prop("readOnly", false);
		});

		$('#updateComplete').on('click', function() {
			let postId = $("#postId").val();
			let postTitle = $('#postTitle').val();
			let postContent = $('#postContent').val();

			console.log(postId);
			console.log(postTitle);
			console.log(postContent);
			let resultData = {
				"postId" : $("#postId").text(),
				"postTitle" : $("#postTitle").val(),
				"postContent" : $("#postContent").val()
			}
			$.ajax({ //jquery ajax
				type : "post", //post방식으로 가져오기 (get 요청시 get 기재 / default get)
				url : "/Board/api/updateOneBoard", //서버에 요청할 경로(컨트롤러 매핑주소)
				data : resultData, 
				success : function(result) { //요청 성공시 실행될 영역 (서버(컨트롤러)에서 보낸 데이터가 result에 담김)
					console.log("통신성공");
					console.log(result);
					$("#updateComplete").hide();
					$("#updateCancel").hide();
					$("#postContent").prop("readonly", true);
					$("#postTitle").prop("readonly", true);
				},
				error : function(xhr) { //요청 실패시 실행될 영역
					console.log("통신에러");
					console.log(xhr.status + "에러코드");
				}
			});

		});

		$("#updateCancel").on("click", function() {
			location.reload();
		});
		
		$("#deletebtn").on("click", function() {
			let postId = $("#postId").text();

			let resultData = {
				"postId" : postId
			};
			//console.log(postId);
			$.ajax({ //jquery ajax
				type : "post", //post방식으로 가져오기 (get 요청시 get 기재 / default get)
				url : "/Board/api/deleteOneBoard", //서버에 요청할 경로(컨트롤러 매핑주소)
				//contentType: "application/json; charset=utf-8", // 서버로 보낼 데이터의 타입 (json), 인코딩 타입(UTF-8)
				//dataType: "json", //서버로부터 받은 데이터(result)는 자동으로 JavaScript 객체로 변환됨 종류: xml ,json,script,html	
				data : resultData, // vo 객체를 JSON문자열로 변환 (클라이언트 -> 서버)
				success : function(result) { //요청 성공시 실행될 영역 (서버(컨트롤러)에서 보낸 데이터가 result에 담김)
					console.log("통신성공");
					//console.log(result);
					window.location.href = "/Board/main.jsp";
				},
				error : function(xhr) { //요청 실패시 실행될 영역
					console.log("통신에러");
					console.log(xhr.status);
				}
			});
		});
	}
</script>
</head>
<body>
	<h1>게시글 상세 페이지</h1>

	<input type="button" id="updatebtn" class="btn" value="수정">
	<input type="button" id="deletebtn" class="btn" value="삭제">

	<c:set var="board" value="${oneBoard}" />
	<table>
		<tr>
			<td>글 고유 번호 : </td>
			<td id="postId">${board.postId}</td>
		</tr>
		<tr>	
			<td>글 제목 : </td>
			<td><textarea id="postTitle" rows="1" cols="60" readOnly>${board.postTitle}</textarea></td>
		</tr>
		<tr>
			<td>글 작성시간 : </td>		
			<td>${board.createdAt}</td>
		</tr>
		<tr>
			<td>작성자 : </td>		
			<td id="author" colspan="3">${board.author}</td>
		</tr>
		<tr>
			<td colspan="3"><textarea id="postContent" rows="10" cols="100"
					readOnly>${board.postContent}</textarea></td>
		</tr>
	</table>
	<input type="button" id="updateComplete" value="수정완료">
	<input type="button" id="updateCancel" value="취소">
	<input type="button" value="뒤로가기" onclick="window.location.href='/Board/main.jsp'">
	<hr>
	
	
	
	<!-- 댓글 작성 공간 -->
	<c:set var="map" value="${oneBoardComment}" />
	<script>
		console.log(typeof(map));
	</script>
	
	<c:set var="commentCount" value="${map[1]}" />
	<c:set var="commentList" value="${map[2]}" />
	<h1>가져온 데이터 : ${commentCount}</h1>
	
	<h3>작성 댓글 &nbsp;&nbsp;<span></span> 개</h3>
	<div>
		<textarea id="commentContent" rows="3" cols="100"></textarea><br>
		<input type="button" id="insertComment" value="댓글작성">
		<input type="button" id="insertComment" value="취소">
	</div>
	<hr>
	<!-- 댓글 영역    댓글 없으면 댓글이 없습니다-->
	<%-- <table>
		<c:forEach var="comment" items="${commentList}">
			<tr>
				<td>
					<div>
						<span></span>&nbsp;&nbsp;
						<span></span>
					</div>
					<div>
						
					</div>
				</td>
			</tr>
		</c:forEach>
	</table> --%>
</body>
</html>