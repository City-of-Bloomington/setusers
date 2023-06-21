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

@WebServlet(urlPatterns = {"/AssetTrackUser"})
public class AssetTrackUserServ extends TopServlet{

    static Logger logger = LogManager.getLogger(AssetTrackUserServ.class);
    static String appDbInfo = null;
    static String[] roleNames = {"End User","View","Edit","Admin"};
    static String[] roles = {"end_user","View","Edit","Edit:Admin"};

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
	appDbInfo = getAppDbInfo("assettrack");
	AssetTrackUser user = new AssetTrackUser();
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
	    else if (name.equals("first_name"))  {
		user.setFirst_name(value);
	    }
	    else if (name.equals("last_name"))  {
		user.setLast_name(value);
	    }	    
	    else if (name.equals("office_phone")){
		user.setOffice_phone(value);
	    }
	    else if (name.equals("role")){
		user.setRole(value);
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
		user = new AssetTrackUser();
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
	out.println("<html><head><title>Add/Delete App Access </title>");
	out.println("<script language=Javascript>                ");
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
		    "AssetTrack Application </font><br>"); 
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
		    "onchange='myForm.submit()'>");
	TopUserList tl = new TopUserList();
	tl.setDbParams(appDbInfo);
	String back = tl.findAssetTrackUsers();
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
	out.println("<input name=\"username\" size=\"30\" value=\""+user.getUsername()+"\"></input>");
	out.println("</td></tr>");
	out.println("<tr><td align=right><b>First Name</b>:</td><td>");
	out.println("<input name=first_name size=30 value=\""+user.getFirst_name()+"\"></input><br />");
	out.println("</td></tr>");
	out.println("<tr><td align=right><b>Last Name</b>:</td><td>");
	out.println("<input name=last_name size=30 value=\""+user.getLast_name()+"\"></input><br />");
	out.println("</td></tr>");	
	out.println("<tr><td align=right><b>Office Phone</b>:</td><td>");
	out.println("<input name=office_phone size=30 value=\""+user.getOffice_phone()+"\"></input><br />");
	out.println("</td></tr>");	
	out.println("<tr><td align=right><b>Role:</b>:</td><td>");
	for(int jj=0; jj< roles.length;jj++){
	    String checked = "";
	    if(user.getRole().equals(roles[jj])){
		checked = "checked=\"checked\"";

	    }
	    out.println("<input type=\"radio\" name=\"role\" "+checked+" value=\""+roles[jj]+"\">"+roleNames[jj]);			    
	}
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
	    out.println("<a href=\""+url+"AssetTrackUser\">New User</a>");
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
