package ua.nure.kn.voroniuk.db;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.kn.voroniuk.usermanagement.User;
import ua.nure.kn.voroniuk.db.DatabaseException;
import ua.nure.kn.voroniuk.db.HsqldbUserDao;

public class HsqldbUserDaoTest extends DatabaseTestCase {

	private HsqldbUserDao dao; 
	private ConnectionFactory connectionFactory;
	private static final int DAY_OF_BIRTH = 1;
	private static final int MONTH = 1;
	private static final int YEAR = 2013;
	private static final String LAST_NAME = "AAA";
	private static final String FIRST_NAME = "Ann";
	private static final long ID = 1L;
	private static final String USER = "sa";
	private static final String PASSWORD = "knure.ctde.us";
	private static final String URL = "jdbc:hsqldb:file:db/usermanagment";
	private static final String DRIVER = "org.hsqldb.jdbcDriver";
	private HsqldbUserDao hsqldbUserDao;
	private User user;

	private User createUserWithoutID() {
		User user = new User(null, FIRST_NAME, LAST_NAME, new Date());
		return user;
	}

	private User createUserWithID() {
		User user = new User(ID, FIRST_NAME, LAST_NAME, new Date());
		return user;
	}

	public void testCreate() throws DatabaseException {
		User user = new User();
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR, MONTH, DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		assertNull(user.getId());
		User userToCheck = dao.create(user);
		assertNotNull(userToCheck);
		assertNotNull(userToCheck.getId());
		assertEquals(FIRST_NAME, userToCheck.getFirstName());
		assertEquals(LAST_NAME, userToCheck.getLastName());
		assertEquals(calendar.getTime(), userToCheck.getDateOfBirth());
	}

	public void testFind() throws DatabaseException {
		hsqldbUserDao.create(createUserWithID());
		User testUser = hsqldbUserDao.find(ID);
		assertNotNull(testUser);
		assertEquals(testUser.getFirstName(), user.getFirstName());
		assertEquals(testUser.getLastName(), user.getLastName());
	}

	public void testDelete() throws DatabaseException {
		User testUser = createUserWithID();
		hsqldbUserDao.delete(testUser);
		assertNull(hsqldbUserDao.find(ID));
	}

	public void testUpdate() throws DatabaseException {
		String testFirstName = "Steve";
		String testLastName = "Jobs";
		Date testDateOfBirth = new Date();
		User testUser = new User(1L, testFirstName, testLastName, testDateOfBirth);
		hsqldbUserDao.create(testUser);

		testUser.setFirstName("Steve1");

		hsqldbUserDao.update(testUser);
		User updatedUser = hsqldbUserDao.find(testUser.getId());
		assertNotNull(updatedUser);
		assertEquals(testUser.getFirstName(), updatedUser.getFirstName());
		assertEquals(testUser.getLastName(), updatedUser.getLastName());
	}

	public void testFindAll() throws DatabaseException {
		Collection<User> items = dao.findAll();
		assertNotNull(items);
		assertEquals(2, items.size());
	}

	protected void setUp() throws Exception {
		super.setUp();
		user = createUserWithoutID();
		connectionFactory = new ConnectionFactoryImpl(USER, PASSWORD, URL, DRIVER);
		dao = new HsqldbUserDao(connectionFactory);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("userdataset.xml"));
		return dataSet;
	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		return new DatabaseConnection(connectionFactory.getConnection());
	}

}
