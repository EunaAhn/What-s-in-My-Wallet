package kr.or.kosa.nux2.web.restcontroller;

import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import kr.or.kosa.nux2.domain.cardproduct.service.CardProductServiceImpl;
import kr.or.kosa.nux2.web.common.code.SuccessCode;
import kr.or.kosa.nux2.web.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cardproduct")
public class CardProductRestController {
    private final CardProductServiceImpl cardProductService;

    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<CardProductDto.DetailsResponse>> cardProductDetail(@RequestBody CardProductDto.DetailRequest request) {
        CardProductDto.DetailsResponse response = cardProductService.showCardProductDetail(request);
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<CardProductDto.Response>>> cardProductDetail(@RequestBody CardProductDto.ListRequest request) {
        int startNum = request.getStartNum();
        int endNum = request.getEndNum();
        Map<String, Object> map = new HashMap<>();

        map.put("startnum", startNum);
        map.put("endnum", endNum);
        List<CardProductDto.Response> response = cardProductService.showCardProductList(map);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }


    @GetMapping("/top4list")
    public ResponseEntity<ApiResponse<List<CardProductDto.Response>>> top4Card() {
        List<CardProductDto.Response> responses = cardProductService.showTop4CardProduct();
        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/memberlike")
    public ResponseEntity<ApiResponse<List<CardProductDto.Response>>> memberLike() {
        List<CardProductDto.Response> responses = cardProductService.showMembersLikeCard("dnwo111");
        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
}
