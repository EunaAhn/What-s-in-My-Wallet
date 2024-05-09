package kr.or.kosa.nux2.domain.expenditure.dto;


import kr.or.kosa.nux2.domain.member.dto.MemberTargetExpenditureDto;
import lombok.Setter;

import java.util.List;

public class ExenditureDto {
    // insert, update 용 DTO도 정의해야한다.
/*
      ========================================================================================
                                          일반 RESPONSE DTO
      ========================================================================================
     */
    public static class Response {
        // 달력 한칸에 표현되는 지출 DTO -> 30~31개의 idx를 갖는 List로 Wrapping되는 DTO
        private List<ExenditureDto.CategoryName> expenditureCategoryList;
        private Long expenditureAmount;
    }

    public static class DetailsReponse {
        // 달력에서 일자별 지출을 클릭했을때 표현되는 지출상세 DTO
        private Long expenditureTotalAmount;
        private String memo;
        private List<ExenditureDto.Summary> expenditureSummaryDtoList;
    }


    public static class Summary {
        // 일자별 지출 상세 내역의 지출 요약  DTO
        private Long expenditureId;
        private String storeName;
        private String expenditureDatetime;
        private String totalExpenditureConut; // 지출 횟수
        private String storeAddress;
    }

    public static class CategoryName {
        // 달력 한칸에 표현되는 지출 카테고리명
        private String expenditureCategoryName;
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


    public static class RatioByCategoryResponse {
        // 카테고리별 지출 비율 응답 DTO
        private ExenditureDto.CategoryName categoryName;
        private Float expenditrueRatio;
    }

    /*
      ========================================================================================
                                            소비성향 사용 DTO
      ========================================================================================
     */

    //    int expenditureConutByTime;
    public static class TendencyAnalysis {
        private MemberTargetExpenditureDto targetExpenditure;
        private int savingAmount;
    }

    public static class TotalCount{
        private int expenditureTotalCount;
    }

    /*
      ========================================================================================
                                              REQUEST DTO
      ========================================================================================
     */
    @Setter
    public static class InsertRequest {
        private Long cardId;
        private String memberId;
        private String categoryId;
        private Long expenditureAmount;
        private String expenditureDateTime;
    }

    public static class UpdateMemoRequest{
        private String memo;
    }
}
