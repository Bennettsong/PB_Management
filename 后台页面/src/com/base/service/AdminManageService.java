package com.base.service;

import java.util.List;

import com.base.po.Admin;
import com.base.po.AdminFunction;

public interface AdminManageService {

	 public List<AdminFunction> getAdminFunctionInfos();
	 
	 /*
	   ����˵����
	   ����ֵ��   List<Admin>
	   �������ܣ���ȡAdmin���е�������Ϣ
	 */
     public List<Admin> getAdminInfos();
     
     public void setAdminFunction(String insertSql);
}
