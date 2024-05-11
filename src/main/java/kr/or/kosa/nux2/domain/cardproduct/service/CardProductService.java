package kr.or.kosa.nux2.domain.cardproduct.service;


import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;

import java.util.List;
import java.util.Map;

public interface CardProductService {
    List<CardProductDto.Response> showCardProductList(Map<String, Object> map);
    CardProductDto.DetailsResponse showCardProductDetail(CardProductDto.DetailRequest request);
}
