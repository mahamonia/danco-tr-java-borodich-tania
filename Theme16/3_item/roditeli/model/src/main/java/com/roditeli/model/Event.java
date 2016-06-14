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
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Event")
public class Event extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3082440532758064130L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_fk")
	@JsonManagedReference
	private User author;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipient_fk")
	@JsonManagedReference
	private User recipient;
	
	@OneToMany(mappedBy = "event",fetch = FetchType.LAZY)
	@JsonBackReference
	private List<CommentEvent> commentList;
	 
	public Event() {
	}

	public Event(Date date, String text) {
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public List<CommentEvent> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentEvent> commentList) {
		this.commentList = commentList;
	}
	
}
