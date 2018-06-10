package com.sms.inbound;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sms.GenericProcess;

import base.Constants;
import base.message.*;
import base.util.db.DBManager;
import base.util.General;
import base.util.JedisHandler;
import base.util.Validator;

@Path("/inbound")
public class Process extends GenericProcess{
	public Process() {
		super();
	}
	@POST
	@Path("/sms")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ResponseMessage doProcess(RequestMessage aMessage) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException{
		String text = aMessage.getText();
		String from = aMessage.getFrom();
		String to = aMessage.getTo();
		/*
		 * validate inputs
		 */
		String iError = validateInput(text,from,to);
		/*
		 * check any error from inputs
		 */
		if(iError.trim().length()>0)
			return new ResponseMessage("", iError);
		if(!setInDb(Constants.INBOUND, aMessage))
			return new ResponseMessage("", Constants.UNKNOWN);
		/*
		 * message text contains stop or stop/n or stop/r or stop/r/n then cache in redis
		 */
		if(Validator.isCacheable(text)){
			 JedisHandler.setCacheWithExpire(from, to);
		} 
		return new ResponseMessage(Constants.INBOUND+Constants.SMS_OK,"");
	}
}
