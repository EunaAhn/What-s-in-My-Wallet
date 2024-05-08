package kr.or.kosa.nux2.domain.cardmd.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CardmdDto {
    // 카드혜택코드 와 카드 테이블의 컬럼을 ㄷ조인해서 바인딩 할 dto
    // 혜택코드
    // 카드아이디
    // 혜택명
    // 카드이미지명
    // 연회비
    // 카드이름
    String cardCompanyId;
    String cardCompanyName;
    String cardCompanyLink;
    String cardName;
    String cardImageFileName;
    int membershipFee;
    String benefitSummary;
    List<CardmdBenefitCategoryDto> cardmdBenefitCategoryDtoList;
    boolean isLikeCard; // 어쩔수 없이 N+1 쿼리 수행
}
