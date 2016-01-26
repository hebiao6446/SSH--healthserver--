package com.health.entity;

import java.io.Serializable;
import javax.persistence.*;

import cn.eavic.framework.entity.BaseEntity;


/**
 * 欢迎页面表user映射的实体类
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="tb_bootpage")
public class Bootpage extends  BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long id;

	@Column(name="boot_page")
	private String bootPage;

	
    public Bootpage() {
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

	public void setBootPage(String bootPage) {
		this.bootPage = bootPage;
	}

	public String getBootPage() {
		return bootPage;
	}
	

	


}