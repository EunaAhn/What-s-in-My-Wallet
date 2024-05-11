package kr.or.kosa.nux2.web.restcontroller;

import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import kr.or.kosa.nux2.domain.cardproduct.service.CardProductServiceImpl;
import kr.or.kosa.nux2.web.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardProductRestController {
    private final CardProductServiceImpl cardProductService;

    public ResponseEntity<ApiResponse<CardProductDto.DetailsResponse>> cardProductDetail(@RequestBody CardProductDto.DetailRequest request) {
        cardProductService.showCardProductDetail(request);
        return null;
    }
}
