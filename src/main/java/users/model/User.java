package users.model;
import java.sql.*;
import javax.naming.*;
import java.io.Serializable;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import users.utils.*;

public class User implements Serializable{

    static Logger logger = LogManager.getLogger(User.class);
    String id="", username = "", fullName="";
    public User(){
    }		
    public User(String str){
	if(str != null)
	    username = str;
    }
    public User(String str, String str2){
	setId(str);
	setUsername(str2);
    }	
    public User(String str, String str2, String str3){
	setId(str);
	setUsername(str2);
	setFullName(str3);
    }
    public void setId(String val){
	if(val != null)
	    id = val;
    }    
    public void setUsername(String val){
	if(val != null)
	   username = val;
    }    
    public void setFullName(String val){
	if(val != null)
	    fullName = val;
    }
    public String getId(){
	return id;
    }    
    public String getUsername(){
	return username;
    }
    public String getFullName(){
	return fullName;
    }    
    public String toString(){
		
	if(!fullName.equals("")) return fullName;
	return username;
    }
    @Override
    public int hashCode(){
	int seed = 17;
	if(!username.equals("")){
	    seed += username.hashCode()*31;
	}
	return seed;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
	return ((User)obj).getUsername().equals(username);

    }

    public String doSelect(){

	String msg="";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;					
	String qq = "select * from users where username=?";
	String qq2 = "select * from users where id=?";		
	try{
	    con = Helper.getConnection();
	    if(con == null){
		msg = " Could not connect to database ";
	    }
	    else {
		if(!id.isEmpty())
		    qq = qq2;
		logger.debug(qq);
		pstmt = con.prepareStatement(qq);
		if(id.isEmpty())
		    pstmt.setString(1, username);
		else
		    pstmt.setString(1, id);						
		rs = pstmt.executeQuery();
		if(rs.next()){
		    String str = rs.getString(1);
		    if(str != null)
			id = str;
		    str = rs.getString(2);
		    if(str != null)
			username = str;					
		    str = rs.getString(3);
		    if(str != null)
			fullName = str;
		}
		else{
		    msg = "User not found";
		}
	    }
	}
	catch(Exception ex){
	    msg += qq +": "+ex;	    
	    logger.error(msg);

	}
	finally{
	    Helper.databaseDisconnect(con, pstmt, rs);
	}
	return msg;
    }
    public String doSave(){

	String msg="";
	Connection con = null;
	PreparedStatement pstmt = null, pstmt2=null;
	ResultSet rs = null;					
	String qq = "insert into users values(0,?,?)";
	String qq2 = "select LAST_INSERT_ID() ";
	if(username.isEmpty() || fullName.isEmpty()){
	    msg = " username and full name are required ";
	    return msg;
	}
	con = Helper.getConnection();
	if(con == null){
	    msg = " Could not connect to database ";
	    return msg;
	}

	try{
	    logger.debug(qq);
	    pstmt = con.prepareStatement(qq);
	    pstmt.setString(1, username);
	    pstmt.setString(2, fullName);
	    int cnt = pstmt.executeUpdate();
	    //
	    if(cnt > 0){
		logger.debug(qq2);
		pstmt2 = con.prepareStatement(qq2);
		rs = pstmt2.executeQuery();
		if(rs.next()){
		    id = rs.getString(1);
		}
	    }
	}
	catch(Exception ex){
	    msg += qq+": "+ex;	    
	    logger.error(msg);
	}
	finally{
	    Helper.databaseDisconnect(con, rs, pstmt, pstmt2);
	}
	return msg;
    }
    public String doUpdate(){

	String msg="";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;					
	String qq = "update users set username=?, fullName=? where id=?";
	if(username.isEmpty() || fullName.isEmpty()){
	    msg = " username and full name are required ";
	    return msg;
	}
	con = Helper.getConnection();
	if(con == null){
	    msg = " Could not connect to database ";
	    return msg;
	}

	try{
	    logger.debug(qq);
	    pstmt = con.prepareStatement(qq);
	    pstmt.setString(1, username);
	    pstmt.setString(2, fullName);
	    pstmt.setString(3, id);
	    pstmt.executeUpdate();
	    //
	}
	catch(Exception ex){
	    msg += qq+": "+ex;	    
	    logger.error(msg);

	}
	finally{
	    Helper.databaseDisconnect(con, pstmt, rs);
	}
	return msg;
    }
    public String doDelete(){

	String msg="";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;					
	String qq = "delete from users where id=?";
	con = Helper.getConnection();
	if(con == null){
	    msg = " Could not connect to database ";
	    return msg;
	}

	try{
	    logger.debug(qq);
	    pstmt = con.prepareStatement(qq);
	    pstmt.setString(1, id);
	    pstmt.executeUpdate();
	    //
	}
	catch(Exception ex){
	    msg += qq+": "+ex;	    
	    logger.error(msg);

	}
	finally{
	    Helper.databaseDisconnect(con, pstmt, rs);
	}
	return msg;
    }					
		

}






















































