package users.utils;
import java.net.URLEncoder;



public class Configuration{
    //
    // Openid parameters
    //
    
    String auth_end_point = "";
    String token_end_point = "";
    String callback_uri = "";
    String client_id = "";
    String client_secret = "";
    String scope="openid";
    String dicovery_uri = "";
    String username = "";
    
    public Configuration(String val, String val2, String val3, String val4,
		  String val5, String val6, String val7, String val8){
	setAuthEndPoint(val);
	setTokenEndPoint(val2);
	setCallbackUri(val3);
	setClientId(val4);
	setClientSecret(val5);
	setScope(val6);
	setUsername(val8);
	
    }
    void setAuthEndPoint(String val){
	if(val != null)
	    auth_end_point = val;
    }
    void setTokenEndPoint(String val){
	if(val != null)
	    token_end_point = val;
    }
    void setCallbackUri(String val){
	if(val != null)
	    callback_uri = val;
    }
    void setClientId(String val){
	if(val != null)
	    client_id = val;
    }
    void setClientSecret(String val){
	if(val != null)
	    client_secret = val;
    }
     void setDicoveryUri(String val){
	 if(val != null)
	     dicovery_uri = val;
     }
     void setScope(String val){
	 if(val != null)
	     scope = val;
     }
     void setUsername(String val){
	 if(val != null)
	     username = val;
     }    
    String getAuthEndPoint(){
	return auth_end_point;
    }
    String getTokenEndPoint(){
	return token_end_point;
    }
    String getCallbackUri(){
	return callback_uri;
    }
    String getClientId(){
	return client_id;
    }
    String getClientSecret(){
	return client_secret;
    }
    String getScope(){
	return scope;
    }
    String getUsername(){
	return username;
    }
    String getDicoveryUri(){
	return dicovery_uri;
    }
    public String toString(){
	String ret = "";
	ret += " auth_end_point: "+auth_end_point;
	ret += " token_end_point: "+token_end_point;
	ret += " callback_uri: "+callback_uri;
	ret += " client_id: "+client_id;
	ret += " client_secret: "+client_secret;
	ret += " scope: "+scope;
	ret += " username: "+username;
	return ret;
    }
}


