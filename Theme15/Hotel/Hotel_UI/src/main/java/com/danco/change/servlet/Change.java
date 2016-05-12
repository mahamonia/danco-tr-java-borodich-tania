package com.danco.change.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Change
 */
@WebServlet("/Change")
public class Change extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Change() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String resultPage = null;

		if (request.getParameter("combobox") != null) {
			int item = Integer.parseInt(request.getParameter("combobox"));
			switch (item) {
			case 1:
				resultPage = "/change/ChangeRoom.jsp";
				break;
			case 2:
				resultPage = "/change/ChangeService.jsp";
				break;
			}
		}
		ServletContext contex = getServletContext();
		dispatcher = contex.getRequestDispatcher(resultPage);
			dispatcher.forward(request, response);
	}

}
