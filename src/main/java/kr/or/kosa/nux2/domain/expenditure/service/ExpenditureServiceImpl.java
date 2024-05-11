package kr.or.kosa.nux2.domain.expenditure.service;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import kr.or.kosa.nux2.domain.expenditure.repository.ExpenditureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExpenditureServiceImpl implements ExpenditureService{
    private final ExpenditureRepository expenditureRepository;

    @Override
    public List<ExenditureDto.Response> showMemberMonthlyExpenditures() {
        //멤버아이디 컨텍스트에서 받아오기
        //연월을 클라이언트로 부터 받아오기
        // 해당 쿼리를 불러오기
        LocalDate today = LocalDate.now();
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM");         // 포맷 적용        String formatedNow = now.format(formatter);
        String nowDate = today.format(formatter);

        String memberId = "dnwo1111";
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("nowDate", nowDate);

        List<ExenditureDto.Response> expenditureList = expenditureRepository.findAllExpenditure(map);
        return expenditureList;
    }

    @Override
    public ExenditureDto.DetailsReponse showMemberDailyExpenditureDetails(ExenditureDto.ExpenditureDetailRequest request) {
        // 해당 날짜 YYYY-MM 보내기
        Map<String, Object> map = new HashMap<>();

        String memberId = "dnwo1111";
        String nowDate = request.getNowDate();

        map.put("memberId", memberId);
        map.put("nowDate", nowDate);

        ExenditureDto.DetailsReponse expenditureDetail = expenditureRepository.findAllExpenditureDetails(map);
        return null;
    }
}
