package com.kata.clientprofileavatar.dao;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.kata.clientprofileavatar.entity.Avatar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class AvatarDaoImp implements AvatarDao {
    @PersistenceContext
    private final EntityManager entityManager;

    //методы сохранения аватара
    @SneakyThrows
    @Transactional
    public void addAvatarActive(MultipartFile file, String profileIdentification, boolean active) {
/*        Avatar avatar = Avatar.builder().uuid(UUID.randomUUID().toString())
                .name(file.getOriginalFilename())
                .fileSize((int) file.getSize())
                .byteSize(file.getBytes()).profileIdentification(profileIdentification).active(active)
                .md5(String.valueOf(Hashing.md5().hashString(Arrays.toString(file.getBytes()), Charsets.UTF_8)))
                .build();
        log.info("преобразования мультифайла прошло успешно");
        refactorActivity(avatar);
        entityManager.merge(avatar);*/
        Avatar avatar=new Avatar();
        updateAvatarDefolt(file,avatar, active);
        log.info("аватар сохранен");

    }

    //методы изменения аватара
    @SneakyThrows
    @Override
    public void updateAvatar(MultipartFile file, Integer id) {
        Avatar avatar = getAvatarById(id);
        log.info("Аватар из базы получен");
        updateAvatarDefolt(file, avatar, avatar.isActive());
        log.info("Аватар обновлен");
    }

    @SneakyThrows
    public void updateAvatarActive(MultipartFile file, Integer id, boolean active) {
        Avatar avatar = getAvatarById(id);
        log.info("Аватар из базы получен");
        log.info("изображение направлено в БД для обновления");
        updateAvatarDefolt(file, avatar, active);
        log.info("аватар сохранен");
    }

    @SneakyThrows
    public void updateActive(Integer id, boolean active) {
        Avatar avatar = getAvatarById(id);
        log.info("Аватар из базы получен");
        avatar.setActive(active);
        log.info("изображение направлено в БД для обновления");
        updateAvatarDefolt(null, avatar, active);
        log.info("аватар сохранен");
    }

    // методы получения аватара
    @Override
    @Transactional
    public Avatar getAvatarById(Integer id) {
        log.info("id переданно для поиска по БД");
        return entityManager.find(Avatar.class, id);
    }

    @Override
    public List<Avatar> getListOfAvatars() {
        return entityManager.createQuery("select avatar from Avatar avatar", Avatar.class).getResultList();
    }

    @Override
    @Transactional
    public Avatar getByAvatarUUID(String uuid) {
        log.info("uuid переданно для поиска по БД");
        Query query = entityManager.createQuery("select avatar from Avatar avatar where avatar.uuid = :uuid", Avatar.class);
        query.setParameter("uuid", uuid);
        log.info("Аватар из базы получен");
        return (Avatar) query.getSingleResult();
    }

    @Override
    public List<Avatar> getListAvatarsByProfileIdentification(String profileIdentification) {
        log.info("Производится поиск совподений в БД по идентификатору " + profileIdentification);
        List<Avatar> resultAvatar = entityManager.createQuery("select avatar from Avatar avatar where avatar.profileIdentification = :profileIdentification", Avatar.class)
                .setParameter("profileIdentification", profileIdentification).getResultList();
        if (resultAvatar.size() != 0) {
            log.info("Изображение с идентификатором " + profileIdentification + " получено");
        } else {
            log.info("Совподения не найдены");
        }
        return resultAvatar;
    }

    public List<Avatar> getAvatarByActiveAndProfileIdentification(String profileIdentification, boolean active) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Avatar> query = builder.createQuery(Avatar.class);
        Root<Avatar> root = query.from(Avatar.class);
        query.select(root).where(builder.equal(root.get("active"), active), builder.equal(root.get("profileIdentification"), profileIdentification));
        TypedQuery<Avatar> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }

    public Avatar getAvatarByIdAndActive(String profileIdentification) {
        Boolean active = true;
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Avatar> query = builder.createQuery(Avatar.class);
        Root<Avatar> root = query.from(Avatar.class);
        query.select(root).where(builder.equal(root.get("active"), active), builder.equal(root.get("profileIdentification"), profileIdentification));
        TypedQuery<Avatar> typedQuery = entityManager.createQuery(query);
        return typedQuery.getSingleResult();
    }

    //Методы удаления аватара
    @Override
    public void deleteAvatarById(Integer id) {
        log.info("аватар направлен на удаление");
        Avatar avatar = getAvatarById(id);
        log.info("аватар удален");
        entityManager.remove(avatar);
        refactorOfDeleteActivity(avatar);
    }

    public void deleteAvatarByProfileIdentification(String profileIdentification) {
        log.info("аватар направлен на удаление");
        Avatar avatar = getAvatarByIdAndActive(profileIdentification);
        entityManager.remove(avatar);
        log.info("аватар удален");
        refactorOfDeleteActivity(avatar);
    }

    //Метод поиска аналагичных аватаров
    @SneakyThrows
    public List<Avatar> getCheckingDuplicateAvatars(MultipartFile file) {
        String md5 = String.valueOf(Hashing.md5().hashString(Arrays.toString(file.getBytes()), Charsets.UTF_8));
        log.info("Производится поиск совподений в БД по Аватару ");
        List<Avatar> resultAvatar = entityManager.createQuery("select avatar from Avatar avatar where avatar.md5 = :md5", Avatar.class)
                .setParameter("md5", md5).getResultList();
        if (resultAvatar.size() != 0) {
            log.info("Анологичные изображения получены");
        } else {
            log.info("Совподения не найдены");
        }
        return resultAvatar;
    }

    //Внутриние мотоды обработки
    public void refactorOfDeleteActivity(Avatar avatar) {
        if (avatar.isActive() == true && getListAvatarsByProfileIdentification(avatar.getProfileIdentification()).size() != 0) {
            getListAvatarsByProfileIdentification(avatar.getProfileIdentification()).get(0).setActive(true);
            log.info("активный аватар заменен");
        }
    }

    public void refactorActivity(Avatar avatar) {
        List<Avatar> avatarList = getAvatarByActiveAndProfileIdentification(avatar.getProfileIdentification(), avatar.isActive());
        if (avatarList.size() != 0 && avatar.isActive() == true) {
            for (Avatar avatar1 : avatarList) {
                avatar1.setActive(false);
                entityManager.merge(avatar1);
            }
        }
    }

    public void updateAvatarDefolt(MultipartFile file, Avatar avatar, boolean active) throws IOException {
        log.info("изображение направлено в БД для обновления");
        Avatar avatarUpdate = Avatar.builder()
                .uuid(avatar==null?avatar.getUuid():UUID.randomUUID().toString())
                .name(file == null ?avatar.getName(): file.getOriginalFilename())
                .fileSize(file == null ? avatar.getFileSize() : (int) file.getSize())
                .byteSize(file == null ? avatar.getByteSize() : file.getBytes())
                .profileIdentification(avatar.getProfileIdentification())
                .active(active)
                .md5(file == null ? avatar.getMd5() : String.valueOf(Hashing.md5().hashString(Arrays.toString(file.getBytes()), Charsets.UTF_8)))
                .build();
        if (avatar.getId()!=null) {
            avatarUpdate.setId(avatar.getId());
        }
        refactorActivity(avatarUpdate);
        entityManager.merge(avatarUpdate);
    }
}
