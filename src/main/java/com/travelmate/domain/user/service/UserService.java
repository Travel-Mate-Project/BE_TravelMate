package com.travelmate.domain.user.service;

import com.travelmate.domain.user.domain.User;
import com.travelmate.domain.user.repository.UserRepository;
import com.travelmate.domain.auth.dto.NewUserValidationDto;
import com.travelmate.domain.auth.exception.UserEmailDuplicatedBadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void validateNewMemberDuplicated(final NewUserValidationDto dto) {
        final Optional<User> optionalUser =
                userRepository.findUserByUserEmail(
                        dto.userEmail());
        validateDuplicated(dto, optionalUser);
    }

    private static void validateDuplicated(
            final NewUserValidationDto dto,
            final Optional<User> optionalUser) {
        if (optionalUser.isEmpty()) {
            return;
        }
        User user = optionalUser.get();
        if (user.getUserEmail().equals(dto.userEmail())) {
            throw new UserEmailDuplicatedBadRequest();
        }
    }
}
