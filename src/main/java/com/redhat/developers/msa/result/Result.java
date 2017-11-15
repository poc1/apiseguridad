package com.redhat.developers.msa.result;

import java.util.List;

public class Result {
	
	private int code;
	private String error;
	private List<String> messages;
	
	
	public Result(int code, String error) {
		super();
		this.code = code;
		this.error = error;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	

}


