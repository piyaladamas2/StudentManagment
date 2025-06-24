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
import com.piyal.studentmanagement.Services.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/students")
public class StudentController {

  @Autowired
  StudentService studentService;

  @PostMapping
  public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO dto) {
    return ResponseEntity.ok(studentService.createStudent(dto));
  }

  @GetMapping("/all")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
    return ResponseEntity.ok(studentService.getAllStudents());
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<StudentResponseDTO> getStudent(@PathVariable Long id) {
    return ResponseEntity.ok(studentService.getStudentById(id));
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public ResponseEntity<StudentResponseDTO> updateStudent(
      @PathVariable Long id,
      @RequestBody StudentRequestDTO dto) {
    return ResponseEntity.ok(studentService.updateStudent(id, dto));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN','USER')")
  public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);
    return ResponseEntity.ok("Student deleted successfully");
  }
}
