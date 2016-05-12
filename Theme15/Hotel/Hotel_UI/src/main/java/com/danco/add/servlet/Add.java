package com.danco.add.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Add() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String resultPage = null;

		if (request.getParameter("combobox") != null) {
			int item = Integer.parseInt(request.getParameter("combobox"));
			switch (item) {
			case 1:
				resultPage = "/add/AddGuest.jsp";
				break;
			case 2:
				resultPage = "/add/AddRoom.jsp";
				break;
			case 3:
				resultPage = "/add/AddService.jsp";
				break;
			}
		}
		ServletContext contex = getServletContext();
		dispatcher = contex.getRequestDispatcher(resultPage);
			dispatcher.forward(request, response);
	}

}
