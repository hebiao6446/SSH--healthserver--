package com.health.entity;

import java.io.Serializable;
import javax.persistence.*;

import cn.eavic.framework.entity.BaseEntity;


/**
 * 在线测试 首页 表user映射的实体类
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="tb_onlinefirst")
public class Onlinefirst extends  BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long id;

    private String content; 
    private String imgInfo; 
    private String introduction; 


	
    public Onlinefirst() {
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

	public String getImgInfo() {
		return imgInfo;
	}

	public void setImgInfo(String imgInfo) {
		this.imgInfo = imgInfo;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getIntroduction() {
		return introduction;
	}

	




	


}