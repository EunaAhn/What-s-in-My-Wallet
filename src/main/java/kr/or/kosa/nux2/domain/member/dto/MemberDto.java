package kr.or.kosa.nux2.domain.member.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

public class MemberDto {

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDto {
        private String memberId;
        private String memberPassword; //암호화
        private String memberName;
        private String role;
        private String provider;
        private String socialToken;
        private String targetExpenditure;
        private int status;

        public static UserDto of(String registrationId, String memberId, String memberName, String socialToken) {
            if (registrationId.equals("google")) {
                return ofGoogle(memberId, memberName, socialToken);
            }
            return null;
        }

        private static UserDto ofGoogle(String memberId, String memberName, String socialToken) {
            return UserDto.builder()
                    .memberId(memberId)
                    .memberName(memberName)
                    .provider("google")
                    .role(Role.USER.getRoles())
                    .socialToken(socialToken)
                    .status(0)
                    .build();
        }
    }

    /*
      ========================================================================================
                                            RESPONSE DTO
      ========================================================================================
     */
    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class CheckMemberIdResponse {
        private boolean isExistMember;
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class CheckAuthenticationNumberResponse {
        private boolean isSameNumber;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class ProfileResponse {
        private String memberId;
        private String memberName;
        private List<MemberConsCategoryDto.MemberConsCategoryResponse> memberConsCategoryDtoList;
        private String targetExpenditure;
    }

    /*
      ========================================================================================
                                              REQUEST DTO
      ========================================================================================
     */
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateMemberInfoRequest {

        private List<MemberConsCategoryDto.MemberConsCategoryIdDto> memberConsCategoryIdDtoList;

        private String targetExpenditure;
    }

    @NoArgsConstructor
    @Getter
    public static class UpdatePasswordRequest {
        @Pattern(regexp = "/^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$/", message = "비밀번호는 문자, 숫자, 특수문자 1개씩 필수이고 8~20자리입니다.")
        private String changePassword;
        @Pattern(regexp = "/^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$/", message = "비밀번호는 문자, 숫자, 특수문자 1개씩 필수이고 8~20자리입니다.")
        private String checkPassword;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignUpRequest {
        @Pattern(regexp = "/^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$/", message = "아이디는 이메일형식입니다.")
        private String memberId;

        @Pattern(regexp = "/^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$/", message = "비밀번호는 문자, 숫자, 특수문자 1개씩 필수이고 8~20자리입니다.")
        private String memberPassword; //암호화
        @NotNull
        private String memberName;
        private String role;
        @NotNull
        private List<MemberConsCategoryDto.MemberConsCategoryIdDto> memberConsCategoryDtoList;
        @NotNull
        private String targetExpenditure;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class MemberIdRequest {
        @Pattern(regexp = "/^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$/", message = "아이디는 이메일형식입니다.")
        private String memberId;
    }

    @NoArgsConstructor
    @Getter
    public static class AuthenticationRequest {
        @Pattern(regexp = "\\d{6}", message = "인증번호 형식은 6자리 숫자입니다.")
        private String authenticationNumber;
    }

}
