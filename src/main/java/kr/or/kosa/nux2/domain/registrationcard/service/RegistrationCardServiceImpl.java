package kr.or.kosa.nux2.domain.registrationcard.service;

import kr.or.kosa.nux2.domain.expenditure.repository.ExpenditureRepository;
import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;
import kr.or.kosa.nux2.domain.registrationcard.repository.RegistrationCardRepository;
import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import kr.or.kosa.nux2.domain.virtualmydata.repository.MyDataCardRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegistrationCardServiceImpl implements RegistrationCardService {
    private final RegistrationCardRepository registrationCardRepository;
    private final MyDataCardRepository myDataCardRepository;
    private final ExpenditureRepository expenditureRepository;

    @Override
    public List<RegistrationCardDto.Response> showAllRegisteredCardByMemberId() {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        List<RegistrationCardDto.Response> responses = registrationCardRepository.findAllRegistrationCardByMemberId(memberId);
        return responses;
    }

    @Override
    @Transactional
    public boolean deleteRegistrationCard(String registeredCardId) {
        boolean result = registrationCardRepository.deleteRegistrationCard(registeredCardId);
        expenditureRepository.deleteExpenditure(registeredCardId);
        // 카드가 삭제되면 지출내역도 삭제 되어야 함.

        return result;
    }

    @Override
    @Transactional
    public boolean insertRegistrationCard(List<RegistrationCardDto.InsertControllerRequest> requests) {
        // 내카드 조회 -> 카드번호 보내면 카드 등록
        // 컨트롤러에서는 카드번호(16)만 보낸다.
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean result = false;
        for(RegistrationCardDto.InsertControllerRequest request : requests) {

            System.out.println(request.getCardNumber());
            Map<String, Object> mdcMap = new HashMap<>();
            mdcMap.put("cardNumber", request.getCardNumber());
            MyDataCardDto.Response response = myDataCardRepository.findMyDataCardByCardNumber(mdcMap);
            Map<String, Object> map = new HashMap<>();

            map.put("cardNumber" , response.getCardNumber());
            map.put("cardName", response.getCardName());
            map.put("memberId", memberId);
            result = registrationCardRepository.insertRegistrationCard(map);
        }
        return result;
    }
}
