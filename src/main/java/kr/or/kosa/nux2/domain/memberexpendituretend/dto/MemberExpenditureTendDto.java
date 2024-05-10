package kr.or.kosa.nux2.domain.memberexpendituretend.dto;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;

public class MemberExpenditureTendDto {

    public static class ServiceResponse {
        private String expenditureTendencyId;
        private String memberExpenditureTendency;
    }

    public static class ControllerResponse {
        private String memberExpenditureTendencyConcat;
    }
}
