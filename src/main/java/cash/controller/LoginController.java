package cash.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.MemberDao;
import cash.vo.Member;
import service.MemberService;

@WebServlet("/off/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿠키에 저장된 로그인된 아이다가 있다면 request 속성에 저장해서 view에서 value에 넣어 출력
		Cookie[] cookies = request.getCookies(); // getCookies 메서드를 통하여 모든 쿠키값을 배열에 저장
		
		if(cookies != null) {
			for(Cookie c :  cookies) {
				if(c.getName().equals("cookieLoginId")) {
					//request.setAttribute 에다가 찾은 쿠키값 저장
					request.setAttribute("cookieId", c.getValue());
					System.out.println(request.getAttribute("cookieId")+":저장된 cookieId");
				}
			}
		}
		// alert창을 띄울 메세지 변수선언
		String msg = null;
		if(request.getParameter("msg") != null) {
			msg = request.getParameter("msg");
		}
		System.out.println(msg + "로그인컨트롤러 msg");
		request.setAttribute("msg", msg);
		
		// 포워드 방식으로 view에 값전달
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		Member member = new Member(memberId, memberPw, null, null, null, null);
		System.out.println(member+"loginController");
		
		MemberService memberService = new MemberService();
		Member loginMember = memberService.selectMemberById(member);
			
		if(loginMember == null) { // null 로그인실패
		    System.out.println("로그인 실패");
		    response.sendRedirect(request.getContextPath()+"/off/login");
		    return;
		}
			
		String loginId = loginMember.getMemberId();
		// 로그인 성공시 : session 사용
		HttpSession session = request.getSession();
		System.out.println("로그인 성공");
		System.out.println(loginMember+"LoginCotroller loginMember");
		session.setAttribute("loginMember", loginId);
		
		if(request.getParameter("idSave") != null) {
			Cookie loginIdCookie = new Cookie("cookieLoginId", loginId);
			//클라이언트쪽으로 쿠키전송
			response.addCookie(loginIdCookie);
		}
		
		response.sendRedirect(request.getContextPath()+"/on/home");
	}

}

