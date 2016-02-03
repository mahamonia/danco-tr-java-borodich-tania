package com.danco.api;

import java.util.List;

import com.danco.training.entity.AdditionalService;
import com.danco.training.entity.Service;

public interface IControllerAdditionalService {

	public void createService(AdditionalService service);

	public int getIdForNewService();

	public void deleteService(AdditionalService service);

	public void updateService(AdditionalService service);

	public Service getService(int Id);

	public List<AdditionalService> getListAdditionalService();

	public void setListAdditionalService(List<AdditionalService> servicesList);

	public List<AdditionalService> printServicesSortedByPrice(
			List<AdditionalService> servicesList);

	public List<AdditionalService> printServicesSortedByName(
			List<AdditionalService> servicesList);

	public int getServicesSumPrice(List<AdditionalService> servicesList);

	public void changePrice(AdditionalService service, int price);

	public void changeAdditionalPrice(int idService, int price);

	public List<AdditionalService> importServicesList();

	public void exportServicesList(List<AdditionalService> servicesList);

}
