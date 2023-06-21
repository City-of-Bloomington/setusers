package users.list;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import javax.naming.*;
import javax.naming.directory.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import users.model.*;
import users.utils.*;


public class UserList{

    static Logger logger = LogManager.getLogger(UserList.class);
    String conUrl="", appStr="", appUser="", appPass="";
    List<User> users = null;
    //
    public UserList(){
	
    }
    public void setDbParams(String str,
			    String str2,
			    String str3,
			    String str4){
	setConUrl(str);
	setAppStr(str2);
	setAppUser(str3);
	setAppPass(str4);
    }
    void setConUrl(String val){
	if(val != null)
	    conUrl = val;
    }
    void setAppStr(String val){
	if(val != null)
	    appStr = val;
    }
    void setAppUser(String val){
	if(val != null)
	    appUser = val;
    }
    void setAppPass(String val){
	if(val != null)
	    appPass = val;
    }
    public String getAppPass(){
	return appPass;
    }
    public String getMysqlConStr(){
	if(conUrl.isEmpty() || appStr.isEmpty() || appUser.isEmpty()){
	    return null;
	}
	String  conStr = conUrl+appStr+"?autoReconnect=true&serverTimezone=America/New_York&useSSL=false&user="+appUser+"&password=";
	return conStr;
    }    
    //
    public List<User> getUsers(){
	return users;
    }
    //
    // setters
    //
    public String find(){
		
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String msg="";
	String qq = "select * from users order by 1";
	System.err.println(qq);
	logger.debug(qq);
	try{
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    if(con == null){
		msg += " could not connect to DB ";
		logger.error(msg);
		return msg;
	    }
	    stmt = con.prepareStatement(qq);			
	    rs = stmt.executeQuery();
	    while(rs.next()){
		User user = new User(rs.getString(1),
				     rs.getString(2),
				     rs.getString(3));
		if(users == null)
		    users = new ArrayList<>();
		if(!users.contains(user))
		    users.add(user);
	    }
	}
	catch(Exception ex){
	    msg += " "+ex+": "+qq;
	    logger.error(msg);
	}
	finally{
	    Helper.databaseDisconnect(con, stmt, rs);
	}
	return msg;
    }

}
