package kr.or.kosa.nux2.domain.virtualmydata.dto;

import lombok.*;

public class MyDataCardDto {
    @AllArgsConstructor
    @Getter
    public static class Response {
        private String cardNumber;
        private String cardCompanyCode;
        private String memberName;
        private String cardName;
    }

    @AllArgsConstructor
    @Getter
    public static class InsertRequest {
        private String cardNumber;
        private String cvs;
        private String memberName;
        private String ExpireMonth;
        private String ExpireYear;
        private String memberContactNumber;
        private String cardCompanyCode;
    }

    @Getter
    @Setter
    public static class AuthenticationRequest {
        private String memberName;
        private String memberContactNumber;
        // email로 변경하기
    }
}
