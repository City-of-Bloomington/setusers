package users.web;
import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import users.model.*;
import users.list.*;

@WebServlet(urlPatterns = {"/WaiversUser"})
public class WaiverUserServ extends TopServlet{

    static Logger logger = LogManager.getLogger(WaiverUserServ.class);
    static String appDbInfo = null;
    static String[] roleNames = {"View","Edit","Edit & Delete","Admin"};
    static String[] roles = {"View","Edit","Edit:Delete","Edit:Delete:Admin"};
    static String[] depts = {"Controller","ITS","Legal","Utilities"};
    public void doPost(HttpServletRequest req, 
		       HttpServletResponse res) 
	throws ServletException, IOException{

	res.setContentType("text/html");
	Enumeration values = req.getParameterNames();
	PrintWriter out = res.getWriter();
	String name = "";
	String value = "";
	String action = ""; 
	String id="", username,newuser = "";
	List<TopUser> users = null;
	String message = "";
	appDbInfo = getAppDbInfo("waivers");
	WaiverUser user = new WaiverUser();
	user.setDbParams(appDbInfo);
	while (values.hasMoreElements()){

	    name = ((String)values.nextElement()).trim();
	    value = (req.getParameter(name)).trim();

	    if (name.equals("action")) 
		action = value;  // Add, Remove
	    else if (name.equals("id")){
		user.setId(value);
		id = value;
	    }
	    else if (name.equals("username")){
		user.setUsername(value);
	    }
	    else if (name.equals("newuser"))  
		newuser = value.toLowerCase();
	    else if (name.equals("fullName"))  {
		user.setFullName(value);
	    }
	    else if (name.equals("dept")){
		user.setDept(value);
	    }
	    else if (name.equals("role")){
		user.setRole(value);
	    }
	    else if (name.equals("activeMail")){
		user.setActiveMail(value);
	    }
	    else if (name.equals("inactive")){
		user.setInactive(value);
	    }	    
	}
	HttpSession session = req.getSession();
	User adminUser = null;
	if(session != null){
	    adminUser = (User)session.getAttribute("user");
	}
	if(adminUser == null){
	    res.sendRedirect(url+"Login");
	    return;
	}
	//
	// Set defaults
	//
	if(!newuser.equals("") && action.equals("Add")){
	    user.setUsername(newuser);
	    String back = user.doSave();
	    if(!back.isEmpty()){
		message += back;
	    }
	    else{
		id = user.getId();
		message += "Saved Successfully";
	    }
	}
	else if(action.equals("Update")){
	    String back = user.doUpdate();
	    if(!back.isEmpty()){
		message += back;
	    }
	    else{
		message += "Updated Successfully";
	    }
	}
	else if(action.equals("Delete")){
	    String back = user.doDelete();
	    if(!back.isEmpty()){
		message += back;
	    }
	    else{
		user = new WaiverUser();
		id = "";
		message += "Delete Successfully";
	    }
	}
	else if(!id.isEmpty()){
	    String back = user.doSelect();
	    if(!back.isEmpty()){
		message += back;
	    }
	}
	//
	// get all users here
	// 
	//
	out.println("<html><head><title>Set User Access </title>");
	out.println("<script language=javascript>                ");
	out.println(" function validateAdd(){     ");
	out.println(" if(document.myForm.newuser.value.length == 0){ ");
	out.println("	alert(\"User name not entered \");  ");  
	out.println("   document.myForm.newuser.focus();    ");
	out.println("   return false;}                      ");
	out.println("   return true;         ");
	out.println("  }  ");
	out.println(" function validateDelete(){       ");
	out.println(" if(document.myForm.id.selectedIndex < 0){ ");
	out.println("	alert(\"User not selected \"); ");  
	out.println("   return false;}                 ");
	out.println("   return true;                   ");		
	out.println("  }                               ");
	out.println(" </script>		               ");
	out.println("</head><body>");

	out.println("<center>");
	out.println("<font size=+2>Grant/Deny Access </font><br>");
	out.println("<font size=+1 color=green> "+
		    "Waiver Application </font><br>"); 
	out.println("<br />");
	if(!message.equals("")){
	    out.println(message);
	    out.println("<br />");
	}
	out.println("<form name=\"myForm\">");
	out.println("<table border=\"1\">");
	out.println("<tr><td>");
	out.println("<table><tr>");
	out.println("<td><b>Current users</b>:");
	out.println("</td><td>");
	out.println("<select name=\"id\" "+
		    "onchange=\"myForm.submit()\">");
	TopUserList tl = new TopUserList();
	tl.setDbParams(appDbInfo);
	String back = tl.findWaiversUsers();
	if(back.isEmpty()){
	    users = tl.getUsers();
	}
	out.println("<option value\"\">Pick User</option> \n");	
	if(users != null){
	    for(TopUser one: users){
		String selected = "";
		if(id.equals(one.getId()))
		    selected = "selected=\"selected\"";
		out.println("<option value=\""+one.getId()+"\" "+selected+">"+one.getFullName()+"</option>");
	    }
	} 
	out.println("</select></td></tr>");
	out.println("<tr><td align=right><b>Username</b>:</td><td>");
	out.println("<input name=\"username\" size=\"30\" value=\""+user.getUsername()+"\" ></input>");
	out.println("</td></tr>");
	out.println("<tr><td align=right><b>Full Name</b>:</td><td>");
	out.println("<input name=fullName size=30 value=\""+user.getFullName()+"\"></input><br />");
	out.println("</td></tr>");
	out.println("<tr><td align=right><b>Dept:</b>:</td><td>");
	for(int jj=0; jj< depts.length;jj++){
	    String checked = "";
	    if(user.getDept().equals(depts[jj])){
		checked = "checked=\"checked\"";

	    }
	    out.println("<input type=\"radio\" name=\"dept\" "+checked+" value=\""+depts[jj]+"\" >"+depts[jj]);			    
	}
	out.println("</td></tr>");	
	out.println("<tr><td align=right><b>Role:</b>:</td><td>");
	for(int jj=0; jj< roles.length;jj++){
	    String checked = "";
	    if(user.getRole().equals(roles[jj])){
		checked = "checked=\"checked\"";

	    }
	    out.println("<input type=\"radio\" name=\"role\" "+checked+" value=\""+roles[jj]+"\">"+roleNames[jj]);			    
	}
	out.println("</td></tr>");
	out.println("<tr><td align=right><b>Active Email:</b>:</td><td>");
	String checked = "";
	if(!user.getActiveMail().isEmpty()){
	    checked = "checked=\"checked\"";
		
	}
	out.println("<input type=\"checkbox\" name=\"activeMail\" value=\"y\" "+checked+" /> Yes ");
	out.println("</td></tr>");
	out.println("<tr><td align=right><b>Inactive:</b>:</td><td>");
	checked = "";
	if(!user.getInactive().isEmpty()){
	    checked = "checked=\"checked\"";
		
	}
	out.println("<input type=\"checkbox\" name=\"inactive\" value=\"y\" "+checked+" /> Yes ");
	out.println("</td></tr>");
	
	out.println("<tr><td colspan=2 align=right><table><tr>");
	out.println("<tr><td>");
	if(id.isEmpty()){	
	    out.println("<input type=\"submit\" value=\"Add\" name=\"action\" "+
			"onclick=\"return validateAdd()\" />&nbsp;&nbsp;"+
			"&nbsp;&nbsp;&nbsp;</td><td>");
	}
	else{
	    out.println("<input type=\"submit\" value=\"Update\" name=\"action\" "+
			" />&nbsp;&nbsp;"+
			"&nbsp;&nbsp;&nbsp;</td><td>");		
	    out.println("<input type=\"submit\" value=\"Delete\" name=\"action\" "+
			"onclick=\"return validateDelete()\" /> ");
	    out.println("<a href=\""+url+"WaiversUser\">New User</a>");
	}
	out.println("</td></tr></table>");
	out.println("</td></tr></table>");
	out.println("</form>");
	out.println("</td></tr>");
	out.println("</table><br>");
	out.println("<a href=\""+url+"PickApp\">Pick Another Application</a><br>");
	out.println("<a href=\""+url+"Logout\">Log out</a><br>");
	out.println("</body></html>");
	out.close();

    }
    public void doGet(HttpServletRequest req, 
		      HttpServletResponse res) 
	throws ServletException, IOException{
	doPost(req, res);
    }
    
}
