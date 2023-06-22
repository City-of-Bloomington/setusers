package users.model;
import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import users.utils.*;

public class LegalTrackUser extends TopUser implements Serializable{

    static Logger logger = LogManager.getLogger(LegalTrackUser.class);

    public LegalTrackUser(){

    }
    public LegalTrackUser(
			  String str,
			  String str2,
			  String str3,
			  String str4){
	setVals(str, str2, str3, str4);
    }
    void setVals(
		 String str,
		 String str2,
		 String str3,
		 String str4
		 ){
	setUserid(str);
	setDept(str2);
	setFullName(str3);
	setRole(str4);
    }
    public String toString(){
		
	return userid;
    }
    @Override
    public int hashCode(){
	int seed = 17;
	if(!userid.equals("")){
	    seed += userid.hashCode()*31;
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
	return ((LegalTrackUser)obj).getUserid().equals(userid);

    }

    public String doSave(){
	String msg="";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;	
	String qq = "insert into users values(?,?,?,?)";
	logger.debug(qq);
	try{
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    stmt = con.prepareStatement(qq);
	    stmt.setString(1, userid);
	    if(dept.equals(""))
		stmt.setNull(2,Types.VARCHAR);
	    else
		stmt.setString(2, dept);
	    if(fullName.equals(""))
		stmt.setNull(3,Types.VARCHAR);
	    else
		stmt.setString(3, fullName);
	    if(role.equals(""))
		stmt.setNull(4,Types.VARCHAR);
	    else
		stmt.setString(4, role);
	    stmt.executeUpdate();
	}catch(Exception e){
	    msg = " Error adding the user "+e;
	    logger.error(msg);
	}
	finally{
	    Helper.databaseDisconnect(con, stmt, rs);	
	}
	return msg;
    }
    public String doDelete(){
	String msg="";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;		
	String qq = "delete from users where userid=?";
	logger.debug(qq);
	try {
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    stmt = con.prepareStatement(qq);
	    stmt.setString(1, userid);
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
	String qq = "update users set dept=?,fullName=?,role=? ";
	qq += " where userid =?";
	try{
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    logger.debug(qq);
	    stmt = con.prepareStatement(qq);
	    if(dept.equals(""))
		stmt.setNull(1,Types.VARCHAR);
	    else
		stmt.setString(1, dept);
	    if(fullName.equals(""))
		stmt.setNull(2,Types.VARCHAR);
	    else
		stmt.setString(2, fullName);
	    if(role.equals(""))
		stmt.setNull(3,Types.VARCHAR);
	    else
		stmt.setString(3, role);
	    stmt.setString(4, userid);
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
	String qq = " select * from users where userid = ?";	
	logger.debug(qq);
	try {
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    stmt = con.prepareStatement(qq);
	    stmt.setString(1, userid);
	    rs = stmt.executeQuery();
	    if(rs.next()){
		setVals(rs.getString(1),
			rs.getString(2),
			rs.getString(3),
			rs.getString(4));
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
