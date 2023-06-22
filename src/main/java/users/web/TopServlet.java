package users.web;
import java.util.*;
import java.net.URI;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import users.utils.*;

public class TopServlet extends HttpServlet {
    static String url = "", cur_ver="";
    static String cookieName="", cookieValue="";
    static String server_path="";
    static String mySqlConStr="", mysqlPass="", dbConnectStr = "";
    static Configuration config = null;
    static Logger logger = LogManager.getLogger(TopServlet.class);
    static ServletContext context = null;
    static String [] appNames = {
	"assettrack",
	"legaltrack",
	"risktrack",
	"licensepro",
	"marketbucks",
	
	"sponsors",
	"proloan",
	"promt",
	"waivers",
	"phones",
	
	"legacytime",
	"setusers"
    };
    HashMap<String, String> appsDbInfo = new HashMap<>();
    public void init(ServletConfig conf){
	try{
	    context = conf.getServletContext();
	    url = context.getInitParameter("url");
	    String str = context.getInitParameter("mySqlConStr");
	    if(str != null)
		mySqlConStr = str;
	    str = context.getInitParameter("mysqlPass");
	    if(str != null)
		mysqlPass = str;
	    str = context.getInitParameter("cookieName");
	    if(str != null)
		cookieName = str;
	    str = context.getInitParameter("cookieValue");
	    if(str != null)
		cookieValue = str;
	    for(String appName:appNames){
		str = context.getInitParameter(appName);
		if(str != null)
		    setAppInfo(appName, str);
	    }
	    String username = context.getInitParameter("adfs_username");
	    String auth_end_point = context.getInitParameter("auth_end_point");
	    String token_end_point = context.getInitParameter("token_end_point");
	    String callback_uri = context.getInitParameter("callback_uri");
	    String client_id = context.getInitParameter("client_id");
	    String client_secret = context.getInitParameter("client_secret");
	    String scope = context.getInitParameter("scope");
	    String discovery_uri = context.getInitParameter("discovery_uri");
	    config = new
		Configuration(auth_end_point, token_end_point, callback_uri, client_id, client_secret, scope, discovery_uri, username);
	}catch(Exception ex){
	    System.err.println(" top init "+ex);
	    logger.error(" "+ex);
	}
    }
    void setAppInfo(String appName, String str){
	if(appName != null && str != null){
	    try{
		appsDbInfo.put(appName, str);
		// System.err.println(" adding "+appName+" "+str);
	    }catch(Exception ex){
		logger.error(appName+":"+str+":"+ex);
	    }
	}

    }
    public String getAppDbInfo(String appName){
	//System.err.println(" getting "+appName);
	if(appsDbInfo != null && appsDbInfo.containsKey(appName)){
	    String appInfo = appsDbInfo.get(appName);
	    return appInfo;
	}
	return null;
    }
}
