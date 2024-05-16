package kr.or.kosa.nux2.domain.cardproduct.service;

import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import kr.or.kosa.nux2.domain.cardproduct.repository.CardProductRepository;
import kr.or.kosa.nux2.domain.member.dto.MemberConsCategoryDto;
import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.repository.MemberExpenditureCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CardProductServiceImpl implements CardProductService {
    private final CardProductRepository cardProductRepository;
    private final MemberExpenditureCategoryRepository memberExpenditureCategoryRepository;

    @Override
    public List<CardProductDto.Response> showCardProductList(CardProductDto.ListRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> map = new HashMap<>();

        map.put("startnum", request.getStartNum());
        map.put("endnum", request.getEndNum());
        map.put("keyword", request.getKeyWord());

        MemberDto.MemberIdRequest serviceRequest = new MemberDto.MemberIdRequest();
        serviceRequest.setMemberId(memberId);
        List<MemberConsCategoryDto.MemberConsCategoryResponse>  result = memberExpenditureCategoryRepository.selectMemberConsCategoryNames(serviceRequest);

        map.put("categoryList", result);
        List<CardProductDto.Response> responses = cardProductRepository.findAllCards(map);
        return responses;
    }

    @Override
    public CardProductDto.DetailsResponse showCardProductDetail(CardProductDto.DetailRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("cardProductId", request.getCardProductId());
        map.put("memberId", memberId);
        CardProductDto.DetailsResponse response = cardProductRepository.findCardDetails(map);

        return response;
    }

    @Override
    public List<CardProductDto.Response> showTop4CardProduct() {
        List<CardProductDto.Response> responses = cardProductRepository.findTop4LikeCard();

        return responses;
    }

    @Override
    public List<CardProductDto.Response> showMembersLikeCard() {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        return cardProductRepository.findMemberLikeCard(memberId);
    }

    @Override
    @Transactional
    public boolean clickLikeCardProduct(CardProductDto.LikeRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("cardId", request.getCardId());

        return cardProductRepository.insertMemberLikeCard(map);
    }

    @Override
    @Transactional
    public boolean unclickLikeCardProduct(CardProductDto.LikeRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("cardProductId", request.getCardId());

        return cardProductRepository.deleteMemberLikeCard(map);
    }
}
