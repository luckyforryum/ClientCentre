package org.kata.clientprofileloader.dao;

public interface UserDao {
    String getUserInfoById(String icp);
    void setRecognizedDocument (String icp, String recognizedDocument);
}
