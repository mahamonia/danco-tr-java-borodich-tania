package com.danco.api;

import java.util.List;

import com.danco.training.entity.DailService;

public interface IControllerDailService {

	public void createService(DailService service);

	public int getIdForNewService();

	public void updateService(DailService service);

	public void deleteService(DailService service);

	public DailService getService(int Id);

	public List<DailService> getListDailService();

	public void setListDailService(List<DailService> servicesList);

	public List<DailService> printServicesSortedByPrice(
			List<DailService> servicesList);

	public List<DailService> printServicesSortedByName(
			List<DailService> servicesList);

	public int getServicesSumPrice(List<DailService> serviceList);

	public void changePrice(DailService service, int price);

	public List<DailService> importServicesList();

	public void exportServicesList(List<DailService> servicesList);

}
