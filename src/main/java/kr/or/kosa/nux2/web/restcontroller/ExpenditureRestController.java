package kr.or.kosa.nux2.web.restcontroller;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import kr.or.kosa.nux2.domain.expenditure.service.ExpenditureService;
import kr.or.kosa.nux2.web.common.code.SuccessCode;
import kr.or.kosa.nux2.web.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/list")
    public ResponseEntity<ApiResponse<Map<String, Object>>> memberMonthlyExpenditures(@RequestBody  ExenditureDto.YearAndMonthRequest request) {
        Map<String, Object> response = expenditureService.showMemberMonthlyExpenditures(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }


    @PostMapping("/detail")
    public ResponseEntity<ApiResponse<ExenditureDto.DetailsReponse>> memberDailyExpenditureDetail(@RequestBody ExenditureDto.ExpenditureDetailRequest request) {
        ExenditureDto.DetailsReponse response = expenditureService.showMemberDailyExpenditureDetails(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    // 월별지출횟수
    @PostMapping("/totalexpenditure")
    public ResponseEntity<ApiResponse<List<ExenditureDto.TotalCount>>> totalExpenditure(@RequestBody ExenditureDto.TotalExpenditureCountRequest request) {
        List<ExenditureDto.TotalCount> responses = expenditureService.showTotalExpenditureByMonth(request);

        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);

    }
    @PostMapping("/ratioByCategory")
    public ResponseEntity<ApiResponse<List<ExenditureDto.RatioByCategoryResponse>>> expenditureRatioByCategory(@RequestBody ExenditureDto.YearAndMonthRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("month", request.getYearAndMonth());
        map.put("memberId", "dnwo1111");
        List<ExenditureDto.RatioByCategoryResponse> response = expenditureService.showExpenditureRatioForCategoryByMonth(map);
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/totalamountby12month")
    public ResponseEntity<ApiResponse<Map<String, Object>>> avgExpenditureForMonthByYear(@RequestBody ExenditureDto.YearRequest request) {
        Map<String, Object> map = expenditureService.findAverageExpenditureForMonthByYear(request.getYear());
        return new ResponseEntity<>(new ApiResponse<>(map, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    //월별시간대지출금액
    @PostMapping("/totalamountbytime")
    public ResponseEntity<ApiResponse<Map<String, Object>>> test(@RequestBody ExenditureDto.YearAndMonthRequest request) {
        Map<String, Object> map = expenditureService.showTotalExpenditureForMonthAndTimeByYearAndMonth(request.getYearAndMonth());
        return new ResponseEntity<>(new ApiResponse<>(map, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/countforcategory")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> categoryCount(@RequestBody ExenditureDto.YearAndMonthRequest request) {
        System.out.println(request.getYearAndMonth());
        List<Map<String, Object>> map = expenditureService.showExpenditureCountForCategoryByMonth(request.getYearAndMonth());
        return new ResponseEntity<>(new ApiResponse<>(map, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> reloadNewExpenditures() {
        expenditureService.insertExpenditure();
        return new ResponseEntity<>(new ApiResponse<>("insert ok", SuccessCode.INSERT_SUCCESS), HttpStatus.CREATED);
    }

    @PostMapping("/totalcountbytimeperiod")
    public ResponseEntity<ApiResponse<ExenditureDto.TotalCount>> totalCountByPeriod(@RequestBody ExenditureDto.TotalExpenditureCountByTimePeriodRequest request) {
        ExenditureDto.TotalCount response = expenditureService.showExpenditureTotalCount(request);
        return new ResponseEntity<>(new ApiResponse(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/memo")
    public ResponseEntity<ApiResponse<?>> updateMemo(@RequestBody ExenditureDto.UpdateMemoRequest request) {
        expenditureService.updateDailyExpenditureMemo(request);
        return new ResponseEntity<>(new ApiResponse("INSERTOK", SuccessCode.INSERT_SUCCESS), HttpStatus.CREATED);
    }

    @PostMapping("/savingamount")
    public ResponseEntity<ApiResponse<?>> showSavingAmount(@RequestBody ExenditureDto.YearAndMonthRequest request) {
        ExenditureDto.TendencyAnalysis response = expenditureService.findExpendiutreTendencyAnalysis(request);
        return new ResponseEntity<>(new ApiResponse(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
}
