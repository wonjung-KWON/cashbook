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

@WebServlet("/on/RemoveMemberController")
public class RemoveMemberController extends HttpServlet {
	// 비밀번호 입력 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성검사
		request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
	}
	//탈퇴
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session 유효성검사
		HttpSession session = request.getSession();
		
		String memberId = (String) session.getAttribute("loginMember");
		
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberId);
		System.out.println(memberPw);
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		MemberDao memberDao = new MemberDao();
		int row = memberDao.removeMember(member);
		if(row == 1) {
		//탈퇴성공
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/off/login");
		System.out.println("탈퇴성공");
		return;
		} else if(row == 0) {
			//비밀번호 틀림
			response.sendRedirect(request.getContextPath()+"/on/RemoveMemberController");
			System.out.println("비밀번호 틀림");
			return;
		} else {
			System.out.println("add member error!");
		}
		
	}

}
