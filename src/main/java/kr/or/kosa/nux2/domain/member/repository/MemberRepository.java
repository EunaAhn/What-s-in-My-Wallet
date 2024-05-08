package kr.or.kosa.nux2.domain.member.repository;

import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    private final MemberMapper memberMapper;

    public MemberDto.MemberAuthenticationResponse findById(String memberId) {
        return memberMapper.findById(memberId);
    }
}
