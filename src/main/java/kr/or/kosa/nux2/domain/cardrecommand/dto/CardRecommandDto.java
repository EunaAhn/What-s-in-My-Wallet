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
        private DiscountAmountByCategory[] discountAmountByCategoryList;
    }

    @Getter
    @Setter
    public static class DiscountAmountByCategory {
        private int cardId;
        private double AC5;
        private double CE7;
        private double CS2;
        private double CT1;
        private double FD6;
        private double HP8;
        private double MT1;
        private double OL7;
        private double 언제나할인;
        private double 통합할인액;
    }
}
