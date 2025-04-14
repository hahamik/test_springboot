package com.example.test_springboot.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO {
        private String email;
        private String password;
        private String username;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .password(password)
                    .username(username)
                    .build();
        }
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @Data
    public static class UpdateDTO {
        private String email;
        private String password;
    }
}
