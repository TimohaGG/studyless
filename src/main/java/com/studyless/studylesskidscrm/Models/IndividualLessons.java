package com.studyless.studylesskidscrm.Models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class IndividualLessons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private IndividualTeacher teacher;

    @OneToOne
    private Student student;


    private Date courseStartDay;
    private Date courseFinishDay;

    @OneToOne
    private Course course;

    @ManyToOne
    private User manager;


}
