package cis.javaholics.util;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}