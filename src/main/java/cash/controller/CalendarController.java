package cash.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;
import service.CashbookService;
import service.HashtagService;


@WebServlet("/on/calendar")
public class CalendarController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("loginMember");
		
		// view에 넘겨줄 달력정보(모델값)
		Calendar firstDay = Calendar.getInstance(); // 오늘날짜로 저장이됌
		Calendar c = Calendar.getInstance(); // 오늘날짜로 저장이됌
		
		//출력하고자 하는 년도, 월, 일의 기본값은 이번달 1일
		int targetYear = firstDay.get(Calendar.YEAR);
		int targetMonth = firstDay.get(Calendar.MONTH);
		System.out.println(targetYear+"<-- targetYear");
		System.out.println(targetMonth+"<-- targetMonth");
		
		int today = c.get(Calendar.DATE);
		int todayMonth = c.get(Calendar.MONTH);
		int todayYear = c.get(Calendar.YEAR);
		firstDay.set(Calendar.DATE, 1);
		//오늘날짜
		// 출력하고자하는 년도와 월이 매개값으로 넘어왔다면
		if(request.getParameter("targetYear") != null
				&& request.getParameter("targetMonth") != null) {
			targetYear = Integer.parseInt(request.getParameter("targetYear"));
			targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
			
			firstDay.set(Calendar.YEAR, targetYear);
			// API 에서 자동으로 Calendar.MONTH값으로 12가 입력되면 월 1, 년 +1
			// API 에서 자동으로 Calendar.MONTH값으로 -1 입력되면 월 12, 년 -1
			firstDay.set(Calendar.MONTH, targetMonth);
			targetYear = firstDay.get(Calendar.YEAR);
			targetMonth = firstDay.get(Calendar.MONTH);
			
		}
		System.out.println(targetYear+"<-- targetYear 2");
		System.out.println(targetMonth+"<-- targetMonth 2");
		// 달력출력시 첫번째 1일이 무슨요일인지 알기 위한 알고리즘
		// 1일날짜의 요일(일1, 월2, ... 토6)-1
		int beginBlank = firstDay.get(Calendar.DAY_OF_WEEK)-1;
		System.out.println(beginBlank + "<-- beginBlank");
		
		// 달력출력시 마지막 날짜
		int lastDate = firstDay.getActualMaximum(Calendar.DATE);
		System.out.println(lastDate + "<-- lastDate");
		
		// 달력시 마지막 날짜 출력 후 공백 개수 - > 전체 셀의 개수가 7로 나누어 떨어져야 한다
		int endBlank = 0;
		
		if((beginBlank + lastDate)%7 != 0) {
			endBlank = 7 - ((beginBlank + lastDate)%7);
			
		}
		int totalCell = beginBlank + lastDate + endBlank;
		System.out.println(totalCell+"<-- totalCell");
		System.out.println(endBlank+"<-- endBlank");
		
		Calendar preMonth = Calendar.getInstance();
		preMonth.set(Calendar.YEAR, targetYear);
		preMonth.set(Calendar.MONTH, targetMonth -1);
		int preLastDate = preMonth.getActualMaximum(Calendar.DATE);
		System.out.println(preLastDate+"<-- 전달마지막날짜");
		//서비스 호출 변수 선언
		CashbookService n = new CashbookService();
		// 현재 년월을 값을 보내 가장 수익이많았던 값 cashbookService 호출에서 가져오기
		int maxMoney = n.selectMaxMoney(memberId, targetYear, targetMonth+1);
		// 현재 년월을 값을 보내 가장 지출이많았던 값 cashbookService 호출에서 가져오기
		int maxUnMoney = n.selectMaxUnMoney(memberId, targetYear, targetMonth+1);
		// Cashbook 클래스에 수입 마지막 날의 가계부 작성한 데이터 cashbookService 호출에서 값받아서 넣기 
		Cashbook lastMoney = n.selectLastMoney(memberId, targetYear, targetMonth+1);
		// 변수안에 lastMoney 클래스에서 price 값 가져와서 초기값 설정
		int lastPrice = lastMoney.getPrice();
		// 마지막날 변수에 lastMoney 클래스에서 값 가져와서 초기값 설정
		String lastDay = lastMoney.getUpdatedate();
		// 총 수익 cashbookService 호출에서 값 받아오기
		int totalMoney = n.totalMoney(memberId);
		// 총 지출 cashbookService 호출에서 값 받아오기
		int totalUnMoney = n.totalUnMoney(memberId);
		// 총 수익과 총 지출을 연산하여 남은 자산 출력
		int totalM = totalMoney - totalUnMoney;
		// 모델을 호출(DAO 타켓 월의 수입/지출데이터
		List<Cashbook> list = n.selectCashbookListByMonth(memberId, targetYear, targetMonth+1);
		
		List<Map<String,Object>> htList = new HashtagService().selectWordCountByMonth(targetYear, targetMonth+1, memberId);
		System.out.println(htList+"CalendarController htList");
		// 뷰에 값넘기기 위해 request속성에 값 넘기기
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("lastDate", lastDate);
		request.setAttribute("totalCell", totalCell);
		request.setAttribute("beginBlank", beginBlank);
		request.setAttribute("endBlank", endBlank);
		request.setAttribute("today", today);
		request.setAttribute("todayMonth", todayMonth);
		request.setAttribute("todayYear", todayYear);
		request.setAttribute("preLastDate", preLastDate);
		request.setAttribute("list", list);
		request.setAttribute("htList", htList);
		request.setAttribute("maxMoney", maxMoney);
		request.setAttribute("maxUnMoney", maxUnMoney);
		request.setAttribute("lastPrice", lastPrice);
		request.setAttribute("lastDay", lastDay);
		request.setAttribute("totalM", totalM);
		
		//달력을 출력하는 뷰
		request.getRequestDispatcher("/WEB-INF/view/calendar.jsp").forward(request, response);
	}
}
