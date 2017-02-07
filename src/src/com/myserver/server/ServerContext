package com.myserver.server;

import java.util.ArrayList;
import java.util.List;

public class ServerContext 
{
	private List<WebApplicationContext> webAppContexts;
	
	private ServerContext()
	{
		webAppContexts = new ArrayList<WebApplicationContext>();
	}
	
	public void addWebApplicationContext(WebApplicationContext webAppContext)
	{
		webAppContexts.add(webAppContext);
	}

	public WebApplicationContext getWebApplicationContext(String webAppName)
	{
		for(WebApplicationContext webAppConetxt : webAppContexts)
		{
			if(webAppConetxt.getWebAppName().equals(webAppName))
			{
				return webAppConetxt;
			}
		}
		return null;
	}
	
	static class InstanceHolder
	{
		static final ServerContext INSTANCE = new ServerContext();
	}
	
	public static ServerContext getInstance()
	{
		return InstanceHolder.INSTANCE;
	}
}
