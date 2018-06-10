package base.util;

import base.Constants;
import redis.clients.jedis.Jedis;

public class JedisHandler {
	private static final Jedis jedis = new Jedis(Constants.JEDIS_URL);
	 
	/*
	 * check connection for jedis
	 */
	public static boolean checkJedis(){
		try{
			jedis.ping();
			return true;
		}
		catch (Exception e) {
			System.out.println("Kindly check jedis information "+e);
			return false;
		}
	}
	/*
	 * checking max request limit on outbound. from number have 50 request per hour
	 * if it's exceeds, set MAX request limit response error
	 * cache is fast so request management maintained here
	 */
	public static boolean isMaxRequested(String iFrom){
		iFrom="INC"+iFrom;
			String value = jedis.get(iFrom);
			if(General.isNotNull(value)){
				int count = Integer.parseInt(value)+1;
				jedis.set(iFrom, String.valueOf(count));
				return Constants.OUTBOUND_REQ_LIMIT<=count;				
			}
			else {
				jedis.set(iFrom, String.valueOf(1));
				jedis.expire(iFrom, Constants.REQUEST_LIMIT_EXPIRE);
				return false;
			} 
	}
	/*
	 * set cache with 4 horurs expire for inbound stop message in redis
	 */
	public static boolean setCacheWithExpire(String iFrom, String iTo){
		try{
			jedis.set(iFrom, iTo);
			jedis.expire(iFrom, Constants.CACHE_EXPIRE);
			return true;
		}
		catch (Exception e) {
			System.out.println("Exception "+e);
			return false;
		}
	}
	public static boolean isKeyAvailable(String iKey,String iValue){
		String value = jedis.get(iKey);
		if(General.isNotNull(value)&&iValue.equals(value))
			return true;
		return false;
	}
	public static void main(String[] args) {
		System.out.println(checkJedis());
		//setCacheWithExpire("shah", "test");
		//System.out.println(isKeyAvailable("shah","tests"));
	}
}
