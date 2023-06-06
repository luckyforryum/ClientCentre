package com.kata.clientprofileavatar.entity;


import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Entity
@Table(name = "Avatar")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "uuid")
    private String  uuid;
    @Column(name = "name")
    private String name;
    @Column(name = "mp5")
    private String md5;
    @Column(name = "file_size")
    private int fileSize;
    @Lob
    private byte[] byteSize;

}
