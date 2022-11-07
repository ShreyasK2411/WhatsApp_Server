package Tester;

import java.sql.SQLException;
import java.text.ParseException;

import com.app.core.AccType;
import com.app.core.CardStatus;
import com.app.core.Category;
import com.app.core.PaymentMode;
import com.app.util.AccountUtil;

/*
 * Menu:
 	* generate csv
 	* get expenses --> 
 	* get accounts -->
 	* add card -->functionality added
 	* add expenses -->functionality added
 	* add account -->functionality added
 	* change status of the card --> functionality added
 
 * Add class expenses
 */


public class TestAccountUtil {

	public static void main(String[] args) throws SQLException {
					try {
						AccountUtil.addCard("583802010005414","10000000", "2023-02-08", 9089, "Valid");
					} catch (SQLException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//			AccountUtil.addAccount("583802010005414", AccType.SAVINGS,"UNION BANK OF INDIA", "BANER", "UBIN0558389", 85646.56);
//			AccountUtil.changeCardStatus("10000000", CardStatus.EXPIRED);
//		System.out.println(Category.SALARY.getID());
//		AccountUtil.addExpense("Zerodha", Category.valueOf("INVESTMENT"), PaymentMode.valueOf("CARD"), 500.23, "583802010005414");

	}

}
