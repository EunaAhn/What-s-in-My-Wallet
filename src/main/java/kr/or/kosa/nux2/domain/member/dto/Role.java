package kr.or.kosa.nux2.domain.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN,ROLE_USER");

    private final String roles;

    public static String getIncludingRoles(String role) {
        return Role.valueOf(role).getRoles();
    }

}
