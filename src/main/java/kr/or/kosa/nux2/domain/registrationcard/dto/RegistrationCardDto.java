package kr.or.kosa.nux2.domain.registrationcard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class RegistrationCardDto {
    public static class Response {
        private String cardCompanyName;
        private String cardNumber;
        private String cardNickName;
    }


    @Setter
    public static class InsertRequest {
        Long cardId;
        String memberId;
        Long cardCompanyId;
        String cardNumber;
        String cardNickName;
    }
}
