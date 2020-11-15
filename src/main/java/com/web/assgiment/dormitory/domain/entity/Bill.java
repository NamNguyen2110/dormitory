package com.web.assgiment.dormitory.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double totalBill;
    private double totalService;
    private double totalRoom;
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Student.class)
    @JoinColumn(name = "student_id")
    private Student student;
}
