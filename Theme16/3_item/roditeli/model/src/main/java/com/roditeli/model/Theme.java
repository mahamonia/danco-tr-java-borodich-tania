package com.roditeli.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Theme")
public class Theme extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5331285691846847797L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name="rubric_fk")
	@JsonIgnore
	private Rubric rubric;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_fk") 
	@JsonIgnore
	private User author;
	
	@OneToMany(mappedBy = "theme",fetch = FetchType.LAZY)
	@JsonIgnore
	private List<MessageTheme> messageList;
	
	public Theme() {
	}

	public Theme(String name, Date date, User author, Rubric rubric) {
		this.name = name;
		this.date = date;
		this.author = author;
		this.rubric = rubric;
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Rubric getRubric() {
		return rubric;
	}

	public void setRubric(Rubric rubric) {
		this.rubric = rubric;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<MessageTheme> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageTheme> messageList) {
		this.messageList = messageList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
