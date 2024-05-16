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

    /**
     * 카드상품리스트 출력 함수
     *
     * @param request: 시작번호, 끝번호, 검색조건(옵션)
     * @return 카드상품리스트
     */
    @Override
    public List<CardProductDto.Response> showCardProductList(CardProductDto.ListRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> map = new HashMap<>();

        map.put("startnum", request.getStartNum());
        map.put("endnum", request.getEndNum());
        map.put("keyword", request.getKeyWord());

        MemberDto.MemberIdRequest serviceRequest = new MemberDto.MemberIdRequest();
        serviceRequest.setMemberId(memberId);
        List<MemberConsCategoryDto.MemberConsCategoryResponse> result = memberExpenditureCategoryRepository.selectMemberConsCategoryNames(serviceRequest);

        map.put("categoryList", result);
        List<CardProductDto.Response> responses = cardProductRepository.findAllCards(map);
        return responses;
    }

    /**
     * 카드 상세 조회 함수
     *
     * @param request: 카드상품 아이디
     * @return: 해당 카드 상세 정보
     */
    @Override
    public CardProductDto.DetailsResponse showCardProductDetail(CardProductDto.DetailRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("cardProductId", request.getCardProductId());
        map.put("memberId", "dnwo1111");
        CardProductDto.DetailsResponse response = cardProductRepository.findCardDetails(map);

        return response;
    }

    /**
     * 인기카드 조회 함수
     *
     * @return: 찜한 수가 많은 상위 6개 정보
     */
    @Override
    public List<CardProductDto.Response> showTop4CardProduct() {
        List<CardProductDto.Response> responses = cardProductRepository.findTop4LikeCard();

        return responses;
    }

    /**
     * 찜한 카드 리스트 조회 함수
     *
     * @return: 찜한 카드 리스트 정보
     */
    @Override
    public List<CardProductDto.Response> showMembersLikeCard() {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        return cardProductRepository.findMemberLikeCard(memberId);
    }

    /**
     * 찜 추가 함수
     *
     * @param request: 카드 상품 아이디
     * @return: 성공 여부
     */
    @Override
    @Transactional
    public boolean clickLikeCardProduct(CardProductDto.LikeRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("cardId", request.getCardId());

        return cardProductRepository.insertMemberLikeCard(map);
    }

    /**
     * 찜 해제 함수
     *
     * @param request: 카드 상품 아이디
     * @return: 성공 여부
     */
    @Override
    @Transactional
    public boolean unclickLikeCardProduct(CardProductDto.LikeRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("cardId", request.getCardId());

        return cardProductRepository.deleteMemberLikeCard(map);
    }
}
