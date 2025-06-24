package com.piyal.studentmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {
  // validation for name
  @NotBlank(message = "Name is required")
  private String name;

  // validation for email
  @Email(message = "Email is invalid")
  @NotBlank(message = "Email is required")
  private String email;
  // valivation for phoneNumber
  @NotBlank(message = "Phone Number is required")
  private String phoneNumber;
  // validation for department
  @NotBlank(message = "Department is required")
  private String department;
}
