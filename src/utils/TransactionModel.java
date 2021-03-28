package utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionModel {
	public static void deposit(int userId, double amount) {
		try {
			String query = "UPDATE users SET balance = balance + " + amount + "WHERE userid =  " + userId + ";";
			PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
			preparedStatement.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static double getAccountBalance(int userId) {
		String getAccountBalance = "SELECT balance from users where userid = " + userId;

        ResultSet account = DBConnection.executeQuery(getAccountBalance);
        double accountBalance = 0;
        
        try
        {
            while(account.next())
            {
                accountBalance = account.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountBalance;
}
	
	public static boolean checkDepositInfo(String deposit) {
		double showinfo = 0;
		try {
			showinfo = Double.parseDouble(deposit);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static void performTransaction(String amount, double balance, int userid) {
			Double d = Double.valueOf(amount);
			try {
				String query = "UPDATE users set balance = " + (balance - d) + " where userid = " + userid + ";";
				
				PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
				preparedStatement.execute(); 
				}
			catch (SQLException ex) {
				ex.printStackTrace();
			}
			} 
	}
