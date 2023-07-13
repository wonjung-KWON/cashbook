<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<h1>회원가입</h1>
	<form action="${pageContext.request.contextPath}/off/AddMemberController" method="post">
		<p>아이디</p>
		<input type="text" name="memberId" placeholder="아이디" required="required">
		<p>비밀번호</p>
		<input type="password" name="memberPw" placeholder="비밀번호" required="required">
		<p>이름</p>
		<input type="text" name="memberName" required="required">
		<p>전화번호</p>
		<input type="tel" name="memberPhone" required="required">
		<br>
		<button type="submit" id="signinBtn">회원가입</button>
	</form>
</body>
</html>