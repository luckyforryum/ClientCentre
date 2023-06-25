package org.kata.clientprofileloader.controller;

import lombok.AllArgsConstructor;
import org.kata.clientprofileloader.service.ContactMediumService;
import org.kata.entity.contactmedium.ContactMedium;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/contact-mediums")
public class ContactMediumController {
    private final ContactMediumService contactMediumService;
    @GetMapping("/{uuid}")
    public ResponseEntity<ContactMedium> getContactMediumById(@PathVariable String uuid) {
        ContactMedium contactMedium = contactMediumService.getContactMediumById(uuid);
        if (contactMedium == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contactMedium);
    }

    @PostMapping
    public ResponseEntity<ContactMedium> addContactMedium(@RequestBody ContactMedium contactMedium) {
        ResponseEntity<ContactMedium> response = contactMediumService.addContactMedium(contactMedium);
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(response.getBody());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ContactMedium> updateContactMedium(@PathVariable String uuid, @RequestBody ContactMedium contactMedium) {
        ResponseEntity<ContactMedium> response = contactMediumService.updateContactMedium(uuid, contactMedium);
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok().build();
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
