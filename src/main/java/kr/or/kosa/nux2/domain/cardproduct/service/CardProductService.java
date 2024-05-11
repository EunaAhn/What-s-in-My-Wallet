package kr.or.kosa.nux2.domain.cardproduct.service;


import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;

import java.util.List;

public interface CardProductService {
    List<CardProductDto.Response> showCardProductList();
    CardProductDto.DetailsResponse showCardProductDetail(CardProductDto.DetailRequest request);
}
