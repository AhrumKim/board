<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>${sessionScope.id} 님, 방갑습니다.</div>

<c:choose>
	<c:when test="${sessionScope.id == 'admin'}"> 
		<a href="memberlist.do">멤버목록보기</a>
	</c:when>
</c:choose>