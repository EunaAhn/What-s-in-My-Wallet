package kr.or.kosa.nux2.domain.member.mapper;


import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface EmailAuthenticationMapper {
    int insertOrUpdateAuthenticationInfo(Map<String, Object> paramMap);

    MemberDto.AuthenticationDto findAuthenticationNumberByMemberIdAndTimeDiffLessThanFiveMinute(MemberDto.MemberIdRequest request);

    int deleteAuthenticationInfoByMemberId(MemberDto.MemberIdRequest request);
}
