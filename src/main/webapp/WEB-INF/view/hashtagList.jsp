<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>해시태그 ${word} 리스트</h2>
		<table>
			<tr>
				<th>지출/수입</th>
				<th>가격</th>
				<th>변동날짜</th>
				<th>메모</th>
				<th>생성날짜</th>
				<th>수정날짜</th>
			</tr>
	<c:forEach var="h" items="${list}">
			<tr>
				<td>${h.category}</td>
				<td>${h.price}</td>
				<td><a href="${pageContext.request.contextPath}/CashbookOne?cashbookDate=${h.cashbookDate}">${h.cashbookDate}</a></td>
				<td>${h.memo}</td>
				<td>${h.createdate}</td>
				<td>${h.updatedate}</td>
			</tr>
	</c:forEach>
		</table>
		<div>
			<c:if test="${startPage > 5}">
				<a href="${pageContext.request.contextPath}/hashtagList?currentPage=${startPage-1}&word=${word}">이전</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
				<a href="${pageContext.request.contextPath}/hashtagList?currentPage=${i}&word=${word}">${i}</a>
			</c:forEach>
			<c:if test="${endPage<lastPage}">
				<a href="${pageContext.request.contextPath}/hashtagList?currentPage=${endPage+1}&word=${word}">다음</a>
			</c:if>
		</div>
</body>
</html>