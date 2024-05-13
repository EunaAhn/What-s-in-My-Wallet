package kr.or.kosa.nux2.domain.member.mapper;


import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailAuthenticationMapper {
    int insertAuthenticationInfo(MemberDto.AuthenticationRequest request);
    MemberDto.AuthenticationResponse findAuthenticationNumberByMemberIdAndTimeDiffLessThanFiveMinute(MemberDto.AuthenticationRequest request);
    int deleteAuthenticationInfoByMemberId(MemberDto.MemberIdRequest request);
}
