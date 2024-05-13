package kr.or.kosa.nux2.domain.expenditure.service;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import kr.or.kosa.nux2.domain.expenditure.repository.ExpenditureRepository;
import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;
import kr.or.kosa.nux2.domain.registrationcard.service.RegistrationCardServiceImpl;
import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataTransanctionHistoryDto;
import kr.or.kosa.nux2.domain.virtualmydata.service.MyDataTransHistorySevice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExpenditureServiceImpl implements ExpenditureService{
    private final ExpenditureRepository expenditureRepository;
    private final MyDataTransHistorySevice myDataTransHistorySevice;
    private final RegistrationCardServiceImpl registrationCardService;

    @Override
    public Map<String, Object> showMemberMonthlyExpenditures(ExenditureDto.YearAndMonthRequest request) {
        //멤버아이디 컨텍스트에서 받아오기
        //연월을 클라이언트로 부터 받아오기
        // 해당 쿼리를 불러오기
        String memberId = "dnwo1111";
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("nowDate", request.getYearAndMonth());
        map.put("keyword", request.getKeyword());
        System.out.println(request.getYearAndMonth());

        List<ExenditureDto.Response> expenditureList = expenditureRepository.findAllExpenditure(map);
        List<ExenditureDto.CategoryList> categoryNames = expenditureRepository.findAllCategoryList(map);



//        for(int i = 0; i < expenditureList.size(); i++) {
//            //expenditureList.get(i).setExpenditureCategoryList(categoryNames.get(i));
//        }
        Map<String, Object> responses = new HashMap<>();
        responses.put("expenditureList", expenditureList);
        responses.put("categoryList", categoryNames);
        return responses;
    }

    @Override
    public ExenditureDto.DetailsReponse showMemberDailyExpenditureDetails(ExenditureDto.ExpenditureDetailRequest request) {
        // 해당 날짜 YYYY-MM 보내기
        Map<String, Object> map = new HashMap<>();

        String memberId = "dnwo1111";
        String nowDate = request.getNowDate();

        String expenditureId = request.getExpenditureId();
        map.put("memberId", memberId);
        map.put("nowDate", nowDate);
        map.put("expenditureId", expenditureId);

        ExenditureDto.DetailsReponse expenditureDetail = expenditureRepository.findAllExpenditureDetails(map);
        return expenditureDetail;
    }

    @Override
    public List<ExenditureDto.TotalCount> showTotalExpenditureByMonth(ExenditureDto.TotalExpenditureCountRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("nowDate", request.getNowDate());
        map.put("memberId", "dnwo1111");
        List<ExenditureDto.TotalCount> response = expenditureRepository.findTotalExpenditureByStartAndEndDate(map);
        return response;
    }

    @Override
    public void modifyExpenditureMemo(Map<String, Object> map) {

    }

    @Override
    public List<ExenditureDto.RatioByCategoryResponse> showExpenditureRatioForCategoryByMonth(Map<String, Object> map) {
        List<ExenditureDto.RatioByCategoryResponse>  response = expenditureRepository.findExpenditureRatioForCategoryByMonth(map);
        return response;
    }

    @Override
    public List<Map<String, Object>>  showExpenditureCountForCategoryByMonth(String yearAndMonth) {
        Map<String, Object> map = new HashMap<>();
        map.put("yearAndMonth", yearAndMonth);
        map.put("memberId", "dnwo1111");


        List<Map<String, Object>> result = expenditureRepository.findExpenditureCountForCategoryByMonth(map);
        return result;
    }

    @Override
    public Map<String, Object> showTotalExpenditureForMonthAndTimeByYearAndMonth(String yearAndMonth) {
        Map<String, Object> columns = new HashMap<>();
        columns.put("nowDate", yearAndMonth);
        columns.put("memberId", "dnwo1111");

        Map<String, Object> map = expenditureRepository.findTotalExpenditureForMonthAndTimeByYearAndMonth(columns);

        return map;
    }

    @Override
    public Map<String, Object> findAverageExpenditureForMonthByYear(int year) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", "dnwo1111");
        map.put("year", year);

        Map<String, Object> m = expenditureRepository.findAverageExpenditureForMonthByYear(map);

        return m;
    }

    @Override
    public int insertExpenditure() {
        String memberId = "dnwo1111";

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        List<RegistrationCardDto.Response> registeredCard = registrationCardService.showAllRegisteredCardByMemberId(memberId);
        for(RegistrationCardDto.Response card : registeredCard) {
            System.out.println(card.getCardNumber());
            List<MyDataTransanctionHistoryDto.Response> transactions = myDataTransHistorySevice.findMemberTransactions(memberId, card.getCardNumber());
            System.out.println(transactions.size());
            if(transactions.size() != 0) {
                map.put("list", transactions);
                expenditureRepository.insertExpenditures(map);
            }
        }

        return 0;
    }

    // 소비성향에서 낮,밤시간대 지출 횟수 통계
    @Override
    public ExenditureDto.TotalCount showExpenditureTotalCount(ExenditureDto.TotalExpenditureCountByTimePeriodRequest request) {
        Map<String, Object> map = new HashMap<>();
        /*
         member_id = #{memberId}
            and
            to_char(expenditure_datetime, 'YYYY-MM') = #{yearAndMonth}
            and
            to_char(expenditure_datetime, 'HH24') between #{startHour} and #{endHour}
         */

        map.put("memberId", "dnwo1111");
        map.put("yearAndMonth", request.getYearAndMonth());
        map.put("startHour", request.getStartHour());
        map.put("endHour", request.getEndHour());
        System.out.println(request.getEndHour());
        ExenditureDto.TotalCount result = expenditureRepository.findExpenditureTotalCount(map);
        return result;
    }

    @Override
    public ExenditureDto.TendencyAnalysis findExpendiutreTendencyAnalysis(ExenditureDto.YearAndMonthRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberId","dnwo1111");
        map.put("yearAndMonth", request.getYearAndMonth());

        ExenditureDto.TendencyAnalysis response = expenditureRepository.findExpendiutreTendencyAnalysis(map);
       return  response;
    }

    @Override
    public boolean updateDailyExpenditureMemo(ExenditureDto.UpdateMemoRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("memoId", request.getMemoId());
        map.put("expenditureMemo", request.getMemo());
        map.put("memberId", "dnwo1111");
        if(checkExistMemo(map) == 1) {
            expenditureRepository.updateExpenditureMemo(map);
        }   else {
            expenditureRepository.insertExpenditureMemo(map);
        }
        return true;
    }

    @Override
    public int checkExistMemo(Map<String, Object> map) {
        return expenditureRepository.isExistMemo(map);
    }
}
