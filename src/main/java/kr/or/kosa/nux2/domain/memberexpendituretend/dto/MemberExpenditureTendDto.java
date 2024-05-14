package kr.or.kosa.nux2.domain.memberexpendituretend.dto;

import lombok.Getter;
import lombok.Setter;

public class MemberExpenditureTendDto {
    @Getter
    public static class Response {
        private String expenditureTendencyId;
        private String memberExpenditureTendency;
    }

    @Setter
    public static class ControllerResponse {
        private String memberExpenditureTendencyConcat;
    }

    @Getter
    @Setter
    public static class ExpenditureTendRequest {
        private String yearAndMonth;
    }
}
