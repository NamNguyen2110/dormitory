package com.web.assgiment.dormitory.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    private Date exportDate;
    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;
}
