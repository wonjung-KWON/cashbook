<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>home</h1>
	<div>
		현재 접속자 : ${currentCounter} <!--  application.getAttribute("cuurentCounter") -->
	</div>
	<div>
		오늘 접속자 : ${counter} <!--  request.getAttribute("counter") --> 
	</div>
	<div>
		누적 접속자 : ${totalCounter} <!--  request.getAttribute("totalCounter") --> 
	</div>
	<c:if test="${loginMember == null}">
		<a href="${pageContext.request.contextPath}/off/login">로그인</a>
	</c:if>
	<c:if test="${loginMember != null}">
		<a href="${pageContext.request.contextPath}/on/cashbook">가계부</a>
	</c:if>
</body>
</html>