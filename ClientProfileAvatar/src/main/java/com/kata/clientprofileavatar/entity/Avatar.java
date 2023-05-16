package com.kata.clientprofileavatar.entity;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class Avatar {
    private final String uuid;
    private final String name;
    private final String md5;
    private final int fileSize;
    private final byte[] byteInterpretation;

    public Avatar(String name, Integer fileSize, byte[] byteInterpretation) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.md5 = String.valueOf(Hashing.md5().hashString(name, Charsets.UTF_8));
        this.fileSize = fileSize;
        this.byteInterpretation = byteInterpretation;
    }

}
