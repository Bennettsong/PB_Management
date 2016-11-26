package com.base.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.base.po.Land_base;
import com.base.po.Layout_InfoView;
import com.base.po.RentMaintain;

@Repository("landLayout_infoDao")
public class LandLayout_infoDaoImpl {
	
	Connection conn = null;
	CallableStatement sp = null;
	ResultSet rs = null;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Layout_InfoView> getlayout_info()
	{
		Session session=sessionFactory.openSession();		
		String hql="from Layout_InfoView";		
		List<Layout_InfoView> li=null;
		
		try {
	    	 Query query=session.createQuery(hql);	    	 
	    	 li=query.list();
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return li;
	}
	
	public List<Layout_InfoView> getlayout_info(int bid)
	{
		Session session=sessionFactory.openSession();			
		List<Layout_InfoView> li=new ArrayList<Layout_InfoView>();
		Layout_InfoView liv;
		
		try {
			conn = (Connection)SessionFactoryUtils.getDataSource(sessionFactory).getConnection();			
			sp= (CallableStatement) conn.prepareCall("{CALL baseweb.landlayout_info(?)}");  //���ʹ洢����
			sp.setInt(1,bid);	
			
			sp.execute();   //ִ�д洢����
			rs=sp.getResultSet();  //��ý����
			
			while(rs.next())    //�������������ֵ��list
			{
				liv=new Layout_InfoView();
				
				liv.setAfford(rs.getInt("affords"));
				liv.setBid(rs.getInt("bids"));
				liv.setBuildingArea(rs.getInt("buildarea"));
				liv.setHeight(rs.getInt("heights"));
				liv.setId(rs.getString("lids"));
				liv.setLandArea(rs.getInt("landarea"));
				liv.setLname(rs.getString("landname"));
				liv.setPlantingContent(rs.getString("aptplant"));
				liv.setWidth(rs.getInt("widths"));
				liv.setX(rs.getInt("xs"));
				liv.setY(rs.getInt("ys"));
				
				li.add(liv);    //�ӵ�list��
			}
	    	 
			
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			session.close();
		}
		return li;
	}

}
