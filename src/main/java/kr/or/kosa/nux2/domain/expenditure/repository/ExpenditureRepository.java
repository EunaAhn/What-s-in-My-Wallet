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

    public boolean insertExpenditures(Map<String, Object> map) {
        int result = expenditureMapper.insertExpenditures(map);

        if(result == 1) return true;
        return false;
    }

    public boolean updateExpenditureMemo(Map<String, Object> map) {
        int result = expenditureMapper.updateExpenditureMemo(map);

        if(result == 1) return true;
        return false;
    }
    // 월별카테고리별지출비율
    public List<ExenditureDto.RatioByCategoryResponse> findExpenditureRatioForCategoryByMonth(Map<String, Object> map) {
        return expenditureMapper.findExpenditureRatioForCategoryByMonth(map);
    }
    // 월별카테고리별지출횟수
    public List<Map<String, Object>> findExpenditureCountForCategoryByMonth(Map<String, Object> map) {
        return expenditureMapper.findExpenditureCountForCategoryByMonth(map);
    }
    // 월별시간대별지출액
    public Map<String, Object> findTotalExpenditureForMonthAndTimeByYearAndMonth(Map<String, Object> map) {
        return expenditureMapper.findTotalExpenditureForMonthAndTimeByYearAndMonth(map);
    }

    // 월별총지출액
    public Map<String, Object> findAverageExpenditureForMonthByYear(Map<String, Object> map) {
        return expenditureMapper.findAverageExpenditureForMonthByYear(map);
    }

    public ExenditureDto.TotalCount findExpenditureTotalCount(Map<String, Object> map) {
        return expenditureMapper.findExpenditureTotalCount(map);
    }

    public ExenditureDto.TendencyAnalysis findExpendiutreTendencyAnalysis(Map<String, Object> map) {
        return expenditureMapper.findExpendiutreTendencyAnalysis(map);
    }

    public boolean deleteExpenditure(String cardNumber) {
        int result = expenditureMapper.deleteExpenditure(cardNumber);

        if(result == 1) return true;
        return false;
    }

    public boolean isExistMemo(Map<String, Object> map) {
        int result = expenditureMapper.isExistMemo(map);

        if(result == 1) return true;
        return false;
    }

    public boolean insertExpenditureMemo(Map<String, Object> map) {
        int result = expenditureMapper.insertExpenditureMemo(map);

        if(result == 1) return true;
        return  false;
    }

    public List<ExenditureDto.CategoryList> findAllCategoryList(Map<String, Object> map) {
        return expenditureMapper.findAllCategoryList(map);
    }
}
