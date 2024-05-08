package kr.or.kosa.nux2.domain.cardmd.repository;

import kr.or.kosa.nux2.domain.cardmd.dto.CardmdDto;
import kr.or.kosa.nux2.domain.cardmd.mapper.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CardRepositoryImpl {
    private final CardRepository cardRepository;

    public List<CardmdDto> findAllCards(Map<String, Object> requestMap){
        return cardRepository.findAllCardList(requestMap);
    }


}
