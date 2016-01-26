package com.health.entity;

import java.io.Serializable;
import javax.persistence.*;

import cn.eavic.framework.entity.BaseEntity;


/**
 * 技术支持表user映射的实体类
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="tb_supports")
public class Supports extends  BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long id;


	private String title;//标题
	
	private String content;//内容



	
    public Supports() {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}




	


}