package com.health.entity;

import java.io.Serializable;
import javax.persistence.*;

import cn.eavic.framework.entity.BaseEntity;


/**
 * 版本更新表user映射的实体类
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="tb_version")
public class Version extends  BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long id;


	private String title;//标题
	
	private String urlNum;//版本内容-------活版本号

	private String urlpath;//网址

	private String type;//类型


	
    public Version() {
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

	public String getUrlNum() {
		return urlNum;
	}

	public void setUrlNum(String urlNum) {
		this.urlNum = urlNum;
	}

	public String getUrlpath() {
		return urlpath;
	}

	public void setUrlpath(String urlpath) {
		this.urlpath = urlpath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	


}