package kr.or.kosa.nux2.domain.member.mapper;

import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public MemberDto findById(String memberId);
}
