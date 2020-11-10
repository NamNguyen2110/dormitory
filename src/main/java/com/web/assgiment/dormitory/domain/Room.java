package com.web.assgiment.dormitory.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "room_code")
    private String roomCode;
    @Column(name = "room_type")
    private String roomType;
    @Column(name = "amount")
    private double amount;
    @Column(name = "max_quantity")
    private Integer quantity;
    @Column(name = "status")
    private Integer status;
    @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL,
            mappedBy = "room", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Student> studentSet;

    private static enum ERoomType {
        GROUP,
        SINGLE,
        DOUBLE
    }
}
