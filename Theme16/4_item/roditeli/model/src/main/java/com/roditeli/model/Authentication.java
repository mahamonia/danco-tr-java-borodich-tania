package com.roditeli.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Authentication")
public class Authentication extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7381216201354573953L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	private String login;
	private String password;

	public Authentication() {
	}

	public Authentication(String login, String password) {
		this.login = login;
		this.password = password;

	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
