package kr.or.kosa.nux2.domain.expenditure.dto;

import java.util.List;

public class DayExpenditureDto {
    // 달력에 표현되는 지출 DTO
    List<ExpenditureCategory> expenditureCategoryList;
    Long expenditureAmount;
}
