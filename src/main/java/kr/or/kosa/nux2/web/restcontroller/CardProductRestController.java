package kr.or.kosa.nux2.web.restcontroller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "CardProduct", description = "카드 상품 Rest API")
public class CardProductRestController {
    private final CardProductService cardProductService;

    @Operation(summary = "카드 상세 조회", description = "해당 카드 상세 정보 출력")
    @PostMapping("/detail")
    public ResponseEntity<ApiResponse<CardProductDto.DetailsResponse>> cardProductDetail(@Valid @RequestBody CardProductDto.DetailRequest request) {
        CardProductDto.DetailsResponse response = cardProductService.showCardProductDetail(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "카드 상품 전체 목록 조회", description = "카드 상품 전체 목록 출력 & 검색에 따른 목록 출력")
    @PostMapping("/list")
    public ResponseEntity<ApiResponse<List<CardProductDto.Response>>> cardProductList(@Valid @RequestBody CardProductDto.ListRequest request) {
        List<CardProductDto.Response> response = cardProductService.showCardProductList(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "인기카드 리스트 조회", description = "찜한 수가 많은 카드 상위 6개 출력")
    @PostMapping("/top4list")
    public ResponseEntity<ApiResponse<List<CardProductDto.Response>>> top4Card() {
        List<CardProductDto.Response> responses = cardProductService.showTop4CardProduct();

        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "찜한 카드리스트 조회", description = "찜한 카드 리스트 정보 출력")
    @GetMapping("/like")
    public ResponseEntity<ApiResponse<List<CardProductDto.Response>>> memberLike() {
        List<CardProductDto.Response> responses = cardProductService.showMembersLikeCard();

        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "카드 찜하기", description = "찜한 카드 추가")
    @PostMapping("/memberlike")
    public ResponseEntity<ApiResponse<Boolean>> clickLike(@Valid @RequestBody CardProductDto.LikeRequest request) {
        cardProductService.clickLikeCardProduct(request);

        return new ResponseEntity<>(new ApiResponse<>(true, SuccessCode.INSERT_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "카드 찜해제", description = "찜한 카드 삭제")
    @DeleteMapping("/memberlike")
    public ResponseEntity<ApiResponse<Boolean>> unClickLike(@Valid @RequestBody CardProductDto.LikeRequest request) {
        cardProductService.unclickLikeCardProduct(request);

        return new ResponseEntity<>(new ApiResponse<>(true, SuccessCode.INSERT_SUCCESS), HttpStatus.OK);
    }
}
