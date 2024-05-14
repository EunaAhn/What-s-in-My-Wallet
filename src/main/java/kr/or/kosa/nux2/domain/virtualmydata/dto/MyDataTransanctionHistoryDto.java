package kr.or.kosa.nux2.domain.virtualmydata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

public class MyDataTransanctionHistoryDto {
    @Getter
    public static class Response {
        private Long transactionId;
        private String transactionCode;
        private String transactionDateTime;
        private Long transactionAmount;
        private String cardCompanyCode;
        private String cardNumber;
        private String storeName;
        private String storeAddress;
        private String industryCode;
    }

    @Getter
    @AllArgsConstructor
    public static class InsertRequest {
        private Long transactionId;
        private String transactionCode;
        private Timestamp transactionDateTime;
        private Long transactionAmount;
        private String cardCompanyCode;
        private String cardNumber;
        private String storeName;
        private String storeAddress;
        private String industryCode;
    }
}
