package base.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import base.Constants;

public class ResponseMessage {
	String message,error;
	/*
	 * response object for output structure
	 */
	public ResponseMessage(
			@JsonProperty(Constants.MESSAGE) 	String iMessage, 
			@JsonProperty(Constants.ERROR) 	String iError
			) {
		message = iMessage;
		error = iError;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getError() {
		return error;
	}
} 
