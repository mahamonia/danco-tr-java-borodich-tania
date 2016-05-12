package com.danco.view.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public View() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String resultPage = null;

		if (request.getParameter("combobox") != null) {
			int item = Integer.parseInt(request.getParameter("combobox"));
			switch (item) {
			case 1:
				resultPage = "/view/ViewGuest.jsp";
				break;
			case 2:
				resultPage = "/view/ViewRoom.jsp";
				break;
			case 3:
				resultPage = "/view/ViewService.jsp";
				break;
			}
		}
		ServletContext contex = getServletContext();
		dispatcher = contex.getRequestDispatcher(resultPage);
			dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
