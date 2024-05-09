package kr.or.kosa.nux2.domain.cardmerchand.repository;

import kr.or.kosa.nux2.domain.cardmerchand.dto.CardProductDto;
import kr.or.kosa.nux2.domain.cardmerchand.mapper.CardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CardRepository {
    private final CardMapper cardMapper;

    public List<CardProductDto.Response> findAllCardList(Map<String, Object> requestMap){
        return cardMapper.findAllCardList(requestMap);
    }
    public List<CardProductDto.Response> findTop4LikeCard(){
        return cardMapper.findTop4LikeCard();
    };
    public List<CardProductDto.Response> findMemberLikeCard(String memberId) {
        return cardMapper.findMemberLikeCard(memberId);
    }
}
