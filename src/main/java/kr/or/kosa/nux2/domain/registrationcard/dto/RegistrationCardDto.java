package kr.or.kosa.nux2.domain.registrationcard.dto;

import lombok.Getter;

@Getter
public class RegistrationCardDto {
    public static class Response {
        private String cardCompanyName;
        private String cardNumber;
        private String cardNameAlias;
    }

    public static class InsertRequest {

    }

    public static class UpdateRequest {

    }
}
