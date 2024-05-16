package kr.or.kosa.nux2.domain.cardrecommand.service;


import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import kr.or.kosa.nux2.domain.cardproduct.repository.CardProductRepository;
import kr.or.kosa.nux2.domain.cardrecommand.dto.CardRecommandDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CardRecommandServiceImpl implements CardRecommadService {
    private final CardProductRepository cardProductRepository;
    private final RestTemplate restTemplate;

    /**
     * 카드 추천 함수
     *
     * @param request: 해당연월일
     * @return 추천 카드 정보
     */
    @Override
    public CardRecommandDto.CardRecommandResponse recommandCards(CardRecommandDto.YearAndMonthRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
//        String memberId = "dnwo1111@naver.com";

        URI uri = UriComponentsBuilder
                .fromUriString("http://13.209.42.180:5000") // flask server uri
                .path("/")
                .queryParam("memberId", memberId)
                .queryParam("date", request.getYearAndMonth())
                .encode()
                .build()
                .toUri();

        // JSON객체 파싱 및 추출 {추천카드아이디 , 혜택금액}
        ResponseEntity<CardRecommandDto.DiscountAmountByCategory[]> response = restTemplate.getForEntity(uri, CardRecommandDto.DiscountAmountByCategory[].class);
        CardRecommandDto.DiscountAmountByCategory[] discountAmountByCategoryList = response.getBody();


        // 파싱한 객체의 추천카드아이디를 통해 카드 상세 조회
        Map<String, Object> map = new HashMap();
        List<Integer> cardProductIdList = new ArrayList<>();
        for (CardRecommandDto.DiscountAmountByCategory dac : discountAmountByCategoryList) {
            cardProductIdList.add(dac.getCardId());
        }

        map.put("list", cardProductIdList);
        map.put("startnum", 1);
        map.put("endnum", 4);
        List<CardProductDto.Response> cardProductList = cardProductRepository.findAllCards(map);


        // DTO에 SETTING
        CardRecommandDto.CardRecommandResponse cardRecommandResponse = new CardRecommandDto.CardRecommandResponse();
        cardRecommandResponse.setCardProductList(cardProductList);
        cardRecommandResponse.setDiscountAmountByCategoryArr(discountAmountByCategoryList);

        return cardRecommandResponse;
    }
}
