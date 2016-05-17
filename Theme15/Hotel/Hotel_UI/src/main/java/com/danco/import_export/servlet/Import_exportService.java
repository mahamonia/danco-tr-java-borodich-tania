package com.danco.import_export.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.services.ServiceAdmin;

/**
 * Servlet implementation class Import_exportService
 */
public class Import_exportService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Import_exportService() {
        super();
        admin = new ServiceAdmin();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		admin = (IServiceAdmin) DependencyInjection.createdObjects
				.get("com.danco.api.backend.IServiceAdmin");
		
		String resultPage = null;
		String nameFile = request.getParameter("file");
		Boolean isPerformed = true;
		if (request.getParameter("import_export") != null) {
			String item = request.getParameter("import_export");
			switch (item) {
			case "import":
				isPerformed = admin.importServicesList(nameFile);
				break;
			case "export":
				isPerformed = admin.exportServicesList(nameFile);
				break;
			}
	}

		if (isPerformed){
			resultPage = "/performedOK.jsp";
		}else {
			resultPage = "/performedError.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
