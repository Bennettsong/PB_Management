package com.base.action;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.serviceImpl.UserInfoServiceImpl;
import com.base.utils.CookieUtils;

@Controller("loginController")
/*@RequestMapping("/jsp1")*/
public class LoginController {
	
	@Autowired
	private UserInfoServiceImpl userInfoServiceImpl;
	
	
	//�û������¼����
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request,HttpServletResponse response)
	{
	
		String userid=request.getParameter("username");
		String pwd=request.getParameter("pwd");
		
		boolean flag=userInfoServiceImpl.login(userid, pwd);
		if(flag)
		{
			//System.out.println("��¼�ɹ�");
			CookieUtils.addCookie("username", userid, response);
			CookieUtils.addCookie("password", pwd, response);
			CookieUtils.addCookie("logintime",String.valueOf(new Date().getTime()),response);
			return "redirect:jsp/mainRent.jsp";
		}else{
			//System.out.println("��¼ʧ��");
			return "redirect:login_soft.html";
		}
	}

}
