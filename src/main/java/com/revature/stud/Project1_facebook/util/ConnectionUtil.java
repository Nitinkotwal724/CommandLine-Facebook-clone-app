package com.revature.stud.Project1_facebook.util;

import java.io.*;
import java.sql.*;
import java.util.*;

public class ConnectionUtil
{
//	Singleton Pattern
	private static  Connection con = null;
	private ConnectionUtil()
	{}
	
	public static Connection getConnection() throws SQLException, FileNotFoundException, IOException
	{
	
			Properties prop = new Properties();
			prop.load(new FileReader("C:\\Users\\Nitin Kotwal\\eclipse-workspace\\Project1_facebook\\src\\main\\resources\\application.properties"));
			
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			
			if(con==null)
			{
				con = DriverManager.getConnection(url,username,password);
			}
			
		return con;
		
	}
}
