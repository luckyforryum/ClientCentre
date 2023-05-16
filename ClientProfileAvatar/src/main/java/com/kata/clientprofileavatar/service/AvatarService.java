package com.kata.clientprofileavatar.service;

import com.kata.clientprofileavatar.entity.Avatar;
import org.springframework.web.multipart.MultipartFile;

public interface AvatarService {
    void initAvatar(MultipartFile avatar);
    Avatar getAvatarById(int id);
}
