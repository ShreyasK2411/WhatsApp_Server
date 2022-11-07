package com.app.core;

public enum Category {
	SALARY(1),ROI(2),FOOD(3),INVESTMENT(4),TRAVEL(5),EMI(6),SELF(7),MEDICAL(8),MISCELLANEOUS(9),LIVINGEXPENSES(10),RENT(11);
	private int id;
	Category(int id) {
		this.id=id;
	}
	public int getID() {
		return this.id;
	}
}
