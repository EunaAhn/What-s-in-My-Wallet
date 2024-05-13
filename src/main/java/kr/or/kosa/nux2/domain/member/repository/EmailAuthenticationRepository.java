package kr.or.kosa.nux2.domain.member.repository;

import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.mapper.EmailAuthenticationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailAuthenticationRepository {
    private final EmailAuthenticationMapper emailAuthenticationMapper;

    public int insertOrUpdateAuthenticationInfo(MemberDto.AuthenticationRequest request){
        return emailAuthenticationMapper.insertOrUpdateAuthenticationInfo(request);
    }
    public MemberDto.AuthenticationResponse findAuthenticationNumberByMemberIdAndTimeDiffLessThanFiveMinute(MemberDto.AuthenticationRequest request){
        return emailAuthenticationMapper.findAuthenticationNumberByMemberIdAndTimeDiffLessThanFiveMinute(request);
    }
    public int deleteAuthenticationInfoByMemberId(MemberDto.MemberIdRequest request){
        return emailAuthenticationMapper.deleteAuthenticationInfoByMemberId(request);
    }
}
