package org.hsq.wjg.demo.codeGenetor.dao;

import org.hsq.wjg.demo.codeGenetor.entity.RegularPurchase;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
  * RegularPurchase 数据库操作
  *
  * Generated by Mybatis-Generator on 2016/10/31 15:34:22
  */
public interface IRegularPurchaseDao {
    List<RegularPurchase> query(RegularPurchase $entityNameVar);

    RegularPurchase queryById(@Param("id") Integer id);

    void add(RegularPurchase $entityNameVar);

    void update(RegularPurchase $entityNameVar);
}