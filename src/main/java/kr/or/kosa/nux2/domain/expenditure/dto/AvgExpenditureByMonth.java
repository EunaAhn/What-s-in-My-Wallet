package kr.or.kosa.nux2.domain.expenditure.dto;

import java.util.List;

public class AvgExpenditureByMonth {
    int year;
    int startMonth;
    int endMonth;
    Long maxAvgExpenditure;
    List<Integer> avgExpenditureList;
}
