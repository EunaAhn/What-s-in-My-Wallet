package kr.or.kosa.nux2.domain.cardrecommand.dto;

import jakarta.validation.constraints.Pattern;
import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class CardRecommandDto {
    /*
      ========================================================================================
                                            RESPONSE DTO
      ========================================================================================
     */
    @Getter
    @Setter
    public static class CardRecommandResponse {
        private List<CardProductDto.Response> cardProductList;
        private DiscountAmountByCategory[] discountAmountByCategoryArr;
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
        private double 연회비;
        private double 피킹률;
    }


    /*
      ========================================================================================
                                            REQUEST DTO
      ========================================================================================
     */

    @Getter
    @NoArgsConstructor
    public static class YearAndMonthRequest {
        //@Pattern(regexp = "\\d{4}-\\d{2}", message = "날짜는 YYYY-MM 형식이어야 합니다.")
        private String yearAndMonth;
    }
}
