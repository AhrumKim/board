<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
	<div class="row justify-content-center">
		<div class="col-8">
			<div class="fs-2 text-center mt-4 mb-4">5조 게시판</div>
		    <table class="table table-bordered">
		        <thead>
		            <tr>
		                <th scope="col">글번호</th>
		                <th scope="col">제목</th>
		                <th scope="col">내용</th>
		                <th scope="col">조회수</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:forEach var="post" items="${postList}">
		                <tr>
		                    <th scope="row">${post.no}</th>
		                    <td><a href="${pageContext.request.contextPath}/board?id=${post.no}">${post.title}</a></td>
		                    <td>${post.contents}</td>
		                    <td>${post.hit}</td>
		                </tr>
		            </c:forEach>
		        </tbody>
		    </table>
	    </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
