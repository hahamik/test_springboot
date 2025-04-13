package com.example.test_springboot.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public void save(String username, String password, String email) {
        Query q = em.createNativeQuery("insert into user_tb (username, password, email) values (?, ?, ?)");
        q.setParameter(1, username);
        q.setParameter(2, password);
        q.setParameter(3, email);
        q.executeUpdate();
    }

    public User findByUsername(String username) {
        Query q = em.createNativeQuery("select * from user_tb where username = ?", User.class);
        q.setParameter(1, username);
        return (User) q.getSingleResult();
    }

    public User findById(int userId) {
        Query q = em.createNativeQuery("select * from user_tb where id = ?", User.class);
        q.setParameter(1, userId);
        return (User) q.getSingleResult();
    }

    public User updateById(String password, String email, int userId) {
        Query q = em.createNativeQuery("update user_tb set password = ?, email = ? where id = ?");
        q.setParameter(1, password);
        q.setParameter(2, email);
        q.setParameter(3, userId);
        q.executeUpdate();
        return findById(userId);
    }
}
