package cash.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;
import cash.vo.Hashtag;
import service.CashbookService;
import service.HashtagService;

@WebServlet("/on/addCashbook")
public class AddCashbookController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request 매개값
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDay = Integer.parseInt(request.getParameter("targetDay"));
		System.out.println(targetMonth+"addcashbookController");
		
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDay", targetDay);
		// 나머지 데이터는 입력폼에서 사용자가 입력
		request.getRequestDispatcher("/WEB-INF/view/addCashbook.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//post인코딩 처리
		//session유효성 검사
				HttpSession session = request.getSession();
				if(session.getAttribute("loginMember") == null) {
					response.sendRedirect(request.getContextPath()+"/off/login");
					return;
				}	
		//request 매개값
		String memberId = (String) session.getAttribute("loginMember");
		String category = request.getParameter("category");
		String cashbookDate = request.getParameter("cashbookDate"); 
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		
		System.out.println(memberId+"<--memberId");
		System.out.println(category+"<--category");
		System.out.println(cashbookDate+"<--cashbookDate");
		System.out.println(price+"<--price");
		System.out.println(memo+"<--memo");
		
		//클래스 값 셋팅
		Cashbook cashbook = new Cashbook();
		cashbook.setMemberId(memberId);
		cashbook.setCategory(category);
		cashbook.setCashbookDate(cashbookDate);
		cashbook.setPrice(price);
		cashbook.setMemo(memo);
		
		//입력 DAO실행
		CashbookService c = new CashbookService();
		int cashbookNo = c.addCashbook(cashbook); //키값반환
		//입력실패
		if(cashbookNo  == 0) {
			System.out.println("입력실패");
			response.sendRedirect(request.getContextPath()+"/on/cashbook");
			return;
		}
		//입력성공 -> 해시태그가 있다면 - > 해시태그를 추출 -> 해시태그를 입력(반복문활용)
		//해시태그 추출 알고리즘
		// # #구디 #구디 #자바
		HashtagService hashtagService = new HashtagService();
		String memo2 = cashbook.getMemo();
		String memo3 = memo2.replace("#", " #"); // "#구디#아카데미" -> " #구디 #아카데미"
		
		Set<String> set = new HashSet<String>();
		
		for(String ht : memo3.split(" ")) {
			if(ht.startsWith("#")) {
			String ht2 = ht.replace("#", "");
			if(ht2.length() > 0) {
				set.add(ht2);
		
				}
			}	
		}
			for(String s: set) {
				Hashtag hashtag = new Hashtag();
				hashtag.setCashbookNo(cashbookNo);
				hashtag.setWord(s);
				hashtagService.insertHashtag(hashtag);
			}
		response.sendRedirect(request.getContextPath()+"/on/calendar");
	}

}
