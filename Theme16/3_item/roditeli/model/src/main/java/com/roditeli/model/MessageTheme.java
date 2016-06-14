package com.roditeli.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "Message_Theme")
public class MessageTheme extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9200890544634949424L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Temporal(TemporalType.DATE)
	private Date date;	
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "theme_fk") 
	@JsonManagedReference
	private Theme theme;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_fk") 
	@JsonManagedReference
	private User author;
	
	public MessageTheme() {
	}
	
	public MessageTheme(Date date, String text) {
		this.date = date;
		this.text = text;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;	
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
