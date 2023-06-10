package org.kata.clientprofileloader.service;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoadService {
    private final JdbcTemplate jdbcTemplate;
    public String getUserInfoByIcp(String icp) {
        String query = "SELECT info FROM users WHERE icp = ?";
        return jdbcTemplate.queryForObject(query, String.class, icp);
    }
    public void setRecognizedDocument(String icp, String recognizedDocument) {
        String query = "UPDATE users SET recognizedDocument = ? WHERE icp = ?";
        jdbcTemplate.update(query, recognizedDocument, icp);
    }

}

