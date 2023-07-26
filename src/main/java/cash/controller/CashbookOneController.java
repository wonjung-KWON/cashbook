package cash.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.vo.Cashbook;
import service.CashbookService;

@WebServlet("/on/CashbookOne")
public class CashbookOneController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			String memberId = (String) session.getAttribute("loginMember");
			
			int targetYear = 0;
			int targetMonth = 0;
			int targetDay = 0;
			String cashbookDate = null;
			if(request.getParameter("cashbookDate") != null) {
				cashbookDate = request.getParameter("cashbookDate");
				targetYear = Integer.parseInt(cashbookDate.substring(0, 4));
				targetMonth = Integer.parseInt(cashbookDate.substring(5, 7));
				targetDay = Integer.parseInt(cashbookDate.substring(8, 10));
				System.out.println(targetYear);
				System.out.println(targetMonth);
				targetMonth = targetMonth-1;
				System.out.println(targetMonth);
				System.out.println(targetDay);
			}else {
				targetYear = Integer.parseInt(request.getParameter("targetYear"));
				targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
				targetDay = Integer.parseInt(request.getParameter("targetDay"));
			}
			List<Cashbook> list = new CashbookService().selectCashbookOne(memberId, targetYear, targetMonth, targetDay);
			request.setAttribute("list", list);
			
			request.setAttribute("targetYear", targetYear);
			request.setAttribute("targetMonth", targetMonth);
			request.setAttribute("targetDay", targetDay);
			
			//상세정보 뷰
			request.getRequestDispatcher("/WEB-INF/view/cashbookOne.jsp").forward(request, response);
	}

}
