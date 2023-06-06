package com.kata.clientprofileavatar.dao;



import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.kata.clientprofileavatar.entity.Avatar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AvatarDaoImp implements AvatarDao{
    @PersistenceContext
    private final EntityManager entityManager;
    public AvatarDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @SneakyThrows
    @Transactional
    public void addAvatar(MultipartFile file) {
        entityManager.merge(Avatar.builder()
                .uuid(UUID.randomUUID().toString())
                .name(file.getOriginalFilename())
                .fileSize((int) file.getSize())
                .byteSize(file.getBytes())
                .md5(String.valueOf(Hashing.md5().hashString(file.getOriginalFilename(),Charsets.UTF_8)))
                .build());

    }

    @Override
    @Transactional
    public Avatar getAvatarById(int id) {
        return entityManager.find(Avatar.class, id);
    }

    @Override
    public List<Avatar> getListOfAvatars() {
        return entityManager.createQuery("select avatar from Avatar avatar", Avatar.class).getResultList();
    }

    @Override
    @Transactional
    public Avatar getUserByAvatarUUID(String uuid) {
        Query query = entityManager.createQuery("select avatar from Avatar avatar where avatar.uuid = :uuid", Avatar.class);
        query.setParameter("uuid", uuid);
        return (Avatar) query.getSingleResult();
    }
}
