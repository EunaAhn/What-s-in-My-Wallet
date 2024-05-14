package kr.or.kosa.nux2.domain.member.repository;

import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    private final MemberMapper memberMapper;

    public MemberDto.UserDto findById(String memberId) {
        return memberMapper.findById(memberId);
    }

    public int insertMember(MemberDto.SignUpRequest signUpRequest) {
        return memberMapper.insertMember(signUpRequest);
    }

    public boolean isExistMemberId(MemberDto.MemberIdRequest request) {
        return memberMapper.isExistMemberId(request);
    }

    public MemberDto.ProfileResponse selectMemberDetail(MemberDto.MemberIdRequest request) {
        return memberMapper.selectMemberDetail(request);
    }

    public int saveOrUpdateMember(Map<String, Object> paramMap) { //oAuth Member
        return memberMapper.saveOrUpdateMember(paramMap);
    }

    public int updateTargetExpenditure(Map<String, Object> paramMap) {
        return memberMapper.updateTargetExpenditure(paramMap);
    }

    public MemberDto.ProfileResponse findMemberNameAndTargetExpenditureByMemberId(Map<String, Object> paramMap) {
        return memberMapper.findMemberNameAndTargetExpenditureByMemberId(paramMap);
    }

    public int updatePassword(Map<String, Object> paramMap) {
        return memberMapper.updatePassword(paramMap);
    }
}
