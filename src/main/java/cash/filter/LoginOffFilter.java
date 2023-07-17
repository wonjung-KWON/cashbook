package cash.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//servelt A, C :  로그아웃 되어있는 상태에서만접근가능
@WebFilter("/off/*")
public class LoginOffFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("/off/* 전");
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		//만약 로그인 되어있다면 cashbook으로 리다이렉트
		if(session.getAttribute("loginMember") != null) {
			HttpServletResponse rep = (HttpServletResponse)response;
			rep.sendRedirect(req.getContextPath()+"/on/cashbook");
			return;
		}
		
		chain.doFilter(request, response);
		System.out.println("/off/* 후");
	}


}
