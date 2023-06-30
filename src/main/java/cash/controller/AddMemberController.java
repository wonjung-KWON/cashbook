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

@WebServlet("/AddMemberController")
public class AddMemberController extends HttpServlet {
	//addMember.jsp 회원 가입폼 이동
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 세션 유효 검사(null일때만)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		//jsp페이지로 포워드(디스페치)
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
		
	}
	//회원가입 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 세션 유효 검사(null일때만)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
	// request.getParameter()
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		//회원가입 DAO 호출
		MemberDao memberDao = new MemberDao();
		int row = memberDao.insertMember(member);
		if(row == 0) {//회원 가입 실패시
			// addMember.jsp view를 이동하는 controller를 리다이렉트
			response.sendRedirect(request.getContextPath()+"/AddMemberController");
			System.out.println("회원가입 실패");
			return;
		}else if(row == 1) {//회원가입 성공시
			// login.jsp view를 이동하는 controller를 리다이렉트
			response.sendRedirect(request.getContextPath()+"/login");
			System.out.println("회원가입 성공");
			return;
			
		}else {
			System.out.println("add member error!");
		}
		//login.jsp
		response.sendRedirect(request.getContextPath()+"/login");
	}

}
