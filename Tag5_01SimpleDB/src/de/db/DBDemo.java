package de.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBDemo {
	
	
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String CONNECTION_STRING = "jdbc:mariadb://localhost:3306/sopra";
	
	
	public static void main(String[] args) throws Exception{
		
		Class.forName(DRIVER);
		try (Connection connection = DriverManager.getConnection(CONNECTION_STRING,"root","yop49JZ")) {
			try (Statement statement = connection.createStatement()) {
				statement.execute("DROP TABLE IF EXISTS DEMO");
				statement.execute("CREATE TABLE DEMO (id int PRIMARY KEY AUTO_INCREMENT, name varchar(20) NOT NULL)");
				
			}
			
		}
		
		
		

	}

}
