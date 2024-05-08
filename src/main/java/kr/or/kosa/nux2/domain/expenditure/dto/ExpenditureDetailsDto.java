package kr.or.kosa.nux2.domain.expenditure.dto;

import java.util.List;

public class ExpenditureDetailsDto {
    Long expenditureTotalAmount;
    String memo;
    List<ExpenditureSummaryDto> expenditureSummaryDtoList;
}
