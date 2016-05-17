package com.danco.view.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.model.entity.Service;
import com.danco.services.ServiceAdmin;

/**
 * Servlet implementation class ViewService
 */
public class ViewService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;

	public ViewService() {
		super();
		admin = new ServiceAdmin();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		admin = (IServiceAdmin) DependencyInjection.createdObjects
				.get("com.danco.api.backend.IServiceAdmin");
		String param = request.getParameter("sort");
		List<Service> list = (List<Service>) admin.getListService(param);

		response.setContentType("text/html");
		request.setAttribute("list", list);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/viewServiceTable.jsp");

		dispatcher.forward(request, response);
	}

}
