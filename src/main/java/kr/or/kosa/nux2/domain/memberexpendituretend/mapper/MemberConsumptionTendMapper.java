package kr.or.kosa.nux2.domain.memberexpendituretend.mapper;

import kr.or.kosa.nux2.domain.memberexpendituretend.dto.MemberExpenditureTendDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.Objects;

@Mapper
public interface MemberConsumptionTendMapper {
    // 회원아이디, 갱신코드(연+월 조합)
    MemberExpenditureTendDto.Response findMemberConsumptionTendMapper(Map<String, Objects> columns);

}
