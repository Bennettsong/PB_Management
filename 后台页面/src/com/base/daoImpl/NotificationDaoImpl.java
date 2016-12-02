package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.NotificationDao;
import com.base.po.Admin;
import com.base.po.Message;
import com.base.po.MessageShow;
import com.base.po.Notification;
import com.base.utils.SqlConnectionUtils;

@Repository("notificationDao")
public class NotificationDaoImpl implements NotificationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void setNotification(String insertSql) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		int i = 0;
		Transaction t = null;
		try {
			t = session.beginTransaction();
			Query q = session.createSQLQuery(insertSql);
			q.executeUpdate();
			t.commit();
		} catch (Exception ex) {
			if (t != null) {
				t.rollback();
			}
		} finally {
			session.close();
		}
	}

	@Override
	public List getNotificationInfo(String currentPage,String itemsPerPage) {
		// TODO Auto-generated method stub
				
		Connection conn=null;
		CallableStatement sp=null; 
		ResultSet rs=null;
		List list = new ArrayList();
		List<Notification> listNews = new ArrayList<Notification>();
		try
		{
			conn = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			
			sp = conn.prepareCall("{call baseweb.find_notification(?,?,?)}");  //���ô洢����
			sp.setInt(1, Integer.valueOf(currentPage));
			sp.setInt(2, Integer.valueOf(itemsPerPage));
			sp.registerOutParameter(3,java.sql.Types.INTEGER);
			sp.execute(); // ִ�д洢���� 
			int maxItems=sp.getInt(3);//�����������
			//���ս����
			rs=sp.getResultSet();   //��ý����
			
			while(rs.next())
			{
				Notification notification=new Notification();
				notification.setId(rs.getInt("id"));
				notification.setMessage(rs.getString("message"));
				notification.setTitle(rs.getString("title"));
				listNews.add(notification);
			}
			
			//��������б�Ϊ�ղ���ӵ�List
			if(CollectionUtils.isNotEmpty(listNews)){
			   list.add(listNews);
			   list.add(maxItems);
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);
		}
		return list;

	}

	@Override
	public void addMessage(String title, String content, String department) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		int dept = Integer.valueOf(department);
		SQLQuery sqlQuery=null;
		// hibernate���ô洢����(�޷��ز���)
		try {
            if(dept==0){
            	sqlQuery = session
    					.createSQLQuery("{CALL baseweb.`send_message`(?,?)}");
    			sqlQuery.setString(0, title);
    			sqlQuery.setString(1, content);
    			sqlQuery.executeUpdate();
			 
            }else{
            	sqlQuery = session
					.createSQLQuery("{CALL baseweb.`send_deptmessage`(?,?,?)}");
			sqlQuery.setInteger(0, dept);
			sqlQuery.setString(1, title);
			sqlQuery.setString(2, content);
			
			sqlQuery.executeUpdate();
            }
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
	}

	@Override
	public List getMessageInfos(String userid,String currentPage,String itemsPerPage) {
		// TODO Auto-generated method stub
			
		//ת��JDBCģʽ
				Connection conn=null;
				CallableStatement sp=null; 
				ResultSet rs=null;
				List list = new ArrayList();
				try
				{
					conn = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
					
					sp = conn.prepareCall("{call baseweb.find_message(?,?,?,?)}");  //���ô洢����
					sp.setString(1, userid);
					sp.setInt(2, Integer.valueOf(currentPage));
					sp.setInt(3, Integer.valueOf(itemsPerPage));
					sp.registerOutParameter(4,java.sql.Types.INTEGER);
					sp.execute(); // ִ�д洢���� 
					int maxItems=sp.getInt(4);//�����������
					//���ս����
					rs=sp.getResultSet();   //��ý����
					
					//messagelist�б�����ȫ���浽 message���󣬻�������һ������sn,������ʾ��ǰ����Ϣ�����
					int sn= (Integer.valueOf(currentPage)-1)*10;
					List<MessageShow> messagelist=new ArrayList<MessageShow>();//����һ����Ӧ���͵�list����ȥ����
					while(rs.next())
					{
						MessageShow me=new MessageShow();
						me.setId(rs.getInt("id"));
						me.setContent(rs.getString("content"));
						me.setIsRead(rs.getInt("isRead"));
						me.setTime(rs.getString("time"));
						me.setTitle(rs.getString("title"));
						me.setUserid(rs.getString("userid"));
						sn++; //��һ����¼�ͱ�һ����ţ���Ŵ�1��ʼ��������
						me.setSn(sn);
						messagelist.add(me);
					}
					
					//�����Ϣ�б�Ϊ�ղ���ӵ�List
					if(CollectionUtils.isNotEmpty(messagelist)){
					   list.add(messagelist);
					   list.add(maxItems);
					}
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					SqlConnectionUtils.free(conn, sp, rs);
				}
		
		
		return list;
	}

	@Override
	public List<Message> getMessageTop5Infos(String userid) {
		// TODO Auto-generated method stub
		/*
		 * Session session = sessionFactory.openSession(); String hql =
		 * "from Message where userid='"+userid+"' order by time desc limit 0,4"
		 * ; System.out.println(hql); List<Message> list = null;
		 * 
		 * try { Query query = session.createQuery(hql); list = query.list();
		 * 
		 * } catch (Exception e) { System.out.println(e); } finally {
		 * session.close(); }
		 */
		CallableStatement sp = null;
		Connection conn = null;
		ResultSet rs = null;
		List<Message> list = new ArrayList<Message>();
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL baseweb.top_message(?)}"); // ���ʹ洢����
			sp.setString(1, userid);
			sp.execute(); // ִ�д洢����

			rs = sp.getResultSet(); // ��ý����
			int i = 0;

			while (rs.next()) // �������������ֵ��list
			{
				Message message = new Message();
				message.setId(rs.getInt("id"));
				message.setTitle(rs.getString("title"));
				message.setUserid(rs.getString("userid"));
				message.setTime(rs.getString("time"));
				message.setContent(rs.getString("content"));
				message.setIsRead(rs.getInt("isRead"));
				// System.out.println(message.getContent());

				list.add(message);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);
		}

		return list;
	}

	@Override
	public int getNoreadMessageCount(String userid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "from Message where userid='" + userid + "' and isRead=0";
		List<Message> list = null;
		int number = 0;
		try {
			Query query = session.createQuery(hql);
			list = query.list();
			if (list != null)
				number = list.size();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return number;
	}

	@Override
	public void setReadMessage(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "update Message set isRead='1' where id=" + id;
		Transaction t = null;
		try {
			t = session.beginTransaction();
			Query q = session.createSQLQuery(hql);
			q.executeUpdate();
			t.commit();
		} catch (Exception ex) {
			if (t != null) {
				t.rollback();
			}
		} finally {
			session.close();
		}
	}

	@Override
	public Notification getNotification(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "from Notification where id=" + id + "";
		List<Notification> list=null;
		try {
			Query query = session.createQuery(hql);
			list =  query.list();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return list.get(0);
	}

	@Override
	public List<Notification> getNotificationTop5Infos() {
		// TODO Auto-generated method stub
		
		CallableStatement sp = null;
		Connection conn = null;
		ResultSet rs = null;
		List<Notification> list = new ArrayList<Notification>();
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(
					sessionFactory).getConnection();
			sp = (CallableStatement) conn
					.prepareCall("{CALL baseweb.top_notification()}"); // ���ʹ洢����
			
			sp.execute(); // ִ�д洢����

			rs = sp.getResultSet(); // ��ý����
			int i = 0;

			while (rs.next()) // �������������ֵ��list
			{
				Notification notification = new Notification();
				notification.setId(rs.getInt("id"));
				notification.setTitle(rs.getString("title"));
				notification.setMessage(rs.getString("message"));
				
				list.add(notification);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SqlConnectionUtils.free(conn, sp, rs);
		}

		return list;
	}
}
