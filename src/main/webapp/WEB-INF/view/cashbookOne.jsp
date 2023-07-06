<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${targetYear}년도 ${targetMonth+1}월 ${targetDay}일</h1>
<table>
	<tr>
		<th>번호</th>
		<th>지출/수입</th>
		<th>지출날짜</th>
		<th>가격</th>
		<th>메모</th>
		<th>수정날짜</th>
		<th>생성날짜</th>
	</tr>
	<c:forEach var="c" items="${list}">
		<tr>
			<td><span>${c.cashbookNo}</span></td>
			<td><span>${c.category}</span></td>
			<td><span>${c.cashbookDate}</span></td>
			<td><span>${c.price}</span></td>
			<td><span>${c.memo}</span></td>
			<td><span>${c.updatedate}</span></td>
			<td><span>${c.createdate}</span></td>
		</tr>
	</c:forEach>
</table>
<a href="${pageContext.request.contextPath}/on/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth+1}&targetDay=${targetDay}">새로 추가</a>
</body>
</html>