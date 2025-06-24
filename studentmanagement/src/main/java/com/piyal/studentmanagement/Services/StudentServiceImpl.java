package com.piyal.studentmanagement.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piyal.studentmanagement.DTO.StudentRequestDTO;
import com.piyal.studentmanagement.DTO.StudentResponseDTO;
import com.piyal.studentmanagement.ExceptionHandler.ResourceNotFoundException;
import com.piyal.studentmanagement.Model.Student;
import com.piyal.studentmanagement.Repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired
  StudentRepository studentRepository;

  @Override
  public StudentResponseDTO createStudent(StudentRequestDTO stuDto) {
    Student student = Student.builder()
        .name(stuDto.getName())
        .email(stuDto.getEmail())
        .department(stuDto.getDepartment())
        .build();
    Student savedStudent = studentRepository.save(student);

    return mapToResponse(savedStudent);
  }

  @Override
  public List<StudentResponseDTO> getAllStudents() {
    return studentRepository.findAll()
        .stream()
        .map(this::mapToResponse)
        .toList();
  }

  @Override
  public StudentResponseDTO getStudentById(Long id) {
    Student student = studentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
    return mapToResponse(student);
  }

  @Override
  public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {
    Student student = studentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
    student.setName(dto.getName());
    student.setEmail(dto.getEmail());
    student.setDepartment(dto.getDepartment());
    return mapToResponse(studentRepository.save(student));
  }

  @Override
  public void deleteStudent(Long id) {
    if (!studentRepository.existsById(id)) {
      throw new ResourceNotFoundException("Student not found with ID: " + id);
    }
    studentRepository.deleteById(id);
  }

  private StudentResponseDTO mapToResponse(Student student) {
    return StudentResponseDTO.builder()
        .id(student.getId())
        .name(student.getName())
        .email(student.getEmail())
        .department(student.getDepartment())
        .build();
  }

}
