package kr.or.kosa.nux2.domain.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.*;

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

        public static UserDto of(String registrationId, String memberId, String memberName,String socialToken){
            if(registrationId.equals("google")){
                return ofGoogle(memberId,memberName,socialToken);
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

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignUpRequest {
        private String memberId;
        private String memberPassword; //암호화
        private String memberName;
        private String role;
        private List<MemberConsCategoryDto.MemberConsCategoryIdDto> memberConsCategoryDtoList;
        private String targetExpenditure;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class MemberIdRequest {
        private String memberId;
    }

    @Getter
    public static class AuthenticationDto {
        private String authenticationNumber;
    }

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
    public static class ProfileResponse{
        private String memberId;
        private String memberName;
        private List<MemberConsCategoryDto.MemberConsCategoryResponse> memberConsCategoryDtoList;
        private String targetExpenditure;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateMemberInfoRequest {
        private List<MemberConsCategoryDto.MemberConsCategoryIdDto> memberConsCategoryIdDtoList;
        private String targetExpenditure;
    }
    @Getter
    public static class UpdatePasswordRequest{
        private String changePassword;
        private String checkPassword;
    }

}
