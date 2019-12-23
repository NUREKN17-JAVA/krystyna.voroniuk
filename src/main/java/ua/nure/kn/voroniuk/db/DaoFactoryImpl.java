package ua.nure.kn.voroniuk.db;

public class DaoFactoryImpl extends DaoFactory {

    @Override
    public Dao getUserDao() throws ReflectiveOperationException {
        Dao result = null;
        try {
            Class hsqldbUserDaoClass = Class.forName(properties.getProperty(USER_DAO));
            result = (Dao) hsqldbUserDaoClass.newInstance();
            result.setConnectionFactory(createConnection());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new ReflectiveOperationException(e);
        }
        return result;
    }
}
