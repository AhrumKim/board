<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<title>Hello World</title>
</head>
<style>
    .footer {
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        background-color: #f8f9fa;
        padding: 20px;
    }
</style>
<body>
		<div class="row header border">
			<%@ include file="/WEB-INF/views/header.jsp"%>
		</div>
		<div class="row main roomy-50 text-center">
			<div class="col-md-2 border">
				<%@ include file="/WEB-INF/views/left.jsp"%>
			</div>
			<div class="col-md-10 border p-3">
				<c:set var="pagePath" value="${requestScope.pagePath}"/>
				<c:choose>
					<c:when test="${empty pagePath}">
						<c:choose>
							<c:when test="${empty sessionScope.id}">
								<jsp:include page="/WEB-INF/views/loginForm.jsp"/>
							</c:when>
							<c:otherwise>
								<jsp:include page="/WEB-INF/views/loginOk.jsp"/>
							</c:otherwise>
						</c:choose>												
					</c:when>					
					<c:otherwise>
						<jsp:include page="${pagePath}"/>
					</c:otherwise>					
				</c:choose>				
			</div>
		</div>
		<div class ="footer">
		<%@ include file="/WEB-INF/views/footer.jsp"%>
		</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous">
	</script>
	<script type="text/javascript">
		window.onload = function() {
			const msg = "${requestScope.msg}";
	 		if(msg!=''){
				alert(msg);
			} 
		};
	</script>
</body>
</html>