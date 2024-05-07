package kr.or.kosa.nux2.domain.virtualmydata.mapper;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyDataCardMapper {
    // 회원 주민등록번호로 카드목록 조회
    public List<MyDataCardDto> findAllMyDataCard(String reg_num);

    // 서비스에서는
    // 1. 처음 카드목록 조회
    // 2. 카드 등록 추가 및 삭제
}
