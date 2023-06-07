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
			<th>ID</th>
			<th>IP</th>
			<th style="width: 10%;"></th>
			<th style="width: 10%;"></th>
		</tr>
		<c:set var="memberList" value="${requestScope.memberList}"></c:set>
		<c:forEach var="member" items="${memberList}">
			<tr>
				<td>${member.id}</td>
				<td>${member.ip}</td>
				<td><button class="btn btn-light rounded-pill px-3" type="button"><a href="memberRegister.do?id=${member.id}">수정</a></button></td>
				<td><button class="btn btn-secondary rounded-pill px-3" type="button"><a href="memberdelete.do?id=${member.id}">삭제</a></button></td>
			</tr>
		</c:forEach>
		<!-- 검색존 -->
		<tr>
			<td></td>
			<td></td>
			<form action="${pageContext.request.contextPath}/memberlist.do" method="post">
				<td class="col-md-2"><input type="text" name="searchName" class="form-control" placeholder="이름을 검색해주세요"></td>
				<td class="col-md-2"><button type="submit" class="btn btn-dark rounded-pill px-3" type="button">검색</button></td>
			</form>
			
		</tr>
	</table>
	
</div>