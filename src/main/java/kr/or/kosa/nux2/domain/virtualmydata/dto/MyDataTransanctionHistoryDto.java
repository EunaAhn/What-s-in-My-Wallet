package kr.or.kosa.nux2.domain.virtualmydata.dto;

public class MyDataTransanctionHistoryDto {
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

    public static class InsertRequest {
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
}
