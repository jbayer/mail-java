package org.cloudfoundry.samples;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork( request, response );
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork( request, response );
	}	
	
	
	protected void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setStatus(200);
		PrintWriter writer = response.getWriter();
		MailUtility simpleMail = new MailUtility();
		
		//if the user posts the form, then use their params
		if( request.getParameter("smtpHost") != null )
		{
			try {
				simpleMail.sendMail( request.getParameter("smtpHost"),
						Integer.valueOf( request.getParameter("smtpPort") ),
						request.getParameter("smtpAuthUser"),
						request.getParameter("smtpAuthPwd"),
						request.getParameter("to"),
						request.getParameter("from"),
						request.getParameter("body"),
						request.getParameter("subject") 
						);
				writer.print( "test email sent with submitted values" );
			} catch (Exception e) {
				writer.print( "test email failed\n" );
				writer.print(e.getMessage());
				e.printStackTrace();
			}			
		}
		//otherwise use the default env and hardcoded values
		else
		{
			try {
				simpleMail.sendMailWithEnvValues();
				writer.print( "test email sent with default values" );
			} catch (Exception e) {
				writer.print( "test email failed" );
				writer.print(e.getMessage());
				e.printStackTrace();
			}
		}
		
		writer.close();
	}
}
