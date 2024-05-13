package kr.or.kosa.nux2.domain.memberexpendituretend.service;

import kr.or.kosa.nux2.domain.memberexpendituretend.dto.MemberExpenditureTendDto;

import java.util.List;
import java.util.Map;

public interface MemberExpenditureTendService {
    List<MemberExpenditureTendDto.Response> findMemberExpenditureTend(String yearAndMonth);
    int insertMemberExpenditureTend();
}
