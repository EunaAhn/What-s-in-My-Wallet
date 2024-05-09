package kr.or.kosa.nux2.domain.registrationcard.dto;

import lombok.Getter;
import lombok.Setter;


public class RegistrationCardDto {
    public static class Response {
        private String cardCompanyName;
        private String cardNumber;
        private String cardNickName;
    }


    // 마이데이터를 통해서 가공해야하므로 Setter사용
    @Setter
    public static class InsertRequest {
        Long cardId;
        String memberId;
        Long cardCompanyId;
        String cardNumber;
        String cardNickName;
    }
}
