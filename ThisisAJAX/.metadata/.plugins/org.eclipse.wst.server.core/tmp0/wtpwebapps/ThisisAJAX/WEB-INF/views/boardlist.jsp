<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
a {
    color: inherit; /* 현재 요소의 부모 요소의 색상 상속 */
    text-decoration: none; /* 밑줄 제거 */
  }
</style>

<div class="table">
	<table class="table">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>날짜</th>
			<th>조회수</th>
			<th style="width: 10%;"></th>
		</tr>
		<c:set var="boardList" value="${requestScope.boardList}"></c:set>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.seq}</td>
				<td>${board.title}</td>
				<td>${board.content}</td>
				<td>${board.regdate}</td>
				<td>${board.hit}</td>
				<td><button class="btn btn-light rounded-pill px-3" type="button"><a href="boardRegister.board?seq=${board.seq}">상세보기</a></button></td>
			</tr>
		</c:forEach>
	</table>	
</div>