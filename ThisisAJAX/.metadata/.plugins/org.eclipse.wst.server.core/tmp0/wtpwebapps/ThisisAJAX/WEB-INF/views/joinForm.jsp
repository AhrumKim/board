<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
/* @import url('https://fonts.googleapis.com/css2?family=Dongle:wght@300&display=swap');

body {
    font-family: 'Dongle', sans-serif;
    font-size: 2.75rem;
} */
</style>
<form action="register.do" method="post">
	<table class="container table table-borderless">
		<thead class="text-center">
				<h3>회원가입</h3>
		</thead>
		<tr class="row">
			<td class="col-md-4"></td>
			<td class="col-md-4">
				<div class="form-floating">
				  <input type="text" class="form-control" id="id" name="id" value="${requestScope.member.id}" required>
				  <label for="floatingInput">ID</label>
				</div>
			</td>
			<td class="col-md-4"></td>
		</tr>
		<tr class="row">
			<td class="col-md-4"></td>
			<td class="col-md-4">
				<div class="form-floating">
				  <input type="password" class="form-control" id="pwd" name="pwd" value="${requestScope.member.pwd}" required>
				  <label for="floatingInput">Password</label>
				</div>
			</td>
			<td class="col-md-4"></td>
		</tr>
		<tr class="row">
			<td class="col-md-4"></td>
			<td class="col-md-4">
				<div class="form-floating">
				  <input type="text" class="form-control" id="name" name="name" value="${requestScope.member.name}" required>
				  <label for="floatingInput">Name</label>
				</div>
			</td>
			<td class="col-md-4"></td>
		</tr>
		<tr class="row">
			<td class="col-md-4"></td>
			<td class="col-md-4">
				<div class="form-floating">
				  <input type="number" min=1 class="form-control" id="age" name="age" value="${requestScope.member.age}">
				  <label for="floatingInput">Age</label>
				</div>				
			</td>
			<td class="col-md-4"></td>
		</tr>
		<tr class="row">
			<td class="col-md-4"></td>
			<td class="col-md-4">
				Gender : <input type="radio" name="gender" id="women" value="여" checked>여자 
				<input type="radio" name="gender" id="man" value="남">남자
			</td>
			<td class="col-md-4"></td>
		</tr>
		<tr class="row">
			<td class="col-md-4"></td>
			<td class="col-md-4">
				<div class="form-floating">
				  <input type="email" class="form-control" id="email" name="email" value="${requestScope.member.email}" onblur="validateEmail()">
				  <label for="floatingInput">Email</label>
				</div>	
			</td>
			<td class="col-md-4"></td>		
		</tr>
		<tr class="row">
			<td class="col-md-4"></td>
			<td class="col-md-4" id="warningMsg"></td>
			<td class="col-md-4 mb-3"></td>		
		</tr>
	</table>
	<div class="mb-4">
		<button type="submit" id="submit" class="btn btn-success">제출</button>
		<button type="reset" class="btn btn-danger">취소</button>		
	</div>
</form>
<button class="btn btn-warning" onclick = "location.href='login.do'">로그인</button>
<script>
	const mode = "${requestScope.mode}";	
	console.log(mode);
	if(mode == 'modify'){//수정모드
		//회원정보 세팅
		let form = document.querySelectorAll('form')[0];
		const gender = "${requestScope.member.gender.trim()}";		
		form.action = "${pageContext.request.contextPath}/memberUpdate.do";
		
		if(gender == "남"){
			document.getElementById("man").checked = true;
			document.getElementById("women").checked = false;
		}

		//버튼명 바꾸기
		let submitButton = document.querySelectorAll('button[type="submit"]')[0];
		submitButton.value = '수정하기';

		//input태그 막기
		let inputId = document.getElementById("id");
		inputId.readOnly = true;
		inputId.style.backgroundColor = "grey";
		let inputPwd = document.getElementById("pwd");
		inputPwd.readOnly = true;
		inputPwd.style.backgroundColor = "grey";
		let inputGender = document.getElementsByName("gender");
		inputGender[0].disabled = true;
		inputGender[1].disabled = true;
		
		document.getElementsByTagName("h3")[0].innerText = "회원정보수정"
		
	}
	
	function validateEmail() {
	      let email = document.getElementById("email").value;
	      let emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	      let msg =  document.getElementById("warningMsg");
	      if (!emailPattern.test(email) && email != '') {	       
		    msg.innerText = "email 양식이 맞지 않습니다."
		    msg.style.color = "red";
		    document.getElementById("submit").disabled = true;
	      } else {
    	  	msg.innerText = "";
    	  	document.getElementById("submit").disabled = false;
	      }
    }
	
</script>