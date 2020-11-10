package com.web.assgiment.dormitory.repository;

import com.web.assgiment.dormitory.domain.Room;
import com.web.assgiment.dormitory.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.studentCode LIKE CONCAT('%',:studentCode,'%') AND s.status = 1")
    Page<Room> filterByCode(Pageable pageable, @Param("studentCode") String studentCode);

    @Query("SELECT s FROM Student s WHERE s.status = 1")
    Page<Student> getAllStudent(Pageable pageable);

    boolean existsByStudentCode(String studentCode);

    boolean existsByCardId(String cartId);
}
