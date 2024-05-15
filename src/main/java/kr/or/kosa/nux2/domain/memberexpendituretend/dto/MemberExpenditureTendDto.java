package kr.or.kosa.nux2.domain.memberexpendituretend.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

public class MemberExpenditureTendDto {
    @Getter
    public static class Response {
        private String expenditureTendencyId;
        private String memberExpenditureTendency;
    }

    @Getter
    @Setter
    public static class ExpenditureTendRequest {
        @Pattern(regexp = "\\d{4}-\\d{2}", message = "날짜는 YYYY-MM 형식이어야 합니다.")
        private String yearAndMonth;
    }
}
