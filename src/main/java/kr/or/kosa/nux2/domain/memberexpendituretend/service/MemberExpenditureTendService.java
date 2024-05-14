package kr.or.kosa.nux2.domain.memberexpendituretend.service;

import kr.or.kosa.nux2.domain.memberexpendituretend.dto.MemberExpenditureTendDto;

import java.util.List;

public interface MemberExpenditureTendService {
    List<MemberExpenditureTendDto.Response> findMemberExpenditureTend(String yearAndMonth);

    int insertMemberExpenditureTend();
}
