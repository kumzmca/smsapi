package base.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Stream;

import base.Constants;
import redis.clients.jedis.Jedis;

public class General {
	public static InputStream propertyStream = null;
	public static Properties dbProperties = null;
	/*
	 * null check for objects
	 */
	public static boolean isNotNull(Object iParam){
		return iParam!=null;
	}
	/*
	 * load system properties 
	 */
	public static void loadProperty() throws IOException{
		try{
			dbProperties = new Properties();
			General.propertyStream = new General().getClass().getClassLoader().getResourceAsStream(("db.properties"));
			dbProperties.load(propertyStream);	
		}
		catch(Exception e){
		}
	}
	
	public static String getProperty(String key) throws FileNotFoundException, IOException {
			if(!General.isNotNull(dbProperties))
				loadProperty();
		return dbProperties.getProperty(key);
		
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
	}
}

