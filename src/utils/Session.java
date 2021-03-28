package utils;

public class Session {
	public static int id;
	private static String name="";
	private static String surname="";
	private static double balance = 0;
	
	public static void setSession(int id, String name, String surname, double balance) {
		Session.id = id;
		Session.name = name;
		Session.surname = surname;
		Session.balance = balance;
	}
	
	public static int getId() {
		return id;
	}
	
	public static String getName() {
		return name;
	}
	
	public static String getSurname() {
		return surname;
	}
	
	public static String getFullName() {
		return name + " " + surname;
	}
	public static double getBalance() {
		return balance;
	}

	public static void setBalance(double balance) {
		Session.balance = balance;
	}
	
	public static void clearSession() {
		id = 0;
		name = "";
		surname = "";
	}

	
}
