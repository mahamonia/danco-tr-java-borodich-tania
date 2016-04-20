package com.borodich.dao;

import java.util.List;

import org.hibernate.Session;

import com.borodich.entity.Employee;

public interface IBaseDao {

	public void create(Session session, Employee model);

	public void update(Session session, Employee model);

	public void delete(Session session, Employee model);

	public Employee getById(Session session, int idModel);
	
	public List<Employee> getAll(Session session);

}
