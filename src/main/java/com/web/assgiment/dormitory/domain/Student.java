package com.web.assgiment.dormitory.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "stu_code")
    private String studentId;
    @Column(name = "card_id")
    private String cardId;
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;
    @Column(name = "grade")
    private String grade;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private Integer status;
    @OneToMany(targetEntity = Visitor.class, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Visitor> visitorSet;
    @ManyToOne(targetEntity = Room.class)
    @JoinColumn(name = "room_id")
    private Room room;
}
