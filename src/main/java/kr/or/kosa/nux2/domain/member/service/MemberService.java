package kr.or.kosa.nux2.domain.member.service;

import kr.or.kosa.nux2.domain.member.dto.MemberConsCategoryDto;
import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.web.auth.principal.CustomUserDetails;

import java.util.List;
import java.util.Map;


public interface MemberService {
    String logout(CustomUserDetails customUserDetails);
    String signUp(MemberDto.SignUpRequest request);
    boolean checkMemberId(MemberDto.MemberIdRequest request);
    void sendEmail(MemberDto.MemberIdRequest request);
    String generateAuthenticationNumber();
    int insertOrUpdateAuthenticationInfo(Map<String,Object> paramMap);
    boolean validateAuthenticationNumber(MemberDto.AuthenticationDto request);
    MemberDto.ProfileResponse showMemberProfile();
    MemberDto.ProfileResponse updateMemberInfo(MemberDto.UpdateMemberInfoRequest request);
    MemberDto.ProfileResponse updateTargetExpenditure(Map<String,Object> paramMap);
    List<MemberConsCategoryDto.MemberConsCategoryResponse> updateMemberConsCategoryList(Map<String,Object> paramMap);
    boolean updatePassword(MemberDto.UpdatePasswordRequest request);
}
