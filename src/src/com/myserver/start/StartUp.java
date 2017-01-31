package com.myserver.start;

import java.io.IOException;

import com.myserver.server.HttpServer;

public class StartUp {
	
	public void start()
	{
		//look for load and parse web.xml
		try 
		{
			new WebXMLParser("TestApp", "/Users/vivekt/Workspace/MyServer/TestApp/web.xml").parse();
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			System.err.println("Couldn't parse web.xml");
			e.printStackTrace();
		} 
		//get the jars and add to classpath
		
		//all ready then start the server
		try 
		{
			new HttpServer().start();
		} 
		catch (IOException e) 
		{
			System.err.println("Unable to start server");
			e.printStackTrace();
		}
	}
	
	public void stop()
	{
		
	}

	public static void main(String[] args)
	{
		new StartUp().start();
	}
}
