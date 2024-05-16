package kr.or.kosa.nux2.web.restcontroller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Expenditure", description = "지출 Rest API")
public class ExpenditureRestController {
    private final ExpenditureService expenditureService;

    @Operation(summary = "지출 내역 조회 ", description = "해당년월 지출내역 출력, 카테고리 선택시 해당카테고리 지출내역 출력")
    @PostMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> memberMonthlyExpenditures(@Valid @RequestBody ExenditureDto.ByYearAndMonthRequest request) {
        Map<String, Object> response = expenditureService.showMemberMonthlyExpenditures(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "지출 내역 상세 조회 ", description = "해당일 지출내역 출력")
    @PostMapping("/detail")
    public ResponseEntity<ApiResponse<ExenditureDto.DetailsReponse>> memberDailyExpenditureDetail(@Valid @RequestBody ExenditureDto.ExpenditureDetailRequest request) {
        ExenditureDto.DetailsReponse response = expenditureService.showMemberDailyExpenditureDetails(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "월별지출횟수 ", description = "해당년월일 지출횟수 출력")
    @PostMapping("/countformonth")
    public ResponseEntity<ApiResponse<List<ExenditureDto.TotalCount>>> totalExpenditure(@Valid @RequestBody ExenditureDto.ByYearAndMonthRequest request) {
        List<ExenditureDto.TotalCount> responses = expenditureService.showTotalExpenditureByMonth(request);

        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);

    }

    @Operation(summary = "카테고리별 지출 비율 조회", description = "해당기간별 지출내역비율 출력")
    @PostMapping("/ratioByCategory")
    public ResponseEntity<ApiResponse<List<ExenditureDto.RatioByCategoryResponse>>> expenditureRatioByCategory(@Valid @RequestBody ExenditureDto.ByYearAndMonthRequest request) {
        List<ExenditureDto.RatioByCategoryResponse> responses = expenditureService.showExpenditureRatioForCategoryByMonth(request);

        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "연도별 월별 지출 금액 조회", description = "해당연도 월별 지출 금액 출력")
    @PostMapping("/totalamountby12month")
    public ResponseEntity<ApiResponse<Map<String, Object>>> avgExpenditureForMonthByYear(@Valid @RequestBody ExenditureDto.ByYearRequest request) {
        Map<String, Object> response = expenditureService.findAverageExpenditureForMonthByYear(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "월별 시간대별 지출 금액 조회", description = "해당년월 시간대별 지출내역 출력")
    @PostMapping("/totalamountbytime")
    public ResponseEntity<ApiResponse<Map<String, Object>>> test(@Valid @RequestBody ExenditureDto.ByYearAndMonthRequest request) {
        Map<String, Object> response = expenditureService.showTotalExpenditureForMonthAndTimeByYearAndMonth(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "지출 내역 조회 ", description = "해당년월 지출내역 출력, 카테고리 선택시 해당카테고리 지출내역 출력")
    @PostMapping("/countforcategory")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> categoryCount(@Valid @RequestBody ExenditureDto.ByYearAndMonthRequest request) {
        List<Map<String, Object>> response = expenditureService.showExpenditureCountForCategoryByMonth(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "소비내역 업데이트 ", description = "해당년월 지출내역 출력, 카테고리 선택시 해당카테고리 지출내역 출력")
    @PostMapping("/reload")
    public ResponseEntity<ApiResponse<Boolean>> reloadNewExpenditures() {
        boolean response = expenditureService.insertExpenditure();

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.INSERT_SUCCESS), HttpStatus.CREATED);
    }

    @Operation(summary = "해당 시간대별 소비횟수 조회", description = "18~24시, 12~18시 중 선택시간대 소비횟수 출력")
    @PostMapping("/totalcountbytimeperiod")
    public ResponseEntity<ApiResponse<ExenditureDto.TotalCount>> totalCountByPeriod(@Valid @RequestBody ExenditureDto.TotalCountByTimePeriodRequest request) {
        ExenditureDto.TotalCount response = expenditureService.showExpenditureTotalCount(request);

        return new ResponseEntity<>(new ApiResponse(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @Operation(summary = "메모 수정 ", description = "해당 메모의 내용 수정")
    @PostMapping("/memo")
    public ResponseEntity<ApiResponse<Boolean>> updateMemo(@Valid @RequestBody ExenditureDto.UpdateMemoRequest request) {
        boolean response = expenditureService.updateDailyExpenditureMemo(request);

        return new ResponseEntity<>(new ApiResponse(response, SuccessCode.INSERT_SUCCESS), HttpStatus.CREATED);
    }

    @Operation(summary = "목표지출금액 대비 지출(절약금액) 조회", description = "해당월 목표지출대비 지출 출력")
    @PostMapping("/savingamount")
    public ResponseEntity<ApiResponse<ExenditureDto.TendencyAnalysis>> showSavingAmount(@Valid @RequestBody ExenditureDto.ByYearAndMonthRequest request) {
        ExenditureDto.TendencyAnalysis response = expenditureService.findExpendiutreTendencyAnalysis(request);

        return new ResponseEntity<>(new ApiResponse(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
}
