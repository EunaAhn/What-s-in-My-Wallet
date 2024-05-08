package kr.or.kosa.nux2.domain.cardmd.dto;

import java.util.List;

public class CardmdBenefitCategoryDto {
    String benefitName;
    // 혜택상세Dto의 리스트 속성은 반드시 lazy loading을 적용해야한다.
    List<CardmdBenefitDetailsDto> cardmdBenefitDetailsDtoList;
}
