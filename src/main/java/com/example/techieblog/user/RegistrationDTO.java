package com.example.techieblog.user;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationDTO {

    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Darf nur Buchstaben, Zahlen und den Unterstrich enthalten.")
    private String username;

    private String userRole;
    @Size(min = 5)
    private String password1;
    private String password2;

    public RegistrationDTO(String username, String password1, String password2) {
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
        this.userRole = "user";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }

    public String getUserRole() {
        return userRole;
    }
}
