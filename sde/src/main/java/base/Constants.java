package base;
/*
 * 	All constant field available and some of them configurable
 */
public class Constants {
    public static final String FROM     = "from";
    public static final String TO       = "to";
    public static final String TEXT     = "text";
    
    public static final int MIN_NO_LENGTH = 6;
    public static final int MAX_NO_LENGTH = 16; 
    
    public static final int MIN_TXT_LENGTH = 1;
    public static final int MAX_TXT_LENGTH = 120;
    
    public static final String NUMBER_CHECK = "\\d{"+MIN_NO_LENGTH+","+MAX_NO_LENGTH+"}";
    public static final String TEXT_CHECK   = ".{"+MIN_TXT_LENGTH+","+MAX_TXT_LENGTH+"}";
    
    public static final String CACHE_MARK = "STOP[\r\n]{0,1}|STOP\r\n";
    
    public static final int CACHE_EXPIRE = 4*60*60;	// Seconds to Hours
    public static final int REQUEST_LIMIT_EXPIRE = 1*60*60;	// Seconds to Hours
    public static final String AUTHENTICATION_HEADER = "Authorization";
    
    public static final String method = "POST";
    public static final String DB_USERNAME = "username";
    public static final String DB_PASSWORD = "password";
    public static final String DB_URl = "url";
    public static final String DRIVER = "driver";
    
    public static final String TABLE_NAME ="sms_log";
    public static final String SMS_LOG = "'"+TABLE_NAME+"'";
    
    public static final String INBOUND = "inbound";
    public static final String OUTBOUND = "outbound";
    public static final String TRUE = "true";
    public static final String FALSE = "false"; 
    public static final String MESSAGE = "message";
    public static final String ERROR = "error";
    
    public static final String INVALID = "is invalid";
    public static final String MISSING = "is missing";
    public static final String UNKNOWN = "unknown failure";
    public static final String SMS_OK = " sms is ok";
    public static final String JEDIS_URL = "localhost";
    public static final String BLOCKED_FROM = "sms from ";
	public static final String BLOCKED_TO = " and to ";
	public static final String BLOCKED_FINISH = " blocked by STOP request";
	public static final int OUTBOUND_REQ_LIMIT = 50;
	public static final String MAX_REQ_LIMIT = "limit reached for from ";
}