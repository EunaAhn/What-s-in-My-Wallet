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
        return responses;
    }

    @Override
    public CardProductDto.DetailsResponse showCardProductDetail(CardProductDto.DetailRequest request) {
        Long cardProductId = request.getCardId();
        CardProductDto.DetailsResponse response = cardProductRepository.findCardDetails(cardProductId);
        return response;
    }

    @Override
    public List<CardProductDto.Response> showTop4CardProduct() {
        List<CardProductDto.Response> responses = cardProductRepository.findTop4LikeCard();
        return responses;
    }

    @Override
    public List<CardProductDto.Response> showMembersLikeCard(String memberId) {
        return cardProductRepository.findMemberLikeCard(memberId);
    }

    @Override
    public boolean clickLikeCardProduct(CardProductDto.LikeRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", "dnwo1111");
        map.put("cardId", request.getCardId());
        cardProductRepository.insertMemberLikeCard(map);
        return true;
    }

    @Override
    public boolean unclickLikeCardProduct(CardProductDto.LikeRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", "dnwo1111");
        map.put("cardId", request.getCardId());
        cardProductRepository.deleteMemberLikeCard(map);
        return true;
    }

}
