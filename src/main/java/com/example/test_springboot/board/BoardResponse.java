package com.example.test_springboot.board;

import lombok.AllArgsConstructor;
import lombok.Data;

public class BoardResponse {
    @AllArgsConstructor
    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private String author;
    }

    @AllArgsConstructor
    @Data
    public static class UpdateDTO {
        private int id;
        private String title;
        private String content;
    }
}