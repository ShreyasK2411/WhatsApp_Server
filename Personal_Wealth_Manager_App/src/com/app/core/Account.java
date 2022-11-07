package com.app.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
	private String accountNo,ifscCode,branch,bankName;
	private double balance;
	Card card;
	
	//inner class
	private class Card{
		private String cardNo;
		private Date expiryDate;
		private final SimpleDateFormat cSdf=new SimpleDateFormat("dd-MMM-yyyy");
		CardStatus status;
		
		//c'tor
		public Card(String cardNo, String expiryDate, CardStatus status) throws ParseException {
			this.cardNo = cardNo;
			this.expiryDate = cSdf.parse(expiryDate);
			this.status = status;
		}
		
		//getters and setters
		public String getCardNo() {
			return cardNo;
		}

		public void setCardNo(String cardNo) {
			this.cardNo = cardNo;
		}

		public Date getExpiryDate() {
			return expiryDate;
		}

		public void setExpiryDate(Date expiryDate) {
			this.expiryDate = expiryDate;
		}

		public CardStatus getStatus() {
			return status;
		}

		public void setStatus(CardStatus status) {
			this.status = status;
		}
		//end of getters and setters

		@Override
		public String toString() {
			return "Card details \ncardNo:" + cardNo + ", \nexpiryDate:" + cSdf.format(expiryDate) + ", \nstatus:" + status + "]";
		}
		
		
	}

	
	//c'tor
	public Account(String accountNo, String ifscCode, String branch, String bankName, double balance,
			String cardNo,String expiryDate,CardStatus status) throws ParseException {
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
		this.branch = branch;
		this.bankName = bankName;
		this.balance = balance;
		this.card=new Card(cardNo,expiryDate,status);
	}

	//getters and setters
	public String getAccountNo() {
		return accountNo;
	}


	public String getIfscCode() {
		return ifscCode;
	}


	public String getBranch() {
		return branch;
	}


	public String getBankName() {
		return bankName;
	}


	public double getBalance() {
		return balance;
	}
	//end of getters and setters

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", ifscCode=" + ifscCode + ", branch=" + branch + ", bankName="
				+ bankName + ", balance=" + balance + ", card=" + card + "]";
	}
	
}
