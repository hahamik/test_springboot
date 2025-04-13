package com.example.test_springboot.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
        private String email;
    }

    @Data
    public static class UpdateDTO {
        private String password;
        private String email;
    }
}
