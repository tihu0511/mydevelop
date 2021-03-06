package org.hsq.wjg.demo.codeGenetor.dao;

import org.hsq.wjg.demo.codeGenetor.entity.RegularProduct;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
  * RegularProduct 数据库操作
  *
  * Generated by Mybatis-Generator on 2016/10/31 15:34:22
  */
public interface IRegularProductDao {
    List<RegularProduct> query(RegularProduct $entityNameVar);

    RegularProduct queryById(@Param("id") Integer id);

    void add(RegularProduct $entityNameVar);

    void update(RegularProduct $entityNameVar);
}