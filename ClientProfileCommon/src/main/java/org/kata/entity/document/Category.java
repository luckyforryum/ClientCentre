package org.kata.entity.document;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@Entity
public class Category {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String uuid;

    @Column(name = "category")
    private String nameCategory;

    @ManyToOne
    @JoinColumn(name = "driving_license_id", referencedColumnName = "id")
    private RFDrivingLicenseDoc drivingLicenseDoc;
}
