package com.app.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Date;

import com.app.core.AccType;
import com.app.core.CardStatus;
import com.app.core.Category;
import com.app.core.PaymentMode;

public class AccountUtil {
	private static Connection con;
	
	static {
		try {
			//establishing a connection with the local database
			con=DatabaseUtil.connect();
			System.out.println("Connected.");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}//end of try-catch
	}//end of static block
	
	public static void addExpense(String desc,Category category,PaymentMode mode,double price,String accNo,String cardNo) throws SQLException {
		//get new transaction id from the database
		PreparedStatement pstmt=con.prepareStatement("select getTranxId() from dual");
		ResultSet rset=pstmt.executeQuery();
		int trxId=0;
		while(rset.next())
			trxId=rset.getInt(1);
		
		//inserting value into the expenses table
		pstmt=con.prepareStatement("insert into expenses values (?,?,?,?,?,?,?,?)");
		
		pstmt.setInt(1, trxId);
		pstmt.setDate(2,java.sql.Date.valueOf(java.time.LocalDate.now()));
		pstmt.setString(3, desc);
		pstmt.setInt(4, category.getID());
		pstmt.setString(5, mode.toString());
		pstmt.setDouble(6,price);
		pstmt.setString(7, accNo);
		pstmt.setString(8, cardNo);
		
		int i=pstmt.executeUpdate();
		System.out.println(i+ "expense added successfully!");
	}
	
	//overloading the addExpense method
	public static void addExpense(String desc,Category category,PaymentMode mode,double price,String accNo) throws SQLException {
		//get new transaction id from the database
		PreparedStatement pstmt=con.prepareStatement("select getTranxId() from dual");
		ResultSet rset=pstmt.executeQuery();
		int trxId=0;
		while(rset.next())
			trxId=rset.getInt(1);
		
		//inserting value into the expenses table
		pstmt=con.prepareStatement("insert into expenses (tranx_id,tranx_date,description,catid,tranx_mode,price,accountno) values (?,?,?,?,?,?,?)");
		
		pstmt.setInt(1, trxId);
		pstmt.setDate(2,java.sql.Date.valueOf(java.time.LocalDate.now()));
		pstmt.setString(3, desc);
		pstmt.setInt(4, category.getID());
		pstmt.setString(5, mode.toString());
		pstmt.setDouble(6,price);
		pstmt.setString(7, accNo);
		
		int i=pstmt.executeUpdate();
		System.out.println(i+ "expense added successfully!");
	}
	
	public static void addAccount(String accountNo,AccType acType ,String bankName, String branch, String ifscCode, double balance) throws SQLException {		
		//associating the query and connection
		PreparedStatement pstmt=con.prepareStatement("insert into accounts (accountNo,accountType,bankName,branch,IFSC_code,balance) values (?,?,?,?,?,?)");
		
		//setting the parameters of the query
		pstmt.setString(1, accountNo);
		pstmt.setString(2, acType.toString());
		pstmt.setString(3, bankName);
		pstmt.setString(4, branch);
		pstmt.setString(5, ifscCode);
		pstmt.setDouble(6, balance);
		
		//executing the query
		int i=pstmt.executeUpdate();
		System.out.println(i+ "account added successfully!");
	}
	
	public static void addCard(String accNo,String cardNo,String expiryDate,int pin,String status) throws SQLException, ParseException {
		java.sql.Date sqd=java.sql.Date.valueOf(expiryDate);
		
		//associating the query and connection
		CallableStatement cstmt=con.prepareCall("{call addCard(?,?,?,?,?)}");
		
		//setting the parameters of the query
		cstmt.setString(1, accNo);
		cstmt.setString(2, cardNo);
		cstmt.setDate(3, sqd);
		cstmt.setInt(4, pin);
		cstmt.setString(5, status);
		
		//executing the query
		int i=cstmt.executeUpdate();
		System.out.println(i+" card added successfully!");
	}

	public static void changeCardStatus(String cardNo,CardStatus status) throws SQLException {
		PreparedStatement pstmt=con.prepareStatement("update cards set status=? where cardNo=?");
		
		//setting the query parameters
		pstmt.setString(1, status.name());
		pstmt.setString(2, cardNo);
		
		//executing the query
		int i=pstmt.executeUpdate(); 
		
		System.out.println(i==0?"Card Not Found":"Status changed successfully!");
	}
}
