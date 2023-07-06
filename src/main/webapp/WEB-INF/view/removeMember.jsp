<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('#btn').click(function(){
		if($('#checkPw').val().length < 1){
			alert('비밀번호를 입력바랍니다');
			return;
		}else{
			$('#removeForm').submit();
		}
	});
});
</script>
</head>
<body>
	<h1>회원탈퇴</h1>
	<form action="${pageContext.request.contextPath}/on/RemoveMemberController" method="post" id="removeForm">
		비밀번호 입력 :<input type="password" name="memberPw" id="checkPw" placeholder="비밀번호">
		<button type="button" id="btn">탈퇴</button>
	</form>
	
</body>
</html>