<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>addMember.jsp</title>

<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/temp/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath}/temp/css/sb-admin-2.min.css"
	rel="stylesheet">
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
	crossorigin="anonymous"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		// 회원가입버튼 클릭시 실행
		$('#addMemberBtn').click(function() {
			if ($('#addName').val().length == 0) {
				console.log($('#addName').val());
				$('#addNameMsg').text('이름을 입력해주세요');
				return;
			} else {
				$('#addNameMsg').text('');
			}
			if ($('#addPhone').val().length == 0) {
				console.log($('#addPhone').val());
				$('#addPhoneMsg').text('전화번호를 입력해주세요');
				return;
			} else {
				$('#addPhoneMsg').text('');
			}
			if ($('#addId').val().length == 0) {
				console.log($('#addId').val());
				$('#addIdMsg').text('아이디를 입력해주세요');
				return;
			} else {
				$('#addIdMsg').text('');
			}

			if ($('#addPw').val().length < 4) {
				console.log($('#addPw').val());
				$('#addPwMsg').text('4자 이상 비밀번호를 입력해주세요');
				return;
			} else {
				$('#addPwMsg').text('');
			}
			if ($('#addPwCk').val().length < 4) {
				console.log($('#addPwCk').val());
				$('#addPwCkMsg').text('4자 이상 재확인 비밀번호를 입력해주세요');
				return;
			} else {
				$('#addPwCkMsg').text('');
			}
			if ($('#addPw').val() != $('#addPwCk').val()) {
				alert('비밀번호가 서로 다릅니다');
				return;
			}

			$('#addMemberForm').submit();
		});
	});
</script>
</head>

<body class="bg-gradient-primary">

	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"style="background-image: url('${pageContext.request.contextPath}/temp/img/cash.png')"></div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">회원가입 하고 나만의 가계부를 만드세요!</h1>
							</div>
							<form class="user" id="addMemberForm" method="post"
								action="${pageContext.request.contextPath}/off/addMember">
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="text" class="form-control form-control-user"
											id="addName" placeholder="이름"> <span id="addNameMsg"></span>
									</div>
									<div class="col-sm-6">
										<input type="tel" class="form-control form-control-user"
											id="addPhone" placeholder="전화번호"> <span
											id="addPhoneMsg"></span>
									</div>
								</div>
								<div class="form-group">
									<input type="text" class="form-control form-control-user"
										id="addId" placeholder="아이디"> <span id="addIdMsg"></span>
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="password" class="form-control form-control-user"
											id="addPw" placeholder="비밀번호"> <span id="addPwMsg"></span>
									</div>
									<div class="col-sm-6">
										<input type="password" class="form-control form-control-user"
											id="addPwCk" placeholder="비밀번호 재입력"> <span
											id="addPwCkMsg"></span>
									</div>
								</div>
								<button class="btn btn-primary btn-user btn-block"
									id="addMemberBtn" type="button">회원가입</button>
								<hr>
								<a href="${pageContext.request.contextPath}/off/login"
									class="btn btn-google btn-user btn-block"> 로그인 하기 </a> <a
									href="${pageContext.request.contextPath}/on/home"
									class="btn btn-facebook btn-user btn-block"> HOME </a>
							</form>
							<hr>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- Bootstrap core JavaScript-->
	<script
		src="${pageContext.request.contextPath}/temp/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/temp/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath}/temp/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath}/temp/js/sb-admin-2.min.js"></script>
</body>
</html>