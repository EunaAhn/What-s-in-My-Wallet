package kr.or.kosa.nux2.web.restcontroller;

import jakarta.validation.Valid;
import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import kr.or.kosa.nux2.domain.expenditure.service.ExpenditureService;
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
@RequestMapping("/api/expenditure")
public class ExpenditureRestController {
    private final ExpenditureService expenditureService;

    @PostMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> memberMonthlyExpenditures(@Valid @RequestBody ExenditureDto.ByYearAndMonthRequest request) {
        Map<String, Object> response = expenditureService.showMemberMonthlyExpenditures(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }


    @PostMapping("/detail")
    public ResponseEntity<ApiResponse<ExenditureDto.DetailsReponse>> memberDailyExpenditureDetail(@Valid @RequestBody ExenditureDto.ExpenditureDetailRequest request) {
        ExenditureDto.DetailsReponse response = expenditureService.showMemberDailyExpenditureDetails(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    // 월별지출횟수
    @PostMapping("/countformonth")
    public ResponseEntity<ApiResponse<List<ExenditureDto.TotalCount>>> totalExpenditure(@Valid @RequestBody ExenditureDto.ByYearAndMonthRequest request) {
        List<ExenditureDto.TotalCount> responses = expenditureService.showTotalExpenditureByMonth(request);

        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);

    }

    @PostMapping("/ratioByCategory")
    public ResponseEntity<ApiResponse<List<ExenditureDto.RatioByCategoryResponse>>> expenditureRatioByCategory(@Valid @RequestBody ExenditureDto.ByYearAndMonthRequest request) {
        List<ExenditureDto.RatioByCategoryResponse> responses = expenditureService.showExpenditureRatioForCategoryByMonth(request);

        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/totalamountby12month")
    public ResponseEntity<ApiResponse<Map<String, Object>>> avgExpenditureForMonthByYear(@Valid @RequestBody ExenditureDto.ByYearRequest request) {
        Map<String, Object> response = expenditureService.findAverageExpenditureForMonthByYear(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    //월별시간대지출금액
    @PostMapping("/totalamountbytime")
    public ResponseEntity<ApiResponse<Map<String, Object>>> test(@Valid @RequestBody ExenditureDto.ByYearAndMonthRequest request) {
        Map<String, Object> response = expenditureService.showTotalExpenditureForMonthAndTimeByYearAndMonth(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/countforcategory")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> categoryCount(@Valid @RequestBody ExenditureDto.ByYearAndMonthRequest request) {
        List<Map<String, Object>> response = expenditureService.showExpenditureCountForCategoryByMonth(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/reload")
    public ResponseEntity<ApiResponse<Boolean>> reloadNewExpenditures() {
        boolean response = expenditureService.insertExpenditure();

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.INSERT_SUCCESS), HttpStatus.CREATED);
    }

    @PostMapping("/totalcountbytimeperiod")
    public ResponseEntity<ApiResponse<ExenditureDto.TotalCount>> totalCountByPeriod(@Valid @RequestBody ExenditureDto.TotalCountByTimePeriodRequest request) {
        ExenditureDto.TotalCount response = expenditureService.showExpenditureTotalCount(request);

        return new ResponseEntity<>(new ApiResponse(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/memo")
    public ResponseEntity<ApiResponse<Boolean>> updateMemo(@Valid @RequestBody ExenditureDto.UpdateMemoRequest request) {
        boolean response = expenditureService.updateDailyExpenditureMemo(request);

        return new ResponseEntity<>(new ApiResponse(response, SuccessCode.INSERT_SUCCESS), HttpStatus.CREATED);
    }

    @PostMapping("/savingamount")
    public ResponseEntity<ApiResponse<ExenditureDto.TendencyAnalysis>> showSavingAmount(@Valid @RequestBody ExenditureDto.ByYearAndMonthRequest request) {
        ExenditureDto.TendencyAnalysis response = expenditureService.findExpendiutreTendencyAnalysis(request);

        return new ResponseEntity<>(new ApiResponse(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
}
