package kr.or.kosa.nux2.domain.registrationcard.service;

import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;

import java.util.List;


public interface RegistrationCardService {
    List<RegistrationCardDto.Response> showAllRegisteredCardByMemberId(String memberId);

    int deleteRegistrationCard(String registeredCardId);

    int insertRegistrationCard(List<RegistrationCardDto.InsertControllerRequest> registrationCard);


}
