package com.danco.main.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.model.entity.Audit;
import com.danco.model.entity.User;
import com.danco.services.ServiceAdmin;

/**
 * Servlet implementation class LogIn
 */

public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;

	public LogIn() {
		super();
		// loading HOTEL
		admin = new ServiceAdmin();
		DependencyInjection.getInstance().getDI(admin);
		// add admin in list created objects
		admin.initData();
		DependencyInjection.createdObjects.put(
				"com.danco.api.backend.IServiceAdmin", admin);
		System.out.println("LOG IN");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		HttpSession session = request.getSession(true);
		StringBuffer resourse = ((HttpServletRequest) request).getRequestURL();

		String resultPage = null;
		user = admin.getUserByLogin(request.getParameter("login"));
		
		if (user != null
				&& user.getPassword().equals(request.getParameter("password"))) {
			session.setAttribute("user", user);
			resultPage = "/view.jsp";
			Audit audit = new Audit(user, new Date(), resourse.toString());
			admin.save(audit);
		} else {
			resultPage = "/index.html";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
