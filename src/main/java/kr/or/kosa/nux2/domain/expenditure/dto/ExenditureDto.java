package kr.or.kosa.nux2.domain.expenditure.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public class ExenditureDto {
    // insert, update 용 DTO도 정의해야한다.
     /*
      ========================================================================================
                                          일반 RESPONSE DTO
      ========================================================================================
     */
    @Getter
    @Setter
    public static class Response {
        // 달력 한칸에 표현되는 지출 DTO -> 30~31개의 idx를 갖는 List로 Wrapping되는 DTO
        private String expenditureDate;
        private Long expenditureAmount;
    }

    @Getter
    public static class DetailsReponse {
        // 달력에서 일자별 지출을 클릭했을때 표현되는 지출상세 DTO
        private Long expenditureTotalAmount;
        private String memo;
        private String totalExpenditureCount; // 지출 횟수
        private List<ExenditureDto.Summary> expenditureSummaryDtoList;
    }

    @Getter
    public static class Summary {
        // 일자별 지출 상세 내역의 지출 요약  DTO
        private Long expenditureId;
        private Long expenditureAmount;
        private String storeName;
        private String expenditureDatetime;
        private String storeAddress;
    }

    @Getter
    public static class CategoryName {
        // 달력 한칸에 표현되는 지출 카테고리명
        private String expenditureCategoryName;
    }

    @Getter
    public static class CategoryList {
        private String expenditureDatetime;
        private List<Map<String, Object>> categoryNameList;
    }


    /*
      ========================================================================================
                                          통계용 RESPONSE DTO
      ========================================================================================
     */

    public static class AverageByMonthResponse {
        // 월별 지출 평균 응답 DTO
        private int year;
        private int startMonth;
        private int endMonth;
        private Long maxAvgExpenditure;
        private List<Integer> avgExpenditureList;
    }

    public static class ByMonthAndTimeResponse {
        // 대상 month의 두시간 단위의 지출 응답 DTO
        private int maxTotalExpenditure;
        private List<Integer> totalExpenditureList;
    }

    public static class CountByCategoryResponse {
        // 카테고리별 지출 횟수 응답 DTO
        private ExenditureDto.CategoryName categoryName;
        private String startDate;
        private String endDate;
        private int maxCount;
        private List<Integer> expenditureCountList;
    }

    @Getter
    public static class RatioByCategoryResponse {
        // 카테고리별 지출 비율 응답 DTO
        private String categoryName;
        private Float expenditrueRatio;
    }

    /*
      ========================================================================================
                                            소비성향 사용 DTO
      ========================================================================================
     */

    @Getter
    public static class TendencyAnalysis {
        private Long totalExpenditure;
        private Long savingAmount;
    }

    @Getter
    public static class TotalCount {
        private Long expenditureTotalCount;
    }

    /*
      ========================================================================================
                                              REQUEST DTO
      ========================================================================================
     */
    @Setter
    public static class InsertRequest {
        // 사용자 활용 DTO X
        private Long cardId;
        private String memberId;
        private String categoryId;
        private Long expenditureAmount;
        private String expenditureDateTime;
    }

    @Getter
    @Setter
    public static class UpdateMemoRequest {
        @Pattern(regexp = "\\d{4}\\d{2}", message = "날짜는 YYYYMM 형식이어야 합니다.")
        private String memoId;
        private String memo;
    }

    @Getter
    @Setter
    public static class ExpenditureDetailRequest {
        @NotNull
        @Positive
        String expenditureId;
        @Pattern(regexp = "\\d{4}-\\d{2}", message = "날짜는 YYYY-MM 형식이어야 합니다.")
        String nowDate;
    }

    @Getter
    @Setter
    public static class ByYearRequest {
        @Pattern(regexp = "\\d{4}", message = "날짜는 YYYY 형식이어야 합니다.")
        int year;
    }

    @Getter
    @Setter
    public static class TotalExpenditureCountRequest {
        String nowDate;
    }

    @Getter
    @Setter
    public static class TotalCountByTimePeriodRequest {
        @Pattern(regexp = "\\d{4}-\\d{2}", message = "날짜는 YYYY-MM 형식이어야 합니다.")
        String yearAndMonth;
        String startHour;
        String endHour;
    }

    @Getter
    @Setter
    public static class ByYearAndMonthRequest {
        @Pattern(regexp = "\\d{4}-\\d{2}", message = "날짜는 YYYY-MM 형식이어야 합니다.")
        String yearAndMonth;
        String keyWord;
    }
}
