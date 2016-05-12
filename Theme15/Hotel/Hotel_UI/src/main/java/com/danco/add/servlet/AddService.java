package com.danco.add.servlet;

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
import com.danco.model.entity.Service;
import com.danco.services.ServiceAdmin;

/**
 * Servlet implementation class AddService
 */
@WebServlet("/AddService")
public class AddService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;
	
    public AddService() {
        super();
        admin = new ServiceAdmin();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		admin = (IServiceAdmin) DependencyInjection.createdObjects
				.get("com.danco.api.backend.IServiceAdmin");

		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		Service service = new Service(name, price);
		admin.createService(service);

		RequestDispatcher dispatcher = null;
		String resultPage = "add/AddSuccessfully.jsp";

		ServletContext contex = getServletContext();
		dispatcher = contex.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
