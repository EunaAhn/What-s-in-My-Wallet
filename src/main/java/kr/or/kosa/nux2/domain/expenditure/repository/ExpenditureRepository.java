package kr.or.kosa.nux2.domain.expenditure.repository;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import kr.or.kosa.nux2.domain.expenditure.mapper.ExpenditureMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class ExpenditureRepository {
    private final ExpenditureMapper expenditureMapper;
    List<Long> findTotalExpenditureByStartAndEndDate(Map<String, Object> columns){
        return expenditureMapper.findTotalExpenditureByStartAndEndDate(columns);
    };

    List<ExenditureDto.Response> findAllExpenditure (Map<String, Object> columns){
        return expenditureMapper.findAllExpenditure(columns);
    };

    List<ExenditureDto.DetailsReponse> findAllExpenditureDetails(String date){
        return expenditureMapper.findAllExpenditureDetails(date);
    };

    int insertExpenditures(List<ExenditureDto.InsertRequest> expenditureList){
        return expenditureMapper.insertExpenditures(expenditureList);
    };

    void updateExpenditureMemo(String expenditureMemo){
        expenditureMapper.updateExpenditureMemo(expenditureMemo);
    };

    ExenditureDto.RatioByCategoryResponse findExpenditureRatioForCategoryByMonth(int month){
        return expenditureMapper.findExpenditureRatioForCategoryByMonth(month);
    };

    ExenditureDto.CountByCategoryResponse findExpenditureCountForCategoryByMonth(int month){
        return expenditureMapper.findExpenditureCountForCategoryByMonth(month);
    };

    ExenditureDto.ByMonthAndTimeResponse findTotalExpenditureForMonthAndTimeByYearAndMonth(String yearAndMonth){
        return expenditureMapper.findTotalExpenditureForMonthAndTimeByYearAndMonth(yearAndMonth);
    };

    ExenditureDto.AverageByMonthResponse findAverageExpenditureForMonthByYear(int year){
        return expenditureMapper.findAverageExpenditureForMonthByYear(year);
    };
}
