package de.telran.hibernate.h2;

public class MainApp {
    public static void main(String[] args) {
        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
        try {
            UserDao userDao = new UserDaoImpl(sessionFactoryUtils);
            userDao.testCaching();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactoryUtils.shutdown();
        }
    }
}
