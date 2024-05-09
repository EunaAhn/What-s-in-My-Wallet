package kr.or.kosa.nux2.domain.cardproduct.repository;

import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import kr.or.kosa.nux2.domain.cardproduct.mapper.CardProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CardProductRepository {
    private final CardProductMapper cardProductMapper;

    public List<CardProductDto.Response> findAllCardList(Map<String, Object> requestMap){
        return cardProductMapper.findAllCardList(requestMap);
    }
    public List<CardProductDto.Response> findTop4LikeCard(){
        return cardProductMapper.findTop4LikeCard();
    };
    public List<CardProductDto.Response> findMemberLikeCard(String memberId) {
        return cardProductMapper.findMemberLikeCard(memberId);
    }
}
