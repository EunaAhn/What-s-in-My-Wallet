package kr.or.kosa.nux2.domain.cardproduct.service;

import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;

import java.util.List;
import java.util.Map;

public interface CardProductService {
    List<CardProductDto.Response> showCardProductList(CardProductDto.ListRequest request);

    CardProductDto.DetailsResponse showCardProductDetail(CardProductDto.DetailRequest request);

    List<CardProductDto.Response> showTop4CardProduct();

    List<CardProductDto.Response> showMembersLikeCard();

    boolean clickLikeCardProduct(CardProductDto.LikeRequest request);

    boolean unclickLikeCardProduct(CardProductDto.LikeRequest request);
}
