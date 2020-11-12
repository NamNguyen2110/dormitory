package com.web.assgiment.dormitory.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Business implements Serializable {
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
