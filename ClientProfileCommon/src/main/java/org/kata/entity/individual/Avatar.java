package org.kata.entity.individual;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "avatars")
public class Avatar {

    @Id
    @Column(name = "id")
    @UuidGenerator
    private String uuid;

    @Column(name = "image_data")
    private Byte[] avatar;

}
