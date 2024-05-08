package kr.or.kosa.nux2.domain.member.mapper;

import kr.or.kosa.nux2.domain.member.dto.CreateMemberReqDto;
import kr.or.kosa.nux2.domain.member.dto.UpdateMemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    // 회원생성
    int insertMember(CreateMemberReqDto createMemberReqDto);
    // 굳이 동적쿼리 쓸이유가 있나? -> 만약 동적쿼리하면 컨트롤러 dto와 서비스 dto를 나누어야할것같음
    // 왜? 하나의 dto로 동적쿼리를 구현하려면 controller에서 valid를 위해 모든 값을 넣어서 넘어와야하는데
    // 프론트에서 변경된 값만 보내야하는 번거로움 추가
    // + 매퍼에서는 dto를 사용하지 않고 map으로 넘겨야한다.

    // 회원 정보 수
    int updateMember(UpdateMemberDto updateMemberDto);
    Long findMemberTargetExpenditure(String memberId);
}
