package kr.or.kosa.nux2.domain.memberexpendituretend.dto;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import lombok.Getter;
import lombok.Setter;

public class MemberExpenditureTendDto {
    @Getter
    public static class ServiceResponse {
        private String expenditureTendencyId;
        private String memberExpenditureTendency;
    }
    @Setter
    public static class ControllerResponse {
        private String memberExpenditureTendencyConcat;
    }
}
