package de.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DBDemo {
	
	
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String CONNECTION_STRING = "jdbc:mariadb://localhost:3306/sopra";
	
	
	public static void main(String[] args) throws Exception{
		String insertString = "INSERT INTO Person (firstname, lastname) VALUES ('%s','%s')";
		String [] vorname = {"John","John","John","John","John","John F.","John Boy"};
		String [] nachnamen = {"Doe","Wayne","Rambo","McClaine","Wick","Kennedy", "Walton"};
		
		//Class.forName(DRIVER);
		try (Connection connection = DriverManager.getConnection(CONNECTION_STRING,"root","yop49JZ")) {
			connection.setAutoCommit(false);
			try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Person (firstname, lastname) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
				
				for (int i = 0; i < nachnamen.length; i++) {
					statement.setString(1, vorname[i]);
					statement.setString(2, nachnamen[i]);
					statement.executeUpdate();
					try (ResultSet keys = statement.getGeneratedKeys()) {
						if(keys.next()) {
							int primaryKey = keys.getInt(1);
							System.out.println("Primary Key = " + primaryKey);
						}
					}
				}
				
				connection.commit();
				
			
				
			}
		}
	}

}
