package com.web.assgiment.dormitory.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "service")
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "service_code")
    private String serviceCode;
    @Column(name = "name")
    private String serviceName;
    @Column(name = "amount")
    private double amount;
    @Column(name = "status")
    private Integer status;
}
