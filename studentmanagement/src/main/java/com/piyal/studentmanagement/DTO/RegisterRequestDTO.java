package com.piyal.studentmanagement.DTO;

import com.piyal.studentmanagement.Model.Role;
import lombok.Data;

@Data
public class RegisterRequestDTO {
  private String username;
  private String password;
  private Role role;
}
