package com.studyless.studylesskidscrm.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Nullable
    private String thirdName;
    private int age;
    @Nullable
    private Date birthDate;
    @Nullable
    private String contact;
    @Nullable
    private String parentName;
    @Nullable
    private String parentContact;

    @OneToMany(mappedBy = "student")
    private List<IndividualTeacher> individual_teachers;


}
