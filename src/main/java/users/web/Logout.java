package users;
import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Logout extends HttpServlet{

	String url = "";
    public void doGet(HttpServletRequest req, 
					  HttpServletResponse res) 
		throws ServletException, IOException{

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		Enumeration values = req.getParameterNames();
		String name= "";
		String value = "";

		HttpSession session = null;
		session = req.getSession(false);
		if(session != null){
			session.invalidate();
		}
		if(url.equals("")){
			url = getServletContext().getInitParameter("url");
		}
		out.println("<HTML><HEAD><TITLE>Log out</TITLE>");
		out.println("<body><center>You have successfully logged out."+
					"<p><a href="+ url + "AdminLogin> "+
					"Login again</a>");
		out.println("</center></body></html>");

		out.close();

    }

}






















































