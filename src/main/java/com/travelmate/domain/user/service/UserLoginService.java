package com.travelmate.domain.user.service;

import com.travelmate.domain.user.domain.User;
import com.travelmate.domain.user.repository.UserRepository;
import com.travelmate.domain.auth.controller.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;

    public User signUpEmail(SignUpRequest request) {
        return userRepository.save(User.of(request));
    }
}
