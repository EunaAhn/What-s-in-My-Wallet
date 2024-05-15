package kr.or.kosa.nux2.domain.registrationcard.mapper;

import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RegistrationCardMapper {
    List<RegistrationCardDto.Response> findAllRegistrationCardByMemberId(@Param("memberId") String memberId);

    int deleteRegistrationCard(@Param("registeredCardId") String registeredCardId);

    int insertRegistrationCard(Map<String, Object> map);

}
