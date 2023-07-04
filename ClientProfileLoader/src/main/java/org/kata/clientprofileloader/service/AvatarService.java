package org.kata.clientprofileloader.service;

import com.google.protobuf.ServiceException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.kata.entity.individual.Avatar;
import org.kata.repository.AvatarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AvatarService {
    private final AvatarRepository avatarRepository;

    @SneakyThrows
    public Optional<Avatar> getClientAvatar(String uuid) {
        try {
            return avatarRepository.getAvatarByUuid(uuid);
        } catch (Exception e) {
            log.error("Ошибка при получении аватара для клиента с UUID: {}", uuid, e);
            throw new ServiceException("Ошибка при получении аватара для клиента");
        }
    }

    @SneakyThrows
    public Avatar addClientAvatar(String uuid, Avatar avatar) {
        try {
            avatar.setUuid(uuid);
            return avatarRepository.save(avatar);
        } catch (Exception e) {
            log.error("Ошибка при добавлении аватара для клиента с ID: {}", uuid, e);
            throw new ServiceException("Ошибка при добавлении аватара для клиента");
        }
    }

    @SneakyThrows
    public Avatar updateClientAvatar(Avatar avatar) {
        try {
            return avatarRepository.save(avatar);
        } catch (Exception e) {
            log.error("Ошибка при обновлении аватара с UUID: {}", avatar.getUuid(), e);
            throw new ServiceException("Ошибка при обновлении аватара");
        }
    }

    @SneakyThrows
    public boolean deleteClientAvatar(String uuid) {
        try {
            Optional<Avatar> avatar = avatarRepository.getAvatarByUuid(uuid);
            if (avatar.isPresent()) {
                avatarRepository.delete(avatar.get());
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("Ошибка при удалении аватара для клиента с ID: {}", uuid, e);
            throw new ServiceException("Ошибка при удалении аватара для клиента");
        }
    }
}





