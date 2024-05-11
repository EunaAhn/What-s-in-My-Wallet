package kr.or.kosa.nux2.domain.cardproduct.repository;

import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import kr.or.kosa.nux2.domain.cardproduct.mapper.CardProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CardProductRepository {
    private final CardProductMapper cardProductMapper;

    public CardProductDto.DetailsResponse findCardDetails(Long cardProductId){
        return cardProductMapper.findCardDetail(cardProductId);
    }

    public List<CardProductDto.Response> findAllCards(Map<String, Object> columns) {
        return cardProductMapper.findAllCards(columns);
    }

    public List<CardProductDto.Response> findTop4LikeCard(){
        return cardProductMapper.findTop4LikeCard();
    };
    public List<CardProductDto.Response> findMemberLikeCard(String memberId) {
        return cardProductMapper.findMemberLikeCard(memberId);
    }
}
