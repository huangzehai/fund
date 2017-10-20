package fund.service;

import fund.mapper.FundMapper;
import fund.model.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundServiceImpl implements FundService {
    @Autowired
    private FundMapper fundMapper;


    @Override
    public int addFunds(List<Fund> funds) {
        return fundMapper.addFunds(funds);
    }
}
