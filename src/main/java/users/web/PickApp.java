package users.web;
import java.util.*;
import java.sql.*;
import java.io.*;
import javax.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@WebServlet(urlPatterns = {"/PickApp"})
public class PickApp extends TopServlet{

    static Logger logger = LogManager.getLogger(PickApp.class);
    String[] appsName={
	
	"Assettrack/ITS",
	"Phones/ITS",
	"LegacyTime/HR",
	"LegalTrack/Legal",
	"RiskTrack/Risk",
	
	"LicensePro/Legal",
	"Sponsors/Parks",
	"Promt/Parks",
	"ProLoan/HAND",
	"Marketbucks/Parks",
	
	"Waivers/Legal",
	"SetUsers/(This app)ITS"
    };
    
    String[] appLink={
	"AssetTrackUser",
	"PhonesUser",
	"LegacytimeUser",
	"LegalTrackUser",
	"RiskTrackUser",
	
	"LicenseproUser",
	"SponsorsUser",
	"PromtUser",
	"ProloanUser",
	"MarketbucksUser",
	
	"WaiversUser",
	"SetusersUser"

    };
    //
		       			
    public void doGet(HttpServletRequest req, 
		      HttpServletResponse res) 
	throws ServletException, IOException{

	res.setContentType("text/html");
	Enumeration values = req.getParameterNames();
	PrintWriter out = res.getWriter(); 
	
	out.println("<HTML><HEAD><TITLE>Add/Delete Users </TITLE>");
	out.println("<script language=Javascript>                ");
	out.println(" </script>		               ");
	out.println("</head><body>");
	
	out.println("<center>");
	out.println("<font size=+2> Grant/Deny Access </font><br>");
	out.println("<font size=+1 color=green>Pick Application </font><br>"); 
	out.println("<br>");
	out.println("<table border width=60%><tr><td>");
	out.println("<table width=100%>");
	
	for(int i=0;i<appsName.length;i++){
	    String str = ""+(i+1)+" - "; 
	    if(i < 9) str = "&nbsp;&nbsp;"+(i+1)+" - ";
	    if(i%2 == 0)
		out.println("<tr><td>"+str);
	    else
		out.println("</td><td>"+str);
	    out.println("<a href="+url+appLink[i]+">"+appsName[i]+"</a>");
	    if(i%2 == 1 || i == (appsName.length - 1)) 
		out.println("</td></tr>");
	}
	out.println("</table>");
	out.println("</td></tr></table><br>");
	out.println("<a href=\""+url+"Logout\">Log out</a><br>");
	out.println("</body></html>");
	
	out.close();
	
    }
    public void doPost(HttpServletRequest req, 
		       HttpServletResponse res) 
	throws ServletException, IOException{
	doGet(req, res);
    }

}
