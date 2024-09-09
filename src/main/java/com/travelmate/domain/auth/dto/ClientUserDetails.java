package com.travelmate.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class ClientUserDetails implements UserDetails {
    private final String userId;
    private final String username;
    private final String password;
    private final Collection<GrantedAuthority> authorities;

    @Builder // builder is test only
    public ClientUserDetails(
            String userId,
            String username,
            String password,
            Collection<GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

//    public static ClientUserDetails of(
//            final MemberWithLoginInfoProjection member,
//            final String userId,
//            final String username,
//            final List<GrantedAuthority> authorities) {
//        return new ClientUserDetails(
//                member.getMemberId(),
//                member.getMemberSocialLoginId(),
//                userId,
//                username,
//                member.getPassword(),
//                authorities);
//    }

//    public static ClientUserDetails of(
//            final MemberWithLoginInfoProjection member,
//            final Collection<GrantedAuthority> grantedAuthorities) {
//        return new ClientUserDetails(
//                member.getMemberId(),
//                member.getMemberSocialLoginId(),
//                member.getUserId(),
//                member.getMemberName(),
//                member.getPassword(),
//                grantedAuthorities);
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
