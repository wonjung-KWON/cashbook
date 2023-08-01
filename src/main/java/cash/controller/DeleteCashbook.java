package cash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CashbookService;

@WebServlet("/on/deleteCashbook")
public class DeleteCashbook extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	// request 요청값 받기
	int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
	HttpSession session = request.getSession();
	String memberId = (String) session.getAttribute("loginMember");
	CashbookService cs = new CashbookService();
	int row = cs.deleteCashbook(cashbookNo, memberId);
	//request에 쿼리 실행값 보내기
	request.setAttribute("row", row);
	
	request.getRequestDispatcher("/WEB-INF/view/cashbookOne.jsp").forward(request, response);
	}
}
