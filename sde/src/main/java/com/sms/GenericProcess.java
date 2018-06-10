package com.sms;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import base.Constants;
import base.message.RequestMessage;
import base.message.ResponseMessage;
import base.util.General;
import base.util.Validator;
import base.util.db.DBManager;

public abstract class GenericProcess {
	public GenericProcess() {
		General.propertyStream = getClass().getClassLoader().getResourceAsStream(("db.properties"));
	}
	public static String validateInput(String iText,String iFrom,String iTo)
	{
		String iError = "";
		/*
		 * validate input if text is null then show as missing 
		 * else	missing boundary check or invalid values set invalid message as response
		 */
		if(!General.isNotNull(iText))
			iError = iError.concat(Constants.TEXT+" "+Constants.MISSING);
		else if(!Validator.isValidText(iText))
			iError = iError.concat(Constants.TEXT+" "+Constants.INVALID);
		
		if(!General.isNotNull(iFrom))
			iError = iError.concat((iError.trim().length()>0?(", "):"")+Constants.FROM+" "+Constants.MISSING);
		else if(!Validator.isMobileNumber(iFrom))
			iError = iError.concat((iError.trim().length()>0?(", "):"")+Constants.FROM+" "+Constants.INVALID);
		
		if(!General.isNotNull(iTo))
			iError = iError.concat((iError.trim().length()>0?(", "):"")+Constants.TO+" "+Constants.MISSING);
		else if(!Validator.isMobileNumber(iTo))
			iError = iError.concat((iError.trim().length()>0?(", "):"")+Constants.TO+" "+Constants.INVALID);
		
		return iError;
	}
	public static boolean setInDb(String iType, RequestMessage iMessage){
		try{
			/*
			 * set inbound/outbound with requst message detail in database
			 */
			DBManager.setMessageLog(iType, iMessage);
			return true;
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
