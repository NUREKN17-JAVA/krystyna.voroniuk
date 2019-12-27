package ua.nure.kn.voroniuk.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import ua.nure.kn.voroniuk.usermanagement.User;
import ua.nure.kn.voroniuk.db.DatabaseException;

class HsqldbUserDao implements Dao<User> {
	
	private static final String SELECT_ALL_QUERY = "SELECT * FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users(firstname, lastname, dateOfBirth) VALUES (?, ?, ?)";
	private ConnectionFactory connectionFactory;
    private final String FIND_BY_ID = "SELECT * FROM USERS WHERE id = ?";
    private final String DELETE_USER = "DELETE FROM USERS WHERE id = ?";
    private final String UPDATE_USER
            = "UPDATE USERS SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
    private static final String SELECT_BY_NAMES = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE firstname = ? AND lastname = ?";

	public HsqldbUserDao() {
	}
	
	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
	@Override
	public User create(User entity) throws DatabaseException {
		try {
		    Connection connection = connectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.setDate(3, new Date(entity.getDateOfBirth().getTime()));
			int numberOfRow = statement.executeUpdate();
			if (numberOfRow != 1) {
				throw new DatabaseException("Number of inserted rows: " + numberOfRow);
			}
			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
			
			ResultSet key = callableStatement.executeQuery();
			if (key.next()) { 
				entity.setId(new Long(key.getLong(1)));
			}
			key.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return entity;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public void update(User entity) throws DatabaseException {
	    try {
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setDate(3, new Date(entity.getDateOfBirth()
                                                        .getTime()));
            preparedStatement.setLong(4, entity.getId());

            int insertedRows = preparedStatement.executeUpdate();

            if (insertedRows != 1) {
                throw new DatabaseException("Number of inserted rows: " + insertedRows);
            }

            connection.close();
            preparedStatement.close();
        } catch (DatabaseException | SQLException e) {
            throw new DatabaseException(e.toString());
        }	
	}

	@Override
	public void delete(User entity) throws DatabaseException {
		 try {
	            Connection connection = connectionFactory.getConnection();

	            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
	            statement.setLong(1, entity.getId());

	            int removedRows = statement.executeUpdate();

	            if (removedRows != 1) {
	                throw new DatabaseException("Number of removed rows: " + removedRows);
	            }
	            connection.close();
	            statement.close();
	        } catch (SQLException e) {
	            throw new DatabaseException(e);
	        }
	}

	@Override
	public User find(Long id) throws DatabaseException {
		 User user = null;
	        try {
	            Connection connection = connectionFactory.getConnection();
	            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
	            statement.setLong(1, id);
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                user = new User();
	                user.setId(resultSet.getLong(1));
	                user.setFirstName(resultSet.getString(2));
	                user.setLastName(resultSet.getString(3));
	                user.setDateOfBirth(resultSet.getDate(4));
	            }
	            connection.close();
	            statement.close();
	            resultSet.close();
	        } catch (SQLException e) {
	            throw new DatabaseException(e);
	        }
	        return user;
	}

	@Override
	public Collection<User> findAll() throws DatabaseException {
		try {
			Collection<User> result = new LinkedList<>();
			Connection connection = connectionFactory.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getLong(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
			}
			//close resources
			connection.close();
			statement.close();
			
			return result;
		} catch(SQLException e) {
			throw new DatabaseException(e);
		}
	}
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	@Override
	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		 this.connectionFactory = connectionFactory; 
	}

	@Override
	public Collection<User> find(String firstName, String lastName) throws DatabaseException {
		Collection result = new LinkedList();
		try {
			Connection connection = connectionFactory.getConnection();
			PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAMES);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
			}
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DatabaseException(e);
		}
		
		return result;
	}
	
}
