package com.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseUtil {
	public static Connection connect() throws ClassNotFoundException, SQLException {
		try(Scanner sc=new Scanner(System.in)) {
			//loading the oracle jar file
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//url for database connectivity
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			//take the username and password from the user
			System.out.println("Enter Username and Password:");
			return DriverManager.getConnection(url,sc.next(),sc.next());
		}
		
	}
}
