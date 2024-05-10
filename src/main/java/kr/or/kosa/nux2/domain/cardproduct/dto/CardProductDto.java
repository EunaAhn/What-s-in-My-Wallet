package kr.or.kosa.nux2.domain.cardproduct.dto;

import lombok.Getter;

import java.util.List;

public class CardProductDto {
    @Getter
    public static class DetailsResponse {
        // 카드상품 테이블 컬럼
        private Long cardProductId;
        private String cardCompanyId;
        private String cardName;
        private String cardImageFileName;
        private String membershipFee;
        private String benefitSummary;

        // 아래 조인 테이블 데이터 DTO 및 속성
        private Long likeCount; // 어쩔수 없이 N+1 쿼리 수행
        private List<BenefitCategoryDetails> benefitCategoryList;
//        private CardCompanyDto.Response cardCompanyDto;
    }

    @Getter
    public static class Response {
        // 카드상품 테이블 컬럼
        private Long cardProductId;
        private String cardCompanyId;
        private String cardName;
        private String cardImageFileName;
        private String membershipFee;
        private String benefitSummary;
        // 아래 조인 테이블 데이터 DTO 및 속성
        private Long likeCount; // 어쩔수 없이 N+1 쿼리 수행
        private List<BenefitCategory> benefitCategoryList;
    }

    @Getter
    public static class BenefitDetails {
        private String benefitDetails;
    }

    @Getter
    public static class BenefitCategoryDetails {
        private String benefitName;
        // 혜택상세Dto의 리스트 속성은 반드시 lazy loading을 적용해야한다.
        private List<BenefitDetails> benefitDetailsList;
    }

    @Getter
    public static class BenefitCategory {
        private String benefitName;
    }
}
