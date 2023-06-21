package users.model;
import java.sql.*;
import javax.naming.*;
import java.io.Serializable;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import users.utils.*;

public class TopUser{

    String id="", userid = "", fullName="", active="";
    String role="",inspector="", fname="", lname="",
	manager="",phone="",
	empid="",dept="",inactive="";
    String mail_notification="";
    String username="",activeMail="", project_manager="";
    String first_name="", last_name="", office_phone="";
    String conUrl="", appStr="", appUser="", appPass="";
    
    static Logger logger = LogManager.getLogger(TopUser.class);

    public TopUser(){
    }
    public void setId(String val){
	if(val != null)
	    id = val;
    }    
    public void setEmpid(String val){
	if(val != null)
	   empid = val;
    }
    public void setUserid(String val){
	if(val != null)
	   userid = val;
    }
    public void setUsername(String val){
	if(val != null)
	   username = val;
    }    
    public void setFullName(String val){
	if(val != null)
	    fullName = val;
    }
    public void setFname(String val){
	if(val != null)
	    fname = val;
    }
    public void setLname(String val){
	if(val != null)
	    lname = val;
    }    
    public void setManager(String val){
	if(val != null)
	    manager = val;
    }
    public void setPhone(String val){
	if(val != null)
	    phone = val;
    }    
    public void setLast_name(String val){
	if(val != null)
	   last_name = val;
    }
    public void setFirst_name(String val){
	if(val != null)
	    first_name = val;
    }
    public void setOffice_phone(String val){
	if(val != null)
	    office_phone = val;
    }        
    public void setRole(String val){
	if(val != null)
	    role = val;
    }
    public void setDept(String val){
	if(val != null)
	    dept = val;
    }
    public void setMail_notification(String val){
	if(val != null)
	    mail_notification = val;
    }    
    public void setProject_manager(String val){
	if(val != null)
	    project_manager = val;
    }    
    public void setInactive(String val){
	if(val != null)
	    inactive = val;
    }
    public void setActive(String val){
	if(val != null)
	    active = val;
    }    
    public void setActiveMail(String val){
	if(val != null)
	    activeMail = val;
    }    
    public String getId(){
	return id;
    }    
    public String getEmpid(){
	return empid;
    }
    public String getUserid(){
	return userid;
    }
    public String getUsername(){
	return username;
    }    
    public String getFullName(){
	if(fullName.isEmpty()){
	    String str = "";
	    if(!first_name.isEmpty())
		str += first_name;
	    if(!last_name.isEmpty()){
		if(!str.isEmpty()) str+= " ";
		str += last_name;
	    }
	    if(!fname.isEmpty()){
		str += fname;
	    }
	    if(!lname.isEmpty()){
		if(!str.isEmpty()) str+= " ";
		str += lname;
	    }	    
	    return str;
	}
	return fullName;
    }
    
    public String getFname(){
	return fname;
    }
    public String getLname(){
	return lname;
    }
    public String getMail_notification(){
	return mail_notification;
    }    
    public String getPhone(){
	return phone;
    }
    public String getManager(){
	return manager;
    }        
    public String getFirst_name(){
	return first_name;
    }
    public String getOffice_phone(){
	return office_phone;
    }
    public String getLast_name(){
	return last_name;
    }
    public String getProject_manager(){
	return project_manager;
    }
    public String getRole(){
	return role;
    }
    public String getDept(){
	return dept;
    }
    public String getInactive(){
	return inactive;
    }
    public String getActive(){
	return active;
    }    
    public String getActiveMail(){
	return activeMail;
    }
    public void setDbParams(String str){
	String strArr[] = str.split(",");
	if(strArr != null){
	    setConUrl(strArr[0]);
	    setAppStr(strArr[1]);
	    setAppUser(strArr[2]);
	    setAppPass(strArr[3]);
	}
    }
    void setConUrl(String val){
	if(val != null)
	    conUrl = val;
    }
    void setAppStr(String val){
	if(val != null)
	    appStr = val;
    }
    void setAppUser(String val){
	if(val != null)
	    appUser = val;
    }
    void setAppPass(String val){
	if(val != null)
	    appPass = val;
    }
    public String getAppPass(){
	return appPass;
    }
    public String getMysqlConStr(){
	if(conUrl.isEmpty() || appStr.isEmpty() || appUser.isEmpty()){
	    return null;
	}
	String  conStr = conUrl+"/"+appStr+"?autoReconnect=true&serverTimezone=America/New_York&useSSL=false&user="+appUser+"&password=";
	return conStr;
    }

}






















































