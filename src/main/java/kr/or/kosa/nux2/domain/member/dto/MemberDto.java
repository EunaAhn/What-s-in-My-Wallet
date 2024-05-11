package kr.or.kosa.nux2.domain.member.dto;


import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
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
        private String memberEmail;
        private String memberName;
        private String role;
        private String provider;
        private String providerId;
        private String socialToken;
        private String targetExpenditure;

        //todo targetexpenditure 추가해서 코드변경해야함
        public static UserDto of(String registrationId, String memberId, String memberName, String email, String password, String providerId, String socialToken){
            if(registrationId.equals("google")){
                return ofGoogle(memberId,memberName, email, password, providerId,socialToken);
            }
            return null;
        }

        private static UserDto ofGoogle(String memberId, String memberName, String email, String password, String providerId, String socialToken) {

            return UserDto.builder()
                    .memberId(memberId)
                    .memberName(memberName)
                    .memberEmail(email)
                    .memberPassword(password)
                    .provider("google")
                    .providerId(providerId)
                    .role(Role.USER.getRoles())
                    .socialToken(socialToken)
                    .build();
        }


    }
    @Builder
    @Getter
    public static class SignInRequest{
        private String memberId;
        private String memberPassword; //암호화
        private String memberEmail;
        private String memberName;
        private String role;
        private List<MemberConsCategoryDto> memberConsCategoryDtoList;
        private String targetExpenditure;

    }

}
