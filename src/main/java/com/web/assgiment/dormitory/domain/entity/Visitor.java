package com.web.assgiment.dormitory.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "visitor")
public class Visitor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "card_id")
    private String cartId;
    @Column(name = "name")
    private String visitorName;
    @Column(name = "dateOfBirth")
    private String dateOfBirth;
    @Column(name = "status")
    private Integer status;
    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "student_id")
    private Student student;
}
