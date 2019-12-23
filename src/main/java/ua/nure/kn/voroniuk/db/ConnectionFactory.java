package ua.nure.kn.voroniuk.db;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection getConnection() throws DatabaseException;
}
