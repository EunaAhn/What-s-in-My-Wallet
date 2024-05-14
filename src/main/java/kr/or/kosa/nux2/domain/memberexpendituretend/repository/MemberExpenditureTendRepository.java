package kr.or.kosa.nux2.domain.memberexpendituretend.repository;

import kr.or.kosa.nux2.domain.memberexpendituretend.dto.MemberExpenditureTendDto;
import kr.or.kosa.nux2.domain.memberexpendituretend.mapper.MemberExpenditureTendMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class MemberExpenditureTendRepository {

    private final MemberExpenditureTendMapper memberExpenditureTendMapper;

    public List<MemberExpenditureTendDto.Response> findMemberExpenditureTendMapper(Map<String, Object> columns) {
        return memberExpenditureTendMapper.findMemberExpenditureTend(columns);
    }

    ;

}
