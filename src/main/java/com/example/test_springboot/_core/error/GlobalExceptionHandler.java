package com.example.test_springboot._core.error;

import com.example.test_springboot._core.error.ex.Exception400;
import com.example.test_springboot._core.error.ex.Exception401;
import com.example.test_springboot._core.error.ex.Exception403;
import com.example.test_springboot._core.error.ex.Exception404;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 -> 잘못된 요청
    @ExceptionHandler(Exception400.class)
    public String ex400(Exception400 e) {
        String html = """
                <script>
                    alert('${msg}');
                </script>이
                """.replace("${msg}", e.getMessage());
        return html;
    }

    // 401 -> 인증 안됐을때
    @ExceptionHandler(Exception401.class)
    public String ex401(Exception401 e) {
        String html = """
                <script>
                    alert('${msg}');
                    location.href = '/login-form';
                </script>
                """.replace("${msg}", e.getMessage());
        return html;
    }

    // 403 -> 권한 없음
    @ExceptionHandler(Exception403.class)
    public String ex403(Exception403 e) {
        String html = """
                <script>
                    alert('${msg}');
                </script>
                """.replace("${msg}", e.getMessage());
        return html;
    }

    // 404 -> 자원 없음
    @ExceptionHandler(Exception404.class)
    public String ex404(Exception404 e) {
        String html = """
                <script>
                    alert('${msg}');
                </script>
                """.replace("${msg}", e.getMessage());
        return html;
    }

    @ExceptionHandler(Exception.class)
    public String exUnknown(Exception e) {
        String html = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}", "관리자에게 문의해주세요");
        System.out.println("관리자님 보세요 : " + e.getMessage());
        return html;
    }
}