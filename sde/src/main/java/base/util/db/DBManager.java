package base.util.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import base.Constants;
import base.message.RequestMessage;
import base.util.General;
import base.util.db.Queries;

public class DBManager {
	static Connection connection = null;
	static Statement stmt=null;
	/*
	 * get information from db.properties
	 * load driver for database and return as connection
	 */
	public static Connection getConnection() throws FileNotFoundException, IOException, SQLException{

        String driver = General.getProperty(Constants.DRIVER);
		String url= General.getProperty(Constants.DB_URl);
		String username= General.getProperty(Constants.DB_USERNAME);
		String password= General.getProperty(Constants.DB_PASSWORD);

		try{
	        Class.forName(driver);
		}
		catch(ClassNotFoundException Exp){
			System.out.println("Kindly install driver properly : PostgreSQL");
		}
		try{
			connection = DriverManager.getConnection(url, username,password);
		}
		catch(Exception exp){
			System.out.println("Kindly check db.properties file");;
		}
		return connection;
	}
	/*
	 * To store type with request message along with table exist check
	 */
	public static boolean setMessageLog(String iType, RequestMessage iMessage) throws SQLException{
		boolean returnValue = false;
		Connection conn = null;
		try{
			try{
				conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(Queries.getQueryTableAvailable());
				if(rs.next()&&!rs.getBoolean(1))		//table available in schema
					stmt.executeUpdate(Queries.getCreateQuery());
			}catch (Exception e) {
				// TODO: handle exception
			}
			finally{
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}
			conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(Queries.getInsertQuery());
			pstmt.setString(1, iType); 
			pstmt.setString(2, iMessage.getFrom());
			pstmt.setString(3, iMessage.getTo());
			pstmt.setString(4, iMessage.getText());
			int inserted = pstmt.executeUpdate();
			if(inserted>0)
				returnValue = true;
		} 
		catch(Exception e){
			e.printStackTrace();
			returnValue = false;
		}
		finally{
			close();
		}
		return returnValue;
	}
	public static void close() throws SQLException{
		/*
		 * close all opened connection
		 */
		if(stmt!=null)
			stmt.close();
		if(connection!=null)
			connection.close();
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
			     System.out.println(DBManager.getConnection());
	}
}
