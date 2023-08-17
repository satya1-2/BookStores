package com.example.bookstores.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data

public class LoginDTO {
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "email are not matching invalid")
    private String emailId;
    private String password;
}
