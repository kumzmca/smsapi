package base.util.db;

import base.Constants;

public class Queries {
	/*
	 * check weather table available or not in db
	 */
	public static String getQueryTableAvailable(){
		return "SELECT EXISTS (   SELECT 1    FROM   pg_tables WHERE  schemaname = 'public' AND    tablename = "+Constants.SMS_LOG+")";
	} 
	/*
	 * if no, create table with appropriate value
	 */
	public static String getCreateQuery(){
		return "CREATE TABLE "+Constants.TABLE_NAME+"(MESSAGE_TYPE VARCHAR(12) NOT NULL,MESSAGE_FROM VARCHAR(12) NOT NULL,MESSAGE_TO VARCHAR(12) NOT NULL,MESSAGE VARCHAR(120) NOT NULL,TIME TIMESTAMPTZ DEFAULT now())";
	}
	/*
	 * store request message with type (inbound/outbound)
	 */
	public static String getInsertQuery(){
		return "INSERT INTO "+Constants.TABLE_NAME+" (MESSAGE_TYPE, MESSAGE_FROM, MESSAGE_TO, MESSAGE) VALUES(?,?,?,?)";
	}
}
