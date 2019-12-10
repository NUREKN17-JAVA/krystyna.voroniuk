package ua.nure.kn.voroniuk.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

class DaoFactoryTest extends TestCase {
	
	@BeforeEach
	protected
	void setUp() throws Exception {
	}

	@Test
	void test() {
		testGetUserDao();
	}
	
	public void testGetUserDao() {
		try {
		    DaoFactory daoFactory = DaoFactory.getInstance();
		    assertNotNull("DaoFactory instance is null", daoFactory);
			Dao UserDao = daoFactory.getUserDao();
			assertNotNull("UserDao instance is null", UserDao);
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
