package kr.or.kosa.nux2.domain.registrationcard.mapper;

import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegistrationCardMapper {
    // 멤버아이디 기반의 등록된 카드 목록 조회
    List <RegistrationCardDto> findAllRegistrationCardByMemberId(String memberId);
    // 삭제, 삽입 개수 반환
    // 등록 카드 삭제
    int deleteRegistrationCard(int registrationId);
    // 등록카드에 추가
    int insertRegistrationCard(RegistrationCardDto.InsertRequest registrationCard);

    // 마이데이터에 카드가 등록될 수 있어야한다.
    // 마이데이터 카드 테이블을 이용하여
    // 1. 처음 카드목록 조회
    // 2. 카드 등록 추가 및 삭제
}
