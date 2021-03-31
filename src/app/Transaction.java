package app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DBConnection;

public class Transaction {
	private String user;
	private String transactionsum;
	private String transactiontype;
	private String transactiondate;
	
	public Transaction(String user, String transactionsum, String transacationtype, String transactiondate) {
		this.user = user;
		this.transactionsum = transactionsum;
		this.transactiontype = transacationtype;
		this.transactiondate = transactiondate;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getTransaction() {
		return transactionsum;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setTransaction(String transaction) {
		this.transactionsum = transaction;
	}
	public String getTransactionType() {
		return transactiontype;
	}

	public void setTransactionType(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public String getTransactiondate() {
		return transactiondate;
	}
	
	public void setTransactiondate(String transactiondate) {
		this.transactiondate = transactiondate;
	}
	public String getTransactionsum() {
		return transactionsum;
	}
	public void setTransactionsum(String transactionsum) {
		this.transactionsum = transactionsum;
	}
	
	public static List<Transaction> gettransaction(int userid) {
		List<Transaction> transactions = new ArrayList();
		
		try 
		{
			String query = "SELECT u.firstname, u.lastname, t.transactionsum, t.transactionType, t.transactiondate "
						 + "FROM transactions t "
						 + "INNER JOIN users u ON u.userid = t.userid where t.userid = " + userid + ";";
			
			PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();	
			while(resultSet.next())
			{
				Transaction transaction = new Transaction(resultSet.getString("firstname") + " " + resultSet.getString("lastname"), resultSet.getString("transactionsum"), resultSet.getString("transactionType"), resultSet.getString("transactiondate"));
				transactions.add(transaction);
			}
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}
		return transactions;
	}

	

	
}