package com.health.entity;

import java.io.Serializable;
import javax.persistence.*;

import cn.eavic.framework.entity.BaseEntity;


/**
 * 
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="tb_knowledge")
public class Knowledge extends  BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long id;
    private String title; 
    private String content; 
    private String introduction; 
    private String source; 
    private String imgInfo; 
    private String imgDetail; 
    private String imgVideo; 
    private String videoUrl; 
    private String creatTime;
 

	
    public Knowledge() {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getImgInfo() {
		return imgInfo;
	}

	public void setImgInfo(String imgInfo) {
		this.imgInfo = imgInfo;
	}

	public String getImgDetail() {
		return imgDetail;
	}

	public void setImgDetail(String imgDetail) {
		this.imgDetail = imgDetail;
	}

	public String getImgVideo() {
		return imgVideo;
	}

	public void setImgVideo(String imgVideo) {
		this.imgVideo = imgVideo;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getCreatTime() {
		return creatTime;
	}


	

	


}