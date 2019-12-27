package ua.nure.kn.voroniuk.db;

import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {
	protected static final String USER_DAO = "dao.knure.ctde.usermanagement.db.UserDao";
	private static final String PROPERTIES = "settings.properties";
	private static final String DAO_FACTORY = "dao.factory";
	protected static Properties properties;
	private static DaoFactory instance;

	public static synchronized DaoFactory getInstance() {
		if (instance == null) {
			try {
				Class factoryClass = Class.forName(properties.getProperty(DAO_FACTORY));
				instance = (DaoFactory) factoryClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}

	public static void init(Properties prop) {
		properties = prop;
		instance = null;
	}

	static {
		properties = new Properties();
		try {
			properties.load(DaoFactory.class.getClassLoader().getResourceAsStream(PROPERTIES));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected DaoFactory() {

	}

	protected ConnectionFactory createConnection() {
		return new ConnectionFactoryImpl(properties);
	}

	public abstract Dao getUserDao() throws ReflectiveOperationException;

}
