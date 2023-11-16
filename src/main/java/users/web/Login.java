package users.web;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import org.jasig.cas.client.authentication.AttributePrincipal;
import java.net.URL;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import users.model.*;

@WebServlet(urlPatterns = {"/CasLogin"})
public class Login extends TopServlet{

    static Logger logger = LogManager.getLogger(Login.class);
    /**
     * Generates the login form for all users.
     *
     * @param req the request 
     * @param res the response
     */
    public void doGet(HttpServletRequest req, 
		      HttpServletResponse res) 
	throws ServletException, IOException {
	String message="";
	boolean found = false;
	String name="", value="";
	Enumeration values = req.getParameterNames();
	while (values.hasMoreElements()) {
	    name = ((String)values.nextElement()).trim();
	    value = (req.getParameter(name)).trim();
	}	
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	HttpSession session = null;
	String username = null;
	AttributePrincipal principal = null;				
	if (req.getUserPrincipal() != null) {
	    principal = (AttributePrincipal) req.getUserPrincipal();
	    username = principal.getName();
	}
	if(username == null || username.isEmpty()){
	    username = req.getRemoteUser();
	}
	logger.debug("CAS username "+username);
	if(username != null){
	    session = req.getSession();			
	    User user = getUser(username);
	    logger.debug("user "+user);
	    logger.debug("got session ");
	    if(session != null){
		session.setAttribute("user",user);
	    }
	    String url2 = url+"PickApp?";
	    out.println("<head><title></title><META HTTP-EQUIV=\""+
			"refresh\" CONTENT=\"0; URL=" + url2 +
			"\"></head>");								
	    out.println("<body>");
	    out.println("</body>");
	    out.println("</html>");
	    out.flush();
	    return;
	}
	else{
	    message = " Unauthorized access";
	}
	out.println("<head><title></title><body>");
	out.println("<p><font color=red>");
	out.println(message);
	out.println("</font></p>");
	out.println("</body>");
	out.println("</html>");
	out.flush();
	out.close();
    }
	
    /**
     * Procesesses the login and check for authontication.
     * Uses ldap for authentication.
     * @param req
     * @param res
     */		
    User getUser(String username){

	User user = null;
	try{
	    User user2 = new User(null, username);
	    String back = user2.doSelect();
	    if(!back.equals("")){
		logger.error(back);
	    }
	    else{
		user = user2;
	    }
	}
	catch (Exception ex) {
	    logger.error(ex);
	}
	return user;
    }

}






















































