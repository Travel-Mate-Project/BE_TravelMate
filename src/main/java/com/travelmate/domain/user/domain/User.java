package com.travelmate.domain.user.domain;

import com.travelmate.domain.auth.controller.dto.request.SignUpRequest;
import com.travelmate.domain.course.domain.Course;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userEmail;

    private String userName;
    private String password;
    private String delYn;
    private String provider;
    private String providerId;
    private LocalDateTime regiDtm;
    private String regiId;
    private LocalDateTime updtDtm;
    private String updtId;
    //TODO: 광고 수신 동의 여부도 추가해야함.

    @OneToMany(mappedBy = "user")
    private List<Course> course;

    public User(String userEmail, String password, String userName, String delYn, String regiId, LocalDateTime updtDtm, String updateId) {
        this.userEmail = userEmail;
        this.password = password;
        this.userName = userName;
        this.delYn = delYn;
        this.regiDtm = LocalDateTime.now();
        this.regiId = regiId;
        this.updtDtm = updtDtm;
        this.updtId = updateId;
    }

    public static User of(SignUpRequest request, String password) {
        return new User(request.userEmail(), password, request.userName(), "N","System", LocalDateTime.now(), "System");
    }

}
