package com.danco.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.services.ServiceAdmin;


/**
 * Servlet implementation class ViewRoomsServlet
 */
public class ViewRoomsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8918630907360265566L;
	private IServiceAdmin admin;
	
    public ViewRoomsServlet() {
        super();
     // loading HOTEL
     		this.admin = new ServiceAdmin();
     		DependencyInjection.getInstance().getDI(this.admin);
     		// add admin in list created objects

     		DependencyInjection.createdObjects.put("com.danco.api.backend.IServiceAdmin", this.admin);
     		this.admin.initData();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List list = admin.getListRoom();
		response.setContentType("text/html");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ViewRooms.jsp");

		request.setAttribute("results", list);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
