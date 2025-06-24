package com.piyal.studentmanagement.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piyal.studentmanagement.DTO.StudentRequestDTO;
import com.piyal.studentmanagement.DTO.StudentResponseDTO;
import com.piyal.studentmanagement.Services.StudentServiceImpl;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  StudentServiceImpl studentservice;

  @PostMapping("/register")
  public StudentResponseDTO register(@Valid @RequestBody StudentRequestDTO dto) {
    return studentservice.createStudent(dto);
  }

  @GetMapping("/all")
  public List<StudentResponseDTO> getAll() {
    return studentservice.getAllStudents();
  }

  @GetMapping("/{id}")
  public StudentResponseDTO getById(@PathVariable Long id) {
    return studentservice.getStudentById(id);
  }

  @PutMapping("/{id}")
  public StudentResponseDTO update(@PathVariable Long id, @Valid @RequestBody StudentRequestDTO dto) {
    return studentservice.updateStudent(id, dto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    studentservice.deleteStudent(id);
    return ResponseEntity.ok("Student deleted successfully.");
  }
}
