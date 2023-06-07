<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="${pageContext.request.contextPath}/loginok.do" method="post">
	<table class="table table-borderless">
		<thead class="text-center">
				<h3>로그인</h3>
		</thead>
		<tbody>
			<tr>
				<td class="col-md-4"></td>
				<td class="col-md-4">
					<div class="form-floating">
					  <input type="text" class="form-control" id="id" name="id" placeholder="name@example.com" required>
					  <label for="floatingInput">ID</label>
					</div>
				</td>
				<td class="col-md-4"></td>
			</tr>
		<tr>
			<td class="col-md-4"></td>
			<td class="col-md-4">
				<div class="form-floating mb-3">
				  <input type="password" class="form-control" id="pwd" name="pwd" placeholder="name@example.com" required>
				  <label for="floatingInput">Password</label>
				</div>
			</td>
			<td class="col-md-4"></td>
		</tr>
	
		<tr>
			<td></td>
			<td>
				<button type="submit" class="btn btn-success">로그인</button>
				<button type="reset" class="btn btn-danger">취소</button>
				
			</td>

		</tr>
		</tbody>
	</table>
</form>
<button class="btn btn-warning" onclick = "location.href='memberRegister.do'">회원가입</button>