package kr.or.kosa.nux2.web.restcontroller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;
import kr.or.kosa.nux2.domain.registrationcard.service.RegistrationCardService;
import kr.or.kosa.nux2.web.common.code.SuccessCode;
import kr.or.kosa.nux2.web.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registrationcard")
@Tag(name = "RegistrationCard", description = "등록카드 Rest API")
public class RegistrationCardRestController {
    private final RegistrationCardService registrationCardService;

    @Operation(summary = "등록카드 목록 조회 ", description = "등록 카드 리스트 출력")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> registeredCard() {
        List<RegistrationCardDto.Response> responses = registrationCardService.showAllRegisteredCardByMemberId();

        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "등록 카드 삭제 ", description = "등록카드 삭제")
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<?>> deleteCard(@Valid @RequestBody RegistrationCardDto.InsertControllerRequest request) {
        boolean response = registrationCardService.deleteRegistrationCard(request.getCardNumber());

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.DELETE_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "카드 등록 ", description = "카드 등록")
    @PostMapping("/register")
    public ResponseEntity<?> registerCard(@Valid @RequestBody List<RegistrationCardDto.InsertControllerRequest> requests) {
        boolean response = registrationCardService.insertRegistrationCard(requests);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.INSERT_SUCCESS), HttpStatus.CREATED);
    }
}
