package kr.or.kosa.nux2.domain.member.mapper;

import kr.or.kosa.nux2.domain.member.dto.ExpenditureDto;
import kr.or.kosa.nux2.domain.member.dto.MemberConsCategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExpenditureCategoryMapper {
    // 관심사 등록을 위한 소비카테고리 조회
    List<ExpenditureDto> findAllConsumptionCategory();
    // 회원의 소비카테고리관심사 목록 조회
    List<MemberConsCategoryDto>  findAllMemberConsCategory(String memberId);
    // 회원의 소비카테고리관심사 해제(삭제) -> user id 는 시큐리티컨텍스트에서 취득하는 내용이므로 dto에 담아오기엔 무리가 있다. 따라서 맵 객체 사용
    // 그냥 서비스단에서 id는 dto에 바인딩 해주자.
    int deleteMemberConsCategory(List<MemberConsCategoryDto> memberConsCategoryDtoList);
    //회원의 소비카테고리관심사 추가 ->
    int insertMemberConsCategory(List<MemberConsCategoryDto> memberConsCategoryDtoList);
}
