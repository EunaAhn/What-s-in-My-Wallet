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

    public List<ExenditureDto.TotalCount> findTotalExpenditureByStartAndEndDate(String month){
        return expenditureMapper.findTotalExpenditureByStartAndEndDate(month);
    };

    public List<ExenditureDto.Response> findAllExpenditure (Map<String, Object> columns){
        return expenditureMapper.findAllExpenditure(columns);
    };

    public ExenditureDto.DetailsReponse findAllExpenditureDetails(Map<String, Object> columns){
        return expenditureMapper.findAllExpenditureDetails(columns);
    };

    int insertExpenditures(List<ExenditureDto.InsertRequest> expenditureList){
        return expenditureMapper.insertExpenditures(expenditureList);
    };

    public void updateExpenditureMemo(Map<String, Object> map){
        expenditureMapper.updateExpenditureMemo(map);
    };

    public ExenditureDto.RatioByCategoryResponse findExpenditureRatioForCategoryByMonth(int month){
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
