package com.myserver.start;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

public class ServletWrapper implements ServletConfig 
{
	private String servletName;
	private String servletClass;

	ServletWrapper(String servletName)
	{
		this.servletName = servletName;
	}

	@Override
	public String getInitParameter(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletName() {
		return servletName;
	}
	
	public void setServletClass(String servletClass) {
		this.servletClass = servletClass;
	}
	
	public String getServletClass() {
		return servletClass;
	}

	public HttpServlet getInstance() {
		try
		{
			Class servletClazz = Class.forName(servletClass);
			return (HttpServlet)servletClazz.getConstructor().newInstance();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
