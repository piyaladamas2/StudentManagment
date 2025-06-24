package com.piyal.studentmanagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.piyal.studentmanagement.Model.Student;

public interface StudentRepository extends JpaRepository<Long, Student> {

}
