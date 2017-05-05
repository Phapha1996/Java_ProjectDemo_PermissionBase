package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	private static ComboPooledDataSource ds = null;

	static {
		try {
			ds = new ComboPooledDataSource("mysql_Ca");
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	
	public static DataSource getPools(){
		return ds;
	}
	
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
	
	public static void release(Connection con,Statement st,ResultSet rs){
		if(con!=null){
			try {
				con.close();
			} catch 
			
			(SQLException e) {
				e.printStackTrace();
			}finally{
				con = null;
			}
		}
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				st = null;
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs = null;
			}
		}

	}
	
}
