package kr.or.kosa.nux2.web.restcontroller;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import kr.or.kosa.nux2.domain.expenditure.service.ExpenditureService;
import kr.or.kosa.nux2.web.common.code.SuccessCode;
import kr.or.kosa.nux2.web.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenditure")
public class ExpenditureRestController {
    private final ExpenditureService expenditureService;

    @GetMapping("/monthly")
    public ResponseEntity<ApiResponse<List<ExenditureDto.Response>>> memberMonthlyExpenditures(@RequestBody  ExenditureDto.YearAndMonthRequest request) {
        List<ExenditureDto.Response>response = expenditureService.showMemberMonthlyExpenditures(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }


    @GetMapping("/daily")
    public ResponseEntity<ApiResponse<ExenditureDto.DetailsReponse>> memberDailyExpenditureDetail(@RequestBody ExenditureDto.ExpenditureDetailRequest request) {
        ExenditureDto.DetailsReponse response = expenditureService.showMemberDailyExpenditureDetails(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    // 월별지출횟수
    @GetMapping("/totalexpenditure")
    public ResponseEntity<ApiResponse<List<ExenditureDto.TotalCount>>> totalExpenditure(@RequestBody ExenditureDto.TotalExpenditureCountRequest request) {
        List<ExenditureDto.TotalCount> responses = expenditureService.showTotalExpenditureByMonth(request);

        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);

    }
    @GetMapping("/ratioByCategory")
    public ResponseEntity<ApiResponse<List<ExenditureDto.RatioByCategoryResponse>>> expenditureRatioByCategory(@RequestBody ExenditureDto.YearAndMonthRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("month", request.getYearAndMonth());
        map.put("memberId", "dnwo1111");
        List<ExenditureDto.RatioByCategoryResponse> response = expenditureService.showExpenditureRatioForCategoryByMonth(map);
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/averageExpenditureby12month")
    public ResponseEntity<ApiResponse<Map<String, Object>>> avgExpenditureForMonthByYear() {
        Map<String, Object> map = expenditureService.findAverageExpenditureForMonthByYear(2023);
        return new ResponseEntity<>(new ApiResponse<>(map, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    //월별시간대지출금액
    @GetMapping("/totalExpenditure")
    public ResponseEntity<ApiResponse<Map<String, Object>>> test(ExenditureDto.YearAndMonthRequest request) {
        Map<String, Object> map = expenditureService.showTotalExpenditureForMonthAndTimeByYearAndMonth(request.getYearAndMonth());
        return new ResponseEntity<>(new ApiResponse<>(map, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/expenditurecount")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> categoryCount() {
        List<Map<String, Object>> map = expenditureService.showExpenditureCountForCategoryByMonth(14);
        return new ResponseEntity<>(new ApiResponse<>(map, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/test1")
    public ResponseEntity<ApiResponse<?>> reloadNewExpenditures() {
        expenditureService.insertExpenditure();
        return new ResponseEntity<>(new ApiResponse<>("insert ok", SuccessCode.INSERT_SUCCESS), HttpStatus.CREATED);
    }

    @GetMapping("/tend2")
    public ResponseEntity<ApiResponse<ExenditureDto.TotalCount>> totalCountByPeriod(@RequestBody ExenditureDto.TotalExpenditureCountByTimePeriodRequest request) {
        ExenditureDto.TotalCount response = expenditureService.showExpenditureTotalCount(request);
        return new ResponseEntity<>(new ApiResponse(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/tend3")
    public ResponseEntity<ApiResponse<?>> updateMemo(@RequestBody ExenditureDto.UpdateMemoRequest request) {
        expenditureService.updateDailyExpenditureMemo(request);
        return new ResponseEntity<>(new ApiResponse("INSERTOK", SuccessCode.INSERT_SUCCESS), HttpStatus.CREATED);
    }

    @GetMapping("/tend4")
    public ResponseEntity<ApiResponse<?>> showSavingAmount(@RequestBody ExenditureDto.YearAndMonthRequest request) {
        ExenditureDto.TendencyAnalysis response = expenditureService.findExpendiutreTendencyAnalysis(request);
        return new ResponseEntity<>(new ApiResponse(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
}
