package kr.or.kosa.nux2.domain.cardrecommand.dto;

import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CardRecommandDto {
    @Getter
    @Setter
    public static class CardRecommandResponse {
        private List<CardProductDto.Response> cardProductList;
        private DiscountAmountByCategory[] discountAmountByCategoryArr;
    }
    @Getter
    public static class DiscountAmountByCategoryArr {
        DiscountAmountByCategory[] discountAmountByCategories;
    }

    @Getter
    @Setter
    public static class DiscountAmountByCategory {
        private int cardId;
        private double ac5;
        private double ce7;
        private double cs2;
        private double ct1;
        private double fd6;
        private double hp8;
        private double mt1;
        private double ol7;
        private double 언제나할인;
        private double 통합할인액;
    }
}
