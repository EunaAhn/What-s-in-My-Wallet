package kr.or.kosa.nux2.domain.cardproduct.mapper;

import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CardProductMapper {

    // map을 파라미터 값으로 전달하는 경우 동적쿼리를 생성할 수 있다.
    CardProductDto.DetailsResponse findCardDetail(@Param("cardProductId") Long cardProductId);
    List<CardProductDto.Response> findAllCards(Map<String, Object> map);
    // company, cardname, benefit 3개의 인자로 동적쿼리를 생성해야한다.
    // 카드 목록 필터 기능
    List<CardProductDto.Response> findTop4LikeCard();
    // 관심 카드 목록 가져오기 (마이페이지)
    List<CardProductDto.Response> findMemberLikeCard(String memberId);
    List<CardProductDto.BenefitDetails> findBenefitDetails(@Param("benefitCode") String benefitCode, @Param("cardProductId") Long cardProductId);
}
