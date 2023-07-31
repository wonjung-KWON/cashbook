<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>login.jsp</title>
    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/temp/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/temp/css/sb-admin-2.min.css" rel="stylesheet">
	<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
    <script>
			$(document).ready(function(){
				// 로그인 클릭시 실행
				$('#loginBtn').click(function(){
					if($('#loginId').val().length == 0) {
						console.log($('#loginId').val());
						$('#loginIdMsg').text('아이디를 입력해주세요');
						return;
					} else {
						$('#loginIdMsg').text('');
					}
					
					if($('#loginPw').val().length == 0) {
						console.log($('#loginPw').val());
						$('#loginPwMsg').text('비밀번호를 입력해주세요');
						return;
					} else {
						$('#loginPwMsg').text('');
					}
					
					$('#loginForm').submit();
				});
				<c:if test="${msg != null}">
					alert('${msg}')
				</c:if>
			});
		</script>
</head>
<body class="bg-gradient-primary">
    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-login-image"style="background-image: url('${pageContext.request.contextPath}/temp/img/cash.png')"></div>
                            <div class="col-lg-6">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">저희 가계부에 오신걸 환영합니다!</h1>
                                    </div>
                                    <form class="user" id="loginForm" method="post" action="${pageContext.request.contextPath}/off/login">
                                        <div class="form-group">
                                            <input type="text" class="form-control form-control-user"
                                                id="loginId" name="memberId"  aria-describedby="emailHelp"
                                                placeholder="Enter Email Address..." value="admin">
                                                <span id="loginIdMsg" class="msg"></span>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user"
                                                 name="memberPw" value="1234" id="loginPw" placeholder="Password">
                                                <span id="loginPwMsg" class="msg"></span>
                                        </div>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" class="custom-control-input" id="customCheck" name="idSave" value="y">
                                                <label class="custom-control-label" for="customCheck">Remember
                                                    Me</label>
                                            </div>
                                        </div>
                                        <button type="button" class="btn btn-primary btn-user btn-block" id="loginBtn">
                                            Login
                                        </button>
                                        <hr>
                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="${pageContext.request.contextPath}/off/AddMemberController">회원가입</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath}/temp/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/temp/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/temp/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/temp/js/sb-admin-2.min.js"></script>

</body>

</html>