package kr.or.kosa.nux2.domain.cardmerchan.repository;

import kr.or.kosa.nux2.domain.cardmerchan.dto.CardProductDto;
import kr.or.kosa.nux2.domain.cardmerchan.mapper.CardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CardRepository {
    private final CardMapper cardMapper;

    public List<CardProductDto.Response> findAllCards(Map<String, Object> requestMap){
        return cardMapper.findAllCardList(requestMap);
    }


}
