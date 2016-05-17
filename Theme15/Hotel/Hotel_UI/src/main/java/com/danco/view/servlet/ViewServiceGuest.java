package com.danco.view.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.model.entity.Guest;
import com.danco.model.entity.Service;
import com.danco.services.ServiceAdmin;

/**
 * Servlet implementation class ViewServiceGuest
 */
public class ViewServiceGuest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;
	
    public ViewServiceGuest() {
        super();
        admin = new ServiceAdmin();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		admin = (IServiceAdmin) DependencyInjection.createdObjects
				.get("com.danco.api.backend.IServiceAdmin");

		ServletContext contex = getServletContext();

		int idGuest = (Integer)contex.getAttribute("idGuest");

		Guest guest = admin.getGuestById(idGuest);
		List<Service> list = (List<Service>) admin.getGuestThemServices(idGuest);

		response.setContentType("text/html");
		request.setAttribute("list", list);
		request.setAttribute("guest", guest);
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/viewServiceTable.jsp");

		dispatcher.forward(request, response);
	}

	
}
