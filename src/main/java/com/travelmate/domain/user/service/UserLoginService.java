package com.travelmate.domain.user.service;

import com.travelmate.commons.constants.EnumYN;
import com.travelmate.commons.security.encryption.AES256;
import com.travelmate.domain.auth.controller.dto.request.LoginRequest;
import com.travelmate.domain.auth.controller.dto.request.PasswordUpdateRequest;
import com.travelmate.domain.auth.controller.dto.request.SignUpRequest;
import com.travelmate.domain.auth.controller.dto.request.WithdrawalRequest;
import com.travelmate.domain.auth.exception.InvalidIdPwException;
import com.travelmate.domain.auth.exception.UserNotFoundException;
import com.travelmate.domain.user.domain.User;
import com.travelmate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;
    private final AES256 aes256;

    @Transactional
    public User signUpEmail(SignUpRequest request) {
        String encryptedPassword = aes256.encrypt(request.password());
        return userRepository.save(User.of(request, encryptedPassword));
    }

    public User login(LoginRequest request) {
        final Optional<User> optionalUser = userRepository.findUserByUserEmailAndPassword(request.userEmail(), aes256.encrypt(request.password()));
        if (!optionalUser.isPresent()) {
            throw new InvalidIdPwException();
        }
        if (EnumYN.Y.getCode().equals(optionalUser.get().getDelYn())) {
            throw new UserNotFoundException();
        }
        return optionalUser.get();
    }

    public void updatePassword(PasswordUpdateRequest request) {
    }

    @Transactional
    public void withdrawal(WithdrawalRequest request) {
        userRepository.updateDelYnByUserId(request.userId());
    }
}
