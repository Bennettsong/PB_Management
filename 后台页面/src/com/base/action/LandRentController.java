package com.base.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.RentMaintain;
import com.base.serviceImpl.LandRentServiceImpl;
import com.base.utils.ExcelReport;

//ʹ��ģ��Ŀ��Ʋ�
@Controller("landRentController")
@RequestMapping("/jsp")
public class LandRentController {

	@Autowired
	private LandRentServiceImpl landRentServiceImpl;

	// �������޼�¼
	@RequestMapping("/landRentInfo.do")
	public String selectBase(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		System.out.println("չʾ����������Ϣ");

		List<RentMaintain> list = landRentServiceImpl.getLandRentInfos(null,
				null, null, null, null);

		JSONObject getObj = new JSONObject();
		getObj.put("data", list);

		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(getObj.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping("/getSingleRentInfo.do")
	public String getSingleRentInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		String lr_id = request.getParameter("lr_id");
		List<RentMaintain> list = landRentServiceImpl.getLandRentInfos(null,
				null, null, null, lr_id);

		JSONArray json = JSONArray.fromObject(list);
		response.setContentType("text/html;charset=UTF-8");

		try {

			response.getWriter().print(json.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping("/submitChoose.do")
	public String submitChoose(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		
		String bname = request.getParameter("baseSh");
		String dept = request.getParameter("deptSh");
		String lid = request.getParameter("lidSh");
		String planting = request.getParameter("contentSh");

		System.out.println("���������ǣ�"+bname + " �����ǣ� " + dept + "  ���ر���ǣ�" + lid + " " + "��ֲ�����ǣ�"+planting);

		List<RentMaintain> list = landRentServiceImpl.getLandRentInfos(bname,
				lid, dept, planting, null);

		JSONObject getObj = new JSONObject();
		getObj.put("data", list);

		response.setContentType("text/html;charset=UTF-8");

		try {
			response.getWriter().print(getObj.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	@RequestMapping("/deleteLandRentInfo.do")
	public String deleteLandRentInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		   
		String[] check=request.getParameterValues("idname");		
		landRentServiceImpl.deleteRentInfo(check);
		
	   return "fieldRent_maintain";
	}
	
	
	@RequestMapping("/exportLandRentInfo.do")
	public String exportLandRentInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) throws Exception {
		
		String dept=request.getParameter("dept");	
		System.out.println(dept);
		List<RentMaintain> list = landRentServiceImpl.getLandRentInfos(null,
				null, dept, null, null);		
	
		
		ExcelReport er=new ExcelReport();
		er.landRentPreserveReport(list);
		
		
		String filename = "����ũҵ��ѧ����������Ϣ��";
		
		String fullFileName = "E://landRentPreserveReport.xlsx";
		// ��ʾ�����ļ���
		response.setContentType("application/octet-stream;charset=utf-8");
		try {
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes(), "iso-8859-1") + ".xlsx");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		// ��ȡ�ļ�
		InputStream in = new FileInputStream(fullFileName);
		OutputStream out = response.getOutputStream();
		// д�ļ�
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}
		in.close();
		out.close();		
	
	   return null;
	}

}
