package org.kata.clientprofileloader.repository;

import lombok.AllArgsConstructor;
import org.kata.clientprofileloader.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final UserDao userDao;

    public String getUserInfoByIcp(String icp){
        return userDao.getUserInfoById(icp);
    }
    public void setRecognizedDocument (String icp, String recognizedDocument){
         userDao.setRecognizedDocument(icp, recognizedDocument);
    }
}
