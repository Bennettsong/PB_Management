package com.base.po;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Notification {

	@Id
	private int id;//֪ͨ��id
	private String message;//֪ͨ����
	private String title;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
