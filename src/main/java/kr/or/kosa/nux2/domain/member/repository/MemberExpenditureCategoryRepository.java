package kr.or.kosa.nux2.domain.member.repository;

import kr.or.kosa.nux2.domain.member.dto.MemberConsCategoryDto;
import kr.or.kosa.nux2.domain.member.mapper.MemberExpenditureCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberExpenditureCategoryRepository {
    private final MemberExpenditureCategoryMapper memberExpenditureCategoryMapper;

    public int insertMemberConsCategory(List<MemberConsCategoryDto> memberConsCategoryDtoList){
        return memberExpenditureCategoryMapper.insertMemberConsCategory(memberConsCategoryDtoList);
    }
}
