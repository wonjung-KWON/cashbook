package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CashbookController
 */
@WebServlet("/on/cashbook")
public class CashbookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//이번달 달력에 가계부목록의 모델값을 셋팅
		System.out.println("cashbook");
		request.getRequestDispatcher("/WEB-INF/view/cashbook.jsp").forward(request, response);
		
	}
}
