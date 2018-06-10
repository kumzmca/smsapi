package com.sms.outbound;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sms.GenericProcess;

import base.Constants;
import base.message.RequestMessage;
import base.message.ResponseMessage;
import base.util.JedisHandler;
import base.util.db.DBManager;

@Path("/outbound")
public class Process extends GenericProcess{
	public Process() {
		super();
	}
	/*
	 * Inbound process 
	 */
	@POST
	@Path("/sms")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseMessage doProcess(RequestMessage aMessage) throws SQLException{
	try{
		String text = aMessage.getText();
		String from = aMessage.getFrom();
		String to = aMessage.getTo();
		/*
		 * validate message context are valid as boundary, minimum length and from and to are numberic
		 */
		String iError = validateInput(text,from,to);
		if(iError.trim().length()>0)
			return new ResponseMessage("", iError);
		/*
		 * perticular sender reaches more than 50 with in an hour 
		 * then error set as max request reached
		 */
		if(JedisHandler.isMaxRequested(from))
			return new ResponseMessage("", Constants.MAX_REQ_LIMIT+from);
		/*
		 * check the from/to pair weather inbound with stop text if so error as blocked user
		 */
		if(JedisHandler.isKeyAvailable(from, to)) 
			return new ResponseMessage("", Constants.BLOCKED_FROM+from+Constants.BLOCKED_TO+to+Constants.BLOCKED_FINISH);
		/*
		 * any runtime db problem, show as unknown error 
		 */
		if(!setInDb(Constants.OUTBOUND, aMessage))
			return new ResponseMessage("", Constants.UNKNOWN);

		/*
		 * all are set, success message
		 */
		return new ResponseMessage(Constants.OUTBOUND+Constants.SMS_OK,"");
	}
	catch (Exception e) {
		return new ResponseMessage("", Constants.UNKNOWN);
	}

	}
}