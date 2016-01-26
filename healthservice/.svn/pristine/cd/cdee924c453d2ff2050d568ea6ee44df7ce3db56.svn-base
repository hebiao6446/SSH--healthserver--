package com.health.entity;

import java.io.Serializable;
import javax.persistence.*;

import cn.eavic.framework.entity.BaseEntity;


/**
 * 反馈意见表user映射的实体类
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="tb_message")
public class Message extends  BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	private Long id;
	
	private String name;//姓名
	
	private String phone;//反馈电话
	
	private String address;//反馈地址
	
	private String content; //反馈内容
	
	private String email;//反馈邮箱
	
	@Column(name="creat_time")
	private String creatTime;
	private Integer correct;
	private Integer total;
	private Integer timeSpace;
	private String endTime;
	private String phoneId;//手机的唯一标志

	
    public Message() {
    }

    @Id
	@Column(name = "id", unique = true, nullable = false, insertable=false, updatable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public String getPhoneId() {
		return phoneId;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotal() {
		return total;
	}

	public void setCorrect(Integer correct) {
		this.correct = correct;
	}

	public Integer getCorrect() {
		return correct;
	}

	public void setTimeSpace(Integer timeSpace) {
		this.timeSpace = timeSpace;
	}

	public Integer getTimeSpace() {
		return timeSpace;
	}

	

	


}