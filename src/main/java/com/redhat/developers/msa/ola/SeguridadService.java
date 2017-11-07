package com.redhat.developers.msa.ola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Service;

import com.redhat.developers.msa.pojo1.CredencialLogin;


@Service
public class SeguridadService {
	
	
	
	
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

	
	
		
	public /*TokenCredencial*/ String getTokenCredencial(CredencialLogin login)
	{
		
		String url = "http://apibank-poc1.193b.starter-ca-central-1.openshiftapps.com/seguridad/credencial/";
		
		 HttpClient client = new HttpClient();
		    PostMethod post = new PostMethod(url);
		    //post.setRequestHeader("Authorization", encodedAuthorizationString);
//		    if(headers != null && !headers.isEmpty()){
//		        for(Entry<String, String> entry : headers.entrySet()){
//		            //post.setRequestHeader(new Header(entry.getKey(), entry.getValue()));
//		            //in the old code parameters were set as headers (the line above is replaced with the line below)
//		            post.addParameter(new Header(entry.getKey(), entry.getValue()));
//		        }
//		    }
		    post.setRequestHeader(new Header("Accept","application/json"));
		    post.setRequestHeader(new Header("Content-Type","application/json"));
		    
		    NameValuePair[] pair = new NameValuePair[2];
		    pair[0] = new  NameValuePair();
		    pair[0].setName("user");
		    pair[0].setValue("jose");
		    
		    
		    pair[1] = new  NameValuePair();
		    pair[1].setName("password");
		    pair[1].setValue("jose123");
		    
		    
		    //pair[0].setName("password");
		    		    
		    post.setRequestBody(pair);
		    
		    
		    
		    String responseFromPost = "Error";
		    try {
				client.executeMethod(post);
				responseFromPost = post.getResponseBodyAsString();
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    post.releaseConnection();
		    return responseFromPost;
		    
		    
		    
//		StringBuilder builder = new StringBuilder();
//		
//		try {
//		
//		  //String urlString = "http://apibank-poc1.193b.starter-ca-central-1.openshiftapps.com/seguridad/credencial/";
//		  String urlString = "http://localhost:8080/seguridad/credencial/";
//		  
//		  
//		  HashMap<String, String> params = new HashMap<String, String>();
////	        params.put("client_id", id);
////	        params.put("client_secret", secret);
////	        params.put("grant_type", "authorization_code");
////	        params.put("redirect_uri", redirect);
////	        params.put("code", code);  // your INSTAGRAM code received
//		    params.put("password", login.getPassword());
//		    params.put("user", login.getUser());
//	        //Set set = params.entrySet();
//	        //Iterator i = set.iterator();
//	        StringBuilder postData = new StringBuilder();
//	        for (Map.Entry<String, String> param : params.entrySet()) {
//	            if (postData.length() != 0) {
//	                postData.append('&');
//	            }
//	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
//	            postData.append('=');
//	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
//	        }
//	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
//		  
//	      URL url = new URL(urlString);
//	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();		  
//	      
//	        conn.setRequestMethod("POST");
//	        conn.setRequestProperty("Accept", "application/json");	        
//	        conn.setRequestProperty("Content-Type", "application/json");	        
//	        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
//	        conn.setDoOutput(true);
//	        conn.getOutputStream().write(postDataBytes);
//	        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));	        
//	        for (String line = null; (line = reader.readLine()) != null;) {
//	            builder.append(line).append("\n");
//	        }
//	        reader.close();
//	        conn.disconnect();   
//		  
//		}catch(IOException ex) {
//			System.out.println(ex.getMessage());
//		}
//		
//	  // System.out.println("INSTAGRAM token returned: "+builder.toString());
//	  return builder.toString();		
//		
//		//return "Seguridad fuente";
	}

	
}
