package com.travelmate.domain.auth.service;

import com.travelmate.domain.auth.controller.dto.request.LoginRequest;
import com.travelmate.domain.auth.controller.dto.request.PasswordUpdateRequest;
import com.travelmate.domain.auth.controller.dto.request.SignUpRequest;
import com.travelmate.domain.auth.controller.dto.request.WithdrawalRequest;
import com.travelmate.domain.auth.dto.NewUserValidationDto;
import com.travelmate.domain.user.domain.User;
import com.travelmate.domain.user.service.UserLoginService;
import com.travelmate.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthFacadeService {

    private final UserService userService;
    private final UserLoginService userLoginService;

    @Transactional
    public User signUp(final SignUpRequest request) {
        userService.validateNewMemberDuplicated(NewUserValidationDto.of(request));

        User user = userLoginService.signUpEmail(request);
        return user;
    }

    public User login(LoginRequest request) {
        return userLoginService.login(request);
    }

    public void updatePassword(PasswordUpdateRequest request) {
        userLoginService.updatePassword(request);
    }

    @Transactional
    public void withdrawal(WithdrawalRequest request) {
        userLoginService.withdrawal(request);
    }
}
