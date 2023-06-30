<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원탈퇴</h1>
	<form action="${pageContext.request.contextPath}/RemoveMemberController" method="post">
		비밀번호 입력 :<input type="password" name="memberPw" placeholder="비밀번호">
		<button type="submit">탈퇴</button>
	</form>
	
</body>
</html>