package kr.or.kosa.nux2.domain.registrationcard.mapper;

import kr.or.kosa.nux2.domain.registrationcard.dto.RegisterationCardReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface RegistrationCardRepository {
    HashMap<String, Object> findAllRegistrationCardByMemberId();
    List<Integer> deleteRegistrationCard(Integer registrationId);
    // byid를 넘겨야한다.
    int insertRegistrationCard(RegisterationCardReqDto registerationCardReqDto);
    // 마이데이터에서
    // 모든 컬럼 값을 넘겨야한다.
}
