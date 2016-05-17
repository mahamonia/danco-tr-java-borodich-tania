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
		response.setContentType("text/html");
		
		String param = request.getParameter("sort");
		String status = request.getParameter("status");
		String resultPage = null;
	
		if (request.getParameter("view") != null) {
			String item = request.getParameter("view");
			switch (item) {
			case "View table":
				List<Room> list = (List<Room>)admin.getListRoom(status, param);
				request.setAttribute("list", list);
				resultPage = "/viewRoomTable.jsp";
				break;
			case "View amount":
				int amount = admin.getAmountRoomFree();
				request.setAttribute("amount", amount);
				break;
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
