package base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import base.Constants;

public class Validator {
	/*
	 * checking input are numberic with 6 to 16 boundary checking
	 */
	public static boolean isMobileNumber(String iNumber){
		if(General.isNotNull(iNumber))
			return Pattern.matches(Constants.NUMBER_CHECK, iNumber);
		return false;
	}
	
	/*
	 * check boundary 1 to 120
	 */
	public static boolean isValidText(String iText){
		if(General.isNotNull(iText))
			return Pattern.matches(Constants.TEXT_CHECK, iText);
		return false;
	}
	/*
	 * 	checks for STOP keyword to cache in redis
	 */
	public static boolean isCacheable(String iText){
		if(General.isNotNull(iText)){
			/*
			 * if the text contains STOP, STOP\n or STOP \n
			 * 
			 */
			//return Pattern.compile(Constants.CACHE_MARK).matcher(iText).find();	@removed
			/*
			 * if the text is equals STOP, STOP\n or STOP\r STOP
			 * 
			 */
			return Pattern.matches(Constants.CACHE_MARK, iText.trim());
		} 
		return false;
	}
	public static void main(String[] args) {
		//System.out.println(isStopable("lolSTOP\rsf"));
		//System.out.println(isCacheable("stop"));;
	}
}
