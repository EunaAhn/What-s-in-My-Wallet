package kr.or.kosa.nux2.domain.virtualmydata.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

public class MyDataCardDto {
    @Getter
    public static class Response {
        private String cardNumber;
        private String cardCompanyCode;
        private String memberName;
        private String cardName;
    }


    @Getter
    public static class InsertRequest {
        @Pattern(regexp = "\\d{16}", message = "카드형식은 16자리 숫자형문자여야합니다.")
        private String cardNumber;
        @Pattern(regexp = "\\d{3}", message = "cvs는 3자리 숫자형문자여야합니다.")
        private String cvs;
        private String memberName;
        private String ExpireMonth;
        private String ExpireYear;
        @Pattern(
                regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",
                message = "이메일 형식이 올바르지 않습니다."
        )
        private String memberEmail;
        private String cardCompanyCode;
    }

    @Getter
    @NoArgsConstructor
    public static class AuthenticationRequest {
        private String memberName;
        @Pattern(
                regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",
                message = "이메일 형식이 올바르지 않습니다."
        )
        private String memberEmail;
    }
}
