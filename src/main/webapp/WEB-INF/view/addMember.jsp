<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		 let allCheck = false; 
		// id유효성 체크
	      $('#id').blur(function() {
	         if ($('#id').val().length < 4) {
	            $('#idMsg').text('ID는 4자이상이어야 합니다');
	            return;
	         } else {
	            console.log($('#id').val()); 
	            // console.log($(this).val()); // this뒤에 jquery메서드를 참조하려면 $(this)후...
	            $('#idMsg').text('');
	         }
	      });
	      // pw유효성 체크
	      $('#pw').blur(function(){
	         if ($('#pw').val().length < 4) {
	            $('#pwMsg').text('PW는 4자이상이어야 합니다');
	            $('#pw').val('');
	            return;
	         } else {
	            $('#pwMsg').text('');	 
	            allCheck = true;
	         }
	      });
	      $('#signinBtn').click(function(){
	    	  if(allCheck == false) { 
		        	 alert('회원정보를 모두 입력하시길 바랍니다')
		            return;
		         }else {
		        	 $('#singinForm').submit();
		        	 }
		         }
	    	  });
			)};
	</script>
</head>
<body>
	<h1>회원가입</h1>
	<form action="${pageContext.request.contextPath}/AddMemberController" method="post" id="singinForm">
		<p>아이디</p>
		<p><span id="idMsg" class="msg"></span></p>
		<input type="text" name="memberId" placeholder="아이디" id="id">
		<p>비밀번호</p>
		<p><span id="pwMsg" class="msg"></span></p>
		<input type="password" name="memberPw" placeholder="비밀번호" id="pw">
		<br><span id="clickMsg" class="msg"></span>
		<button type="button" id="signinBtn">로그인</button>
	</form>
</body>
</html>