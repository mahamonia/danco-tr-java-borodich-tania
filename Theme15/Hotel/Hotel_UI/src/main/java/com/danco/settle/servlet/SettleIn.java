package com.danco.settle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.services.ServiceAdmin;

/**
 * Servlet implementation class SettleIn
 */
@WebServlet("/SettleIn")
public class SettleIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;
	
    public SettleIn() {
        super();
        admin = new ServiceAdmin();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		admin = (IServiceAdmin) DependencyInjection.createdObjects
				.get("com.danco.api.backend.IServiceAdmin");
		
		int idGuest = Integer.parseInt(request.getParameter("idGuest"));
		int idRoom = Integer.parseInt(request.getParameter("idRoom"));
		
		admin.settleGuestInRoom(idGuest, idRoom, null, null);
		
	}

}
