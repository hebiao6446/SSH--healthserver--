package com.health.entity;

import java.io.Serializable;
import javax.persistence.*;

import cn.eavic.framework.entity.BaseEntity;


/**
 * 后台管理用户表user映射的实体类
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="tb_user_info")
public class User extends  BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long id;

	@Column(name="pass_word")
	private String passWord;

	private String account;

    public User() {
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
	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}


	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	


}