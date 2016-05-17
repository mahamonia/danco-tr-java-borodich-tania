package com.danco.settle.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.services.ServiceAdmin;

/**
 * Servlet implementation class SettleIn
 */

public class SettleIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;
	
    public SettleIn() {
        super();
        admin = new ServiceAdmin();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		admin = (IServiceAdmin) DependencyInjection.createdObjects
				.get("com.danco.api.backend.IServiceAdmin");

		ServletContext contex = getServletContext();
		int idGuest = (Integer)contex.getAttribute("idGuest");	
		int idRoom = (Integer)contex.getAttribute("idRoom");
		
		String dateInSettle = request.getParameter("dateInSettle");
		String dateOutSettle = request.getParameter("dateOutSettle");
		String resultPage = null;
		
		if (admin.settleGuestInRoom(idGuest, idRoom, dateInSettle, dateOutSettle)){
			resultPage = "/performedOK.jsp";
		}else {
			resultPage = "/performedError.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}


}
