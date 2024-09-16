package com.travelmate.domain.auth.service;

import com.travelmate.domain.user.domain.User;
import com.travelmate.domain.user.service.UserLoginService;
import com.travelmate.domain.user.service.UserService;
import com.travelmate.domain.auth.controller.dto.SignUpInfoDto;
import com.travelmate.domain.auth.controller.dto.request.SignUpRequest;
import com.travelmate.domain.auth.dto.NewUserValidationDto;
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
    public SignUpInfoDto signUp(final SignUpRequest request) {
        userService.validateNewMemberDuplicated(NewUserValidationDto.of(request));

        User user = userLoginService.signUpEmail(request);
        return SignUpInfoDto.of(user);
    }
}
