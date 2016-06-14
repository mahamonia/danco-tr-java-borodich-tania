package com.roditeli.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "User_Profile")
public class UserProfile extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3818006782096464021L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	private String name;
	private String surname;
	@Column(name = "maiden_name")
	private String maidenName;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_birth")
	private Date dateBirth;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition="enum('MOTHER', 'FATHER', 'GRANDMA', 'GRANDPA')", name="family_status")
	private FamilyStatus familyStatus;
	
	@Column(name = "e_mail")
	private String eMail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_fk") 
	@JsonManagedReference
	private Adress adress;
	
	@OneToMany(mappedBy = "parent",fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Children> childrenList;
	
	
	@OneToOne(mappedBy = "profile")
	private User user;

	public UserProfile() {
	}

	public UserProfile(String name,	Date dateBirth, FamilyStatus familyStatus, String eMail) {
		this.name = name;
		this.surname = null;
		this.maidenName = null;
		this.dateBirth = dateBirth;
		this.familyStatus = familyStatus;
		this.eMail = eMail;
		this.adress = null;
		this.childrenList = new ArrayList<Children>();
	}

	@Override
	public int getId() {
		return this.id;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMaidenName() {
		return maidenName;
	}

	public void setMaidenName(String maidenName) {
		this.maidenName = maidenName;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public FamilyStatus getFamilyStatus() {
		return familyStatus;
	}

	public void setFamilyStatus(FamilyStatus familyStatus) {
		this.familyStatus = familyStatus;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public List<Children> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<Children> childrenList) {
		this.childrenList = childrenList;
	}

}
