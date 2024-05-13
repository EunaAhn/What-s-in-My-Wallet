package kr.or.kosa.nux2.domain.expenditure.service;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;

import java.util.List;
import java.util.Map;

public interface ExpenditureService {
    Map<String, Object> showMemberMonthlyExpenditures(ExenditureDto.YearAndMonthRequest request);
    ExenditureDto.DetailsReponse showMemberDailyExpenditureDetails(ExenditureDto.ExpenditureDetailRequest request);
    List<ExenditureDto.TotalCount> showTotalExpenditureByMonth(ExenditureDto.TotalExpenditureCountRequest request);
    void modifyExpenditureMemo(Map<String, Object> map);
    List<ExenditureDto.RatioByCategoryResponse> showExpenditureRatioForCategoryByMonth(Map<String, Object> map);
    List<Map<String, Object>> showExpenditureCountForCategoryByMonth(String yearAndMonth);
    Map<String, Object> showTotalExpenditureForMonthAndTimeByYearAndMonth(String yearAndMonth);
    Map<String, Object> findAverageExpenditureForMonthByYear(int year);
    int insertExpenditure();
    ExenditureDto.TotalCount showExpenditureTotalCount(ExenditureDto.TotalExpenditureCountByTimePeriodRequest request);
    ExenditureDto.TendencyAnalysis findExpendiutreTendencyAnalysis(ExenditureDto.YearAndMonthRequest request);
    boolean updateDailyExpenditureMemo(ExenditureDto.UpdateMemoRequest request);
    int checkExistMemo(Map<String, Object> map);
}
