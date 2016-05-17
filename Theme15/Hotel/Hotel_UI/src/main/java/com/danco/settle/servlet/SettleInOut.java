package com.danco.settle.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.model.entity.Chek;
import com.danco.model.entity.Room;
import com.danco.services.ServiceAdmin;

/**
 * Servlet implementation class SettleInOut
 */

public class SettleInOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;

	public SettleInOut() {
		super();
		admin = new ServiceAdmin();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		admin = (IServiceAdmin) DependencyInjection.createdObjects
				.get("com.danco.api.backend.IServiceAdmin");

		int idGuest = Integer.parseInt(request.getParameter("id"));
		String resultPage = "";
		List<Chek> chekList = admin.getChekListForIdGuest(idGuest);
		if (chekList == null) {
			resultPage = "/addGuest.jsp";
		}
		boolean isNotPaid = false;
		for (Chek chek : chekList) {
			if (!chek.isStatus()) {
				isNotPaid = true;
			}
		}
		if (isNotPaid) {
			resultPage = "/settleOut.jsp";
		} else {
			List<Room> listFreeRoom = admin.getListRoom("FREE", "id");
			request.setAttribute("list", listFreeRoom);
			resultPage = "/settleIn.jsp";
		}

		response.setContentType("text/html");
		request.setAttribute("idGuest", idGuest);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
