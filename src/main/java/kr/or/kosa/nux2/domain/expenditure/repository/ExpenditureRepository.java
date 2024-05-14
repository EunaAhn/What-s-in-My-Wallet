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

    public List<ExenditureDto.TotalCount> findTotalExpenditureByStartAndEndDate(Map<String, Object> map) {
        return expenditureMapper.findTotalExpenditureByStartAndEndDate(map);
    }

    public List<ExenditureDto.Response> findAllExpenditure(Map<String, Object> columns) {
        return expenditureMapper.findAllExpenditure(columns);
    }

    public List<List<ExenditureDto.CategoryName>> findCategoryListOfDailyExpenditure(Map<String, Object> columns) {
        return expenditureMapper.findCategoryListOfDailyExpenditure(columns);
    }

    public ExenditureDto.DetailsReponse findAllExpenditureDetails(Map<String, Object> columns) {
        return expenditureMapper.findAllExpenditureDetails(columns);
    }

    public int insertExpenditures(Map<String, Object> map) {
        return expenditureMapper.insertExpenditures(map);
    }

    public void updateExpenditureMemo(Map<String, Object> map) {
        expenditureMapper.updateExpenditureMemo(map);
    }

    public List<ExenditureDto.RatioByCategoryResponse> findExpenditureRatioForCategoryByMonth(Map<String, Object> map) {
        return expenditureMapper.findExpenditureRatioForCategoryByMonth(map);
    }

    public List<Map<String, Object>> findExpenditureCountForCategoryByMonth(Map<String, Object> map) {
        return expenditureMapper.findExpenditureCountForCategoryByMonth(map);
    }

    public Map<String, Object> findTotalExpenditureForMonthAndTimeByYearAndMonth(Map<String, Object> map) {
        return expenditureMapper.findTotalExpenditureForMonthAndTimeByYearAndMonth(map);
    }

    public Map<String, Object> findAverageExpenditureForMonthByYear(Map<String, Object> map) {
        return expenditureMapper.findAverageExpenditureForMonthByYear(map);
    }

    public ExenditureDto.TotalCount findExpenditureTotalCount(Map<String, Object> map) {
        return expenditureMapper.findExpenditureTotalCount(map);
    }

    public ExenditureDto.TendencyAnalysis findExpendiutreTendencyAnalysis(Map<String, Object> map) {
        return expenditureMapper.findExpendiutreTendencyAnalysis(map);
    }

    public int isExistMemo(Map<String, Object> map) {
        return expenditureMapper.isExistMemo(map);
    }

    public void insertExpenditureMemo(Map<String, Object> map) {
        expenditureMapper.insertExpenditureMemo(map);
    }

    public List<ExenditureDto.CategoryList> findAllCategoryList(Map<String, Object> map) {
        return expenditureMapper.findAllCategoryList(map);
    }
}
