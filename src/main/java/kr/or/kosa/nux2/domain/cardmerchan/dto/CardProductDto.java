package kr.or.kosa.nux2.domain.cardmerchan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class CardProductDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String cardCompanyId;
        private String cardCompanyName;
        private String cardCompanyLink;
        private String cardName;
        private String cardImageFileName;
        private int membershipFee;
        private String benefitSummary;
        private boolean isLikeCard; // 어쩔수 없이 N+1 쿼리 수행
        private List<CardProductDto.CardmdBenefitCategoryDto> cardmdBenefitCategoryDtoList;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardmdBenefitDetailsDto {
        String benefitDetails;
    }


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardmdBenefitCategoryDto {
        String benefitName;
        // 혜택상세Dto의 리스트 속성은 반드시 lazy loading을 적용해야한다.
        List<CardProductDto.CardmdBenefitDetailsDto> cardmdBenefitDetailsDtoList;
    }
}
