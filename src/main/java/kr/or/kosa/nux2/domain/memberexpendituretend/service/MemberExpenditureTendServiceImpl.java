package kr.or.kosa.nux2.domain.memberexpendituretend.service;

import kr.or.kosa.nux2.domain.memberexpendituretend.dto.MemberExpenditureTendDto;
import kr.or.kosa.nux2.domain.memberexpendituretend.repository.MemberExpenditureTendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberExpenditureTendServiceImpl implements MemberExpenditureTendService {
    private final MemberExpenditureTendRepository memberExpenditureTendRepository;

    @Override
    public List<MemberExpenditureTendDto.Response> findMemberExpenditureTend(String yearAndMonth) {
        Map<String, Object> map = new HashMap<>();
        map.put("yearAndMonth", yearAndMonth);
        map.put("memberId", "dnwo1111");

        List<MemberExpenditureTendDto.Response> responses = memberExpenditureTendRepository.findMemberExpenditureTendMapper(map);
        return responses;
    }

    @Override
    public int insertMemberExpenditureTend() {
        // 프로시저 + 배치
        return 0;
    }
}
