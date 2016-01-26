package com.health.entity;

import java.io.Serializable;
import javax.persistence.*;

import cn.eavic.framework.entity.BaseEntity;


/**
 * 在线测试表user映射的实体类
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="tb_onlinetest")
public class Onlinetest extends  BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long id;


	private String topic;//题目
	private String testa;//A
	private String testb;//B
	private String testc;//C
	private String testd;//D
	private Integer correctAnswer;//正确答案

	
    public Onlinetest() {
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

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTesta() {
		return testa;
	}

	public void setTesta(String testa) {
		this.testa = testa;
	}

	public String getTestb() {
		return testb;
	}

	public void setTestb(String testb) {
		this.testb = testb;
	}

	public String getTestc() {
		return testc;
	}

	public void setTestc(String testc) {
		this.testc = testc;
	}

	public String getTestd() {
		return testd;
	}

	public void setTestd(String testd) {
		this.testd = testd;
	}

	public Integer getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Integer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}





	


}