package kr.or.kosa.nux2.web.restcontroller;

import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import kr.or.kosa.nux2.domain.cardproduct.service.CardProductService;
import kr.or.kosa.nux2.web.common.code.SuccessCode;
import kr.or.kosa.nux2.web.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cardproduct")
public class CardProductRestController {
    private final CardProductService cardProductService;

    @PostMapping("/detail")
    public ResponseEntity<ApiResponse<CardProductDto.DetailsResponse>> cardProductDetail(@RequestBody CardProductDto.DetailRequest request) {
        CardProductDto.DetailsResponse response = cardProductService.showCardProductDetail(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<ApiResponse<List<CardProductDto.Response>>> cardProductList(@RequestBody CardProductDto.ListRequest request) {
        List<CardProductDto.Response> response = cardProductService.showCardProductList(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/top4list")
    public ResponseEntity<ApiResponse<List<CardProductDto.Response>>> top4Card() {
        List<CardProductDto.Response> responses = cardProductService.showTop4CardProduct();

        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/like")
    public ResponseEntity<ApiResponse<List<CardProductDto.Response>>> memberLike() {
        List<CardProductDto.Response> responses = cardProductService.showMembersLikeCard();

        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/memberlike")
    public ResponseEntity<ApiResponse<Boolean>> clickLike(@RequestBody CardProductDto.LikeRequest request) {
        cardProductService.clickLikeCardProduct(request);

        return new ResponseEntity<>(new ApiResponse<>(true, SuccessCode.INSERT_SUCCESS), HttpStatus.OK);
    }

    @DeleteMapping("/memberlike")
    public ResponseEntity<ApiResponse<Boolean>> unClickLike(@RequestBody CardProductDto.LikeRequest request) {
        cardProductService.unclickLikeCardProduct(request);

        return new ResponseEntity<>(new ApiResponse<>(true, SuccessCode.INSERT_SUCCESS), HttpStatus.OK);
    }
}
