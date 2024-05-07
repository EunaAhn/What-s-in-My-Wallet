package kr.or.kosa.nux2.domain.expenditure.mapper;

import kr.or.kosa.nux2.domain.expenditure.dto.ExpenditureReqDto;
import kr.or.kosa.nux2.domain.expenditure.dto.ExpenditureRespDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExpenditureMapper {
    // 동적 쿼리
    public List<ExpenditureRespDto> findAllExpenditure (Map<String, Object> columns);
    // 소비카테고리 별, 월 별
    // 카드별

    public int insertExpenditures(List<ExpenditureReqDto> expenditureReqDtoList);
    // 현재 사용자의 마지막 id값보다 높은 값을 가지는 마이데이터소비내역을 조회해서 insert한다.
    // 다건 삽입 방법 성능 고민


}
