package kr.or.kosa.nux2.domain.member.mapper;

import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberMapper {

    MemberDto.UserDto findById(String memberId);

    int insertOAuthMember(MemberDto.UserDto userDto);

    int insertMember(MemberDto.SignUpRequest signUpRequest);

    boolean isExistMemberId(MemberDto.MemberIdRequest request);

    MemberDto.ProfileResponse selectMemberDetail(MemberDto.MemberIdRequest request);

    int updateStatus(Map<String, Object> paramMap);

    int saveOrUpdateMember(Map<String, Object> paramMap);

    int updateTargetExpenditure(Map<String, Object> paramMap);

    MemberDto.ProfileResponse findMemberNameAndTargetExpenditureByMemberId(Map<String, Object> paramMap);

    int updatePassword(Map<String, Object> paramMap);
}
