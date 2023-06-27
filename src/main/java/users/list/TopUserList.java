package users.list;
import java.util.*;
import java.sql.*;
import java.io.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import users.model.*;
import users.utils.*;

public class TopUserList extends TopUser{

    static Logger logger = LogManager.getLogger(TopUserList.class);	
    List<TopUser> users = null;
    public TopUserList(){
    }
    public List<TopUser> getUsers(){
	return users;
    }
    public String findAssetTrackUsers(){
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
		    TopUser one = new AssetTrackUser(
				     rs.getString(1),
				     rs.getString(2),
				     rs.getString(3),
				     rs.getString(4),
				     rs.getString(5),
				     rs.getString(6)
						     );
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
    public String findLegalTrackUsers(){
	String msg = "";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;			
	String qq = " select * from users order by fullName";
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
		    TopUser one =
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
    public String findLicenseproUsers(){
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
		users = new ArrayList<>();		
		while(rs.next()){
		    TopUser one =
			new LicenseproUser(
				     rs.getString(1),
				     rs.getString(2),
				     rs.getString(3),
				     rs.getString(4),
				     rs.getString(5));
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
    public String findMarketbucksUsers(){
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
		    TopUser one =
			new MarketbucksUser(
				     rs.getString(1),
				     rs.getString(2),
				     rs.getString(3),
				     rs.getString(4),
				     rs.getString(5));
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
    public String findPhonesUsers(){
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
		    TopUser one =
			new PhonesUser(
				     rs.getString(1),
				     rs.getString(2),
				     rs.getString(3),
				     rs.getString(4),
				     rs.getString(5),
				     rs.getString(6),
				     rs.getString(7));
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
    public String findProloanUsers(){
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
		    TopUser one =
			new ProloanUser(
				     rs.getString(1),
				     rs.getString(2),
				     rs.getString(3),
				     rs.getString(4),
				     rs.getString(5),
				     rs.getString(6));
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
    public String findPromtUsers(){
	String msg = "";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;			
	String qq = " select * from users order by 3";
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
		    TopUser one =
			new PromtUser(
				     rs.getString(1),
				     rs.getString(2),
				     rs.getString(3),
				     rs.getString(4),
				     rs.getString(5));
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
    public String findRisktrackUsers(){
	String msg = "";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;			
	String qq = " select * from users order by 2";
	logger.debug(qq);
	try {
	    con = Helper.databaseConnect(getMysqlConStr(), getAppPass());
	    if(con == null){
		msg = "Error coold not connect to db ";		
		logger.error(msg);
	    }
	    else{
		users = new ArrayList<>();
		stmt = con.prepareStatement(qq);
		rs = stmt.executeQuery();
		while(rs.next()){
		    TopUser one =
			new RisktrackUser(
				     rs.getString(1),
				     rs.getString(2),
				     rs.getString(3),
				     rs.getString(4));
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
    public String findSponsorsUsers(){
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
		    TopUser one =
			new SponsorsUser(
				     rs.getString(1),
				     rs.getString(2),
				     rs.getString(3),
				     rs.getString(4),
				     rs.getString(5),
				     rs.getString(6));
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
    public String findLegacytimeUsers(){
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
		    TopUser one =
			new LegacytimeUser(
				     rs.getString(1),
				     rs.getString(2),
				     rs.getString(3),
				     rs.getString(4),
				     rs.getString(5),
				     rs.getString(6));
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
    public String findWaiversUsers(){
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
		    TopUser one =
			new WaiverUser(
				     rs.getString(1),
				     rs.getString(2),
				     rs.getString(3),
				     rs.getString(4),
				     rs.getString(5),
				     rs.getString(6),
				     rs.getString(7));
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
    /**
    public String findForSetUsers(){
	String msg = "";
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;			
	String qq = " select * from users order by 2";
	logger.debug(qq);
	try {
	    con = Helper.getConnection();
	    if(con == null){
		msg = "Error coold not connect to db ";		
		logger.error(msg);
	    }
	    else{
		users = new ArrayList<>();
		stmt = con.prepareStatement(qq);
		rs = stmt.executeQuery();
		while(rs.next()){
		    TopUser one =
			new User(
				 rs.getString(1),
				 rs.getString(2),
				 rs.getString(3));
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
    */
    
}
