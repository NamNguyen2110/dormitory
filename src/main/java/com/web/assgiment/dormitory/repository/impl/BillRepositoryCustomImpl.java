package com.web.assgiment.dormitory.repository.impl;

import com.web.assgiment.dormitory.repository.BillRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository("billRepositoryCustom")
public class BillRepositoryCustomImpl implements BillRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public double roomCharge(Integer studentId) {
        double charge = 0;
        StringBuilder sql = new StringBuilder();
        sql.append("select amount from room join student on room.id=student.id where student.id = :studentId");
        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("studentId", studentId);
        charge = (double) query.getSingleResult();
        return charge;
    }

    @Override
    public double serviceCharge(Integer studentId) {
        return 0;
    }
}
