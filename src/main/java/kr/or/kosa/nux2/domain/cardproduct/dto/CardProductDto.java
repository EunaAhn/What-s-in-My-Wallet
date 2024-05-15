package kr.or.kosa.nux2.domain.cardproduct.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class CardProductDto {
    /*
      ========================================================================================
                                            RESPONSE DTO
      ========================================================================================
     */

    @Getter
    public static class DetailsResponse {
        private Long cardProductId;
        private Long cardCompanyId;
        private String cardName;
        private String cardImageFileName;
        private String membershipFee;
        private String benefitSummary;
        private int isUserClickLike;

        private Long likeCount;
        private List<BenefitCategoryDetails> benefitCategoryList;
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
        private int likeCount; // 어쩔수 없이 N+1 쿼리 수행
        private List<BenefitCategory> benefitCategoryList;
    }

    @Getter
    public static class BenefitDetails {
        private String benefitDetails;
    }

    @Getter
    public static class BenefitCategoryDetails {
        private String benefitName;
        private List<BenefitDetails> benefitDetailsList;
    }

    @Getter
    public static class BenefitCategory {
        private String benefitName;
    }

    @Getter
    @NoArgsConstructor
    public static class DetailRequest {
        private Long cardProductId;
    }

    /*
      ========================================================================================
                                              REQUEST DTO
      ========================================================================================
     */


    @Getter
    @NoArgsConstructor
    public static class ListRequest {
        @NotNull
        @Positive
        int startNum;
        @NotNull
        @Positive
        int endNum;

        String keyWord = null;
    }

    @Getter
    @NoArgsConstructor
    public static class LikeRequest {
        @NotNull
        @Positive
        Long cardId;
    }
}
