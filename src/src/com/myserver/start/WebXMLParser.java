package com.myserver.start;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.myserver.server.WebApplicationContext;

class WebXMLParser extends DefaultHandler {

	private String path;
	
	private ServletContext servletContext;
	
	private ServletWrapper servletWrapper;
	private boolean servlet = false;
	private boolean servletName = false;
	private boolean servletMapping = false;
	private boolean servletClass = false;
	private boolean urlPattern = false;
	
	WebXMLParser(String webAppName, String path)
	{
		this.path = path;
		this.servletContext = new WebApplicationContext(webAppName);
	}
	
	void parse() throws ParserConfigurationException, SAXException, IOException
	{
		SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
        saxParser.parse(new File(path), this);
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes)
	{
        if (qName.equalsIgnoreCase("servlet")) {
        	servlet = true;
        }
        else if (qName.equalsIgnoreCase("servlet-name")) {
            servletName = true;
        }
        else if (qName.equalsIgnoreCase("servlet-class")) {
        	servletClass = true;
        }
        else if (qName.equalsIgnoreCase("servlet-mapping")) {
        	servletMapping = true;
        }
        else if (qName.equalsIgnoreCase("url-pattern")) {
        	urlPattern = true;
        }
    }
	
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException 
    {
        if (qName.equalsIgnoreCase("servlet-name")) {
            servletContext.addServlet(servletWrapper.getServletName(), servletWrapper.getServletClass());
        }
    }
    
    @Override
    public void characters(char ch[], 
       int start, int length) throws SAXException {
       if (servlet && servletName) {
    	   this.servletWrapper = new ServletWrapper(new String(ch, start, length));
    	   servlet = false;
    	   servletName = false;
       } else if (servletClass) {
    	   servletWrapper.setServletClass(new String(ch, start, length));
    	   servletClass = false;
       } else if (servletMapping && servletName) {
          servletMapping = false;
       } else if (urlPattern) {
          
          urlPattern = false;
       }
    }

	
}
