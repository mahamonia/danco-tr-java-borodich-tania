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
 * Servlet implementation class LogOut
 */
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IServiceAdmin admin;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOut() {
        super();
        admin = new ServiceAdmin();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		admin = (IServiceAdmin) DependencyInjection.createdObjects
				.get("com.danco.api.backend.IServiceAdmin");
		
		HttpSession session = request.getSession();

		String resultPage = null;
		StringBuffer resourse = ((HttpServletRequest) request).getRequestURL();
		User user = (User)session.getAttribute("user");
        if (user != null) {
            session.removeAttribute("user");
            Audit audit = new Audit(user, new Date(), resourse.toString());
            admin.save(audit);
            resultPage = "/end.jsp";
        }
       
        RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
