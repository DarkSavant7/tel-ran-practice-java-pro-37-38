package de.telran.hibernate.h2;

import org.hibernate.Session;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private SessionFactoryUtils sessionFactoryUtils;

    public UserDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public User findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public User findByName(String name) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            User user = session
                    .createQuery("select user from User user where user.name = :name", User.class)
//                    .createQuery("select u.id, u.score, u.name from schema_name.users u where u.name = :name", User.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public UserDto findDtoById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            UserDto user = session
                    .createQuery("select new de.telran.hibernate.h2.UserDto(user.name, user.score) from User user where user.id = :id", UserDto.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.getTransaction().commit();
            return user;
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("select u from User u").getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public void save(User user) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateNameById(Long id, String newName) {
//            HQL Example:
//            session.createQuery("update User u set u.name = :name where u.id = :id")
//                    .setParameter("name", newName)
//                    .setParameter("id", id)
//                    .executeUpdate();
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            user.setName(newName);
            session.getTransaction().commit();
        }
    }

    @Override
    public void testCaching() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.get(User.class, 1L);
            session.get(User.class, 1L);
            session.get(User.class, 1L);
            session.getTransaction().commit();
        }
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.get(User.class, 1L);
            session.get(User.class, 1L);
            session.get(User.class, 1L);
            session.getTransaction().commit();
        }

        var user = findDtoById(1L);

            System.out.printf("User got: %s %s\n", user.getName(), user.getScore());

    }
}
