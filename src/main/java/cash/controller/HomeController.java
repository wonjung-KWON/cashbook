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

import cash.vo.Cashbook;
import service.CashbookService;
import service.CounterService;
import service.HashtagService;

@WebServlet("/on/home")
public class HomeController extends HttpServlet {
    private CounterService counterService = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.counterService = new CounterService();
		HttpSession session = request.getSession();
		int counter = counterService.getCounter();
		int totalCounter = counterService.getCounterAll();
		
		String memberId = (String) session.getAttribute("loginMember");
		
		// view에 넘겨줄 달력정보(모델값)
		Calendar firstDay = Calendar.getInstance(); // 오늘날짜로 저장이됌
		Calendar a = Calendar.getInstance(); 
		
		//출력하고자 하는 년도, 월, 일의 기본값은 이번달 1일
		int todayYear = firstDay.get(Calendar.YEAR);
		int todayMonth = firstDay.get(Calendar.MONTH);
		int today = firstDay.get(Calendar.DATE);
		a.set(Calendar.DATE, 1);
		
		// 달력출력시 첫번째 1일이 무슨요일인지 알기 위한 알고리즘
		// 1일날짜의 요일(일1, 월2, ... 토6)-1
		int beginBlank = a.get(Calendar.DAY_OF_WEEK)-1;
		System.out.println(beginBlank + "<-- beginBlank");
		
		// 달력출력시 마지막 날짜
		int lastDate = a.getActualMaximum(Calendar.DATE);
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
		preMonth.set(Calendar.YEAR, todayYear);
		preMonth.set(Calendar.MONTH, todayMonth -1);
		int preLastDate = preMonth.getActualMaximum(Calendar.DATE);
		System.out.println(preLastDate+"<-- 전달마지막날짜");
		
		// 현재 달 디버깅 체크
		System.out.println(todayYear+"home todayYear");
		System.out.println(todayMonth+"home todayMonth");
		System.out.println(today+"home today");
		
		CashbookService c = new CashbookService();
		// 서비스 호출(DAO 타켓 월의 수입/지출데이터
		List<Cashbook> list = c.selectCashbookListByMonth(memberId, todayYear, todayMonth+1);
		// 서비스 호출을 통해 현재달의 카테고리 데이터 출력
		List<Map<String,Object>> htList = new HashtagService().selectWordCountByMonth(todayYear, todayMonth+1, memberId);
		// 현재 년월을 값을 보내 가장 수익이많았던 값 cashbookService 호출에서 가져오기
		int maxMoney = c.selectMaxMoney(memberId, todayYear, todayMonth+1);
		// 현재 년월을 값을 보내 가장 지출이많았던 값 cashbookService 호출에서 가져오기
		int maxUnMoney = c.selectMaxUnMoney(memberId, todayYear, todayMonth+1);
		// Cashbook 클래스에 수입 마지막 날의 가계부 작성한 데이터 cashbookService 호출에서 값받아서 넣기 
		Cashbook lastMoney = c.selectLastMoney(memberId, todayYear, todayMonth+1);
		// 변수안에 lastMoney 클래스에서 price 값 가져와서 초기값 설정
		int lastPrice = lastMoney.getPrice();
		// 마지막날 변수에 lastMoney 클래스에서 값 가져와서 초기값 설정
		String lastDay = lastMoney.getUpdatedate();
		System.out.println(lastPrice + "homeController lastPrice");
		// 총 수익 cashbookService 호출에서 값 받아오기
		int totalMoney = c.totalMoney(memberId);
		// 총 지출 cashbookService 호출에서 값 받아오기
		int totalUnMoney = c.totalUnMoney(memberId);
		// 총 수익과 총 지출을 연산하여 남은 자산 출력
		int totalM = totalMoney - totalUnMoney;
		
		System.out.println(maxMoney+"home maxMoney");
		System.out.println(maxUnMoney+"home maxUnMoney");
		System.out.println(lastMoney+"home lastMoney");
		System.out.println(totalM +"home totalM");
		System.out.println(htList+"home htList");
		System.out.println(list+"home list");
		
		//최근기록 리스트 서비스 호출
		List<Cashbook> cList = c.todayCashbook(memberId, todayYear, todayMonth+1);
		System.out.println(cList+"home clist");
		// 뷰에 값넘기기 위해 request속성에 값 넘기기
		request.setAttribute("todayYear", todayYear);
		request.setAttribute("todayMonth", todayMonth);
		request.setAttribute("today", today);
		request.setAttribute("counter", counter);
		request.setAttribute("totalCounter", totalCounter);
		request.setAttribute("loginMember", session.getAttribute("loginMember"));
		request.setAttribute("list", list);
		request.setAttribute("htList", htList);
		request.setAttribute("maxMoney", maxMoney);
		request.setAttribute("maxUnMoney", maxUnMoney);
		request.setAttribute("totalM", totalM);
		request.setAttribute("lastPrice", lastPrice);
		request.setAttribute("lastDay", lastDay);
		request.setAttribute("lastDate", lastDate);
		request.setAttribute("totalCell", totalCell);
		request.setAttribute("beginBlank", beginBlank);
		request.setAttribute("endBlank", endBlank);
		request.setAttribute("preLastDate", preLastDate);
		request.setAttribute("cList", cList);
		
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
