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
        creatAvatarAdd(file,new Avatar(), active, profileIdentification);
    }

    //методы изменения аватара
    @SneakyThrows
    @Override
    public void updateAvatar(MultipartFile file, Integer id) {
        Avatar avatar = getAvatarById(id);
        log.info("Аватар из базы получен");
        creatAvatarAdd(file, avatar, avatar.isActive(),null);
    }

    @SneakyThrows
    public void updateAvatarActive(MultipartFile file, Integer id, boolean active) {
        Avatar avatar = getAvatarById(id);
        log.info("Аватар из базы получен");
        creatAvatarAdd(file, avatar, active,null);
    }

    @SneakyThrows
    public void updateActive(Integer id, boolean active) {
        Avatar avatar = getAvatarById(id);
        log.info("Аватар из базы получен");
        avatar.setActive(active);
        creatAvatarAdd(null, avatar, active,null);
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
        log.info("Формирование запроса  для поиска");
        CriteriaQuery<Avatar> query = builder.createQuery(Avatar.class);
        Root<Avatar> root = query.from(Avatar.class);
        log.info("Поиск Аватара по статусу и идентификатору пользователя");
        query.select(root).where(builder.equal(root.get("active"), active), builder.equal(root.get("profileIdentification"), profileIdentification));
        TypedQuery<Avatar> typedQuery = entityManager.createQuery(query);
        log.info("Данные получены");
        return typedQuery.getResultList();
    }

    public Avatar getAvatarByIdAndActive(String profileIdentification) {
        Boolean active = true;
        log.info("Формирование запроса  для поиска");
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Avatar> query = builder.createQuery(Avatar.class);
        Root<Avatar> root = query.from(Avatar.class);
        query.select(root).where(builder.equal(root.get("active"), active), builder.equal(root.get("profileIdentification"), profileIdentification));
        log.info("Поиск Аватара по статусу Тру");
        TypedQuery<Avatar> typedQuery = entityManager.createQuery(query);
        log.info("Данные получены");
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
    @SneakyThrows
    public int CheckingDuplicateAvatars(MultipartFile file, String profileIdentification) {
        byte[]image1=getAvatarByIdAndActive(profileIdentification).getByteSize();
        log.info("Изображение из бд получено");
        byte[]image2= file.getBytes();
        log.info("мультифаил приобразован");
        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;
        for (int i = 0; i < image1.length; i++) {
            int pixel1 = image1[i] & 0xff; // convert byte to unsigned int
            int pixel2 = image2[i] & 0xff;

            dotProduct += pixel1 * pixel2;
            norm1 += pixel1 * pixel1;
            norm2 += pixel2 * pixel2;
        }
        double similarity = dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
        log.info("процент определен");
        return (int) (similarity * 100);
    }
    //Внутриние мотоды обработки
    private void refactorOfDeleteActivity(Avatar avatar) {
        if (avatar.isActive() == true && getListAvatarsByProfileIdentification(avatar.getProfileIdentification()).size() != 0) {
            getListAvatarsByProfileIdentification(avatar.getProfileIdentification()).get(0).setActive(true);
            log.info("активный аватар заменен");
        }
    }

    private void refactorActivity(Avatar avatar) {
        List<Avatar> avatarList = getAvatarByActiveAndProfileIdentification(avatar.getProfileIdentification(), avatar.isActive());
        if (avatarList.size() != 0 && avatar.isActive() == true) {
            for (Avatar avatar1 : avatarList) {
                avatar1.setActive(false);
                entityManager.merge(avatar1);
            }
            log.info("активный аватар заменен");
        }
    }

    private void creatAvatarAdd(MultipartFile file, Avatar avatar, boolean active, String profileIdentification) throws IOException {
        log.info("Данные для формирования аватара получены");
        Avatar avatarUpdate = Avatar.builder()
                .uuid(avatar==null?avatar.getUuid():UUID.randomUUID().toString())
                .name(file == null ?avatar.getName(): file.getOriginalFilename())
                .fileSize(file == null ? avatar.getFileSize() : (int) file.getSize())
                .byteSize(file == null ? avatar.getByteSize() : file.getBytes())
                .profileIdentification(profileIdentification==null?avatar.getProfileIdentification():profileIdentification)
                .active(active)
                .md5(file == null ? avatar.getMd5() : String.valueOf(Hashing.md5().hashString(Arrays.toString(file.getBytes()), Charsets.UTF_8)))
                .build();
        log.info("преобразования мультифайла прошло успешно");
        if (avatar.getId()!=null) {
            avatarUpdate.setId(avatar.getId());
            log.info("Аватару присвоен id для обновления информации в БД");
        }
        refactorActivity(avatarUpdate);
        entityManager.merge(avatarUpdate);
        log.info("Данные по Аватару сохранены в БД");
    }
}
