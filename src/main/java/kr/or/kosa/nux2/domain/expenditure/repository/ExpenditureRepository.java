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

    public List<ExenditureDto.TotalCount> findTotalExpenditureByStartAndEndDate(Map<String, Object> map){
        return expenditureMapper.findTotalExpenditureByStartAndEndDate(map);
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

    public List<ExenditureDto.RatioByCategoryResponse> findExpenditureRatioForCategoryByMonth(Map<String, Object> map){
        return expenditureMapper.findExpenditureRatioForCategoryByMonth(map);
    };

    public List<Map<String, Object>>  findExpenditureCountForCategoryByMonth(Map<String, Object> map){
        return expenditureMapper.findExpenditureCountForCategoryByMonth(map);
    };

    public Map<String, Object> findTotalExpenditureForMonthAndTimeByYearAndMonth(Map<String, Object> map){
        return expenditureMapper.findTotalExpenditureForMonthAndTimeByYearAndMonth(map);
    };

    public Map<String, Object> findAverageExpenditureForMonthByYear(Map<String, Object> map){
        return expenditureMapper.findAverageExpenditureForMonthByYear(map);
    };
}
