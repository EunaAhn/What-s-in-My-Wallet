package kr.or.kosa.nux2.domain.member.mapper;

import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.dto.UpdateMemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    MemberDto.UserDto findById(String memberId);

    int insertOAuthMember(MemberDto.UserDto userDto);

    int insertMember(MemberDto.SignInRequest signInRequest);

    int updateSocialToken(MemberDto.UpdateSocialTokenRequest request);

    boolean isExistMemberId(MemberDto.MemberIdRequest request);
    MemberDto.ProfileResponse selectMemberDetail(MemberDto.MemberIdRequest request);

}
