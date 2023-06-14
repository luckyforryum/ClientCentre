package org.kata.clientprofileloader.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kata.clientprofileloader.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
@Transactional
@AllArgsConstructor
public class HibernateUserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;
    private final Logger logger = LogManager.getLogger(HibernateUserDaoImpl.class);

    @Override
    public String getUserInfoById(String icp) {
        try (Session session = sessionFactory.getCurrentSession()) {
            User user = session.get(User.class, icp);
            return user != null ? user.getInfo() : null;
        } catch (Exception e) {
            logger.error("Error getting user info by ID: " + icp, e);
            return null;
        }
    }

    @Override
    public void setRecognizedDocument(String icp, String recognizedDocument) {
        try (Session session = sessionFactory.getCurrentSession()) {
            User user = session.get(User.class, icp);
            if (user != null) {
                user.setRecognizedDocument(recognizedDocument);
            }
        } catch (Exception e) {
            logger.error("not found document: " + recognizedDocument, e);
        }
    }
}
