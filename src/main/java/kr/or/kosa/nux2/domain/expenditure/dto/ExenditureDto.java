package kr.or.kosa.nux2.domain.expenditure.dto;


import java.util.List;

public class ExenditureDto {

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
}
