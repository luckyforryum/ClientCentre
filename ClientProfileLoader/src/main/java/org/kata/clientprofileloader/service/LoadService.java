package org.kata.clientprofileloader.service;

import lombok.AllArgsConstructor;
import org.kata.clientprofileloader.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoadService {
    private final UserRepository userRepository;

    public String getUserInfoByIcp(String icp) {
        return userRepository.getUserInfoByIcp(icp);
    }

    public void setRecognizedDocument(String icp, String recognizedDocument) {
        userRepository.setRecognizedDocument(recognizedDocument, icp);
    }

}

