package kr.or.kosa.nux2.domain.registrationcard.service;

import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;

import java.util.List;


public interface RegistrationCardService {
    List<RegistrationCardDto.Response> showAllRegisteredCardByMemberId();

    boolean deleteRegistrationCard(String registeredCardId);

    boolean insertRegistrationCard(List<RegistrationCardDto.InsertControllerRequest> registrationCard);
}
