package com.roditeli.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Rubric")
public class Rubric extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9152644752684067628L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	private String name;	
	@OneToMany(mappedBy = "rubric",fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Theme> themeList;
	
	public Rubric() {
	}

	public Rubric(String name) {
		this.name = name;
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

	public List<Theme> getThemeList() {
		return themeList;
	}

	public void setThemeList(List<Theme> themeList) {
		this.themeList = themeList;
	}

}
