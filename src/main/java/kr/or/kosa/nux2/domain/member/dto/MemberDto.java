package kr.or.kosa.nux2.domain.member.dto;


import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import lombok.Getter;
import lombok.Setter;
import lombok.*;

import java.util.List;

@Setter
@Getter
public class MemberDto {
    private String memberId;
    private String memberName;
    private String memberContactNumber;
    private String memberPassword;
    private Long targetExpenditure;
    List<ExenditureDto.CategoryName> expenditureCategoryList;
public class MemberDto {
    @Setter
    @Getter
    public static class AuthenticationResponse{
        private String memberId;
        private String memberPassword;
    }

    public static class SignInOAuthRequest{
        private String memberId;
        private String memberPassword; //암호화
        private String memberEmail;
        private String memberRole;
        private String provider;
        private String providerId;
    }
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
}
