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

@WebServlet("on/home")
public class Home extends HttpServlet {
    private CounterService counterService = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.counterService = new CounterService();
		HttpSession session = request.getSession();
		int counter = counterService.getCounter();
		int totalCounter = counterService.getCounterAll();
		
		String memberId = (String) session.getAttribute("loginMember");
		
		// view에 넘겨줄 달력정보(모델값)
		Calendar firstDay = Calendar.getInstance(); // 오늘날짜로 저장이됌
		//출력하고자 하는 년도, 월, 일의 기본값은 이번달 1일
		int todayYear = firstDay.get(Calendar.YEAR);
		int todayMonth = firstDay.get(Calendar.MONTH);
		int today = firstDay.get(Calendar.DATE);
		CashbookService c = new CashbookService();
		// 모델을 호출(DAO 타켓 월의 수입/지출데이터
		List<Cashbook> list = c.selectCashbookListByMonth(memberId, todayYear, todayMonth+1);
		
		List<Map<String,Object>> htList = new HashtagService().selectWordCountByMonth(todayYear, todayMonth+1, memberId);
		
		int maxMoney = c.selectMaxMoney(memberId, todayYear, todayMonth);
		int maxUnMoney = c.selectMaxUnMoney(memberId, todayYear, todayMonth);
		List<Cashbook> lastMoney = c.selectLastMoney(memberId, todayYear, todayMonth);
		int totalM = maxMoney - maxUnMoney;
		
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
		request.setAttribute("lastMoney", lastMoney);
		request.setAttribute("totalM", totalM);
		
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
