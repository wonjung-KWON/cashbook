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

@WebServlet("/CashbookOne")
public class CashbookOneController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 인증 검사 코드
			HttpSession session = request.getSession();
			if(session.getAttribute("loginMember") == null) {
				response.sendRedirect(request.getContextPath()+"/login");
				return;
			}
			String memberId = (String) session.getAttribute("loginMember");
			int targetYear = Integer.parseInt(request.getParameter("targetYear"));
			int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
			int targetDay = Integer.parseInt(request.getParameter("targetDay"));
			
			List<Cashbook> list = new CashbookDao().selectCashbookOne(memberId, targetYear, targetMonth+1, targetDay);
			request.setAttribute("list", list);
			
			request.setAttribute("targetYear", targetYear);
			request.setAttribute("targetMonth", targetMonth);
			request.setAttribute("targetDay", targetDay);
			
			//상세정보 뷰
			request.getRequestDispatcher("WEB-INF/view/cashbookOne.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
