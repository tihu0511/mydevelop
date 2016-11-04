package org.hsq.wjg.demo.codeGenetor.dao;

import org.hsq.wjg.demo.codeGenetor.entity.RegularTransferTrade;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
  * RegularTransferTrade 数据库操作
  *
  * Generated by Mybatis-Generator on 2016/10/31 15:34:22
  */
public interface IRegularTransferTradeDao {
    List<RegularTransferTrade> query(RegularTransferTrade $entityNameVar);

    RegularTransferTrade queryById(@Param("id") Integer id);

    void add(RegularTransferTrade $entityNameVar);

    void update(RegularTransferTrade $entityNameVar);
}