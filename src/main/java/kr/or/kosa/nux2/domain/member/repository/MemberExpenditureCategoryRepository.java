package kr.or.kosa.nux2.domain.member.repository;

import kr.or.kosa.nux2.domain.member.dto.MemberConsCategoryDto;
import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.mapper.MemberExpenditureCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberExpenditureCategoryRepository {
    private final MemberExpenditureCategoryMapper memberExpenditureCategoryMapper;

    public int insertMemberConsCategory(List<MemberConsCategoryDto.MemberConsCategoryRequest> memberConsCategoryDtoList){
        return memberExpenditureCategoryMapper.insertMemberConsCategory(memberConsCategoryDtoList);
    }
    public List<MemberConsCategoryDto.MemberConsCategoryResponse> selectMemberConsCategories(MemberDto.MemberIdRequest request){
        return memberExpenditureCategoryMapper.selectMemberConsCategories(request);
    }
}
