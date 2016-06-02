package com.roditeli.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "User")
public class User extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7137487987775494366L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	private boolean status;
	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;
	private String url;

	private Authentication authen;
	private UserProfile profile;

	public User() {
	}

	public User(boolean status, Date date, String url, Authentication authen,
			UserProfile profile) {
		this.status = status;
		this.date = date;
		this.url = url;
		this.authen = authen;
		this.profile = profile;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Authentication getAuthen() {
		return authen;
	}

	public void setAuthen(Authentication authen) {
		this.authen = authen;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

}
