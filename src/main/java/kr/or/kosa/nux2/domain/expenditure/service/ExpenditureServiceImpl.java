package kr.or.kosa.nux2.domain.expenditure.service;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import kr.or.kosa.nux2.domain.expenditure.repository.ExpenditureRepository;
import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;
import kr.or.kosa.nux2.domain.registrationcard.service.RegistrationCardServiceImpl;
import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataTransanctionHistoryDto;
import kr.or.kosa.nux2.domain.virtualmydata.service.MyDataTransHistorySevice;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExpenditureServiceImpl implements ExpenditureService {
    private final ExpenditureRepository expenditureRepository;
    private final MyDataTransHistorySevice myDataTransHistorySevice;
    private final RegistrationCardServiceImpl registrationCardService;

    @Override
    public Map<String, Object> showMemberMonthlyExpenditures(ExenditureDto.ByYearAndMonthRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        // 쿼리 검색 map 정의
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("nowDate", request.getYearAndMonth());
        map.put("keyword", request.getKeyWord());

        // 지출내역과 소비카테고리내역 리스트 반환
        List<ExenditureDto.Response> expenditureList = expenditureRepository.findAllExpenditure(map);
        List<ExenditureDto.CategoryList> categoryNames = expenditureRepository.findAllCategoryList(map);

        Map<String, Object> responses = new HashMap<>();
        responses.put("expenditureList", expenditureList);
        responses.put("categoryList", categoryNames);

        return responses;
    }

    @Override
    public ExenditureDto.DetailsReponse showMemberDailyExpenditureDetails(ExenditureDto.ExpenditureDetailRequest request) {
        Map<String, Object> map = new HashMap<>();

        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        map.put("memberId", memberId);
        map.put("nowDate", request.getNowDate());
        map.put("expenditureId", request.getExpenditureId());

        ExenditureDto.DetailsReponse response = expenditureRepository.findAllExpenditureDetails(map);
        return response;
    }

    @Override
    public List<ExenditureDto.TotalCount> showTotalExpenditureByMonth(ExenditureDto.ByYearAndMonthRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("nowDate", request.getYearAndMonth());
        map.put("memberId", memberId);

        List<ExenditureDto.TotalCount> response = expenditureRepository.findTotalExpenditureByStartAndEndDate(map);
        return response;
    }



    @Override
    public List<ExenditureDto.RatioByCategoryResponse> showExpenditureRatioForCategoryByMonth(ExenditureDto.ByYearAndMonthRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("month", request.getYearAndMonth());
        map.put("memberId", memberId);

        List<ExenditureDto.RatioByCategoryResponse> response = expenditureRepository.findExpenditureRatioForCategoryByMonth(map);
        return response;
    }

    @Override
    public List<Map<String, Object>> showExpenditureCountForCategoryByMonth(ExenditureDto.ByYearAndMonthRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();

        map.put("yearAndMonth", request.getYearAndMonth());
        map.put("memberId", memberId);

        List<Map<String, Object>> response = expenditureRepository.findExpenditureCountForCategoryByMonth(map);
        return response;
    }

    @Override
    public Map<String, Object> showTotalExpenditureForMonthAndTimeByYearAndMonth(ExenditureDto.ByYearAndMonthRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> columns = new HashMap<>();

        columns.put("nowDate", request.getYearAndMonth());
        columns.put("memberId", "dnwo1111");

        Map<String, Object> response = expenditureRepository.findTotalExpenditureForMonthAndTimeByYearAndMonth(columns);
        return response;
    }

    @Override
    public Map<String, Object> findAverageExpenditureForMonthByYear(ExenditureDto.ByYearRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("year", request.getYear());

        Map<String, Object> response = expenditureRepository.findAverageExpenditureForMonthByYear(map);
        return response;
    }

    @Override
    @Transactional
    public boolean insertExpenditure() {
        boolean result = false;
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);

        // 사용자의 등록카드리스트를 조회
        List<RegistrationCardDto.Response> registeredCard = registrationCardService.showAllRegisteredCardByMemberId();

        // 등록카드를 순회
        for (RegistrationCardDto.Response card : registeredCard) {
            // 마이데이터 거래내역에서 등록카드의 거래내역을 조회
            List<MyDataTransanctionHistoryDto.Response> transactions = myDataTransHistorySevice.findMemberTransactions(memberId, card.getCardNumber());

            if (transactions.size() != 0) {
                // 조회결과를 삽입
                map.put("list", transactions);
                result = expenditureRepository.insertExpenditures(map);
            }
        }

        return result;
    }

    // 소비성향에서 낮,밤시간대 지출 횟수 통계
    @Override
    public ExenditureDto.TotalCount showExpenditureTotalCount(ExenditureDto.TotalCountByTimePeriodRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();

        map.put("memberId", memberId);
        map.put("yearAndMonth", request.getYearAndMonth());
        map.put("startHour", request.getStartHour());
        map.put("endHour", request.getEndHour());

        ExenditureDto.TotalCount response = expenditureRepository.findExpenditureTotalCount(map);
        return response;
    }

    @Override
    public ExenditureDto.TendencyAnalysis findExpendiutreTendencyAnalysis(ExenditureDto.ByYearAndMonthRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("yearAndMonth", request.getYearAndMonth());

        ExenditureDto.TendencyAnalysis response = expenditureRepository.findExpendiutreTendencyAnalysis(map);
        return response;
    }

    @Override
    public boolean updateDailyExpenditureMemo(ExenditureDto.UpdateMemoRequest request) {
        boolean result = false;
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();

        map.put("memoId", request.getMemoId());
        map.put("expenditureMemo", request.getMemo());
        map.put("memberId", memberId);

        if (checkExistMemo(map)) {
            result = expenditureRepository.updateExpenditureMemo(map);
        } else {
            result = expenditureRepository.insertExpenditureMemo(map);
        }
        return result;
    }

    @Override
    public boolean checkExistMemo(Map<String, Object> map) {
        return expenditureRepository.isExistMemo(map);
    }

    @Override
    public boolean deleteExpenditureList(String cardNumber) {
        return expenditureRepository.deleteExpenditure(cardNumber);
    }
}
