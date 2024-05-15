package kr.or.kosa.nux2.domain.cardproduct.mapper;

import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CardProductMapper {

    int insertMemberLikeCardProduct(Map<String, Object> map);

    CardProductDto.DetailsResponse findCardProductDetail(Map<String, Object> map);

    List<CardProductDto.Response> findAllCardProducts(Map<String, Object> map);

    List<CardProductDto.Response> findTop6LikeCardProduct();

    List<CardProductDto.Response> findMemberLikeCardProduct(@Param("memberId") String memberId);

    int deleteMemberLikeCardProduct(Map<String, Object> map);

    List<CardProductDto.BenefitDetails> findBenefitDetails(@Param("benefitCode") String benefitCode, @Param("cardProductId") Long cardProductId);
}
