package com.base.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.Admin;
import com.base.po.AdminFunction;
import com.base.serviceImpl.AdminManageServiceImpl;


@Controller("adminController")
public class AdminController {

	    @Autowired
	    private AdminManageServiceImpl adminManageServiceImpl;
	
	    //获取指定人的权限功能值
		@RequestMapping("jsp/getAdminFunction.do")
		public String getAdminFunction(HttpServletRequest request, ModelMap map,
				HttpServletResponse response)
		{
			try {
				
				List<AdminFunction> adminFunctionList = adminManageServiceImpl.getAdminFunctionInfos();
				response.setContentType("text/html;charset=UTF-8");
				List<Admin> adminList = adminManageServiceImpl.getAdminInfos();
				
				JSONObject getObj = new JSONObject();
				getObj.put("sf", adminList);
				getObj.put("qxm", adminFunctionList);	
				response.getWriter().print(getObj.toString());
				//response.getWriter().print("['sf':[{id:1,name:'系统管理员',upow:3},{id:2,name:'普通教师',upow:7},{id:3,name:'租赁业务专员',upow:31},{id:4,name:'实习业务专员',upow:63}],'qxm':[{qid:11,value:1,pname:'土地租赁审批'},{qid:12,value:2,pname:'实习审批'},{qid:13,value:4,pname:'实习基地审批'},{qid:21,value:8,pname:'基地维护审批'},{qid:22,value:16,pname:'土地租赁维护'},{qid:31,value:32,pname:'租赁分析'}]]");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;		
		}	
	
}
