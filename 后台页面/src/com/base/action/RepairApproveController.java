package com.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("repairApproveController")
@RequestMapping("/jsp")
public class RepairApproveController {
	
	//�������еı�����Ϣ
	   @RequestMapping("/getNoRepair.do")
	    public String getNoRepair(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   return null;		   
	   }
	   
   //������ͬ���ı�����Ϣ
	   @RequestMapping("/getAgreeRepair.do")
	    public String getAgreeRepair(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   return null;		   
	   }	   
	 
	   
	 //���ɸѡ�������еı�����Ϣ
	   @RequestMapping("/getChooseRepair1.do")
	    public String getChooseRepair1(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   return null;		   
	   }
	   
	 //���ɸѡ������ͬ��ı�����Ϣ
	   @RequestMapping("/getChooseRepair2.do")
	    public String getChooseRepair2(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   return null;		   
	   }
	   
	   //�ܾ�����
	   @RequestMapping("/refuseRepairApply.do")
	    public String refuseRepairApply(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   return null;		   
	   }
	   
	   //ͬ������
	   @RequestMapping("/agreeRepairApply.do")
	    public String agreeRepairApply(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   return null;		   
	   }
	   
	   //ά�����
	   @RequestMapping("/repairFinish.do")
	    public String repairFinish(HttpServletRequest request,
		    HttpServletResponse response, ModelMap map) {
		   
		   return null;		   
	   }
	   
}
