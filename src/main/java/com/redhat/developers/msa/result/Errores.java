package com.redhat.developers.msa.result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Errores {
		
	Result result;
	List<String> msg;
	ObjectMapper mapper;
	
	public Errores() {		
		result = new Result(200, "OK");
		msg = new ArrayList<>();
		mapper = new ObjectMapper();
	}
	
	public Errores(Result resultado)
	{
		result = new Result(resultado.getCode(), resultado.getError());
		msg = new ArrayList<>();
		mapper = new ObjectMapper();
	}
	
	public void setResult(Result resultado)
	{
		result.setCode(resultado.getCode());
		result.setError(resultado.getError());
		msg = new ArrayList<>();
	}
	
	public void AddMessage(String message)
	{
		msg.add(message);
	}
	
	public void AtraparError(String respuesta)
	{
		// por defecto OK, aunque sabemos que no era el status final de este metodo
		result = new Result(200, "OK");
		msg = new ArrayList<>();
		
		//JSON from String to Object		
		try {
			Content contenido = mapper.readValue(respuesta, Content.class);
			setResult(new Result(contenido.getStatus(), contenido.getError()));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			setResult(new Result(500, e.getMessage()));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			setResult(new Result(500, e.getMessage()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			setResult(new Result(500, e.getMessage()));
		}
	}
	
	
	@Override
	public String toString() {
		
		try {
			result.setMessages(msg);
			return mapper.writeValueAsString(result);				
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return "{\"code\":500, \"error\":\"Error de servidor\"";
		}
	}

}
