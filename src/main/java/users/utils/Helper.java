package users.utils;
import java.util.*;
import java.sql.*;
import java.io.*;
import javax.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.*;
import javax.naming.directory.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Helper{

    static Logger logger = LogManager.getLogger(Helper.class);
    
    Helper(){

    }
    //
    public final static Connection databaseConnect(String conStr,
						   String myPass){
	Connection con = null;
	try {
	    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

	    String str = conStr+
		java.net.URLEncoder.encode(myPass, "UTF-8");
	    con = DriverManager.getConnection(str);
	}catch (Exception sqle) {
	    System.err.println(sqle);
	}
	return con;
    }
									  
    final static void databaseDisconnect(Connection con,
					 Statement stmt,
					 ResultSet rs){
	try {
	    if(rs != null)
		rs.close();
	    if(stmt != null)
		stmt.close();
	    if(con != null)
		con.close();
	}catch (Exception e) {
	    System.err.println(e);
	}
    }
	
    public final static Connection getConnection(){
	
	Connection con = null;
	int trials = 0;
	boolean pass = false;
	int c_con = 1;
	while(trials < 3 && !pass){
	    try{
		trials++;
		logger.debug("Connection try "+trials);
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/MySQL_setusers");
		con = ds.getConnection();
		if(con == null){
		    String str = " Could not connect to DB ";
		    logger.error(str);
		}
		else{
		    c_con++;
		    logger.debug("Got connection: "+c_con);
		    logger.debug("Got connection at try "+trials);
		}
	    }
	    catch(Exception ex){
		logger.error(ex);
	    }
	}
	return con;
	
    }
    public final static void databaseDisconnect(Connection con,
						PreparedStatement stmt, 
						ResultSet rs){
	
	try {
	    if(rs != null) rs.close();
	    rs = null;
	    if(stmt != null) stmt.close();
	    stmt = null;
	    if(con != null) con.close();
	    con = null;
	}
	catch (Exception e) {
	    //  e.printStackTrace();
	    // logger.error(e); // ignore 
	}
	finally{
	    if (rs != null) {
		try { rs.close(); } 
		catch (
		       SQLException e) { ; }
		rs = null;
	    }
	    if (stmt != null) {
		try { stmt.close(); } 
		catch (SQLException e) { ; }
		stmt = null;
	    }
	    if (con != null) {
		try { con.close(); } 
		catch (SQLException e) { ; }
		con = null;
	    }
	}
    }
    public final static void databaseDisconnect(Connection con,
						ResultSet rs,
						PreparedStatement... stmts
						){
	
	try {
	    if(rs != null) rs.close();
	    rs = null;
	    for(PreparedStatement st:stmts){
		if(st != null) st.close();
		st = null;
	    }
	    if(con != null) con.close();
	    con = null;
	}
	catch (Exception e) {
	    //  e.printStackTrace();
	   logger.error(e); // ignore 
	}
	finally{
	    if (rs != null) {
		try { rs.close(); } 
		catch (
		       SQLException e) { ; }
		rs = null;
	    }
	    if (stmts != null) {
		try {
		    for(PreparedStatement st:stmts){
			st.close();
			st = null;
		    }
		}
		catch (SQLException e) { ; }
	    }
	    if (con != null) {
		try { con.close(); } 
		catch (SQLException e) { ; }
		con = null;
	    }
	}
    }		
    
}
