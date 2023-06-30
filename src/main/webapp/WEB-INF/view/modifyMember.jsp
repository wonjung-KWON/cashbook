<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원정보수정</h1>
	<form action="${pageContext.request.contextPath}/modifyMember" method="post">
		변경전 비밀번호 입력 :<input type="password" name="checkPw" placeholder="변경전 비밀번호">
		변경할 비밀번호 입력 :<input type="password" name="memberPw" placeholder="변경 비밀번호">
		변경할 비밀번호 재입력 :<input type="password" name="ckPw" placeholder="재확인 비밀번호">
		<button type="submit">변경</button>
	</form>
</body>
</html>