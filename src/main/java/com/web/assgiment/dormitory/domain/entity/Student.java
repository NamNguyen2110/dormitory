package com.web.assgiment.dormitory.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer studentId;
    @Column(name = "stu_code")
    private String studentCode;
    @Column(name = "card_id")
    private String cardId;
    @Column(name = "date_of_birth")
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
    @JsonIgnore
    private Room room;
    @OneToMany(targetEntity = Ticket.class, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Ticket> ticketSet;
}
