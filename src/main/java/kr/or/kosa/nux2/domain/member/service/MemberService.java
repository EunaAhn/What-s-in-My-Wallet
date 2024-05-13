package kr.or.kosa.nux2.domain.member.service;

import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.web.auth.CustomUserDetails;


public interface MemberService {
    public String test();
    public String logout(CustomUserDetails customUserDetails);
    public String signIn(MemberDto.SignInRequest request);
    public boolean checkMemberId(MemberDto.MemberIdRequest request);
    public void sendEmail(MemberDto.MemberIdRequest request);
    public String generateAuthenticationNumber();
    public int insertAuthenticationInfo(MemberDto.AuthenticationRequest request);
    public boolean validateAuthenticationNumber(MemberDto.AuthenticationRequest request);
}
