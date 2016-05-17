package com.danco.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.danco.api.backend.IServiceAdmin;
import com.danco.dependency.DependencyInjection;
import com.danco.model.entity.Audit;
import com.danco.model.entity.User;
import com.danco.services.ServiceAdmin;

public class AuditFilter implements Filter {
	private FilterConfig filterConfig;
	private IServiceAdmin admin;

	public AuditFilter() {
		admin = new ServiceAdmin();
		System.out.println("AUDIT FILTER");

	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		admin = (IServiceAdmin) DependencyInjection.createdObjects
				.get("com.danco.api.backend.IServiceAdmin");
		if (filterConfig == null) {
			return;
		}
		StringBuffer resourse = ((HttpServletRequest) request).getRequestURL();
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		User user = null;

		if(session != null && session.getAttribute("user") != null){
			user = (User) session.getAttribute("user");
			if(user !=null){

				Audit audit = new Audit(user, new Date(), resourse.toString());
				admin.save(audit);
			} 
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.filterConfig = fConfig;
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

}
