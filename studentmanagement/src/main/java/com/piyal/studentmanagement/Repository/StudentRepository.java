package com.piyal.studentmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.piyal.studentmanagement.Model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
