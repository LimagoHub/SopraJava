package de.sopra.persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import de.sopra.persistence.models.Person;

public class PersonRepositoryMariaDBImpl implements PersonRepository {
	
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String CONNECTION_STRING = "jdbc:mariadb://localhost:3306/sopra";
	
	private static final String INSERT_STRING = "INSERT INTO Person (firstname, lastname) VALUES (?,?)";
	private static final String UPDATE_STRING = "UPDATE Person set firstname=?, lastname=? where id= ?";
	private static final String DELETE_STRING = "DELETE Person where id= ?";
	private static final String FIND_BY_PK_STRING = "SELECT * FROM Person where id= ?";
	private static final String FIND_ALL_STRING = "SELECT * FROM Person";
	private static final String FIND_BY_VORNAME_STRING = "SELECT * FROM Person where firstname like ?";
	private static final String FIND_BY_NACHNAME_STRING = "SELECT * FROM Person where lastname like ?";
	
	private Connection connection = null;
	private PreparedStatement insertStatement = null;
	private PreparedStatement updateStatement = null;
	private PreparedStatement deleteStatement = null;
	private PreparedStatement findByPkStatement = null;
	private PreparedStatement findAllStatement = null;
	private PreparedStatement findByVornameStatement = null;
	private PreparedStatement findByNachnameStatement = null;
	
	
	

	
	public PersonRepositoryMariaDBImpl() {
		
	}

	@Override
	public void save(Person person) throws Exception{
		getInsertStatement().setString(1, person.getVorname());
		getInsertStatement().setString(2, person.getNachname());
		getInsertStatement().executeUpdate();
		
		try (ResultSet keys = getInsertStatement().getGeneratedKeys()) {
			if(keys.next()) {
				person.setId(keys.getInt(1));
			}
		}
	}

	@Override
	public void update(Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Person findByPrimaryKey(int id) throws Exception{
		getFindByPkStatement().setInt(1, id);
		try (ResultSet resultSet = getFindByPkStatement().executeQuery()){
			if(resultSet.next()) {
				return createPersonFromResultSet(resultSet);
			} 
			return null;
		}
		
	}

	private Person createPersonFromResultSet(ResultSet resultSet) throws SQLException {
		Person person = new Person();
		person.setId(resultSet.getInt("id"));
		person.setVorname(resultSet.getString("firstname"));
		person.setNachname(resultSet.getString("lastname"));
		return person;
	}

	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByVorname(String vorname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByNachname(String nachname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws Exception {
		if(findByPkStatement != null) findByPkStatement.close();
		if(insertStatement != null) insertStatement.close();
		if(connection != null) connection.close();
		
	}

	private Connection getConnection() throws Exception{
		if(connection == null) {
			connection = DriverManager.getConnection(CONNECTION_STRING, "root","yop49JZ");
		}
		return connection;
	}

	

	private PreparedStatement getInsertStatement()  throws Exception{
		if(insertStatement == null) {
			insertStatement = getConnection().prepareStatement(INSERT_STRING, Statement.RETURN_GENERATED_KEYS);
		}
		return insertStatement;
	}

	

	private PreparedStatement getFindByPkStatement() throws Exception{
		if(findByPkStatement == null) {
			findByPkStatement = getConnection().prepareStatement(FIND_BY_PK_STRING);
		}
		return findByPkStatement;
	}

	

}
