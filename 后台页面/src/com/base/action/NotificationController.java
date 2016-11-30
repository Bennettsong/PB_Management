package com.base.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.po.ApplyDept;
import com.base.po.MessageShow;
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

	// ֪ͨ����ҳ��
	@RequestMapping("jsp/notification.do")
	public String notification(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {

		// ��ȡ���еĲ���
		List<ApplyDept> applyDeptList = landApplyServiceImpl.getDepts();
		map.addAttribute("applyDeptList", applyDeptList);

		return "notification";
	}

	// ����֪ͨ
	@RequestMapping("jsp/saveNotification.do")
	public String saveNotification(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {

		String message = request.getParameter("data");
		String title = request.getParameter("title");
		// System.out.println("values:"+values);
		String insertSql = "insert into Notification(title,message) values" +
				"(\'"+ title.trim()+ "\'," +
				"\'"+ message.trim()+ "\')";
	    System.out.println(insertSql);
		notificationServiceImpl.setNotification(insertSql);

		return null;
	}

	//��ȡ֪ͨ����
	@RequestMapping("jsp/newlist.do")
	public String newlist(ModelMap map, HttpServletResponse response,HttpServletRequest request) {

		response.setContentType("text/html;charset=UTF-8");
		// ��ȡ��ǰ�ǵڼ�ҳ
		String currentPage = request.getParameter("page");
		if (currentPage == null)
			currentPage = "1";
		
		// ÿҳ�ж����У�����̶�Ϊÿҳ10��
		//String itemsPerPage = request.getParameter("itemsPerPage");
		int itemsPerPage = 10;
		List list = notificationServiceImpl
				.getNotificationInfo(currentPage, String.valueOf(itemsPerPage));
		//System.out.println("getNotification");
		if (list != null){
			map.addAttribute("notifications", list.get(0));
				
			int maxItems = (Integer) list.get(1);  // ��ȡ����¼��
			map.addAttribute("page", currentPage); // ���ص�ǰҳ��
	        int maxPage=0;
			System.out.println(maxItems);
			
			if(maxItems%itemsPerPage==0)//�ж��Ƿ�����¼��ÿҳ��¼��������
				maxPage = maxItems/itemsPerPage;
			else
				maxPage = maxItems/itemsPerPage+1;
			System.out.println(maxPage);
			map.addAttribute("totalPages",maxPage);// �������ҳ��
			
			//��������ҳ����ʼҳ�룬�����ʾ10ҳ
			List pageList = new ArrayList();
			List tempList = MessageUtils.calcPage(Integer.valueOf(currentPage), maxPage, 5);
			
			for (int i = (Integer) tempList.get(0); i <= (Integer)tempList.get(1); i++)   
			    	 pageList.add(i);
			
			map.addAttribute("pageList", pageList); // ������ʾҳ��1,2,3,4,...Ч��
			// System.out.println("messageList��Ϊ��");
		}

		return "newlist";
	}

	//��Ϣ����
	@RequestMapping("jsp/saveMessage.do")
	public String saveMessage(HttpServletRequest request, ModelMap map,
			HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");
		String content = request.getParameter("content");
		String depatment = request.getParameter("depatment");
		int isRead = 0;

		notificationServiceImpl.addMessage("ϵͳ��Ϣ", content, depatment.trim());

		return null;
	}

	//��ȡ��Ϣ�б�����
	@RequestMapping("jsp/getMessage.do")
	public String getMessage(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {

		response.setContentType("text/html;charset=UTF-8");

		// ��ȡ��ǰ�ǵڼ�ҳ
		String currentPage = request.getParameter("page");
		if (currentPage == null)
			currentPage = "1";
		// ÿҳ�ж����У�����̶�Ϊÿҳ10��
		//String itemsPerPage = request.getParameter("itemsPerPage");
		int itemsPerPage = 10;

		String userid = CookieUtils.getCookieUsername(request, response);
		List<MessageShow> messageList = null;
		List list = null;
		// System.out.println(userid);
		if (userid != "")
			list = notificationServiceImpl.getMessageInfos(userid, currentPage,
					String.valueOf(itemsPerPage));

		if (list.get(0) != null) {
			messageList = (List<MessageShow>) list.get(0);
			map.addAttribute("messageList", messageList); // չʾ����Ϣ�б�
					
			int maxItems = (Integer) list.get(1); // ��ȡ����¼��
			map.addAttribute("page", currentPage); // ���ص�ǰҳ��
			int maxPage=0;
			
			if(maxItems%itemsPerPage==0)//�ж��Ƿ�����¼��ÿҳ��¼��������
				maxPage = maxItems/itemsPerPage;
			else
				maxPage = maxItems/itemsPerPage+1;
			
			map.addAttribute("totalPages",maxPage);// �������ҳ��
			
			//��������ҳ����ʼҳ�룬�����ʾ10ҳ
			List pageList = new ArrayList();
			List tempList = MessageUtils.calcPage(Integer.valueOf(currentPage), maxPage, 5);
			
			for (int i = (Integer) tempList.get(0); i <= (Integer)tempList.get(1); i++)   
			    	 pageList.add(i);
			
			map.addAttribute("pageList", pageList); // ������ʾҳ��1,2,3,4,...Ч��
			// System.out.println("messageList��Ϊ��");
		}

		return "msgUI";
	}

	//��ȡδ����Ϣ��
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

	//������ϢΪ���Ѷ���״̬�����ء�δ����Ϣ����
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
	
	//֪ͨ����ҳ��
	@RequestMapping("jsp/newdetail.do")
	public String newdetail(ModelMap map, HttpServletRequest request,
			HttpServletResponse response) {
				
		response.setContentType("text/html;charset=UTF-8");
		// ��ȡҪ��ȡ��֪ͨ����id
		String id = request.getParameter("id");
		Notification notification =null;
		if (id != null)
			notification = notificationServiceImpl.getNotification(id);
		
		map.addAttribute("notification", notification);
		return "newdetail";
		
	}
	
		
}
