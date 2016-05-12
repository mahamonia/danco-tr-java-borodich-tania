package com.danco.main.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.model.entity.User;
import com.danco.services.ServiceAdmin;

public class Authentication extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;
	private List<User> users = new ArrayList<User>();

	public Authentication() {
		super();
		// loading HOTEL
		admin = new ServiceAdmin();
		DependencyInjection.getInstance().getDI(admin);
		// add admin in list created objects
		admin.initData();
		DependencyInjection.createdObjects.put(
				"com.danco.api.backend.IServiceAdmin", admin);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean isUser = false;
		HttpSession session = request.getSession();

		RequestDispatcher dispatcher = null;
		String resultPage = null;
		users = admin.getListUser();
		for (User user : users) {
			if (user.getLogin().equals(request.getParameter("username"))
					&& user.getPassword().equals(
							request.getParameter("password"))) {
				isUser = true;
				session.setAttribute("user", user);
			}
		}

		if (isUser) {
			resultPage = "/view/View.jsp";
		} else {
			resultPage = "/index.html";
		}

		ServletContext contex = getServletContext();
		dispatcher = contex.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
