package com.borodich.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.borodich.dao.EmployeeDao;
import com.borodich.dao.IBaseDao;
import com.borodich.entity.Employee;
import com.borodich.util.ParseUtilityForCSV;
import com.borodich.util.UtilityForHibernate;

public class Controller {
	
	private IBaseDao dao;
	private ParseUtilityForCSV util;
	private SessionFactory factory = UtilityForHibernate.getSessionFactory();
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(Controller.class);
	
	public Controller(String fileName) {
		if (fileName == null || fileName.isEmpty()){
			fileName= "file.csv";
		}
		this.util = new ParseUtilityForCSV(fileName);
		this.dao = new EmployeeDao();
	}
	
	public void importAndUpdateData(){
		
		Session session = factory.openSession();
		session.beginTransaction();
		try {
			List<Employee> employeeList = util.importData();
			for (Employee employee : employeeList) {
				System.out.println(employee.getId()+". "+employee.getfName()+" work "+employee.getTitle());
				if (dao.getById(session, employee.getId())!=null){
					dao.update(session, employee);
				} else{
					dao.create(session, employee);
				}
				System.out.println(employee.getId()+". "+employee.getfName()+" work "+employee.getTitle());
				System.out.println();
					
			}			
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e.getMessage());
			session.getTransaction().rollback();
		} finally {			
			
			session.close();
			factory.close();
		}		
		
	}

	public void exportData(){
		Session session = factory.openSession();
		session.beginTransaction();
		try {
			List<Employee> employeeList = dao.getAll(session);
			for (Employee employee : employeeList) {
				System.out.println(employee.getfName()+" work "+employee.getTitle());
			}
			util.exportData(employeeList);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.error(e.getMessage());
			session.getTransaction().rollback();
		} finally {
			
			session.flush();
			session.close();
			factory.close();
		}
		
	}
}
