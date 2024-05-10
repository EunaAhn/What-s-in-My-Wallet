package kr.or.kosa.nux2.domain.memberexpendituretend.repository;

import kr.or.kosa.nux2.domain.memberexpendituretend.dto.MemberExpenditureTendDto;
import kr.or.kosa.nux2.domain.memberexpendituretend.mapper.MemberConsumptionTendMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class MemberConsumptionTendRepository {

    private final MemberConsumptionTendMapper memberConsumptionTendMapper;
    List<MemberExpenditureTendDto.ServiceResponse> findMemberExpenditureTendMapper(Map<String, Objects> columns){
        return memberConsumptionTendMapper.findMemberExpenditureTendMapper(columns);
    };

}
