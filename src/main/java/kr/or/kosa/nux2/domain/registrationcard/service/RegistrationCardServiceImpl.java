package kr.or.kosa.nux2.domain.registrationcard.service;

import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;
import kr.or.kosa.nux2.domain.registrationcard.repository.RegistrationCardRepository;
import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import kr.or.kosa.nux2.domain.virtualmydata.repository.MyDataCardRepository;
import lombok.RequiredArgsConstructor;
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

    @Override
    public List<RegistrationCardDto.Response> showAllRegisteredCardByMemberId(String memberId) {
        memberId = "dnwo1111";
        List<RegistrationCardDto.Response> responses = registrationCardRepository.findAllRegistrationCardByMemberId("dnwo1111");

        return responses;
    }

    @Override
    public int deleteRegistrationCard(String registeredCardId) {
        registrationCardRepository.deleteRegistrationCard(registeredCardId);

        // 카드가 삭제되면 지출내역도 삭제 되어야 함.
        return 0;
    }

    @Override
    @Transactional
    public int insertRegistrationCard(List<RegistrationCardDto.InsertControllerRequest> requests) {
        // 내카드 조회 -> 카드번호 보내면 카드 등록
        // 컨트롤러에서는 카드번호(16)만 보낸다.
        // 마이데이터

        for(RegistrationCardDto.InsertControllerRequest request : requests) {
            //
            System.out.println(request.getCardNumber());
            Map<String, Object> mdcMap = new HashMap<>();
            mdcMap.put("cardNumber", request.getCardNumber());
            MyDataCardDto.Response response = myDataCardRepository.findMyDataCardByCardNumber(mdcMap);
            Map<String, Object> map = new HashMap<>();

            map.put("cardNumber" , response.getCardNumber());
            map.put("cardName", response.getCardName());
            map.put("memberId", "dnwo1111");
            registrationCardRepository.insertRegistrationCard(map);
        }
        return 0;
    }
}
