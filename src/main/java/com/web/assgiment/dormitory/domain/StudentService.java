package com.web.assgiment.dormitory.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student_service")
public class StudentService implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
