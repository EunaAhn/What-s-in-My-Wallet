package kr.or.kosa.nux2.domain.registrationcard.repository;

import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;
import kr.or.kosa.nux2.domain.registrationcard.mapper.RegistrationCardMapper;
import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class RegistrationCardRepository {
    private final RegistrationCardMapper registrationCardMapper;
    public List<RegistrationCardDto.Response> findAllRegistrationCardByMemberId(String memberId){
        return registrationCardMapper.findAllRegistrationCardByMemberId(memberId);
    };
    public int deleteRegistrationCard(String registrationId){
        return registrationCardMapper.deleteRegistrationCard(registrationId);
    };
    public int insertRegistrationCard(Map<String, Object> map){
        return registrationCardMapper.insertRegistrationCard(map);
    };
}
