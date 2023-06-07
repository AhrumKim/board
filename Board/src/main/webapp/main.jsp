<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body {
		margin: 10px;
	}

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
	
	tr > td {
		text-align: center;
	}
	
	.postTitle > a {
		color: black;
		text-decoration-line: none;
	}
	
	.writeBoardbtn {
		margin: 0px 0px 10px;
	}
	
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function(){
		console.log('시작');
		$("#boardTable tbody").empty();
		getBoardList();
		//쿠키 삭제
		function deleteCookie(name) {
		  document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
		}
		
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
		console.log(sessionID);
		
		//로그인 여부 파악
		if(sessionID === null){
			$("#logoutbtn").hide();
			alert("게시글을 보려면 로그인이 필요합니다. ^^");
			window.location.href = "/Board/login.jsp";
		} else {
			$("#loginbtn").hide();
			getBoardList();
		}
			
		// 게시글들 가져오는 함수
		function getBoardList(){
			$.ajax({ //jquery ajax
				type : "post", //post방식으로 가져오기 (get 요청시 get 기재 / default get)
				url : "/Board/board/getBoardList", //서버에 요청할 경로(컨트롤러 매핑주소)
				success : function(result) { //요청 성공시 실행될 영역 (서버(컨트롤러)에서 보낸 데이터가 result에 담김)
					console.log("통신성공");
					console.log(result);
					console.log(JSON.stringify(result));
					
					$("#boardTable tbody").empty();
					
					let boardlist = result;
					
					for (var i = 0; i < boardlist.length; i++) {
						  var board = boardlist[i];
						  
						  var postId = board.postId;
						  var postTitle = board.postTitle;
						  var postContent = board.postContent;
						  var createdAt = board.createdAt;
						  var author = board.author;
						  
						  let row = "<tr class'row''>";
							row += "<td class='postId'>" + postId + "</td>";
							row += "<td class='postTitle'><a href='/Board/board/getOneBoard?postId=" + postId + "'>" + postTitle + "</a></td>"
							/* row += "<td class='postContent'>" + postContent + "</td>"; */
							row += "<td class='createdAt'>" + createdAt + "</td>";
							row += "<td class='author'>" + author + "</td>";
/* 							row += "<td class='delete'>X</td>";
 */							row += "</tr>";
							
							$("#boardTable tbody").append(row);
						}
				},
				error : function() { //요청 실패시 실행될 영역
					console.log("통신에러");
				}
			});
		}
		
		$("#logoutbtn").on("click", function(){
			deleteCookie("sessionID");
			//페이지 재로딩
			location.reload();
		});
		
		$("#loginbtn").on("click", function(){
			window.location.href = "login.jsp";
		});
		
		$("#boardTable").on("click", "#boardTable .row", function(){
			let postId = $(this).find(".postId").val();
			console.log(postId);
		});
	});
</script>
</head>
<body>
	<h1>메인페이지</h1>
	<input type="button" id="logoutbtn" value="로그아웃">
	<input type="button" id="loginbtn" value="로그인">
	<br>
	<hr>
	<h1>게시글 리스트</h1><button type="button" class="writeBoardbtn" onclick="window.location.href='/Board/insertBoard.jsp'">글 작성</button>
	<table id="boardTable">
		<thead>
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<!-- <td>내용</td> -->
				<td>작성일</td>
				<td>작성자</td>
				<!-- <td></td> -->
			</tr>
		</thead>
		<tbody></tbody>
	</table>
</body>
</html>