package ua.nure.kn.voroniuk.db;

import com.mockobjects.dynamic.Mock;

public class MockDaoFactory extends DaoFactory {
	
	private Mock mockUserDao;
	
	public MockDaoFactory() {
		mockUserDao = new Mock(Dao.class);
	}
	
	public Mock getMockUserDao() {
		return mockUserDao;
	}

	@Override
	public Dao getUserDao() throws ReflectiveOperationException {
		return (Dao) mockUserDao.proxy();
	}

}
