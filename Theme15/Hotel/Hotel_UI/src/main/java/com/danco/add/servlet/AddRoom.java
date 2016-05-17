package com.danco.add.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.model.entity.Room;
import com.danco.model.entity.Status;
import com.danco.services.ServiceAdmin;

/**
 * Servlet implementation class AddRoom
 */
public class AddRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;
	
    public AddRoom() {
        super();
        admin = new ServiceAdmin();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		admin = (IServiceAdmin) DependencyInjection.createdObjects
				.get("com.danco.api.backend.IServiceAdmin");

		int number = Integer.parseInt(request.getParameter("number"));
		int content = Integer.parseInt(request.getParameter("content"));
		int stars = Integer.parseInt(request.getParameter("stars"));
		Status status = Status.valueOf(request.getParameter("status"));
		int price = Integer.parseInt(request.getParameter("price"));
		Room room = new Room(number, content, status, stars, price);
		String resultPage = null;
		
		if (admin.createRoom(room)){
			resultPage = "/performedOK.jsp";
		}else {
			resultPage = "/performedError.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
