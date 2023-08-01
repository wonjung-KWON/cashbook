<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>cashbook</title>

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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function() {
		//시작하자마자 숨기기
		$('#myElement').toggle();
	});
	$(document).ready(function() {
		// 숨기기/보이기 버튼 클릭 시 실행할 코드
		$('#toggleButton').click(function() {
			// #myElement 요소의 숨김/보임 상태를 토글합니다.
			$('#byElement').toggle();
			$('#myElement').toggle();
			$('#toggleButton').toggle();

		});
		$('#toggle2Button').click(function() {
			// #myElement 요소의 숨김/보임 상태를 토글합니다.
			$('#myElement').toggle();
			$('#byElement').toggle();
			$('#toggleButton').toggle();
		});
	});
</script>
</head>
<style>
td {
	height: 60px;
}
</style>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="${pageContext.request.contextPath}/on/home">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">
					가계부<sup>${loginMember}</sup>
				</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="${pageContext.request.contextPath}/on/home"> <i
					class="fas fa-fw fa-tachometer-alt"></i> <span>Home</span></a></li>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-fw fa-folder"></i> <span>회원정보</span>
			</a>
				<div id="collapsePages" class="collapse"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">회원관련</h6>
						<c:if test="${loginMember == null}">
							<a class="collapse-item"
								href="${pageContext.request.contextPath}/off/login">로그인</a>
							<a class="collapse-item"
								href="${pageContext.request.contextPath}/off/AddMemberController">회원가입</a>
						</c:if>
						<a class="collapse-item"
							href="${pageContext.request.contextPath}/on/modifyMember">회원정보수정</a>
					</div>
				</div></li>
			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="${pageContext.request.contextPath}/on/addCashbook"> <i
					class="fas fa-fw fa-tachometer-alt"></i> <span>가계부 작성하기</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>


		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>


					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">${loginMember}</span>
								<img class="img-profile rounded-circle"
								src="${pageContext.request.contextPath}/temp/img/undraw_profile.svg">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item"
									href="${pageContext.request.contextPath}/on/memberOne"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> 프로필 정보
								</a> <a class="dropdown-item"
									href="${pageContext.request.contextPath}/on/modifyMember">
									<i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> 회원
									설정
								</a>
								<div class="dropdown-divider"></div>
								<c:if test="${memberId != null || memberId != ''}">
									<a class="dropdown-item"
										href="${pageContext.request.contextPath}/on/logout"
										data-toggle="modal" data-target="#logoutModal"> <i
										class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
										Logout
									</a>
								</c:if>

							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">회원정보</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<h1>${loginMember}</h1>
								<table class="table table-bordered">
									<tr>
										<td>아이디</td>
										<td>${member.memberId}</td>
									</tr>
									<tr>
										<td>패스워드</td>
										<td>
											<div id="byElement">
												********
												<button id="toggleButton" type="button" class="btn btn-dark">비밀번호
													변경</button>
											</div>
											<div id="myElement">
												<form
													action="${pageContext.request.contextPath}/on/modifyMember"
													method="post" id="pwForm">
													<span>현재 비밀번호 :</span> <input type="password"
														name="checkPw" required="required"><br>
													<br> <span>변경할 비밀번호 :</span> <input type="password"
														name="memberPw" required="required"><br>
													<br> <span>변경할 비밀번호 확인 :</span> <input type="password"
														name="ckPw" required="required"><br>
													<br>

													<button class="btn btn-dark" type="button"
														id="toggle2Button">취소</button>
													<button class="btn btn-dark" type="submit">완료</button>
												</form>
											</div>

										</td>
									</tr>
									<tr>
										<td>회원이름</td>
										<td>${member.memberName}</td>
									</tr>
									<tr>
										<td>전화번호</td>
										<td>${member.memberPhone}</td>
									</tr>
									<tr>
										<td>생성일</td>
										<td>${member.createdate}</td>
									</tr>
									<tr>
										<td>수정일</td>
										<td>${member.updatedate}</td>
									</tr>
								</table>
								<a
									href="${pageContext.request.contextPath}/on/RemoveMemberController">회원탈퇴</a>
							</div>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; jsd12541 2023</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">모든 일정을 작성하셨나요?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">로그아웃 하시겠습니까?</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/on/logout">Logout</a>
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

	<!-- Page level plugins -->
	<script
		src="${pageContext.request.contextPath}/temp/vendor/chart.js/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<script
		src="${pageContext.request.contextPath}/temp/js/demo/chart-area-demo.js"></script>
	<script
		src="${pageContext.request.contextPath}/temp/js/demo/chart-pie-demo.js"></script>

</body>

</html>