package kr.or.kosa.nux2.domain.memberexpendituretend.mapper;

import kr.or.kosa.nux2.domain.memberexpendituretend.dto.MemberExpenditureTendDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberExpenditureTendMapper {
    List<MemberExpenditureTendDto.Response> findMemberExpenditureTend(Map<String, Object> columns);
}