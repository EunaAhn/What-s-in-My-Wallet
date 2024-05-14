package kr.or.kosa.nux2.domain.registrationcard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class RegistrationCardDto {
    @Getter
    public static class Response {
        private String cardNumber;
        private String cardCompanyName;
        private String cardNickName;
    }

    // 마이데이터를 통해서 가공해야하므로 Setter사용

    @Setter
    @AllArgsConstructor
    public static class InsertServiceRequest {
        Long cardId;
        String memberId;
        Long cardCompanyId;
        String cardNumber;
        String cardNickName;
    }

    @Getter
    @Setter
    public static class InsertControllerRequest {
        String cardNumber;
    }
}
