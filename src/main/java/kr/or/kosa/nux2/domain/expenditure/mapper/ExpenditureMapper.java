package kr.or.kosa.nux2.domain.expenditure.mapper;

import kr.or.kosa.nux2.domain.expenditure.dto.*;
import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataTransanctionHistoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExpenditureMapper {
    // startDate, endDate 기준으로 total 산출
    // 소비성향 한달 지출액
    List<ExenditureDto.TotalCount> findTotalExpenditureByStartAndEndDate(Map<String, Object> map);

    // 동적 쿼리 : 소비카테고리, 월, 카드 별 검색
    List<ExenditureDto.Response>  findAllExpenditure (Map<String, Object> columns);

    // 상세조회
    // 파라미터 형식 string인지 date인지 고민하기
    ExenditureDto.DetailsReponse findAllExpenditureDetails(Map<String , Object> columns);


    List<List<ExenditureDto.CategoryName>>  findCategoryListOfDailyExpenditure(Map<String, Object> columns);
    // 현재 사용자의 마지막 id값보다 높은 값을 가지는 마이데이터소비내역을 조회해서 insert한다.
    // 다건 삽입 방법 성능 고민
    int insertExpenditures(Map<String, Object> map);

    // 소비 상세 내역 메모 수정하기
    void updateExpenditureMemo(Map<String, Object> columns);

    // 소비 카테고리 별 지출비율
    // 개월수가 컬럼으로 들어감
    List<ExenditureDto.RatioByCategoryResponse> findExpenditureRatioForCategoryByMonth(Map<String, Object> map);

    // 소비 카테고리 별 지출 횟수
    List<Map<String, Object>>  findExpenditureCountForCategoryByMonth(Map<String, Object> map);

    // 월별 시간대 지출금액
    Map<String, Object> findTotalExpenditureForMonthAndTimeByYearAndMonth(Map<String, Object> map);

    // 월 평균 지출 금액
    Map<String, Object> findAverageExpenditureForMonthByYear(Map<String, Object> map);

    // 소비성향을 넘겨주면 해당 시간대의 총 지출 횟수를 반환해야한다.
    // 리팩터링 부채 : enum의 위치 애매 + 단순 columns으로 넘기는게 타당한지 의문
    // 일단 MemberExpenditureTendDto에서 값을 꺼내서 전달
    // 저녁형 : 18~24
    // 아침형 : 06~18
    // 시작시간, 종료시간, 년월을 넘겨줘야한다.
    // 소비성향을 넘겨서 매퍼가 로직을 처리하는 것 보다는 서비스레이어가 로직을 처리하는 편이 더 바람직하다고 판단하여 조건만 넘ㅡ
    ExenditureDto.TotalCount findExpenditureTotalCount(Map<String, Object>  map);


    // member-expenditure 조인 쿼리
    ExenditureDto.TendencyAnalysis findExpendiutreTendencyAnalysis(Map<String, Object> map);

    int isExistMemo(Map<String, Object> map) ;

    void insertExpenditureMemo(Map<String, Object> map);

    List<ExenditureDto.CategoryList> findAllCategoryList (Map<String, Object> map);

}


