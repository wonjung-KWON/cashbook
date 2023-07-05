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
		
		//페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		//페이지당 보여줄 행 수
		int rowPerPage = 5;
		
		int beginRow = (currentPage - 1) * rowPerPage;
		
		System.out.println(word+"<-- hashtagList word");
		List<Map<String,Object>> list = new HashtagDao().AllHashtagList(word, memberId, beginRow, rowPerPage);
		
		int totalRow = new HashtagDao().CountHashtagList(word, memberId);
		System.out.println(totalRow + "<-- totalRow");
		int lastPage = totalRow / rowPerPage;
		if(totalRow % rowPerPage != 0) {
			lastPage ++;
		}
		int pageCount = 5;
		// startPage가 currentPage가 1~10이면 1로 고정 11~20이면 2로 고정되게 소수점을 이용하여 고정값 만드는 알고리즘
		int startPage = ((currentPage -1)/pageCount)*pageCount+1;
		// startPage에서 9를 더한값이 마지막 출력될 Page이지만 lastPage보다 커지면 endPage는 lastpage로변환
		int endPage = startPage+9;
		if(endPage > lastPage){
			endPage = lastPage;
			}
		System.out.println(startPage+"<-- startPage");
		System.out.println(endPage+"<-- endPage");
		
		request.setAttribute("word", word);
		request.setAttribute("beginRow", beginRow);
		request.setAttribute("word", word);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/view/hashtagList.jsp").forward(request, response);
	}

}
