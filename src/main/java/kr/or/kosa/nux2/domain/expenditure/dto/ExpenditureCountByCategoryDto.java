package kr.or.kosa.nux2.domain.expenditure.dto;

import java.util.List;

public class ExpenditureCountByCategoryDto {
    String categoryName;
    String startDate;
    String endDate;
    int maxCount;
    List<Integer> expenditureCountList;
}
