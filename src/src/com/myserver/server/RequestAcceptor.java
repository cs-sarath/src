package com.myserver.server;

import java.net.Socket;
import java.util.LinkedList;

public class RequestAcceptor
{
	private final int nThreads = 200;
    private final PoolWorker[] threads;
    private final LinkedList queue;
    private RequestHandler handler;
 
    public RequestAcceptor(Socket socket)
    {
    	handler = new RequestHandler();
        queue = new LinkedList();
        threads = new PoolWorker[nThreads];
 
        for (int i=0; i<nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }
 
    public void execute(Runnable r) {
        synchronized(queue) {
            queue.addLast(r);
            queue.notify();
        }
    }
 
    private class PoolWorker extends Thread 
    {
        public void run() 
        {
            Runnable r;
 
            while (true) 
            {
                synchronized(queue) 
                {
                    while (queue.isEmpty()) 
                    {
                        try
                        {
                            queue.wait();
                        }
                        catch (InterruptedException ignored)
                        {
                        }
                    }
                    r = (Runnable) queue.removeFirst();
                }
                try {
                    r.run();
                }
                catch (RuntimeException e) 
                {
                    System.err.println("Error executing task");
                }
            }
        }
    }
	
}
