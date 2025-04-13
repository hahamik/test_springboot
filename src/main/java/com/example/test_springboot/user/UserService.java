package com.example.test_springboot.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        userRepository.save(joinDTO.getUsername(), joinDTO.getPassword(), joinDTO.getEmail());
    }

    public User 로그인(UserRequest.LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        return user;
    }

    @Transactional
    public User 회원정보수정(UserRequest.UpdateDTO updateDTO, int userId) {
        User user = userRepository.findById(userId);
        if (user == null) throw new RuntimeException("회원을 찾을 수 없습니다.");
        User updateUser = userRepository.updateById(updateDTO.getPassword(), updateDTO.getEmail(), userId);
        return updateUser;
    }
}
