package com.kata.clientprofileavatar.service;

import com.kata.clientprofileavatar.dao.AvatarDao;
import com.kata.clientprofileavatar.entity.Avatar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@Service
@Slf4j
public class AvatarServiceImpl implements AvatarService {
    private AvatarDao avatarDao;

    public AvatarServiceImpl(AvatarDao avatarDao) {
        this.avatarDao = avatarDao;
    }

    @Transactional
    public void addAvatar(MultipartFile file) throws IOException {
        avatarDao.addAvatar(file);

    }

    //заменить сет билдером сушьность билдера
    @Override
    @Transactional
    public Avatar getAvatarById(int id) {
        return avatarDao.getAvatarById(id);
    }

    @Override
    public List<Avatar> getListOfAvatars() {
        return avatarDao.getListOfAvatars();
    }

    @Override
    @Transactional
    public Avatar getUserByAvatarUUID(String uuid) {
        return avatarDao.getUserByAvatarUUID(uuid);
    }
}
