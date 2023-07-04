<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h1>회원가입</h1>
	<form action="${pageContext.request.contextPath}/AddMemberController" method="post" id="singinForm">
		<p>아이디</p>
		<p><span id="idMsg" class="msg"></span></p>
		<input type="text" name="memberId" placeholder="아이디" id="id">
		<br>
		<p>비밀번호</p>
		<p><span id="pwMsg" class="msg"></span></p>
		<input type="password" name="memberPw" placeholder="비밀번호" id="pw">
		<br><span id="clickMsg" class="msg"></span>
		<br>
		<button type="submit" id="signinBtn">회원가입</button>
	</form>
</body>
</html>