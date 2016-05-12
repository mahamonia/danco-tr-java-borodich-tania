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
import com.danco.model.entity.Guest;
import com.danco.services.ServiceAdmin;

/**
 * Servlet implementation class ViewGuest
 */
public class ViewGuest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IServiceAdmin admin;
    
    public ViewGuest() {
        super();
        admin = new ServiceAdmin();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		admin = (IServiceAdmin)DependencyInjection.createdObjects.get("com.danco.api.backend.IServiceAdmin");
		List<Guest> list = (List<Guest>)admin.getListGuest();
		response.setContentType("text/html");
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/ViewGuestTable.jsp");

		request.setAttribute("list", list);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
