package kr.or.kosa.nux2.domain.member.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.*;

import java.util.List;


public class MemberDto {

    @AllArgsConstructor
    public static class UpdateSocialTokenRequest{
        private String memberId;
        private String socialToken;

    }

    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    @Builder
    @AllArgsConstructor
    public static class UserDto {
        private String memberId;
        private String memberPassword; //암호화
        private String memberName;
        private String role;
        private String provider;
        private String socialToken;
        private String targetExpenditure;
        private int status;

        //todo targetexpenditure 추가해서 코드변경해야함
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
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignInRequest{
        private String memberId;
        private String memberPassword; //암호화
        private String memberName;
        private String role;
        private List<MemberConsCategoryDto> memberConsCategoryDtoList;
        private String targetExpenditure;

    }
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MemberIdRequest {
        private String memberId;
    }
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class AuthenticationRequest{
        private String memberId;
        private String authenticationNumber;
    }

    @Getter
    public static class AuthenticationResponse{
        private String authenticationNumber;
    }
    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class checkMemberIdResponse{
        private boolean isExistMember;
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class checkAuthenticationNumberResponse{
        private boolean isSameNumber;
    }
}
