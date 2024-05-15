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

    public boolean deleteMemberLikeCard(Map<String, Object> map) {
        int result = cardProductMapper.deleteMemberLikeCardProduct(map);

        if(result == 1) return true;
        return false;
    }

    public boolean insertMemberLikeCard(Map<String, Object> map) {
        int result = cardProductMapper.insertMemberLikeCardProduct(map);

        if(result == 1) return true;
        return false;
    }

    public CardProductDto.DetailsResponse findCardDetails(Map<String, Object> map) {
        return cardProductMapper.findCardProductDetail(map);
    }

    public List<CardProductDto.Response> findAllCards(Map<String, Object> columns) {
        return cardProductMapper.findAllCardProducts(columns);
    }

    public List<CardProductDto.Response> findTop4LikeCard() {
        return cardProductMapper.findTop6LikeCardProduct();
    }

    public List<CardProductDto.Response> findMemberLikeCard(String memberId) {
        return cardProductMapper.findMemberLikeCardProduct(memberId);
    }
}
