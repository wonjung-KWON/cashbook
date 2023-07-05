package cash.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.HashtagDao;

@WebServlet("/hashtagList")
public class HashtagListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 인증 검사 코드
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		String memberId = (String) session.getAttribute("loginMember");
		String word = request.getParameter("word");
		System.out.println(word+"<-- hashtagList word");
		List<Map<String,Object>> list = new HashtagDao().AllHashtagList(word, memberId);
		
		request.setAttribute("word", word);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/view/hashtagList.jsp").forward(request, response);
	}

}
