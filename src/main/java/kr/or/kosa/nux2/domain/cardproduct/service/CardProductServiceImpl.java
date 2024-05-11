package kr.or.kosa.nux2.domain.cardproduct.service;

import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import kr.or.kosa.nux2.domain.cardproduct.repository.CardProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CardProductServiceImpl implements CardProductService {
    private final CardProductRepository cardProductRepository;
    @Override
    public List<CardProductDto.Response> showCardProductList(Map<String, Object> map) {

        List<CardProductDto.Response> responses = cardProductRepository.findAllCards(map);
        //페이지네이션
        return responses;
    }

    @Override
    public CardProductDto.DetailsResponse showCardProductDetail(CardProductDto.DetailRequest request) {
        Long cardProductId = request.getCardId();
        CardProductDto.DetailsResponse response = cardProductRepository.findCardDetails(cardProductId);
        return response;
    }
}
