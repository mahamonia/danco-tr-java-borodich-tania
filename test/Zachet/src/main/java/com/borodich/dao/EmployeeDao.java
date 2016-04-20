package com.borodich.dao;

import java.util.List;

import org.hibernate.Session;
import com.borodich.entity.Employee;

public class EmployeeDao implements IBaseDao{

	@Override
	public void create(Session session, Employee model) {
		session.save(model);		
	}

	@Override
	public void update(Session session, Employee model) {
		session.merge(model);		
	}

	@Override
	public void delete(Session session, Employee model) {
		session.delete(model);		
	}

	@Override
	public Employee getById(Session session, int idModel) {
		return (Employee) session.get(Employee.class, idModel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAll(Session session) {
		return (List<Employee>) session.createCriteria(Employee.class).list();
	}

}
