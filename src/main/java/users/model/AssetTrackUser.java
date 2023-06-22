package users.model;
import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import users.utils.*;

public class AssetTrackUser extends TopUser implements Serializable{

    static Logger logger = LogManager.getLogger(AssetTrackUser.class);

    public AssetTrackUser(){

    }
    public AssetTrackUser(
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
	setFirst_name(str3);
	setLast_name(str4);
	setOffice_phone(str5);
	setRole(str6);
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
	return ((AssetTrackUser)obj).getUsername().equals(username);

    }

    public String doSave(){
	String msg="";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;	
	String qq = "insert into users values(0,?,?,?,?,?)";
	logger.debug(qq);
	try{
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    stmt = con.prepareStatement(qq);
	    stmt.setString(1, username);
	    if(first_name.equals(""))
		stmt.setNull(2,Types.VARCHAR);
	    else
		stmt.setString(2, first_name);
	    if(last_name.equals(""))
		stmt.setNull(3,Types.VARCHAR);
	    else
		stmt.setString(3, last_name);
	    if(office_phone.equals(""))
		stmt.setNull(4,Types.VARCHAR);
	    else
		stmt.setString(4, office_phone);	    
	    if(role.equals(""))
		stmt.setNull(5,Types.VARCHAR);
	    else
		stmt.setString(5, role);
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
	
	String qq = "update users set username=?,first_name=?,last_name=?, office_phone=?, role=? where id=?";
	try{
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    logger.debug(qq);
	    stmt = con.prepareStatement(qq);
	    stmt.setString(1, username);
	    if(first_name.equals(""))
		stmt.setNull(2,Types.VARCHAR);
	    else
		stmt.setString(2, first_name);
	    if(last_name.equals(""))
		stmt.setNull(3,Types.VARCHAR);
	    else
		stmt.setString(3, last_name);
	    if(office_phone.equals(""))
		stmt.setNull(4,Types.VARCHAR);
	    else
		stmt.setString(4, office_phone);
	    if(role.equals(""))
		stmt.setNull(5,Types.VARCHAR);
	    else
		stmt.setString(5, role);			       

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
		String str = rs.getString(2);
		if(str != null) username = str;
		str = rs.getString(3);		
		if(str != null) first_name = str;
		str = rs.getString(4);
		if(str != null) last_name = str;
		str = rs.getString(5);
		if(str != null) office_phone = str;
		str = rs.getString(6);
		if(str != null) role = str;
		role = role.trim();
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
