package users.model;
import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import users.utils.*;

public class ProloanUser extends TopUser implements Serializable{

    static Logger logger = LogManager.getLogger(ProloanUser.class);

    public ProloanUser(){

    }
    public ProloanUser(
			  String str,
			  String str2,
			  String str3,
			  String str4,
			  String str5,
			  String str6){
	setVals(str, str2, str3, str4, str5, str6);
    }
    void setVals(
		 String str,
		 String str2,
		 String str3,
		 String str4,
		 String str5,
		 String str6
		 ){
	setId(str);
	setUsername(str2);
	setFullName(str3);		
	setRole(str4);	
	setProject_manager(str5);
	setActive(str6);

    }
    public String toString(){
		
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
	return ((ProloanUser)obj).getUsername().equals(username);

    }

    public String doSave(){
	String msg="";
	Connection con = null;
	PreparedStatement stmt = null, stmt2=null, stmt3=null;
	ResultSet rs = null;	
	String qq = "insert into users values(0,?,?,?,?,?)";
	String qq2 = "select LAST_INSERT_ID()";	
	logger.debug(qq);
	try{
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    stmt = con.prepareStatement(qq);
	    stmt.setString(1, username);
	    if(fullName.equals(""))
		stmt.setNull(2,Types.VARCHAR);
	    else
		stmt.setString(2, fullName);
	    if(role.equals(""))
		stmt.setNull(3,Types.VARCHAR);
	    else
		stmt.setString(3, role);
	    
	    if(project_manager.equals(""))
		stmt.setNull(4,Types.CHAR);
	    else
		stmt.setString(4, "y");
	    if(active.equals(""))
		stmt.setNull(5,Types.CHAR);
	    else
		stmt.setString(5, "y");
	    
	    stmt.executeUpdate();
	    stmt2 = con.prepareStatement(qq2);
	    rs = stmt2.executeQuery();
	    if(rs.next()){
		id = rs.getString(1);
	    }
	}catch(Exception e){
	    msg = " Error adding the user "+e;
	    logger.error(msg);
	}
	finally{
	    Helper.databaseDisconnect(con, rs, stmt, stmt2, stmt3);
	}
	return msg;
    }
    public String doDelete(){
	String msg="";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;		
	String qq = "delete from users where id=?";
	logger.debug(qq);
	try {
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    stmt = con.prepareStatement(qq);
	    stmt.setString(1, id);
	    stmt.executeUpdate();
	}
	catch(Exception e){
	    msg = " Error deleting the user "+e;
	    logger.error(msg);
	}
	finally{
	    Helper.databaseDisconnect(con, stmt, rs);
	}
	return msg;
    }
    public String doUpdate(){

	String msg = "";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	String qq = "update users set username=?,fullName=?,role=?,project_manager=?,active=? where id =?";
	try{
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    logger.debug(qq);
	    stmt = con.prepareStatement(qq);
	    stmt.setString(1, username);
	    if(fullName.equals(""))
		stmt.setNull(2,Types.VARCHAR);
	    else
		stmt.setString(2, fullName);
	    if(role.equals(""))
		stmt.setNull(3,Types.VARCHAR);
	    else
		stmt.setString(3, role);
	    
	    if(project_manager.equals(""))
		stmt.setNull(4,Types.CHAR);
	    else
		stmt.setString(4, "y");
	    if(active.equals(""))
		stmt.setNull(5,Types.CHAR);
	    else
		stmt.setString(5, "y");
	    stmt.setString(6, id);
	    stmt.executeUpdate();
	}catch(Exception e){
	    msg = " Error updating the user "+e;
	    logger.error(msg);
	}
	finally{
	    Helper.databaseDisconnect(con, stmt, rs);
	}
	return msg;
    }
    public String doSelect(){
	String msg = "";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String qq = " select * from users where id = ?";	
	logger.debug(qq);
	try {
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    stmt = con.prepareStatement(qq);
	    stmt.setString(1, id);
	    rs = stmt.executeQuery();
	    if(rs.next()){
		setVals(rs.getString(1),
			rs.getString(2),
			rs.getString(3),
			rs.getString(4),
			rs.getString(5),
			rs.getString(6));
	    }
	}
	catch(Exception e){
	    logger.error(e);
	    msg += e+": "+qq;
	}
	finally{
	    Helper.databaseDisconnect(con, stmt, rs);
	}
	return msg;
    }
    
}
