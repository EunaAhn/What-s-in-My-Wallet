package kr.or.kosa.nux2.domain.cardrecommand.service;


import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import kr.or.kosa.nux2.domain.cardproduct.repository.CardProductRepository;
import kr.or.kosa.nux2.domain.cardrecommand.dto.CardRecommandDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @Override
    public CardRecommandDto.CardRecommandResponse recommandCards() {


        URI uri = UriComponentsBuilder // Uri라는 class 객체 생성 가능
                .fromUriString("http://3.39.9.210:5000")
                .path("/") // 서버 입장의 서버 Controller 부분에 존재
                .queryParam("memberId", "dnwo1111")
                .queryParam("date", "2024-06")
                .encode()
                .build()
                .toUri();

        ResponseEntity<CardRecommandDto.DiscountAmountByCategory[]> response = restTemplate.getForEntity(uri, CardRecommandDto.DiscountAmountByCategory[].class);
        CardRecommandDto.DiscountAmountByCategory[] discountAmountByCategoryList = response.getBody();

        Map<String, Object> map = new HashMap();
        List<Integer> cardProductIdList = new ArrayList<>();
        for (CardRecommandDto.DiscountAmountByCategory dac : discountAmountByCategoryList) {
            cardProductIdList.add(dac.getCardId());
        }

        map.put("list", cardProductIdList);
        map.put("startnum", 1);
        map.put("endnum", 4);
        List<CardProductDto.Response> cardProductList = cardProductRepository.findAllCards(map);

        CardRecommandDto.CardRecommandResponse resp = new CardRecommandDto.CardRecommandResponse();
        resp.setCardProductList(cardProductList);
        resp.setDiscountAmountByCategoryList(discountAmountByCategoryList);

        return resp;
    }
}
