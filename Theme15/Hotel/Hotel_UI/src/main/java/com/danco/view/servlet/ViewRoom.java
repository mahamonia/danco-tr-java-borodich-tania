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
import com.danco.model.entity.Room;
import com.danco.services.ServiceAdmin;

/**
 * Servlet implementation class ViewRoom
 */
public class ViewRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;   

    public ViewRoom() {
        super();
        admin = new ServiceAdmin();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		admin = (IServiceAdmin)DependencyInjection.createdObjects.get("com.danco.api.backend.IServiceAdmin");
		String param = request.getParameter("sort");
		String status = request.getParameter("status");
		List<Room> list = (List<Room>)admin.getListRoom(status, param);
		response.setContentType("text/html");
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/ViewRoomTable.jsp");

		request.setAttribute("list", list);
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
