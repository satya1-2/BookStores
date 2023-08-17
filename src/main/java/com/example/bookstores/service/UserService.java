package com.example.bookstores.service;

import com.example.bookstores.dto.LoginDTO;
import com.example.bookstores.dto.UserDTO;
import com.example.bookstores.exception.UserCustomException;
import com.example.bookstores.model.User;
import com.example.bookstores.repo.UserRepo;
import com.example.bookstores.responseDto.ResponseDTO;
import com.example.bookstores.util.EmailService;
import com.example.bookstores.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTToken jwtToken;
    @Autowired
    private EmailService userDetailsServices;

    private User checkIfUserExist(String emailId) {
        return userRepo.getUserByEmailId(emailId);
    }

    public ResponseDTO registerUser(UserDTO userDTO) {
        User data = checkIfUserExist(userDTO.getEmailId());

        if (data == null) {
            Random random = new Random();
            User registerNewUser = new User(userDTO);
            int otp = (random.nextInt(9000) + 1000); // Generate a 4-digit OTP
            registerNewUser.setOtp(otp);
            userRepo.save(registerNewUser);

            userDetailsServices.sendEmail(userDTO.getEmailId(), "User Account Verification",
                    "Hello " + userDTO.getFirstName() +
                            "\n\nVerification OTP: " + otp);

            return new ResponseDTO("New user registered, OTP sent for verification", data);
        } else {
            return new ResponseDTO("User with same Email ID already exists.", data);
        }
    }

    public ResponseDTO verifyOtp(String emailId, Integer otp) {
        if (isVerifyOtp(emailId, otp)) {
            User verifiedUser = userRepo.getUserByEmailId(emailId);
            verifiedUser.setVerify(true);
            userRepo.save(verifiedUser);
            return new ResponseDTO("User registered and verified", verifiedUser);
        } else {
            return new ResponseDTO("OTP verification failed", null);
        }

    }

    public boolean isVerifyOtp(String emailId, Integer otp) {
        User user = userRepo.findByEmailId(emailId);
        return user.getOtp() == otp;
    }


    public ResponseDTO login(LoginDTO loginDTO) {
        User user = userRepo.getUserByEmailId(loginDTO.getEmailId());

        if (user == null) {
            return new ResponseDTO("Email ID does not exist.", null);
        }

        if (!user.isVerify()) {
            return new ResponseDTO("Account not verified.", null);
        }

        if (user.getPassword().equals(loginDTO.getPassword())) {
            String token = jwtToken.createToken(user.getId());
            return new ResponseDTO("Login successful.", token);
        } else {
            return new ResponseDTO("Invalid credentials, please check password or email.", null);
        }
    }


    public User updateUser(long id, UserDTO userDTO) {
        User userData = getUserById(id);
        if (userData == null) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userData.updateUser(userDTO);
        return userRepo.save(userData);
    }

    public void deleteUser(long id) {
        User userData = getUserById(id);
        if (userData == null) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userRepo.deleteById(id);
    }

    public void deleteAllUser() {
        userRepo.deleteAll();
    }

    public User getUserById(long id) {
        return userRepo.findById(id).orElseThrow(() -> new UserCustomException("user with id: " + id + "not present"));
    }

    public List<User> getAllUser() {
        return userRepo.findAll();
    }


}

