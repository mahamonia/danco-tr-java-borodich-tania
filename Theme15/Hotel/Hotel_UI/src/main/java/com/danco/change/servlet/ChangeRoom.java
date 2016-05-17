package com.danco.change.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.services.ServiceAdmin;

public class ChangeRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;

	public ChangeRoom() {
		super();
		admin = new ServiceAdmin();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		admin = (IServiceAdmin) DependencyInjection.createdObjects
				.get("com.danco.api.backend.IServiceAdmin");

		int idRoom = Integer.parseInt(request.getParameter("id"));
		Boolean isPerformed = true;
		if (request.getParameter("change") != null) {
			String item = request.getParameter("change");
			switch (item) {
			case "price":
				int price = Integer.parseInt(request.getParameter("value"));
				isPerformed = admin.changeRoomPrice(idRoom, price);
				break;
			case "status":
				String status = request.getParameter("value");
				isPerformed = admin.changeRoomStatus(idRoom, status);
				break;
			}
		}

		String resultPage = null;
		
		if (isPerformed){
			resultPage = "/performedOK.jsp";
		}else {
			resultPage = "/performedError.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
