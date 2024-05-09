package kr.or.kosa.nux2.domain.memberexpendituretend.mapper;

import kr.or.kosa.nux2.domain.memberexpendituretend.dto.MemberExpenditureTendDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface MemberExpenditureTendMapper {
    // 회원아이디, 갱신코드(연+월 조합) -> 갱신 시점에 배치가 돌고 있으면 이전 달의 데이터를 보여줘야하는 예외처리 진행
    List<MemberExpenditureTendDto.ServiceResponse> findMemberExpenditureTendMapper(Map<String, Object> columns);
}
