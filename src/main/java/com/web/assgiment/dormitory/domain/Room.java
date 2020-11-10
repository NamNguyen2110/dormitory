package com.web.assgiment.dormitory.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "room_code")
    private String roomId;
    @Column(name = "amount")
    private double amount;
    @Column(name = "max_quantity")
    private Integer quantity;
    @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL,
            mappedBy = "room", fetch = FetchType.LAZY)
    private Set<Student> studentSet;
}
