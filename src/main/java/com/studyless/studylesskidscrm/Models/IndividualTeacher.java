package com.studyless.studylesskidscrm.Models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class IndividualTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private Date startDate;
    private Date finishDate;

    @OneToMany(mappedBy = "teacher")
    private List<IndividualLessons> students;




}
