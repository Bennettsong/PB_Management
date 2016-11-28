package com.base.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.Admin;
import com.base.po.AdminFunction;
import com.base.po.ApplyDept;
import com.base.po.Message;
import com.base.po.Notification;
import com.base.serviceImpl.LandApplyServiceImpl;
import com.base.serviceImpl.NotificationServiceImpl;
import com.base.utils.CookieUtils;
import com.base.utils.MessageUtils;

@Controller("NotificationController")
public class NotificationController {

	@Autowired
	private NotificationServiceImpl notificationServiceImpl;

	@Autowired
	private LandApplyServiceImpl landApplyServiceImpl;

	// ����֪ͨ��Ϣ�����ݿ�
	@RequestMapping("jsp/notification.do")
	public String notification(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {

		// ��ȡ���еĲ���
		List<ApplyDept> applyDeptList = landApplyServiceImpl.getDepts();
		map.addAttribute("applyDeptList", applyDeptList);

		return "notification";
	}

	// ����֪ͨ��Ϣ�����ݿ�
	@RequestMapping("jsp/saveNotification.do")
	public String saveNotification(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {

		String values = request.getParameter("data");
		// System.out.println("values:"+values);
		String insertSql = "insert into Notification(id,message) values(1,\""
				+ values.trim()
				+ "\") on duplicate key update message=values(message)";
		// System.out.println(insertSql);
		notificationServiceImpl.setNotification(insertSql);

		return null;
	}

	@RequestMapping("jsp/getNotification.do")
	public String getNotification(ModelMap map, HttpServletResponse response,HttpServletRequest request) {

		response.setContentType("text/html;charset=UTF-8");
		// ��ȡ��ǰ�ǵڼ�ҳ
		String currentPage = request.getParameter("page");
		if (currentPage == null)
			currentPage = "1";
		
		// ÿҳ�ж����У�����̶�Ϊÿҳ10��
		String itemsPerPage = request.getParameter("itemsPerPage");
		itemsPerPage = "10";
		List list = notificationServiceImpl
				.getNotificationInfo(currentPage, itemsPerPage);
		System.out.println("getNotification");
		if (list != null){
			map.addAttribute("notifications", list.get(0));
			
		
			int maxItems = (Integer) list.get(1); // ��ȡ����¼��
			map.addAttribute("page", currentPage); // ���ص�ǰҳ��
			map.addAttribute("totalPages",
					maxItems / Integer.valueOf(itemsPerPage) + 1);// �������ҳ��
			
			
			//��������ҳ����ʼҳ�룬�����ʾ10ҳ
			List pageList = new ArrayList();
			List tempList = MessageUtils.calcPage(Integer.valueOf(currentPage), maxItems / Integer.valueOf(itemsPerPage) + 1, 5);
			
			for (int i = (Integer) tempList.get(0); i <= (Integer)tempList.get(1); i++)   
			    	 pageList.add(i);
			
			map.addAttribute("pageList", pageList); // ������ʾҳ��1,2,3,4,...Ч��
			// System.out.println("messageList��Ϊ��");
		}

		return "newlist";
	}

	// ����֪ͨ��Ϣ�����ݿ�
	@RequestMapping("jsp/saveMessage.do")
	public String saveMessage(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");
		String content = request.getParameter("content");
		String depatment = request.getParameter("depatment");
		int isRead = 0;

		notificationServiceImpl.addMessage("ϵͳ֪ͨ", content, depatment.trim());

		return null;
	}

	@RequestMapping("jsp/getMessage.do")
	public String getMessage(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ��ǰ�ǵڼ�ҳ
		String currentPage = request.getParameter("page");
		if (currentPage == null)
			currentPage = "1";
		// ÿҳ�ж����У�����̶�Ϊÿҳ10��
		String itemsPerPage = request.getParameter("itemsPerPage");
		itemsPerPage = "10";

		String userid = CookieUtils.getCookieUsername(request, response);
		List<Message> messageList = null;
		List list = null;
		// System.out.println(userid);
		if (userid != "")
			list = notificationServiceImpl.getMessageInfos(userid, currentPage,
					itemsPerPage);

		if (list != null) {
			map.addAttribute("messageList", list.get(0)); // չʾ����Ϣ�б�

			int maxItems = (Integer) list.get(1); // ��ȡ����¼��
			map.addAttribute("page", currentPage); // ���ص�ǰҳ��
			map.addAttribute("totalPages",
					maxItems / Integer.valueOf(itemsPerPage) + 1);// �������ҳ��
			
			
			//��������ҳ����ʼҳ�룬�����ʾ10ҳ
			List pageList = new ArrayList();
			List tempList = MessageUtils.calcPage(Integer.valueOf(currentPage), maxItems / Integer.valueOf(itemsPerPage) + 1, 5);
			
			for (int i = (Integer) tempList.get(0); i <= (Integer)tempList.get(1); i++)   
			    	 pageList.add(i);
			
			map.addAttribute("pageList", pageList); // ������ʾҳ��1,2,3,4,...Ч��
			// System.out.println("messageList��Ϊ��");
		}

		return "msgUI";
	}

	@RequestMapping("jsp/getNoReadMessageCount.do")
	public String getNoReadMessageCount(ModelMap map,
			HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String userid = CookieUtils.getCookieUsername(request, response);
		int number = notificationServiceImpl.getNoreadMessageCount(userid);
		// System.out.println("δ����Ϣ��Ϊ��"+number);
		try {
			if (number == 0)
				response.getWriter().print("");
			else
				response.getWriter().print(number);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@RequestMapping("jsp/setReadMessage.do")
	public String setReadMessage(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		notificationServiceImpl.setReadMessage(Integer.valueOf(id));

		// ��ȡδ����Ϣ����������Ϣ����
		String userid = CookieUtils.getCookieUsername(request, response);
		int number = notificationServiceImpl.getNoreadMessageCount(userid);
		// System.out.println("δ����Ϣ��Ϊ��"+number);
		try {
			if (number == 0)
				response.getWriter().print("");
			else
				response.getWriter().print(number);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
