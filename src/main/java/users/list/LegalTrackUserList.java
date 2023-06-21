package users.list;
import java.util.*;
import java.sql.*;
import java.io.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import users.model.*;
import users.utils.*;

public class LegalTrackUserList extends TopUser{

    static Logger logger = LogManager.getLogger(LegalTrackUserList.class);	
    List<LegalTrackUser> users = null;
    public LegalTrackUserList(){
    }
    public List<LegalTrackUser> getUsers(){
	return users;
    }
    public String find(){
	String msg = "";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;			
	String qq = " select * from users";
	logger.debug(qq);
	try {
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    if(con == null){
		msg = "Error coold not connect to db ";		
		logger.error(msg);
	    }
	    else{
		stmt = con.prepareStatement(qq);
		rs = stmt.executeQuery();
		while(rs.next()){
		    LegalTrackUser one =
			new LegalTrackUser(
				     rs.getString(1),
				     rs.getString(2),
				     rs.getString(3),
				     rs.getString(4));
		    if(users == null)
			users = new ArrayList<>();
		    if(!users.contains(one))
			users.add(one);
		}
	    }
	}
	catch(Exception e){
	    logger.error(e);
	    msg += e+";"+qq;
	}
	finally{
	    Helper.databaseDisconnect(con, stmt, rs);
	}
	return msg;
    }
    
}
