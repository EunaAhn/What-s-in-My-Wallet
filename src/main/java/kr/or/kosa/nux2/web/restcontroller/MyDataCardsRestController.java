package kr.or.kosa.nux2.web.restcontroller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.or.kosa.nux2.domain.cardproduct.repository.CardProductRepository;
import kr.or.kosa.nux2.domain.expenditure.repository.ExpenditureRepository;
import kr.or.kosa.nux2.domain.memberexpendituretend.repository.MemberExpenditureTendRepository;
import kr.or.kosa.nux2.domain.registrationcard.repository.RegistrationCardRepository;
import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataTransanctionHistoryDto;
import kr.or.kosa.nux2.domain.virtualmydata.repository.MyDataCardRepository;
import kr.or.kosa.nux2.domain.virtualmydata.repository.MyDataTransHistoryRepository;
import kr.or.kosa.nux2.domain.virtualmydata.service.MyDataCardService;
import kr.or.kosa.nux2.domain.virtualmydata.service.MyDataCardServiceImpl;
import kr.or.kosa.nux2.domain.virtualmydata.service.MyDataTransHistorySevice;
import kr.or.kosa.nux2.web.common.code.SuccessCode;
import kr.or.kosa.nux2.web.common.response.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Mydata", description = "마이데이터 Rest API")
public class MyDataCardsRestController {
    private final MyDataCardService myDataCardService;

    @Operation(summary = "내 카드 목록 조회 ", description = "내 카드 리스트 출력")
    @PostMapping("/mycard")
    public ResponseEntity<ApiResponse<List<MyDataCardDto.Response>>> showMyCard(@Valid  @RequestBody MyDataCardDto.AuthenticationRequest request){
        List<MyDataCardDto.Response> responses = myDataCardService.showAllMyDataCard(request);

        return new ResponseEntity<>(new ApiResponse(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
}
