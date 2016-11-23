package com.base.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public final class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;//Ȩ�ޱ�id
	private String name;//�û�����˵��
	private int upow; //�û�Ȩ��ֵ
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUpow() {
		return upow;
	}

	public void setUpow(int upow) {
		this.upow = upow;
	}

	public Admin() {
		super();
	}
	
}
