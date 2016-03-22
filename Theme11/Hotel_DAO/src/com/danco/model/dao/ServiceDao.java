package com.danco.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.model.entity.Service;

public class ServiceDao implements BaseDao<Service> {

	private static final Logger LOGGER = LogManager.getLogger(ServiceDao.class);

	@Override
	public void create(Connection connect, Service model) {
		try {
			Statement statement = connect.createStatement();
			statement.executeUpdate("INSERT INTO `Hotel_service`.`Service` "
					+ "(`idService`, `name`, `price`, `idCheck`) " + "VALUES ("
					+ getIdForNewModel(connect) + ", '" + model.getName()
					+ "', '" + model.getPrice() + "', '" + model.getIdCheck()
					+ "' );");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} 
	}

	@Override
	public int getIdForNewModel(Connection connect) {
		int id = 0;
		Statement statement = null;
		try {
			statement = connect.createStatement();
			ResultSet result = statement
					.executeQuery("SELECT * FROM Service ORDER BY idService");
			
			if (result.next()){
				result.last();
				id = result.getInt(1);
			} else id = 1;

			

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 
		return id + 1;
	}

	@Override
	public void update(Connection connect, int idModel) {
		Service service = getById(connect, idModel);
		try {
			Statement statement = connect.createStatement();

			statement.executeUpdate("UPDATE  Service SET idService = "
					+ service.getId() + ", name = '" + service.getName()
					+ "', price = " + service.getPrice() + ", idCheck = "
					+ service.getIdCheck() + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} 

	}

	@Override
	public void delete(Connection connect, int idModel) {
		try {
			Statement statement = connect.createStatement();

			statement.executeUpdate("DELETE FROM Service WHERE idService = "
					+ idModel + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} 
	}

	@Override
	public Service getById(Connection connect, int idModel) {
		Statement statement = null;
		ResultSet result = null;
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Service WHERE idService ="
							+ idModel + ";");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return parseResultSet(result);
	}

	@Override
	public List<Service> getList(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Service> serviceList = new ArrayList<Service>();
		try {
			statement = connect.createStatement();
			result = statement.executeQuery("SELECT * FROM Guest;");
			while (result.next()) {
				serviceList.add(parseResultSet(result));
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return serviceList;
	}

	@Override
	public Service parseResultSet(ResultSet result) {
		Service service = null;
		try {
			if (result.next()){
			int idService = result.getInt("idService");
			String name = result.getString("name");
			int price = result.getInt("price");
			int idCheck = result.getInt("idCheck");

			service = new Service(name, price);
			service.setIdCheck(idCheck);
			service.setId(idService);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return service;
	}

	public List<Service> getListServiceSortedByPrice(Connection connect) {
		Statement statement = null;
		ResultSet result = null;
		List<Service> serviceList = new ArrayList<Service>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT * FROM Service ORDER BY price;");
			while (result.next()) {

				serviceList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return serviceList;

	}

	public List<Service> getGuestThemServices(Connection connect, int idGuest) {

		Statement statement = null;
		ResultSet result = null;
		List<Service> serviceList = new ArrayList<Service>();
		try {
			statement = connect.createStatement();
			result = statement
					.executeQuery("SELECT Service.idService, Service.name, Service.price, Service.idCheck"
							+ " FROM Service join `Check`"
							+ "ON service.idCheck = Check.idCheck"
							+ "WHERE Check.idCheck = " + idGuest + ";");
			while (result.next()) {

				serviceList.add(parseResultSet(result));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return serviceList;

	}

}
