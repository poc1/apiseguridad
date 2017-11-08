package com.redhat.developers.msa.ola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.redhat.developers.msa.pojo1.CredencialAcessToken;
import com.redhat.developers.msa.pojo1.CredencialLogin;


@SuppressWarnings("deprecation")
@Service
public class SeguridadService {
	
	
		
	
	/**
	 * GET
	 * Obtener el token Credencial
	 * @return
	 */
	public /*TokenCredencial*/ String getTokenCredencial()
	{
		StringBuffer response = new StringBuffer();
		
		try {
		
		  String urlString = "http://apibank-poc1.193b.starter-ca-central-1.openshiftapps.com/seguridad/credencial/";
		  
		  URL url = new URL(urlString);
		  HttpURLConnection con = (HttpURLConnection) url.openConnection();
		 
		  // By default it is GET request
		  con.setRequestMethod("GET");
		 
		  //add request header
		  con.setRequestProperty("Accept", "application/json");
		  con.setRequestProperty("Content-Type", "application/json");
		 
		  //int responseCode = con.getResponseCode();
			 
		  // Reading response from input Stream
		  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		  String output;
		  		 
		  while ((output = in.readLine()) != null) {
		   response.append(output);
		  }
		  in.close();
		  
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		
	  // System.out.println(response.toString());
	  return response.toString();		
	  // return customerList.get(0);
	}
		
	
	/**
	 * POST
	 * Obtener el token Credencial
	 * @param login
	 * @return
	 */
	@SuppressWarnings("resource")
	public /*TokenCredencial*/ String getTokenCredencial(CredencialLogin login)
	{
		
		String url = "http://apibank-poc1.193b.starter-ca-central-1.openshiftapps.com/seguridad/credencial/";
		
		try {
				 
		    HttpPost post = new HttpPost(url);
		    post.addHeader("Accept", "application/json");
		    post.addHeader("Content-Type", "application/json");
		    StringEntity entity = new StringEntity("{\"password\":\"jose123\", \"user\":\"jose\"}");
		    post.setEntity(entity);
		    HttpClient client = new DefaultHttpClient();
		    HttpResponse response = client.execute(post);

		    //StatusLine status = response.getStatusLine();
			String content = EntityUtils.toString(response.getEntity());
//			JSONObject json = new JSONObject(content);
//			
//			if(status.getStatusCode() == 200){
//				listener.onSuccess(json);
//			}else{
//				listener.onError(json, status);
//			}	
			
		    return content;
		}catch(Exception ex) {
			
		}		    
		    return "ERROR";

	}

	
	/**
	 * POST
	 * Obtener el access token en base al token credencial
	 * @param credencial
	 * @return
	 */
	@SuppressWarnings("resource")
	public /*RegresoAccessToken*/ String postaccesstoken(CredencialAcessToken credencial)
	{
		
		 String url = "http://apibank-poc1.193b.starter-ca-central-1.openshiftapps.com:80/seguridad/accesstoken/";
	   //String url = "https://webapigateway.dev.mx.corp/santander-mexico/intranet-client-dev/oauth/password/token
		
		try {
				 
		    HttpPost post = new HttpPost(url);
		    post.addHeader("Accept", "application/json");
		    post.addHeader("Content-Type", "application/json");
		    StringEntity entity = new StringEntity("{\"client_id\":\"" + credencial.getClient_id() + "\"," +
		    							"\"client_secret\":\"" + credencial.getClient_secret() + "\"," +
		    							"\"grant_type\":\"" + credencial.getGrant_type() + "\"," +
		    							"\"scope\":\"" + credencial.getScope() + "\"," +
		    		                    "\"token\":\"" + credencial.getToken() + "\"}");
		    post.setEntity(entity);
		    HttpClient client = new DefaultHttpClient();
		    HttpResponse response = client.execute(post);

		    // StatusLine status = response.getStatusLine();
			String content = EntityUtils.toString(response.getEntity());
//			JSONObject json = new JSONObject(content);
//			
//			if(status.getStatusCode() == 200){
//				listener.onSuccess(json);
//			}else{
//				listener.onError(json, status);
//			}	
			
		    return content;
		}catch(Exception ex) {
			
		}		    
		    return "ERROR";

	}


	
	
}
