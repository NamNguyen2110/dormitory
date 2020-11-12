package com.web.assgiment.dormitory.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student_service")
public class Used implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "start_used")
    private Date startUsed;
    @Column(name = "end_used")
    private Date endUsed;
}
