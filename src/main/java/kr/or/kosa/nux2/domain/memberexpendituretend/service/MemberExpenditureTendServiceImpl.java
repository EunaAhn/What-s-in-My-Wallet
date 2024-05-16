package kr.or.kosa.nux2.domain.memberexpendituretend.service;

import kr.or.kosa.nux2.domain.memberexpendituretend.dto.MemberExpenditureTendDto;
import kr.or.kosa.nux2.domain.memberexpendituretend.repository.MemberExpenditureTendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberExpenditureTendServiceImpl implements MemberExpenditureTendService {
    private final MemberExpenditureTendRepository memberExpenditureTendRepository;

    /**
     * 소비 성향 조회 함수
     *
     * @param request: 해당연월일
     * @return: 소비 성향 정보
     */
    @Override
    public List<MemberExpenditureTendDto.Response> findMemberExpenditureTend(MemberExpenditureTendDto.ExpenditureTendRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("yearAndMonth", request.getYearAndMonth());
        map.put("memberId", memberId);

        List<MemberExpenditureTendDto.Response> responses = memberExpenditureTendRepository.findMemberExpenditureTendMapper(map);
        return responses;
    }
}
