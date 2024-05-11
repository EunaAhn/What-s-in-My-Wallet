package kr.or.kosa.nux2.domain.member.repository;

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

    public int save(MemberDto.UserDto userDto){
        return memberMapper.save(userDto);
    }
    public int updateSocialToken(MemberDto.UpdateSocialTokenRequest request){
        return memberMapper.updateSocialToken(request);
    }

}
