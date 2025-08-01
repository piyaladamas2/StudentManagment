package com.piyal.studentmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDTO {

  private Long id;
  private String name;
  private String email;
  private String phoneNumber;
  private String department;

}
