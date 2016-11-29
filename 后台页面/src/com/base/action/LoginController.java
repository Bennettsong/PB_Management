package com.base.action;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.UserInfo;
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
		System.out.println(userid+"  "+pwd);	
		long adminValue=userInfoServiceImpl.login(userid, pwd);
		UserInfo ui=userInfoServiceImpl.getImage(userid);
		String src="";
		String name="";
		if(ui!=null)
		{
		  src=ui.getImg();
		  name=ui.getName();
		}
		//System.out.println(name);
		if(adminValue!=-1)
		{
			//System.out.println("��¼�ɹ�");
			CookieUtils.addCookie("username", userid, response);
			CookieUtils.addCookie("password", pwd, response);
			CookieUtils.addCookie("logintime",String.valueOf(new Date().getTime()),response);
			CookieUtils.addCookie("adminValue", String.valueOf(adminValue),response);			
			CookieUtils.addCookie("image", src,response);
			try {
				CookieUtils.addCookie("name",java.net.URLEncoder.encode(name,"utf-8") ,response);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:jsp/index.do";
		}else{
			//System.out.println("��¼ʧ��");
			return "redirect:login_soft.html";
		}
		
	
	}
	
	
	//�û������¼����
	@RequestMapping("/loginout.do")
	public String loginOut(HttpServletRequest request,HttpServletResponse response)
	{
		CookieUtils.loginout(request, response);
		return "redirect:login_soft.html";
	}
}
