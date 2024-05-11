package kr.or.kosa.nux2.web.restcontroller;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import kr.or.kosa.nux2.domain.expenditure.service.ExpenditureService;
import kr.or.kosa.nux2.web.common.code.SuccessCode;
import kr.or.kosa.nux2.web.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenditure")
public class ExpenditureRestController {
    private final ExpenditureService expenditureService;


    @GetMapping("/daily")
    public ResponseEntity<ApiResponse<ExenditureDto.DetailsReponse>> memberDailyExpenditureDetail(@RequestBody ExenditureDto.ExpenditureDetailRequest request) {
        ExenditureDto.DetailsReponse response = expenditureService.showMemberDailyExpenditureDetails(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
}
