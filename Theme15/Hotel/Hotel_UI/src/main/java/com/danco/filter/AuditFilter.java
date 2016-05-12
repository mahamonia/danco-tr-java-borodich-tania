package com.danco.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.danco.model.entity.User;

/**
 * Servlet Filter implementation class AuditFilter
 */
public class AuditFilter implements Filter {
	private FilterConfig filterConfig;
	 
    public AuditFilter() {

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		  if (filterConfig == null){
	            return;
	        }


	        StringBuffer resourse = ((HttpServletRequest)request).getRequestURL();
	        HttpSession session = ((HttpServletRequest)request).getSession();
	        User user = (User)session.getAttribute("user");
	        
	        System.out.println("resourse - "+resourse);
	        System.out.println("date - "+new Date());
	        System.out.println("user - "+user);
	       
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
