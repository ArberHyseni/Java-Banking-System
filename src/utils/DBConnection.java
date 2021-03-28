package utils;

import java.sql.*;

public class DBConnection {

	private static Connection dbConnection;
	private final static String host = "localhost:3306";
	private final static String dbName = "bankdb";
	private final static String username = "root";
	private final static String password = "Krankics1999";

	public static Connection getConnection() {
		if (dbConnection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				dbConnection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + dbName + "?allowMultiQueries=TRUE&useUnicode=TRUE&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return dbConnection;
	}
	public static ResultSet executeQuery(String queryToExecute)
    {
        ResultSet resultSet;
        try
        {
        	PreparedStatement statement = dbConnection.prepareStatement(queryToExecute);
            resultSet = statement.executeQuery(queryToExecute);
            return resultSet;
        } catch(SQLException e)
        {
            System.out.println("Unable to execute query");
            e.printStackTrace();
            return null;
        }
    }
	public static void executeStatement(String statementToExecute)
    {
        try
        {
            PreparedStatement statement = dbConnection.prepareStatement(statementToExecute);
            statement.execute();
        } catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

}