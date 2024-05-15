package kr.or.kosa.nux2.domain.expenditure.service;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;

import java.util.List;
import java.util.Map;

public interface ExpenditureService {
    Map<String, Object> showMemberMonthlyExpenditures(ExenditureDto.ByYearAndMonthRequest request);

    ExenditureDto.DetailsReponse showMemberDailyExpenditureDetails(ExenditureDto.ExpenditureDetailRequest request);

    List<ExenditureDto.TotalCount> showTotalExpenditureByMonth(ExenditureDto.ByYearAndMonthRequest request);

    List<ExenditureDto.RatioByCategoryResponse> showExpenditureRatioForCategoryByMonth(ExenditureDto.ByYearAndMonthRequest request);

    List<Map<String, Object>> showExpenditureCountForCategoryByMonth(ExenditureDto.ByYearAndMonthRequest request);

    Map<String, Object> showTotalExpenditureForMonthAndTimeByYearAndMonth(ExenditureDto.ByYearAndMonthRequest request);

    Map<String, Object> findAverageExpenditureForMonthByYear(ExenditureDto.ByYearRequest request);

    boolean insertExpenditure();

    ExenditureDto.TotalCount showExpenditureTotalCount(ExenditureDto.TotalCountByTimePeriodRequest request);

    ExenditureDto.TendencyAnalysis findExpendiutreTendencyAnalysis(ExenditureDto.ByYearAndMonthRequest request);

    boolean updateDailyExpenditureMemo(ExenditureDto.UpdateMemoRequest request);

    boolean checkExistMemo(Map<String, Object> map);
}
