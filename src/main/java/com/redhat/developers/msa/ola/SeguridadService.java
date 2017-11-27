package com.redhat.developers.msa.ola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.redhat.developers.msa.pojo.CredencialAcessToken;
import com.redhat.developers.msa.pojo.CredencialLogin;
import com.redhat.developers.msa.result.Errores;
import com.redhat.developers.msa.result.Result;


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
		
       // String urlString = "http://apibank-poc1.193b.starter-ca-central-1.openshiftapps.com/seguridad/credencial/";
		  String urlString = "https://api.us.apiconnect.ibmcloud.com/paulosbaldogmailcom-dev/sb/seguridad/credencial/";
		  
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
		
	  return response.toString();		

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
		
	 // String url = "http://localhost:8080/seguridad/credencial/";
	 // String url = "http://apibank-poc1.193b.starter-ca-central-1.openshiftapps.com/seguridad/credencial/";
		String url = "https://api.us.apiconnect.ibmcloud.com/paulosbaldogmailcom-dev/sb/seguridad/credencial/";
		String content = "";
		
		// manejo de errores
		Errores error = new Errores();	
		if(login.getPassword().equals("")|| login.getUser().equals(""))
		{
			error.setResult(new Result(400, "Parámetros incorrectos"));
			if(login.getUser().equals("")) {
				error.AddMessage("user : no puede ser null");				
			}
			if(login.getPassword().equals("")) {
				error.AddMessage("password : no puede ser null");				
			}			
			return error.toString();
		}
		
		
		try {
				 
		    HttpPost post = new HttpPost(url);
		    post.addHeader("Accept", "application/json");
		    post.addHeader("Content-Type", "application/json");
		    StringEntity entity = new StringEntity("{\"user\":\"" + login.getUser() + "\"," +
		    									    "\"password\":\"" + login.getPassword() + "\"}");		    
		    
		    post.setEntity(entity);
		    HttpClient client = new DefaultHttpClient();
		    HttpResponse response = client.execute(post);

		    StatusLine status = response.getStatusLine();
		    
		    // JSON (string) con la respuesta al API externa ( o en su caso el json de un error cualquiera )
			String respuesta = EntityUtils.toString(response.getEntity());
//			JSONObject json = new JSONObject(content);
			
			// estatus de la respuesta ( tambien biene en respuesta )
			if(status.getStatusCode() == 200){

				content = respuesta;
				
			}else{ // cualquier error ( lo atrapo y lo agrego a mi clase manejadora de errores.. )
				
				// se asume que la estructura del error que devuelve la invocación al API externa es siempre la misma, con la estructura json de la clase "Content"
				// osea  {"timestamp":1510785515127,"status":404,"error":"Not Found","message":"No message available","path":"/seguridad/credencia/"}
				error.AtraparError(respuesta);
				content = error.toString();
			}

		}catch(Exception ex) {			
			error.setResult(new Result(500, ex.getMessage()));
			content = error.toString();
		}		    
		return content;

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

	  // String url = "https://webapigateway.dev.mx.corp/santander-mexico/intranet-client-dev/oauth/password/token
	  // String url = "http://apibank-poc1.193b.starter-ca-central-1.openshiftapps.com:80/seguridad/accesstoken/";
		 String url = "https://api.us.apiconnect.ibmcloud.com/paulosbaldogmailcom-dev/sb/seguridad/accesstoken/";
		 String content = "";
		 
		 
		// manejo de errores
		Errores error = new Errores();	
		if(credencial.getClient_id().equals("") || credencial.getClient_secret().equals("") || credencial.getGrant_type().equals("") || credencial.getScope().equals("") /*|| credencial.getToken().equals("")*/)
		{
			error.setResult(new Result(400, "Parámetros incorrectos"));
			if(credencial.getClient_id().equals("")) {
				error.AddMessage("client_id : no puede ser null");				
			}
			if(credencial.getClient_secret().equals("")) {
				error.AddMessage("client_secret : no puede ser null");				
			}			
			if(credencial.getGrant_type().equals("")) {
				error.AddMessage("grant_type : no puede ser null");				
			}
			if(credencial.getScope().equals("")) {
				error.AddMessage("scope : no puede ser null");				
			}
			return error.toString();
		}
		
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

		    StatusLine status = response.getStatusLine();
		    
		 // JSON (string) con la respuesta al API externa ( o en su caso el json de un error cualquiera )
		 	String respuesta = EntityUtils.toString(response.getEntity());
//		 	JSONObject json = new JSONObject(content);

		 // estatus de la respuesta ( tambien biene en respuesta )
		 	if(status.getStatusCode() == 200){

		 		content = respuesta;
		 				
		 	}else{ // cualquier error ( lo atrapo y lo agrego a mi clase manejadora de errores.. )
		 				
		 		// se asume que la estructura del error que devuelve la invocación al API externa es siempre la misma, con la estructura json de la clase "Content"
		 		// osea  {"timestamp":1510785515127,"status":404,"error":"Not Found","message":"No message available","path":"/seguridad/credencia/"}
		 		error.AtraparError(respuesta);
		 		content = error.toString();
		 	}

		 }catch(Exception ex) {			
		 	error.setResult(new Result(500, ex.getMessage()));
		 	content = error.toString();
		 }		    
		 return content;

	}


	
	
}
