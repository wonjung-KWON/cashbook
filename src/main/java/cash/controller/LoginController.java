package cash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;

@WebServlet("/off/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		Member member = new Member(memberId, memberPw, null, null);
		
		MemberDao memberDao = new MemberDao();
		Member loginMember = memberDao.selectMemberById(member);
			
		if(loginMember == null) { // null 로그인실패
		    System.out.println("로그인 실패");
		    response.sendRedirect(request.getContextPath()+"/off/login");
		    return;
		}
		String loginM = loginMember.getMemberId();
		// 로그인 성공시 : session 사용
		HttpSession session = request.getSession();
		System.out.println("로그인 성공");
		System.out.println(loginMember);
		session.setAttribute("loginMember", loginM);
		response.sendRedirect(request.getContextPath()+"/on/cashbook");
	}

}

