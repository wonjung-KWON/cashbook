<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- JSP컴파일시 자바코드로 변환되는 c: .. (제어문법코드) 커스터태그 -->


<!DOCTYPE html>
<html>
<head>
<style>
td { height : 150px; width: 300px;}
glanlink {color: #000000; text-decoration: none;}
 
</style>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 변수값or반환값 : EL사용 표현식 -->
	<!-- 속성값 대신 EL사용 ex) request.getAttribute("targetYear") : requestScope.targetYear :  requestScope생략가능 -->
	<!--  형변환연산이 필요없다(EL이 자동으로 처리) -->
	
	<!-- 자바코드(제어문) : JSTL 사용 -->
	<h1>${targetYear}년 ${targetMonth+1}월</h1>
	<a href = "${pageContext.request.contextPath}/on/calendar?targetYear=${targetYear}&targetMonth=${targetMonth-1}">이전달</a>
	<a href = "${pageContext.request.contextPath}/on/calendar?targetYear=${targetYear}&targetMonth=${targetMonth+1}">다음달</a>
	<div>
		<h2>이달의 해시태그</h2>
		<div>
			<c:forEach var="m" items="${htList}">
				<a href="${pageContext.request.contextPath}/on/hashtagList?word=${m.word}" style="color: green; text-decoration: none;">${m.word}(${m.cnt})</a>
			</c:forEach>
		</div>
	</div>
	<table class="table">
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
					
					
					<td><div><a href="${pageContext.request.contextPath}/on/CashbookOne?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDay=${d}" style="color: #000000; text-decoration: none;">${d}</a></div>
						
						
						<c:forEach var="c" items="${list}">
							<c:if test="${d == fn:substring(c.cashbookDate, 8, 10)}">
								<div>
									<c:if test="${c.category == '수입'}">
										<span style="color:blue;">+${c.price}</span>
									</c:if>
									<c:if test="${c.category == '지출'}">
										<span style="color:red;">-${c.price}</span>
									</c:if>
								</div>
							</c:if>
						</c:forEach>
					</td>
				
				</c:if>
				<!-- 
				<c:if test="${(!(d<1 || (i-beginBlank+1) > lastDate)) && todayMonth == targetMonth
								&& today == num 
								&& todayYear == targetYear}">
					<td style="background-color:gray;"><a href="" >${d}</a></td>
				</c:if>
				<c:if test="${(!(d<1 || (i-beginBlank+1) > lastDate)) && i % 7 == 6}">
					<td><a href="" style="color:blue; text-decoration: none;">${d}</a></td>	
				</c:if>
				<c:if test="${(!(d<1 || (i-beginBlank+1) > lastDate)) && i % 7 == 0}">
					<td><a href="" style="color:red; text-decoration: none;">${d}</a></td>	
				</c:if>
				 -->
			</c:forEach>
		</tr>
	</table>
<!--  -->

</body>
</html>