<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
   <header class="p-3 text-bg-dark">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
        

        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
          <li><a href="main.jsp" class="nav-link px-3 text-white">Home</a></li>
          <c:choose>
				<c:when test="${sessionScope.id=='admin'}">
		          <li><a href="memberlist.do" class="nav-link px-3 text-white">Members</a></li>
		         </c:when>
			</c:choose>
        </ul>

        <div class="text-end">
			<c:choose>
				<c:when test="${empty sessionScope.id}">
		          <button type="button" class="btn btn-outline-light me-2" onclick = "location.href='login.do'">Login</button>
		          <button type="button" class="btn btn-warning" onclick = "location.href='memberRegister.do'">Sign-up</button>
		         </c:when>
				<c:otherwise>
					[${sessionScope.id}]님 안녕하세요^^ &nbsp
					<button type="button" class="btn btn-outline-light me-2" onclick = "location.href='logout.do'">Logout</button>
				</c:otherwise>
			</c:choose>
        </div>
      </div>
    </div>
  </header>
	