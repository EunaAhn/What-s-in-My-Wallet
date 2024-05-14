package kr.or.kosa.nux2.domain.member.mapper;

import kr.or.kosa.nux2.domain.member.dto.ExpenditureCategoryDto;
import kr.or.kosa.nux2.domain.member.dto.MemberConsCategoryDto;
import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberExpenditureCategoryMapper {
    int insertMemberConsCategory(List<MemberConsCategoryDto.MemberConsCategoryRequest> memberConsCategoryDtoList);
    List<MemberConsCategoryDto.MemberConsCategoryResponse> selectMemberConsCategories(MemberDto.MemberIdRequest request);
}
