package com.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.utils.CookieUtils;

public class checkLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String url = request.getServletPath();
		// ���ò���Ҫ���˵�ҳ��
		if (url.endsWith("login.do")) {
			arg2.doFilter(arg0, arg1);
			return;
		}
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null ) {
			boolean flag = CookieUtils.checkLogin(request, response);
			if (!flag) { // ���û�е�¼���ߵ�¼�󳬹�ʧЧʱ�䶼��ת����¼ҳ��
				response.sendRedirect("/BaseWeb/login_soft.html");
				return;
			}
		}else{
			System.out.print("cookiesΪnull");
		}
		
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
