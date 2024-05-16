package kr.or.kosa.nux2.domain.expenditure.mapper;

import kr.or.kosa.nux2.domain.expenditure.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExpenditureMapper {

    List<ExenditureDto.TotalCount> findTotalExpenditureByStartAndEndDate(Map<String, Object> map);

    List<ExenditureDto.Response> findAllExpenditure(Map<String, Object> columns);

    ExenditureDto.DetailsReponse findAllExpenditureDetails(Map<String, Object> columns);

    List<List<ExenditureDto.CategoryName>> findCategoryListOfDailyExpenditure(Map<String, Object> columns);

    int insertExpenditures(Map<String, Object> map);

    int updateExpenditureMemo(Map<String, Object> columns);
    int deleteExpenditure(@Param("cardNumber") String cardNumber);

    List<ExenditureDto.RatioByCategoryResponse> findExpenditureRatioForCategoryByMonth(Map<String, Object> map);

    List<Map<String, Object>> findExpenditureCountForCategoryByMonth(Map<String, Object> map);

    Map<String, Object> findTotalExpenditureForMonthAndTimeByYearAndMonth(Map<String, Object> map);

    Map<String, Object> findAverageExpenditureForMonthByYear(Map<String, Object> map);

   Long findTotalExpenditureByMonth(Map<String, Object> map);
    // 소비성향을 넘겨주면 해당 시간대의 총 지출 횟수를 반환해야한다.
    // 리팩터링 부채 : enum의 위치 애매 + 단순 columns으로 넘기는게 타당한지 의문
    // 일단 MemberExpenditureTendDto에서 값을 꺼내서 전달
    // 저녁형 : 18~24
    // 아침형 : 06~18
    // 시작시간, 종료시간, 년월을 넘겨줘야한다.
    // 소비성향을 넘겨서 매퍼가 로직을 처리하는 것 보다는 서비스레이어가 로직을 처리하는 편이 더 바람직하다고 판단하여 조건만 넘ㅡ
    ExenditureDto.TotalCount findExpenditureTotalCount(Map<String, Object> map);

    ExenditureDto.TendencyAnalysis findExpendiutreTendencyAnalysis(Map<String, Object> map);

    int isExistMemo(Map<String, Object> map);

    int insertExpenditureMemo(Map<String, Object> map);

    List<ExenditureDto.CategoryList> findAllCategoryList(Map<String, Object> map);

    int insertExpenditureDetails(Map<String, Object> map);
}


