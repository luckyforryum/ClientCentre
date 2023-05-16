package com.kata.clientprofileavatar.service;

import com.kata.clientprofileavatar.entity.Avatar;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AvatarServiceImpl implements AvatarService {
    //List for test of controller CHANGE after database implemented!!!!
    private final List<Avatar> avatars = new ArrayList<>();

    public void initAvatar(MultipartFile avatar) {
        try {
            Avatar avatarEntity = new Avatar(avatar.getOriginalFilename(),
                    (int) avatar.getSize(),
                    avatar.getBytes());
            avatars.add(avatarEntity);
        } catch (Exception e) {
            log.error(e.getClass() + ": " + e.getMessage());
        }
    }

    public Avatar getAvatarById(int id) {
        return avatars.get(--id);
    }
}
