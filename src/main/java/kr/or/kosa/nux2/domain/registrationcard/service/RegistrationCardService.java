package kr.or.kosa.nux2.domain.registrationcard.service;

import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;

import java.util.List;


public interface RegistrationCardService {
    List<RegistrationCardDto.Response> showAllRegisteredCardByMemberId(String memberId);

    // 삭제, 삽입 개수 반환
    // 등록 카드 삭제
    int deleteRegistrationCard(String registeredCardId);

    // 등록카드에 추가
    int insertRegistrationCard(List<RegistrationCardDto.InsertControllerRequest> registrationCard);


}
