package com.base.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.dao.AdminMangeDao;
import com.base.po.Admin;
import com.base.po.AdminFunction;
import com.base.po.UserInfo;
import com.base.utils.SqlConnectionUtils;

@Repository("adminManageDao")
public class AdminManageDaoImpl implements AdminMangeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AdminFunction> getAdminFunctionInfos() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "from AdminFunction";
		List<AdminFunction> list = null;

		try {
			Query query = session.createQuery(hql);
			list = query.list();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<Admin> getAdminInfos() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		String hql = "from Admin";
		List<Admin> list = null;

		try {
			Query query = session.createQuery(hql);
			list = query.list();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public void setAdminFunction(String sql) {
		// TODO Auto-generated method stub
		/*Session session = sessionFactory.openSession();

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
		}*/

		//ʵ����������
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn = (Connection) SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
			// ��������Ϊ���Զ��ύ  
	        conn.setAutoCommit(false);
	        pst = conn.prepareStatement(""); 
			
			// ���ִ��sql  
	        pst.addBatch(sql);  
	        // ִ�в���  
	        pst.executeBatch();  
	        // �ύ����  
	        conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SqlConnectionUtils.free(conn, pst, null);
		}
		  
        

	}
}
