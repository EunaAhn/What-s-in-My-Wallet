package kr.or.kosa.nux2.domain.registrationcard.repository;

import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;
import kr.or.kosa.nux2.domain.registrationcard.mapper.RegistrationCardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class RegistrationCardRepository {
    private final RegistrationCardMapper registrationCardMapper;

    public List<RegistrationCardDto.Response> findAllRegistrationCardByMemberId(String memberId) {
        return registrationCardMapper.findAllRegistrationCardByMemberId(memberId);
    }

    public boolean deleteRegistrationCard(String registrationId) {
        int result = registrationCardMapper.deleteRegistrationCard(registrationId);

        if(result == 1) return true;
        return false;
    }

    public boolean insertRegistrationCard(Map<String, Object> map) {
        int result =  registrationCardMapper.insertRegistrationCard(map);

        if(result == 1) return true;
        return false;
    }

}
