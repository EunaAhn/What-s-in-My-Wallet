package kr.or.kosa.nux2.domain.member.repository;

import kr.or.kosa.nux2.domain.member.dto.ExpenditureCategoryDto;
import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    private final MemberMapper memberMapper;

    public MemberDto.UserDto findById(String memberId) {
        return memberMapper.findById(memberId);
    }

    public int insertOAuthMember(MemberDto.UserDto userDto){
        return memberMapper.insertOAuthMember(userDto);
    }
    public int updateSocialToken(MemberDto.UpdateSocialTokenRequest request){
        return memberMapper.updateSocialToken(request);
    }
    public int insertMember(MemberDto.SignInRequest signInRequest){
        return memberMapper.insertMember(signInRequest);
    }


}
