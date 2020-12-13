package com.web.assgiment.dormitory.repository.impl;

import com.web.assgiment.dormitory.domain.dto.BillDto;
import com.web.assgiment.dormitory.domain.dto.request.BillExportDto;
import com.web.assgiment.dormitory.domain.dto.request.BillServiceDto;
import com.web.assgiment.dormitory.domain.dto.request.BillServiceRequestDto;
import com.web.assgiment.dormitory.repository.BillRepositoryCustom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    public double serviceChargeByDate(BillExportDto dto) {
        double charge = 0;
        StringBuilder sql = new StringBuilder();
        sql.append("select sum(amount) from student_service ss ");
        sql.append("join service s on ss.service_id=s.id ");
        sql.append("join student st on ss.student_id=st.id and st.id= :studentId and ss.start_used > :startDate and ss.end_used < :endDate");
        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("studentId", dto.getStudentId());
        query.setParameter("startDate", dto.getStartDay());
        query.setParameter("endDate", dto.getEndDay());
        charge = (double) query.getSingleResult();
        return charge;
    }

    @Override
    public List<BillDto> getAllBill() {
        StringBuilder sql = new StringBuilder();
        sql.append("select bill.id,stu_code,total_room,total_service,total_bill,export_date from bill join student on bill.id=student.id");
        Query query = entityManager.createNativeQuery(sql.toString());
        List<Object[]> bills = query.getResultList();
        List<BillDto> billDtoList = new ArrayList<>();
        for (Object[] bill : bills) {
            BillDto billDto = new BillDto();
            billDto.setId((Integer) bill[0]);
            billDto.setStudentCode((String) bill[1]);
            billDto.setTotalRoom((Double) bill[2]);
            billDto.setTotalService((Double) bill[3]);
            billDto.setTotalBill((Double) bill[4]);
            billDto.setExportDate((Date) bill[5]);
            billDtoList.add(billDto);
        }
        return billDtoList;
    }

    @Override
    public List<BillServiceDto> getAllService(BillServiceRequestDto dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("select stu_code,service_code,name,sum(amount) as total from student_service ss ");
        sql.append("join service s on ss.service_id=s.id ");
        sql.append("join student st on ss.student_id = st.id ");
        sql.append("where start_used >= :startDate and end_used <= :endDate group by service_code");
        Query query = entityManager.createNativeQuery(sql.toString());
        query.setParameter("startDate", dto.getStartDate());
        query.setParameter("endDate", dto.getEndDate());
        List<Object[]> list = query.getResultList();
        List<BillServiceDto> dtos = new ArrayList<>();
        for (Object[] o : list) {
            BillServiceDto serviceDto = new BillServiceDto();
            serviceDto.setStudentCode((String) o[0]);
            serviceDto.setServiceCode((String) o[1]);
            serviceDto.setServiceName((String) o[2]);
            serviceDto.setTotal((Double) o[3]);
            dtos.add(serviceDto);
        }
        return dtos;
    }
}
