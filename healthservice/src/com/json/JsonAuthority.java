package com.json;

public class JsonAuthority {

	private Long id;
	private String title;
	private String url;
	private Integer isHas;//是否有权限1有0无
	public Long getId() {
		return id;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIsHas() {
		return isHas;
	}
	public void setIsHas(Integer isHas) {
		this.isHas = isHas;
	}
}
