package users.model;
import java.util.*;
import java.sql.*;
import java.io.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import users.utils.*;

public class WaiverUser extends TopUser implements Serializable{

    static Logger logger = LogManager.getLogger(WaiverUser.class);	

    public WaiverUser(){

    }
    public WaiverUser(String str,
		      String str2,
		      String str3,
		      String str4,
		      String str5,
		      String str6,
		      String str7	      
		      ){
	setVals(str, str2, str3, str4, str5, str6, str7);
    }
    private void setVals(
			 String str,
			 String str2,
			 String str3,
			 String str4,
			 String str5,
			 String str6,
			 String str7
			 ){
	setId(str);
	setUsername(str2);
	setFullName(str3);
	setDept(str4);	
	setRole(str5);
	setActiveMail(str6);
	setInactive(str7);

    }
  public String toString(){
		
	if(!fullName.equals("")) return fullName;
	return empid;
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
	return ((WaiverUser)obj).getUsername().equals(username);

    }    
    public String doSave(){
	String msg="";
	Connection con = null;
	PreparedStatement stmt = null, stmt2=null;
	ResultSet rs = null;	
	String qq = "insert into users values(0,?,?,?,?,?,?)";
	String qq2 = "select LAST_INSERT_ID() ";
	logger.debug(qq);
	try{
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    stmt = con.prepareStatement(qq);
	    int jj = 1;
	    stmt.setString(1, username);
	    stmt.setString(2, fullName);
	    if(dept.isEmpty()){
		stmt.setNull(3, Types.VARCHAR);
	    }
	    else{
		stmt.setString(3, dept);
	    }
	    if(!role.equals("")){
		stmt.setString(4, role);
	    }
	    else
		stmt.setNull(4, Types.VARCHAR);
	    if(!activeMail.equals("")){
		stmt.setString(5, "y");
	    }
	    else
		stmt.setNull(5, Types.CHAR);
	    if(!inactive.equals("")){
		stmt.setString(6, "y");
	    }
	    else
		stmt.setNull(6, Types.CHAR);
	    stmt.executeUpdate();
	    //
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
	    Helper.databaseDisconnect(con, rs, stmt, stmt2);
	}
	return msg;
    }
    public  String doDelete(){
	String msg="";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;		
	String qq = "delete from users where id = ? ";
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
	//
	String msg="";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;		
	String qq = "update users set username=?,full_name=?,dept=?,role=?, activeMail=?,inactive=? where id=? ";
	String qq2 = "";
	logger.debug(qq);
	try{
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    stmt = con.prepareStatement(qq);
	    stmt.setString(1, username);
	    stmt.setString(2, fullName);
	    if(dept.isEmpty()){
		stmt.setNull(3, Types.VARCHAR);
	    }
	    else{
		stmt.setString(3, dept);
	    }
	    if(!role.equals("")){
		stmt.setString(4, role);
	    }
	    else
		stmt.setNull(4, Types.VARCHAR);
	    if(!activeMail.equals("")){
		stmt.setString(5, "y");
	    }
	    else
		stmt.setNull(5, Types.CHAR);
	    if(!inactive.equals("")){
		stmt.setString(6, "y");
	    }
	    else
		stmt.setNull(6, Types.CHAR);
	    stmt.setString(7, id);
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
	String qq = " select * from users where id=?";
	logger.debug(qq);
	try {
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    if(con == null){
		msg = "Error coold not connect to db ";
		logger.error(msg);

	    }
	    else{
		stmt = con.prepareStatement(qq);
		stmt.setString(1, id);
		rs = stmt.executeQuery();
		if(rs.next()){
		    setVals(id,
			    rs.getString(2),
			    rs.getString(3),
			    rs.getString(4),
			    rs.getString(3),
			    rs.getString(6),
			    rs.getString(7));
		}
	    }
	}
	catch(Exception e){
	    logger.error(e);
	    msg += e+":"+qq;
	}
	finally{
	    Helper.databaseDisconnect(con, stmt, rs);
	}
	return msg;
    }
    
}
