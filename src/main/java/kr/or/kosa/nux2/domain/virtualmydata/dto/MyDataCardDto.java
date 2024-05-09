package kr.or.kosa.nux2.domain.virtualmydata.dto;

import lombok.Setter;

public class MyDataCardDto {
    public static class Response {
        private String cardNumber;
        private String cardCompanyCode;
        private String memberName;
    }


    @Setter
    public static class InsertRequest {
        private String cardNumber;
        private int cvs;
        private String memberName;
        private String ExpireMonth;
        private String ExpireYear;
        private String memberContactNumber;
        private String cardCompanyCode;
    }

    @Setter
    public static class authenticationRequest {
        private String memberName;
        private String memberContactNumber;
    }
}
