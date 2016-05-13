package com.danco.settle.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.model.entity.Chek;
import com.danco.model.entity.Guest;
import com.danco.services.ServiceAdmin;

/**
 * Servlet implementation class SettleInOut
 */
@WebServlet("/SettleInOut")
public class SettleInOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;

	public SettleInOut() {
		super();
		admin = new ServiceAdmin();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		admin = (IServiceAdmin) DependencyInjection.createdObjects
				.get("com.danco.api.backend.IServiceAdmin");

		int id = Integer.parseInt(request.getParameter("id"));
		Guest guest = admin.getGuestById(id);
		String resultPage = "";
		List<Chek> chekList = guest.getChekList();
		if (chekList == null) {
			resultPage = "settle/SettleIn.jsp";
		}
		boolean isNotPaid = false;
		for (Chek chek : chekList) {
			if (!chek.isStatus()) {
				isNotPaid = true;
			}
		}
		if (isNotPaid) {
			resultPage = "settle/SettleOut.jsp";
		} else {
			resultPage = "/settle/SettleIn.jsp";
		}

		response.setContentType("text/html");
		request.setAttribute("guest", guest);
		
		RequestDispatcher dispatcher = null;
		ServletContext contex = getServletContext();
		dispatcher = contex.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
