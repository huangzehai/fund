package fund.mapper;

import fund.model.Fund;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FundMapper {

    int addFunds(@Param("funds") List<Fund> funds);

}
