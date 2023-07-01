package org.kata.clientprofileloader.controller;

import lombok.AllArgsConstructor;
import org.kata.clientprofileloader.service.AvatarService;
import org.kata.entity.individual.Avatar;
import org.kata.repository.AvatarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping()
public class AvatarController {
        private final AvatarService avatarService;
        private final AvatarRepository avatarRepository;
        @GetMapping("/avatar/{uuid}")
        public ResponseEntity<Avatar> getAvatarByUuid(@PathVariable String uuid) {
            Avatar avatar = avatarService.getAvatarByUuid(uuid);
            if (avatar == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(avatar);
        }

    @PostMapping("/avatar")
    public ResponseEntity<Avatar> addAvatar(@RequestBody Avatar avatar) {
        Avatar savedAvatar = avatarRepository.save(avatar);
        return ResponseEntity.ok(savedAvatar);
    }
    @PutMapping("/avatar/{uuid}")
    public ResponseEntity<Avatar> updateAvatar(@PathVariable String uuid, @RequestBody Avatar avatar) {
        Avatar existingAvatar = avatarRepository.getAvatarByUuid(uuid);
        if (existingAvatar == null) {
            return ResponseEntity.notFound().build();
        }
        existingAvatar.setUuid(avatar.getUuid());
        Avatar updatedAvatar = avatarRepository.save(existingAvatar);
        return ResponseEntity.ok(updatedAvatar);
    }

}
