package users.model;
import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import users.utils.*;

public class SponsorsUser extends TopUser implements Serializable{

    static Logger logger = LogManager.getLogger(SponsorsUser.class);

    public SponsorsUser(){

    }
    public SponsorsUser(
			String str,
			String str2,
			String str3,
			String str4,
			String str5,
			String str6
			){
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
	setUserid(str);
	setRole(str2);	
	setFname(str3);
	setManager(str4);
	setLname(str5);
	setPhone(str6);
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
	return ((SponsorsUser)obj).getUserid().equals(userid);

    }

    public String doSave(){
	String msg="";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;	
	String qq = "insert into users values(?,?,?,?,?,?)";
	logger.debug(qq);
	try{
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    stmt = con.prepareStatement(qq);
	    stmt.setString(1, userid);
	    if(role.equals(""))
		stmt.setNull(2,Types.VARCHAR);
	    else
		stmt.setString(2, role);
	    if(fname.equals(""))
		stmt.setNull(3,Types.VARCHAR);
	    else
		stmt.setString(3, fname);
	    if(manager.equals(""))
		stmt.setNull(4,Types.CHAR);
	    else
		stmt.setString(4, "y");
	    if(lname.equals(""))
		stmt.setNull(5,Types.VARCHAR);
	    else
		stmt.setString(5, lname);
	    if(phone.equals(""))
		stmt.setNull(6,Types.VARCHAR);
	    else
		stmt.setString(6, phone);	    
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
	    userid = "";dept="";role="";fullName="";
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
	
	String qq = "update users set role=?,fname=?,manager=?,lname=?,phone=? ";
	qq += " where userid =?";
	try{
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    logger.debug(qq);
	    stmt = con.prepareStatement(qq);
	    if(role.equals(""))
		stmt.setNull(1,Types.VARCHAR);
	    else
		stmt.setString(1, role);
	    if(fname.equals(""))
		stmt.setNull(2,Types.VARCHAR);
	    else
		stmt.setString(2, fname);
	    if(manager.equals(""))
		stmt.setNull(3,Types.CHAR);
	    else
		stmt.setString(3, "y");
	    if(lname.equals(""))
		stmt.setNull(4,Types.VARCHAR);
	    else
		stmt.setString(4, lname);
	    if(phone.equals(""))
		stmt.setNull(5,Types.VARCHAR);
	    else
		stmt.setString(5, phone);
	    stmt.setString(6, userid);
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
