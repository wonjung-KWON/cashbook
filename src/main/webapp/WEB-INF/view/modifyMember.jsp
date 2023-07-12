<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#memberPw').blur(function(){
			if($('#memberPw').val().length < 8){
				$('#memberPwMsg').text('비밀번호는 8자 이상 가능');
			}else{
				$('#memberPwMsg').text('');
			}
		});
		$('#ckPw').blur(function(){
			if($('#memberPw').val() != $('#ckPw').val()){
				$('#ckPwMsg').text('비밀번호가 같지 않습니다.');
			}else{
				$('#ckPwMsg').text('');
			}
		});
		
		$('#btn').click(function(){
			if($('#checkPw').val().length < 1){
				alert('변경전 비밀번호를 입력바랍니다');
				return;
			} else if($('#memberPw').val().length < 1){
				alert('변경할 비밀번호를 입력바랍니다');
				return;
			} else if($('#ckPw').val().length < 1){
				alert('변경 비밀번호 확인 입력바랍니다.');
				return;
			}else{
				$('#modiForm').submit();
			}
		});
	});
</script>
</head>
<body>
<h1>회원정보수정</h1>
	<form action="${pageContext.request.contextPath}/on/modifyMember" method="post" id="modiForm">
		변경전 비밀번호 입력 :<input id="checkPw" type="password" name="checkPw" placeholder="변경전 비밀번호"><br>
		변경할 비밀번호 입력 :<input id="memberPw" type="password" name="memberPw" placeholder="변경 비밀번호">
		<p><span id="memberPwMsg" ></span></p>
		변경할 비밀번호 재입력 :<input id="ckPw" type="password" name="ckPw" placeholder="재확인 비밀번호">
		<p><span id=ckPwMsg ></span></p>
		<button type="button" id="btn">변경</button>
	</form>
</body>
</html>