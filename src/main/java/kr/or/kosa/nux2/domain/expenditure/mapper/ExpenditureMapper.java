package kr.or.kosa.nux2.domain.expenditure.mapper;

import kr.or.kosa.nux2.domain.expenditure.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExpenditureMapper {
    // startDate, endDate 기준으로 total 산출
    List<Long> findTotalExpenditureByStartAndEndDate(Map<String, Object> columns);

    // 동적 쿼리 : 소비카테고리, 월, 카드 별 검색
    List<ExenditureDto.Response> findAllExpenditure (Map<String, Object> columns);

    // 상세조회
    // 파라미터 형식 string인지 date인지 고민하기
    List<ExenditureDto.DetailsReponse> findAllExpenditureDetails(String date);


    // 현재 사용자의 마지막 id값보다 높은 값을 가지는 마이데이터소비내역을 조회해서 insert한다.
    // 다건 삽입 방법 성능 고민
    int insertExpenditures(List<ExenditureDto.InsertRequest> expenditureList);

    // 소비 상세 내역 메모 수정하기
    void updateExpenditureMemo(String expenditureMemo);

    // 소비 카테고리 별 지출비율
    // 개월수가 컬럼으로 들어감
    ExenditureDto.RatioByCategoryResponse findExpenditureRatioForCategoryByMonth(int month);

    // 소비 카테고리 별 지출 횟수
    ExenditureDto.CountByCategoryResponse findExpenditureCountForCategoryByMonth(int month);

    // 월별 시간대 지출금액
    ExenditureDto.ByMonthAndTimeResponse findTotalExpenditureForMonthAndTimeByYearAndMonth(String yearAndMonth);

    // 월 평균 지출 금액
    ExenditureDto.AverageByMonthResponse findAverageExpenditureForMonthByYear(int year);
}
