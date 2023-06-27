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
	    con = Helper.getConnection();
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
