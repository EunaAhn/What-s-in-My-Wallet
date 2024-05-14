package kr.or.kosa.nux2.domain.member.repository;

import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.mapper.EmailAuthenticationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EmailAuthenticationRepository {
    private final EmailAuthenticationMapper emailAuthenticationMapper;

    public int insertOrUpdateAuthenticationInfo(Map<String,Object> paramMap){
        return emailAuthenticationMapper.insertOrUpdateAuthenticationInfo(paramMap);
    }
    public MemberDto.AuthenticationDto findAuthenticationNumberByMemberIdAndTimeDiffLessThanFiveMinute(MemberDto.MemberIdRequest request){
        return emailAuthenticationMapper.findAuthenticationNumberByMemberIdAndTimeDiffLessThanFiveMinute(request);
    }
    public int deleteAuthenticationInfoByMemberId(MemberDto.MemberIdRequest request){
        return emailAuthenticationMapper.deleteAuthenticationInfoByMemberId(request);
    }
}
