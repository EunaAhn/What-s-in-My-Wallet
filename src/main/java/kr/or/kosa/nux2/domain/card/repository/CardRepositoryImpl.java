package kr.or.kosa.nux2.domain.card.repository;

import kr.or.kosa.nux2.domain.card.dto.CardRespDto;
import kr.or.kosa.nux2.domain.card.mapper.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CardRepositoryImpl {
    private final CardRepository cardRepository;

    public CardRespDto findAllCards(){
        return cardRepository.findAllCardList();
    }


}
