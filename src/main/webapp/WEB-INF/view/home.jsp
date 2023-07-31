<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>cashbook</title>

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/temp/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/temp/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/on/home">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">가계부<sup>${loginMember}</sup></div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/on/calendar">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>달력보기</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                 이달의 가계
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                    <div style="text-align: center;  padding: 5px;">
					    <span style="color: white;">수입 지출</span>
					</div>

                    
                    <div class="bg-white py-2 px-4 collapse-inner rounded">
					    <h6 class="collapse-header">현재달</h6>
					    <table>
					        <tr>
					            <td>수입</td>
					            <td>
					                <c:set var="totalSumIncome" value="0" /> <!-- 수입 총 합을 저장할 변수를 초기화합니다 -->
					
					                <c:forEach var="c" items="${list}">
					                    <c:if test="${c.category == '수입'}">
					                        <c:set var="totalSumIncome" value="${totalSumIncome + c.price}" /> <!-- 합을 누적합니다 -->
					                    </c:if>
					                </c:forEach>
					
					                <span style="color: blue;">총 합: ${totalSumIncome}</span> <!-- 총 합을 표시합니다 -->
					            </td>
					        </tr>
					        <tr>
					            <td>지출</td>
					            <td>
					                <c:set var="totalSumExpense" value="0" /> <!-- 지출 총 합을 저장할 변수를 초기화합니다 -->
					
					                <c:forEach var="c" items="${list}">
					                    <c:if test="${c.category == '지출'}">
					                        <c:set var="totalSumExpense" value="${totalSumExpense + c.price}" /> <!-- 합을 누적합니다 -->
					                    </c:if>
					                </c:forEach>
					
					                <span style="color: red;">총 합: ${totalSumExpense}</span> <!-- 총 합을 표시합니다 -->
					            </td>
					        </tr>
					    </table>
					</div>

            </li>


            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                가계부
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                    aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>회원정보</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">회원관련</h6>
                        <c:if test="${loginMember == null}">
 	                       <a class="collapse-item" href="${pageContext.request.contextPath}/off/login">로그인</a>
    	                   <a class="collapse-item" href="${pageContext.request.contextPath}/off/AddMemberController">회원가입</a>
                        </c:if>
                        <a class="collapse-item" href="${pageContext.request.contextPath}/on/memberOne">회원정보</a>
                    </div>
                </div>
            </li>


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
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>


                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${loginMember}</span>
                                <img class="img-profile rounded-circle"
                                    src="${pageContext.request.contextPath}/temp/img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/on/memberOne">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    프로필 정보
                                </a>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/on/modifyMember">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    회원 설정
                                </a>
                                <div class="dropdown-divider"></div>
                                <c:if test="${memberId != null || memberId != ''}">
	                                <a class="dropdown-item" href="${pageContext.request.contextPath}/on/logout" data-toggle="modal" data-target="#logoutModal">
	                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
	                                    Logout
	                                </a>
                                </c:if>
                                
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">이번달 현황</h1>

                    </div>

                    <!-- Content Row -->
                    <div class="row">

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                이번달 최고 수입</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${maxMoney}원</div>
                                        </div>
                                       <div class="col-auto">
                                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                이번달 최고 지출</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${maxUnMoney}원</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">남은 자산
                                            </div>
                                            <div class="row no-gutters align-items-center">
                                                <div class="col-auto">
                                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${totalM}원</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Pending Requests Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-warning shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                마지막 수입</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">

                                            	<h6>${lastPrice} : ${lastDay}</h6>

                                            </div>
                                        </div>
                                       <div class="col-auto">
                                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Content Row -->

                    <div class="row">

                        <!-- Area Chart -->
                        <div class="col-xl-8 col-lg-7">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">이번달의 해쉬 태그 키워드 : 
	                                    <c:forEach var="m" items="${htList}">
											<a href="${pageContext.request.contextPath}/on/hashtagList?word=${m.word}" style="color: green; text-decoration: none;">${m.word}(${m.cnt})</a>
										</c:forEach>
									</h6>
                                </div>
                            </div>
                        </div>

                        <!-- Pie Chart -->
                        <div class="col-xl-4 col-lg-5">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">접속 현황</h6>
                                    <div>
										현재 접속자 : ${currentCounter} <!--  application.getAttribute("cuurentCounter") -->
									</div>
									<div>
										오늘 접속자 : ${counter} <!--  request.getAttribute("counter") --> 
									</div>
									<div>
										누적 접속자 : ${totalCounter} <!--  request.getAttribute("totalCounter") --> 
									</div>
                                </div>
                            </div>
                        </div>
                         <!-- Area Chart -->
                        <div class="col-xl-8 col-lg-7">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                	<table class="table">
	                                	<tr>
											<td style="color: red;">일</td>
											<td style="color: black;">월</td>
											<td style="color: black;">화</td>
											<td style="color: black;">수</td>
											<td style="color: black;">목</td>
											<td style="color: black;">금</td>
											<td style="color: blue;">토</td>
										</tr>
										<tr>
											<c:forEach var="i" begin="0" end="${totalCell-1}" step="1">
												<c:set var="d" value="${i-beginBlank+1}"/>
											
												<c:if test="${i != 0 && i%7 == 0}">
													</tr><tr>
												</c:if>
												
												<c:if test="${d<1}">
													<td>${preLastDate - beginBlank + i + 1}</td>
												</c:if>
												<c:if test="${d > lastDate}">
													<td>${i - lastDate - beginBlank + 1}</td>
												</c:if>
												
												<c:if test="${!(d<1 || d > lastDate)}">
													<td>
														<div>
														<c:if test="${today == d}">
															<a href="${pageContext.request.contextPath}/on/CashbookOne?targetYear=${todayYear}&targetMonth=${todayMonth}&targetDay=${d}" style=" color: #FFB2D9; text-decoration: none;">${d}</a>
														</c:if>
														<c:if test="${today != d}">
															<a href="${pageContext.request.contextPath}/on/CashbookOne?targetYear=${todayYear}&targetMonth=${todayMonth}&targetDay=${d}" style="color: #000000; text-decoration: none;">${d}</a>
														</c:if>
														</div>
													</td>
												</c:if>
											</c:forEach>
										</tr>
										<tr>
										<td></td>
										</tr>
									</table>
                                </div>
                            </div>
                        </div>
                         <!-- Pie Chart -->
                        <div class="col-xl-4 col-lg-5">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">최근 거래 기록</h6><br>
                                </div>
                                <br>
                                    <c:forEach var="n" items="${cList}">
                                    	<span style="text-align: center;">거래내역 : ${n.category} : ${n.price}원 ${fn:substring(n.cashbookDate, 5, 10)}</span><br>
                                    </c:forEach>
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
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">모든 일정을 작성하셨나요?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">로그아웃 하시겠습니까?</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/on/logout">Logout</a>
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

    <!-- Page level plugins -->
    <script src="${pageContext.request.contextPath}/temp/vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="${pageContext.request.contextPath}/temp/js/demo/chart-area-demo.js"></script>
    <script src="${pageContext.request.contextPath}/temp/js/demo/chart-pie-demo.js"></script>

</body>

</html>