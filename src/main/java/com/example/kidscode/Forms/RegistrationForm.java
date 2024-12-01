package com.example.kidscode.Forms;


import com.example.kidscode.Models.Users;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String surname;
    private String name;
    private String patronymic;
    private String role;

    public Users toUser(PasswordEncoder passwordEncoder) {
        return new Users(username, passwordEncoder.encode(password), surname, name, patronymic, role);
    }
}
