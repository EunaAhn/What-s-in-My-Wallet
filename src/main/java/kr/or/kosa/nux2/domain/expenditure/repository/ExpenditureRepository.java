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
    private final ExpenditureMapper expenditureRepository;
    List<Long> findTotalExpenditureByStartAndEndDate(Map<String, Object> columns){
        return expenditureRepository.findTotalExpenditureByStartAndEndDate(columns);
    };

    List<ExenditureDto.Response> findAllExpenditure (Map<String, Object> columns){
        return expenditureRepository.findAllExpenditure(columns);
    };

    List<ExenditureDto.DetailsReponse> findAllExpenditureDetails(String date){
        return expenditureRepository.findAllExpenditureDetails(date);
    };

    int insertExpenditures(List<ExenditureDto.InsertRequest> expenditureList){
        return expenditureRepository.insertExpenditures(expenditureList);
    };

    void updateExpenditureMemo(String expenditureMemo){
        expenditureRepository.updateExpenditureMemo(expenditureMemo);
    };

    ExenditureDto.RatioByCategoryResponse findExpenditureRatioForCategoryByMonth(int month){
        return expenditureRepository.findExpenditureRatioForCategoryByMonth(month);
    };

    ExenditureDto.CountByCategoryResponse findExpenditureCountForCategoryByMonth(int month){
        return expenditureRepository.findExpenditureCountForCategoryByMonth(month);
    };

    ExenditureDto.ByMonthAndTimeResponse findTotalExpenditureForMonthAndTimeByYearAndMonth(String yearAndMonth){
        return expenditureRepository.findTotalExpenditureForMonthAndTimeByYearAndMonth(yearAndMonth);
    };

    ExenditureDto.AverageByMonthResponse findAverageExpenditureForMonthByYear(int year){
        return expenditureRepository.findAverageExpenditureForMonthByYear(year);
    };
}
