package kr.or.kosa.nux2.domain.member.service;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    @Override
    public String test() {
        return "context 분리 test";
    }
}
