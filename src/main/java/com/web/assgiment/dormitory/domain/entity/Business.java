package com.web.assgiment.dormitory.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Business implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer serviceId;
    @Column(name = "service_code")
    private String serviceCode;
    @Column(name = "name")
    private String serviceName;
    @Column(name = "amount")
    private double amount;
    @Column(name = "status")
    private Integer status;
    @OneToMany(targetEntity = Used.class, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "business")
    private Set<Used> usedSet;
}
