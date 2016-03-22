package com.danco.model.dao;

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
	public void create(DataSource source, Service model) {
		try {
			Statement statement = source.openConnection().createStatement();
			statement.executeUpdate("INSERT INTO `Hotel_service`.`Service` "
					+ "(`idService`, `name`, `price`, `idCheck`) " + "VALUES ("
					+ getIdForNewModel(source) + ", '" + model.getName()
					+ "', '" + model.getPrice() + "', '" + model.getIdCheck()
					+ "' );");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}

	}

	@Override
	public int getIdForNewModel(DataSource source) {
		int id = 0;
		Statement statement = null;
		try {
			statement = source.openConnection().createStatement();
			ResultSet result = statement
					.executeQuery("SELECT * FROM Service order by idService");

			result.last();
			id = result.getInt(1);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}
		return id + 1;
	}

	@Override
	public void update(DataSource source, int idModel) {
		Service service = getById(source, idModel);
		try {
			Statement statement = source.openConnection().createStatement();

			statement.executeUpdate("UPDATE  Service SET idService = "
					+ service.getId() + ", name = '" + service.getName()
					+ "', price = " + service.getPrice() + ", idCheck = "
					+ service.getIdCheck() + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}

	}

	@Override
	public void delete(DataSource source, int idModel) {
		try {
			Statement statement = source.openConnection().createStatement();

			statement.executeUpdate("DELETE FROM Service WHERE idService = "
					+ idModel + ";");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}
	}

	@Override
	public Service getById(DataSource source, int idModel) {
		Statement statement = null;
		ResultSet result = null;
		try {
			statement = source.openConnection().createStatement();
			result = statement
					.executeQuery("SELECT * FROM Service WHERE idService ="
							+ idModel + ";");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			source.closeConnection();
		}
		return parseResultSet(result);
	}

	@Override
	public List<Service> getList(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Service> serviceList = new ArrayList<Service>();
		try {
			statement = source.openConnection().createStatement();
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
			int idService = result.getInt("idService");
			String name = result.getString("name");
			int price = result.getInt("price");
			int idCheck = result.getInt("idCheck");

			service = new Service(name, price);
			service.setIdCheck(idCheck);
			service.setId(idService);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return service;
	}

	public List<Service> getListServiceSortedByPrice(DataSource source) {
		Statement statement = null;
		ResultSet result = null;
		List<Service> serviceList = new ArrayList<Service>();
		try {
			statement = source.openConnection().createStatement();
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

	public List<Service> getGuestThemServices(DataSource source, int idGuest) {

		Statement statement = null;
		ResultSet result = null;
		List<Service> serviceList = new ArrayList<Service>();
		try {
			statement = source.openConnection().createStatement();
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
