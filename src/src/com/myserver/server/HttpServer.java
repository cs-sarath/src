package com.myserver.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.myserver.start.MyServer;

public class HttpServer implements Server
{

	@Override
	public void start() throws UnknownHostException, IOException 
	{
		ServerSocket server = new ServerSocket (5000, 10, InetAddress.getByName("127.0.0.1"));
		
		while(true) 
		{
			Socket connected = server.accept();
			(new RequestAcceptor(connected)).start();
		}
	}

	@Override
	public void stop() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPort() {
		// TODO Auto-generated method stub
		
	}

}
