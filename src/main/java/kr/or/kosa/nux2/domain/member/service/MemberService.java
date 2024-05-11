package kr.or.kosa.nux2.domain.member.service;

import kr.or.kosa.nux2.web.auth.CustomUserDetails;


public interface MemberService {
    public String test();
    public String logout(CustomUserDetails customUserDetails);
}
