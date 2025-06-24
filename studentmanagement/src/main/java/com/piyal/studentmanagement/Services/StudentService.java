package com.piyal.studentmanagement.Services;

import java.util.List;
import com.piyal.studentmanagement.DTO.StudentRequestDTO;
import com.piyal.studentmanagement.DTO.StudentResponseDTO;

public interface StudentService {
  StudentResponseDTO createStudent(StudentRequestDTO stuDto);

  List<StudentResponseDTO> getAllStudents();

  StudentResponseDTO getStudentById(Long id);

  StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto);

  void deleteStudent(Long id);
}
