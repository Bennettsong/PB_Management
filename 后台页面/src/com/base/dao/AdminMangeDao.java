package com.base.dao;

import java.util.List;

import com.base.po.Admin;
import com.base.po.AdminFunction;

public interface AdminMangeDao {

	   /*
	   ����˵����
	   ����ֵ��   List<AdminFunction>,ΪȨ�޹��ܵ�AdminFunction����ļ���
	   �������ܣ�����������Ҫ���Ƶ�Ȩ�޹���ID������
	 */
     public List<AdminFunction> getAdminFunctionInfos();
     
     
     /*
	   ����˵����
	   ����ֵ��   List<Admin>
	   �������ܣ���ȡAdmin���е�������Ϣ
	 */
     public List<Admin> getAdminInfos();
     
     public void setAdminFunction(String insertSql);
     
     public long getAdminValue(String userid);
}

