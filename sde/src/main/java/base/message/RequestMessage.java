package base.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import base.Constants;

public class RequestMessage{
    private String text, from, to;
    /*
     * request object for input structure created according to json input
     */
    @JsonCreator
    public RequestMessage(
    		@JsonProperty(Constants.FROM) 	String from, 
			@JsonProperty(Constants.TO) 	String to,
			@JsonProperty(Constants.TEXT) 	String text
			) 
    {
        this.from = from;
        this.to = to;
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getFrom() {
		return from;
	}
    public void setFrom(String from) { 
		this.from = from;
	}
    public String getTo() {
		return to;
	}
    public void setTo(String to) {
		this.to = to;
	}
}
