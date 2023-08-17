package com.example.bookstores.model;

import com.example.bookstores.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "/user_Table")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private LocalDate registeredDate = LocalDate.now();
    private LocalDate updatedDate = LocalDate.now();
    private String emailId;
    private String password;
    private boolean verify;
    private int otp;

    public User(UserDTO userDTO) {
        this.updateUser(userDTO);
    }

    public void updateUser(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.dob = userDTO.getDob();
        this.emailId = userDTO.getEmailId();
        this.password = userDTO.getPassword();


    }

}