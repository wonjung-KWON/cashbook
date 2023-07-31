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
import service.MemberService;

@WebServlet("/on/modifyMember")
public class ModifyMemberController extends HttpServlet {
	@Override
	//수정폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/modifyMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session 유효성검사
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("loginMember");
		String memberPw = request.getParameter("memberPw");
		String checkPw = request.getParameter("checkPw");
		String ckPw = request.getParameter("ckPw");
		if(!memberPw.equals(ckPw)) {
			System.out.println("두개의 비밀번호가 같지않음");
			response.sendRedirect(request.getContextPath()+"/on/memberOne");
			return;
		}
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(checkPw);
		
		MemberService memberService = new MemberService();
		int row = memberService.checkPw(member);
		if(row == 0) {
			System.out.println("비밀번호 틀림");
			response.sendRedirect(request.getContextPath()+"/on/memberOne");
			return;
		} else if(row == 1) {
			int row2 = memberService.modifyMember(memberId, memberPw);
			if(row2 == 0) {
				System.out.println("회원변경실패");
				response.sendRedirect(request.getContextPath()+"/on/memberOne");
				return;
			} else if(row2 == 1) {
				System.out.println("회원변경성공");
				response.sendRedirect(request.getContextPath()+"/on/memberOne");
				return;
			}else {
				System.out.println("에러");
				response.sendRedirect(request.getContextPath()+"/on/memberOne");
			}
		}
	}

}
