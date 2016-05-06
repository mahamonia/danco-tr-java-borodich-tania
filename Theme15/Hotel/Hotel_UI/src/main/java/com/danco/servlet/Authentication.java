package com.danco.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.model.entity.User;
import com.danco.services.ServiceAdmin;


/**
 * Servlet implementation class Authentication
 */
public class Authentication extends HttpServlet {


	private IServiceAdmin  admin;
	
    public Authentication() {
        super();
     // loading HOTEL
    	admin = new ServiceAdmin();
    		DependencyInjection.getInstance().getDI(admin);
    		// add admin in list created objects
    		DependencyInjection.createdObjects.put("com.danco.api.backend.IServiceAdmin", admin);
    		admin.initData();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		List list = admin.getListUser();
		Iterator iterator = list.iterator();
		String userName=new String("");
		String passwrd=new String("");
		RequestDispatcher dispatcher = null;
		while (iterator.hasNext()) {
			User user = (User) iterator.next();
			if (user.getLogin().equals(request.getParameter("username"))&& user.getPassword().equals(request.getParameter("password"))){
				dispatcher = request.getRequestDispatcher("/main.jsp");
			}
			else dispatcher = request.getRequestDispatcher("/index.html");
			
		}
		
		}
	

}
