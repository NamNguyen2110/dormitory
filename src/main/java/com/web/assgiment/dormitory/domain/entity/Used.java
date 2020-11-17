package com.web.assgiment.dormitory.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student_service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Used implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "start_used")
    private Date startUsed;
    @Column(name = "end_used")
    private Date endUsed;
    @Column(name = "status")
    private Integer status;
    @ManyToOne(targetEntity = Business.class)
    @JoinColumn(name = "service_id")
    @JsonIgnore
    private Business business;
    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;
}
