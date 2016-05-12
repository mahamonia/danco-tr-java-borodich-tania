package com.danco.change.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.services.ServiceAdmin;

/**
 * Servlet implementation class ChangeService
 */
@WebServlet("/ChangeService")
public class ChangeService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;

	public ChangeService() {
		super();
		admin = new ServiceAdmin();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		admin = (IServiceAdmin) DependencyInjection.createdObjects
				.get("com.danco.api.backend.IServiceAdmin");

		int idService = Integer.parseInt(request.getParameter("id"));
		int price = Integer.parseInt(request.getParameter("price"));
		admin.changeServicePrice(idService, price);

		RequestDispatcher dispatcher = null;
		String resultPage = " .jsp";

		ServletContext contex = getServletContext();
		dispatcher = contex.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
