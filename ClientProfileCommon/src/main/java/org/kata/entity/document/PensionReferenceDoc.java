package org.kata.entity.document;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "pension_reference")
public class PensionReferenceDoc {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private String uuid;

    @Column(name = "receipt_date")
    @Temporal(TemporalType.DATE)
    private Date receiptDocDate;

    @Column(name = "validate_date")
    @Temporal(TemporalType.DATE)
    private Date validateDateDoc;

    @Column(name = "department")
    private String departmentDoc;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "gender")
    private String gender;
    @Column(name = "doc_version")
    private String docVersion;



    @Column(name = "issued_date")
    @Temporal(TemporalType.DATE)
    private Date getDateDoc;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", referencedColumnName = "id")
    private Documents documents;

}
