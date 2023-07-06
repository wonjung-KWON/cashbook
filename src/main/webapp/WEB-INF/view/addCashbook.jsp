<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${targetYear}년도 ${targetMonth}월 ${targetDay}일 가계부 작성</h1>
<form action="${pageContext.request.contextPath}/on/addCashbook" method="post">
	<input type="hidden" name="cashbookDate" value="${targetYear}-${targetMonth}-${targetDay}">
	<table>
		<tr>
			<th>지출날짜</th>
			<td><span>${targetYear}년도 ${targetMonth}월 ${targetDay}일</span></td>
		</tr>
		<tr>
			<th>지출/수입</th>
			<td>
				<input type="radio" name="category" value="지출">지출
				<input type="radio" name="category" value="수입">수입
			</td>
		</tr>
		<tr>
			<th>가격</th>
			<td>
				<input type="text" name="price">
			</td>
		</tr>
		<tr>
			<th>해시태그 및 메모</th>
			<td>
				<input type="text" name="memo">	
			</td>
		</tr>
	</table>
	<button type="submit">추가</button>
</form>
</body>
</html>