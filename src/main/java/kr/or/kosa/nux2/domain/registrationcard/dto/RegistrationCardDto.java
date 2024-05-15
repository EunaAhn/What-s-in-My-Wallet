package kr.or.kosa.nux2.domain.registrationcard.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class RegistrationCardDto {
    @Getter
    public static class Response {
        private String cardNumber;
        private String cardCompanyName;
        private String cardNickName;
    }



    @Getter
    @NoArgsConstructor
    public static class InsertControllerRequest {
        @Pattern(regexp = "\\d{16}", message = "카드형식은 16자리 숫자형문자여야합니다.")
        String cardNumber;
    }
}
