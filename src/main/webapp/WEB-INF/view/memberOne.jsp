<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cash.vo.*" %>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원상세보기</h1>
<a href="${pageContext.request.contextPath}/on/cashbook">가계부</a>
<table>
	<tr>
		<td>아이디</td>
		<td>${member.memberId}</td>
	</tr>
	<tr>
		<td>패스워드</td>
		<td>${member.memberPw}</td>
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
	<a href="${pageContext.request.contextPath}/on/modifyMember">회원정보수정</a>
	<a href="${pageContext.request.contextPath}/on/RemoveMemberController">회원탈퇴</a>

</body>
</html>