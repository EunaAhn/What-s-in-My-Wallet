package kr.or.kosa.nux2.domain.cardmerchan.dto;

import kr.or.kosa.nux2.domain.cardcompany.dto.CardCompanyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class CardProductDto {

    public static class Response {
        // 카드상품 테이블 컬럼
        private String cardCompanyId;
        private String cardName;
        private String cardImageFileName;
        private int membershipFee;
        private String benefitSummary;

        // 아래 조인 테이블 데이터 DTO 및 속성
        private boolean isLikeCard; // 어쩔수 없이 N+1 쿼리 수행
        private List<CardProductDto.CardmdBenefitCategoryDto> cardmdBenefitCategoryDtoList;
        private CardCompanyDto.Response cardCompanyDto;
    }


    public static class CardmdBenefitDetailsDto {
        private String benefitDetails;
    }


    public static class CardmdBenefitCategoryDto {
        private String benefitName;
        // 혜택상세Dto의 리스트 속성은 반드시 lazy loading을 적용해야한다.
        private List<CardProductDto.CardmdBenefitDetailsDto> cardmdBenefitDetailsDtoList;
    }
}
